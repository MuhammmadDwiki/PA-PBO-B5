package Utama;

public final class Novel extends HarryPotter {
    private String judulNovel;

    public Novel(String idBarang, String judulNovel, int stokBarang, int hargaBarang) {
        super(idBarang, "Novel", stokBarang, hargaBarang);
        this.judulNovel = judulNovel;
    }
    
    public String toString() {
        return super.getIdBarang() + "," + judulNovel + "," + stokBarang + "," + hargaBarang + "," + "novel" ;
    }

    public String getJudulNovel() {
        return judulNovel;
    }

    public void setJudulNovel(String judulNovel) {
        this.judulNovel = judulNovel;
    }
    
    @Override
    public void tampil() {
        System.out.printf("| %-13s | %-13s | %-51s | %-16d | %-13d |\n",
                super.getIdBarang(), "Novel", this.judulNovel, super.getStokBarang(), super.getHargaBarang());
    }
        
}