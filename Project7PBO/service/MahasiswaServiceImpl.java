package service;

import java.util.ArrayList;

public class MahasiswaServiceImpl<T> implements MahasiswaService {
    private ArrayList<T> data = new ArrayList<>();

    @Override
    public void tambahMahasiswa(Object m) {
        data.add((T) m);
    }

    @Override
    public void tampilkan() {
        for (T m : data) {
            System.out.println(m);
        }
    }
}