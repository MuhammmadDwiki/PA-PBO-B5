package Utama;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataSaver {
    // Metode untuk menyimpan data barang ke dalam file
    public static void simpanDataBarang(ArrayList<HarryPotter> dataBarang, String namaFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile))) {
            for (HarryPotter barang : dataBarang) {
                writer.write(barang.toString());
                writer.newLine();
            }
            System.out.println("Data barang berhasil disimpan ke dalam file " + namaFile);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data.");
            e.printStackTrace();
        }
    }
}