import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BiodataMahasiswa extends JFrame {

    private JTextField txtNim;
    private JTextField txtNama;
    private JTextField txtProdi;
    private JTextArea txtOutput;

    public BiodataMahasiswa() {
        setTitle("Aplikasi Biodata Mahasiswa");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ===== Panel Input Data =====
        JPanel panelInput = new JPanel(new GridBagLayout());
        panelInput.setBorder(new TitledBorder("Input Data"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label NIM
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        panelInput.add(new JLabel("NIM"), gbc);

        // TextField NIM
        txtNim = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        panelInput.add(txtNim, gbc);

        // Label Nama
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panelInput.add(new JLabel("Nama"), gbc);

        // TextField Nama
        txtNama = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        panelInput.add(txtNama, gbc);

        // Label Program Studi
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        panelInput.add(new JLabel("Program Studi"), gbc);

        // TextField Program Studi
        txtProdi = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1;
        panelInput.add(txtProdi, gbc);

        // ===== Panel Tombol =====
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnTampilkan = new JButton("Tampilkan");
        JButton btnReset = new JButton("Reset");
        panelTombol.add(btnTampilkan);
        panelTombol.add(btnReset);

        // ===== Panel Atas (Input + Tombol) =====
        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelInput, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);

        // ===== Panel Output =====
        JPanel panelOutput = new JPanel(new BorderLayout());
        panelOutput.setBorder(new TitledBorder("Output"));

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        panelOutput.add(scrollPane, BorderLayout.CENTER);

        // ===== Tambahkan ke Frame =====
        add(panelAtas, BorderLayout.NORTH);
        add(panelOutput, BorderLayout.CENTER);

        // ===== Aksi Tombol Tampilkan =====
        btnTampilkan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = txtNim.getText();
                String nama = txtNama.getText();
                String prodi = txtProdi.getText();

                StringBuilder sb = new StringBuilder();
                sb.append("========== BIODATA MAHASISWA ==========\n\n");
                sb.append(String.format("%-14s: %s\n", "NIM", nim));
                sb.append(String.format("%-14s: %s\n", "Nama", nama));
                sb.append(String.format("%-14s: %s\n", "Program Studi", prodi));

                txtOutput.setText(sb.toString());
            }
        });

        // ===== Aksi Tombol Reset =====
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNim.setText("");
                txtNama.setText("");
                txtProdi.setText("");
                txtOutput.setText("");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BiodataMahasiswa frame = new BiodataMahasiswa();
            frame.setVisible(true);
        });
    }
}
