package Utama;

public class TipeBarangHarryPotter extends HarryPotter {
    // Konstruktor
    public TipeBarangHarryPotter(String idBarang, String namaBarang, int stokBarang, int hargaBarang) {
        super(idBarang, namaBarang, stokBarang, hargaBarang);
    }

    // Implementasi metode abstrak
    @Override
    public void tampil() {
        System.out.println("Tipe Barang Harry Potter");
        System.out.println("ID Barang: " + getIdBarang());
        System.out.println("Nama Barang: " + getNamaBarang());
        System.out.println("Stok Barang: " + getStokBarang());
        System.out.println("Harga Barang: " + getHargaBarang());
    }

    // Metode untuk mengatur nama barang
    public void setNamaBarang(String namaBarang) {
        super.setNamaBarang(namaBarang); // Menggunakan metode setter yang sudah disediakan di kelas HarryPotter
    }
}
