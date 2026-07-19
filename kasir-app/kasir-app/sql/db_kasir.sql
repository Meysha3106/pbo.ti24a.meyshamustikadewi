-- =====================================================================
-- Aplikasi Kasir Sederhana (CLI) - Skrip Database
-- Database : db_kasir
-- =====================================================================

DROP DATABASE IF EXISTS db_kasir;
CREATE DATABASE db_kasir;
USE db_kasir;

-- ---------------------------------------------------------------------
-- 1. TABEL
-- ---------------------------------------------------------------------

CREATE TABLE produk (
    id_produk     INT AUTO_INCREMENT PRIMARY KEY,
    nama_produk   VARCHAR(100)    NOT NULL,
    harga         DECIMAL(12,2)   NOT NULL,
    stok          INT             NOT NULL DEFAULT 0
) ENGINE=InnoDB;

CREATE TABLE transaksi (
    id_transaksi  INT AUTO_INCREMENT PRIMARY KEY,
    tanggal       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total         DECIMAL(12,2)   NOT NULL DEFAULT 0
) ENGINE=InnoDB;

CREATE TABLE detail_transaksi (
    id_detail     INT AUTO_INCREMENT PRIMARY KEY,
    id_transaksi  INT             NOT NULL,
    id_produk     INT             NOT NULL,
    jumlah        INT             NOT NULL,
    subtotal      DECIMAL(12,2)   NOT NULL,
    CONSTRAINT fk_detail_transaksi FOREIGN KEY (id_transaksi)
        REFERENCES transaksi(id_transaksi) ON DELETE CASCADE,
    CONSTRAINT fk_detail_produk FOREIGN KEY (id_produk)
        REFERENCES produk(id_produk)
) ENGINE=InnoDB;

-- ---------------------------------------------------------------------
-- 2. PROCEDURE
-- ---------------------------------------------------------------------

-- tambah_produk : menambah data produk baru
DELIMITER //
CREATE PROCEDURE tambah_produk (
    IN p_nama_produk VARCHAR(100),
    IN p_harga       DECIMAL(12,2),
    IN p_stok        INT
)
BEGIN
    INSERT INTO produk (nama_produk, harga, stok)
    VALUES (p_nama_produk, p_harga, p_stok);
END //
DELIMITER ;

-- tambah_transaksi : menambah satu baris detail transaksi.
-- subtotal dihitung otomatis dari harga produk saat ini,
-- lalu total pada tabel transaksi ikut diperbarui.
-- Pengurangan stok TIDAK dilakukan di sini, melainkan oleh trigger
-- trg_kurangi_stok setelah baris detail berhasil disimpan.
DELIMITER //
CREATE PROCEDURE tambah_transaksi (
    IN p_id_transaksi INT,
    IN p_id_produk    INT,
    IN p_jumlah       INT
)
BEGIN
    DECLARE v_harga    DECIMAL(12,2);
    DECLARE v_stok     INT;
    DECLARE v_subtotal DECIMAL(12,2);

    SELECT harga, stok INTO v_harga, v_stok
    FROM produk
    WHERE id_produk = p_id_produk
    FOR UPDATE;

    IF v_harga IS NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Produk tidak ditemukan';
    ELSEIF v_stok < p_jumlah THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Stok produk tidak mencukupi';
    END IF;

    SET v_subtotal = v_harga * p_jumlah;

    INSERT INTO detail_transaksi (id_transaksi, id_produk, jumlah, subtotal)
    VALUES (p_id_transaksi, p_id_produk, p_jumlah, v_subtotal);

    UPDATE transaksi
    SET total = total + v_subtotal
    WHERE id_transaksi = p_id_transaksi;
END //
DELIMITER ;

-- ---------------------------------------------------------------------
-- 3. FUNCTION
-- ---------------------------------------------------------------------

-- hitung_total_penjualan : mengembalikan total seluruh penjualan
DELIMITER //
CREATE FUNCTION hitung_total_penjualan()
RETURNS DECIMAL(12,2)
DETERMINISTIC
READS SQL DATA
BEGIN
    DECLARE v_total DECIMAL(12,2);
    SELECT IFNULL(SUM(total), 0) INTO v_total FROM transaksi;
    RETURN v_total;
END //
DELIMITER ;

-- ---------------------------------------------------------------------
-- 4. TRIGGER
-- ---------------------------------------------------------------------

-- trg_kurangi_stok : setelah detail transaksi disimpan,
-- stok produk terkait otomatis berkurang sesuai jumlah yang terjual.
DELIMITER //
CREATE TRIGGER trg_kurangi_stok
AFTER INSERT ON detail_transaksi
FOR EACH ROW
BEGIN
    UPDATE produk
    SET stok = stok - NEW.jumlah
    WHERE id_produk = NEW.id_produk;
END //
DELIMITER ;

-- ---------------------------------------------------------------------
-- 5. VIEW
-- ---------------------------------------------------------------------

-- v_laporan_penjualan : laporan gabungan transaksi, detail, dan produk
CREATE VIEW v_laporan_penjualan AS
SELECT
    t.id_transaksi,
    t.tanggal,
    p.nama_produk,
    dt.jumlah,
    dt.subtotal,
    t.total AS total_transaksi
FROM transaksi t
JOIN detail_transaksi dt ON t.id_transaksi = dt.id_transaksi
JOIN produk p             ON dt.id_produk    = p.id_produk
ORDER BY t.tanggal DESC;

-- ---------------------------------------------------------------------
-- 6. DATA CONTOH (opsional)
-- ---------------------------------------------------------------------

CALL tambah_produk('Indomie Goreng', 3500, 100);
CALL tambah_produk('Aqua Botol 600ml', 4000, 150);
CALL tambah_produk('Teh Pucuk Harum', 5000, 80);
