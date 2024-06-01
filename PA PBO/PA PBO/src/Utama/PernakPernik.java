package Utama;

public final class PernakPernik extends HarryPotter {
    private final String idBarang; // Atribut idBarang dijadikan final
    private String namaPernakPernik;

    public PernakPernik(String idBarang, String namaPernakPernik, int stokBarang, int hargaBarang) {
        super(idBarang, "Pernak Pernik", stokBarang, hargaBarang);
        this.idBarang = idBarang; // Inisialisasi atribut idBarang saat objek PernakPernik dibuat
        this.namaPernakPernik = namaPernakPernik;
    }

    public String toString() {
        return super.getIdBarang() + "," + namaPernakPernik + "," + stokBarang + "," + hargaBarang + "," + "Pernak Pernik";
    }

    public String getIdBarang() {
        return idBarang;
    }

    public String getNamaPernakPernik() {
        return namaPernakPernik;
    }

    public void setNamaPernakPernik(String namaPernakPernik) {
        this.namaPernakPernik = namaPernakPernik;
    }

    @Override
    public void tampil() {
        System.out.printf("| %-13s | %-13s | %-51s | %-16d | %-13d |\n",
                super.getIdBarang(), "Pernak Pernik", this.namaPernakPernik, super.getStokBarang(), super.getHargaBarang());
    }    
}