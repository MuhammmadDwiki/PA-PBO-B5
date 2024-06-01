package Account;

import java.util.ArrayList;
import Utama.HarryPotter; 

public class User {
    private String idUser;
    private String password;
    private ArrayList<HarryPotter> pesanan;
    private boolean transaksiSelesai;

    public User(String idUser, String password) {
        this.idUser = idUser;
        this.password = password;
        this.pesanan = new ArrayList<>();
        this.transaksiSelesai = false;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getPassword() {
        return password;
    }

    // Getter dan setter untuk idUser dan password

    public void pesanBarang(HarryPotter barang) {
        // Periksa apakah stok barang mencukupi sebelum menambahkan ke pesanan
        if (barang.getStokBarang() > 0) {
            pesanan.add(barang);
            // Kurangi stok barang yang telah dipesan
            barang.setStokBarang(barang.getStokBarang() - 1);
        } else {
            System.out.println("Maaf, stok barang " + barang.getNamaBarang() + " tidak mencukupi.");
        }
    }

    public ArrayList<HarryPotter> getPesanan() {
        return pesanan;
    }

     // Method to check if transaksi is finished
     public boolean isTransaksiSelesai() {
        return transaksiSelesai;
    }

    public void hapusPesanan(int index) {
        if (index >= 0 && index < pesanan.size()) {
            pesanan.remove(index);
        }
    }

    public void transaksi() {
        transaksiSelesai = true;
    }
}

