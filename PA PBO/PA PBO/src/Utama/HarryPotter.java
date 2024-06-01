package Utama;

public abstract class HarryPotter {
    private String idBarang;
    private String namaBarang;
    protected int stokBarang;
    public int hargaBarang;

    public HarryPotter(String idBarang, String namaBarang, int stokBarang, int hargaBarang) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.stokBarang = stokBarang;
        this.hargaBarang = hargaBarang;
    }

    @Override
    public String toString() {
        return idBarang + "," + namaBarang + "," + stokBarang + "," + hargaBarang;
    }

    public abstract void tampil();

    public String getIdBarang() {
        return idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public int getStokBarang() {
        return stokBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public void setStokBarang(int stokBarang) {
        this.stokBarang = stokBarang;
    }

    public int getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(int hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public static void tampilkanHeader() {
        System.out.println("==========================================================================================================================");
        System.out.println("|                                                DATA BARANG DI TOKO WIZARDNEEDS                                         |");
        System.out.println("==========================================================================================================================");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-13s | %-13s | %-51s | %-16s | %-13s |\n", "Id Barang", "Jenis Barang", "Nama Barang", "Stok Barang", "Harga Barang");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
    }
}
