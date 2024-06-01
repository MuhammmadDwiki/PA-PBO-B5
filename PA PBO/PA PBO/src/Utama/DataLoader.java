package Utama;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataLoader {
    public static void muatDataBarang(String namaFile, ArrayList<HarryPotter> dataBarang) {
        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String idBarang = data[0];
                String namaBarang = data[1];
                int stokBarang = Integer.parseInt(data[2]);
                int hargaBarang = Integer.parseInt(data[3]);
                String jenis = data[4];

                    if (jenis.equals("novel")) {
                        dataBarang.add(new Novel(idBarang, namaBarang, stokBarang, hargaBarang));
                    } else {
                        dataBarang.add(new PernakPernik(idBarang, namaBarang, stokBarang, hargaBarang));
                    }
            }
            System.out.println("Data barang berhasil dimuat dari file " + namaFile);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat memuat data dari file.");
            e.printStackTrace();
        }
    }
}
