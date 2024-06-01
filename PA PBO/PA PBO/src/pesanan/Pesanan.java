package pesanan;

public class Pesanan {
    private String idBarang;
    private int jumlah;

    public Pesanan(String idBarang, int jumlah) {
        this.idBarang = idBarang;
        this.jumlah = jumlah;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
