import java.util.ArrayList;
import java.util.Scanner;

import Utama.*;
import Account.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

interface DataManagement {
    void 
    tambahData(ArrayList<HarryPotter> Hp, BufferedReader br) throws IOException;

    void hapusData(ArrayList<HarryPotter> Hp, BufferedReader br) throws IOException;
}

public class App implements DataManagement {
    protected App() {
    } // Menggunakan protected untuk membatasi akses konstruktor

    static ArrayList<HarryPotter> Hp = new ArrayList<>();
    static ArrayList<Admin> admn = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        DataManagement dataManager = new App();
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        DataLoader.muatDataBarang("data/data_admin.txt", Hp);

        // Tetapkan satu admin default
        Admin defaultAdmin = new Admin("123", "Admin");
        admn.add(defaultAdmin);

        boolean programBerjalan = true; // Gunakan flag untuk menandai apakah program masih berjalan

        while (programBerjalan) {
            boolean loggedIn = false;
            Admin loggedInAdmin = null;
            User loggedInUser = null;

            // Menampilkan menu untuk memilih apakah akan login sebagai admin atau user
            while (!loggedIn) {
                System.out.println("==============================================================================================================");
                System.out.println("| SELAMAT DATANG DI PROGRAM PENDATAAN DAN PENJUALAN NOVEL DAN PERNAK PERNIK HARRY POTTER DI TOKO WIZARDNEEDS |");
                System.out.println("==============================================================================================================");
                System.out.println("|                                           1. Login Admin                                                   |");
                System.out.println("|                                           2. Login User                                                    |");
                System.out.println("|                                           3. Registrasi User                                               |");
                System.out.println("|                                           4. Exit                                                          |");
                System.out.println("==============================================================================================================");
                System.out.print("Pilih menu (1/2/3/4) : ");

                int menuPilihan;
                try {
                    menuPilihan = Integer.parseInt(br.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Pilihan tidak valid. Masukkan nomor menu!");
                    continue;
                }

                switch (menuPilihan) {
                    case 1:
                        // Login Admin
                        loggedInAdmin = loginAdmin(br);
                        if (loggedInAdmin != null) {
                            loggedIn = true;
                            adminMenu(dataManager, br);
                        }
                        break;
                    case 2:
                        // Login User
                        loggedInUser = loginUser(br);
                        if (loggedInUser != null) {
                            loggedIn = true;
                            userMenu(loggedInUser, br);
                        }
                        break;
                    case 3:
                        registrasiUser(br);
                        break;
                    case 4:
                        System.out.println("Terima kasih! Program selesai.");
                        programBerjalan = false; // Ubah flag untuk menghentikan program
                        return;

                    default:
                        System.out.println("Pilihan tidak valid!");
                        break;
                }
            }
        }
    }

    static void adminMenu(DataManagement dataManager, BufferedReader br) throws IOException {
        int Pilihan = 0;
        boolean loggedOut = false; // Tambahkan variabel untuk menandai apakah admin telah logout

        while (!loggedOut) { // Ubah dari do-while menjadi while biasa
            System.out.println("=======================================================================================================");
            System.out.println("| MENU UTAMA PROGRAM PENDATAAN DAN PENJUALAN NOVEL DAN PERNAK PERNIK HARRY POTTER DI TOKO WIZARDNEEDS |");
            System.out.println("=======================================================================================================");
            System.out.println("|                                         1. Manajemen Data                                           |");
            System.out.println("|                                         2. History Pesanan                                          |");
            System.out.println("|                                         3. Manajemen Pesanan                                        |");
            System.out.println("|                                         4. Log out                                                  |");
            System.out.println("=======================================================================================================");
            System.out.print("Pilih menu (1/2/3/4) : ");
            try {
                Pilihan = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Pilihan tidak valid. Masukkan nomor menu!");
                continue;
            }

            switch (Pilihan) {
                case 1:
                    // Sub-menu Manajemen Data Barang (CRUD)
                    int subMenuData;
                    do {
                        System.out.println("==============================================================");
                        System.out.println("|             MENU MANAJEMEN DATA BARANG PADA ADMIN          |");
                        System.out.println("==============================================================");
                        System.out.println("|                   1. Tampilkan Data Barang                 |");
                        System.out.println("|                   2. Tambah Data Barang                    |");
                        System.out.println("|                   3. Ubah Data Barang                      |");
                        System.out.println("|                   4. Hapus Data Barang                     |");
                        System.out.println("|                   5. Kembali ke Menu Utama                 |");
                        System.out.println("==============================================================");
                        System.out.print("Pilih menu (1/2/3/4/5) : ");
                        try {
                            subMenuData = Integer.parseInt(br.readLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Pilihan tidak valid. Masukkan nomor menu!");
                            subMenuData = 0; // Reset subMenuData menjadi 0 agar loop tetap berlanjut
                            continue;
                        }

                        switch (subMenuData) {
                            case 1:
                                ArrayList<HarryPotter> filteredHp = new ArrayList<>();
                                // Panggil metode untuk menampilkan data barang
                                tampilkanData(Hp, filteredHp);
                                break;
                            case 2:
                                // Panggil metode untuk menambah data barang
                                dataManager.tambahData(Hp, br);
                                // Simpan data setelah menambah
                                String namaFile = "data/data_admin.txt";
                                DataSaver.simpanDataBarang(Hp, namaFile);
                                break;
                            case 3:
                                // Panggil metode untuk mengubah data barang
                                ubahData(Hp, br);
                                break;
                            case 4:
                                // Panggil metode untuk menghapus data barang
                                dataManager.hapusData(Hp, br);
                                break;
                            case 5:
                                // Kembali ke menu utama
                                break;
                            default:
                                System.out.println("Pilihan Tidak Valid !");
                                break;
                        }
                    } while (subMenuData != 5);
                    break;
                case 2:
                    // Menampilkan history pemesanan
                    lihatHistoryPemesanan(users);
                    break;
                case 3:
                    // Panggil metode manajemen pesanan
                    manajemenPesananAdmin(users, br);
                    break;
                case 4:
                    System.out.println("Anda berhasil !");
                    loggedOut = true; // Set flag loggedOut menjadi true untuk keluar dari loop
                    break;

                default:
                    System.out.println("Pilihan Tidak Valid !");
                    break;
            }
        }
    }

    static void userMenu(User user, BufferedReader br) throws IOException {
        int Pilihan;
        do {
            System.out.println("======================================================");
            System.out.println("|          MENU UTAMA USER DI TOKO WIZARDNEES        |");
            System.out.println("======================================================");
            System.out.println("|                   1. Pesan Barang                  |");
            System.out.println("|                   2. Lihat Pesanan                 |");
            System.out.println("|                   3. Hapus Pesanan                 |");
            System.out.println("|                   4. Log out                       |");
            System.out.println("======================================================");
            System.out.print("Pilih menu (1/2/3/4) : ");

            try {
                Pilihan = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Pilihan tidak valid. Masukkan nomor menu!");
                Pilihan = 0; // Reset Pilihan menjadi 0 agar loop tetap berlanjut
                continue;
            }

            switch (Pilihan) {
                case 1:
                    // Panggil metode untuk memesan barang
                    pesanBarang(user, br);
                    break;
                case 2:
                    // Panggil metode untuk melihat pesanan
                    lihatPesanan(user);
                    break;
                case 3:
                    // Panggil metode untuk menghapus pesanan
                    hapusPesanan(user, br);
                    break;
                case 4:
                    System.out.println("Anda Telah Keluar!");
                    return;
                default:
                    System.out.println("Pilihan Tidak Valid!");
                    break;
            }
        } while (Pilihan != 4);
    }

    static Admin loginAdmin(BufferedReader br) throws IOException {
        System.out.println("===========================");
        System.out.println("|       LOGIN ADMIN       |");
        System.out.println("===========================");
        System.out.print("Masukkan ID Admin       : ");
        String adminId = br.readLine();
        System.out.print("Masukkan Password Admin : ");
        String password = br.readLine();

        for (Admin admin : admn) {
            if (admin.getIdAdmin().equals(adminId) && admin.getPassword().equals(password)) {
                System.out.println("Anda berhasil login sebagai Admin!");
                return admin;
            }
        }
        System.out.println("ID atau Password yang Anda masukkan salah. Silakan coba lagi!");
        return null;
    }

    static void registrasiUser(BufferedReader br) throws IOException {
        System.out.println("=================================");
        System.out.println("|        REGISTRASI USER        |");
        System.out.println("=================================");
        System.out.print("Masukkan ID User Baru       : ");
        String userId = br.readLine();
        System.out.print("Masukkan Password User Baru : ");
        String password = br.readLine();

        users.add(new User(userId, password));
        System.out.println("User berhasil registrasi!");
    }

    static User loginUser(BufferedReader br) throws IOException {
        System.out.println("=================================");
        System.out.println("|           LOGIN USER          |");
        System.out.println("=================================");
        System.out.print("Masukkan ID User       : ");
        String userId = br.readLine();
        System.out.print("Masukkan Password User : ");
        String password = br.readLine();

        for (User user : users) {
            if (user.getIdUser().equals(userId) && user.getPassword().equals(password)) {
                System.out.println("Anda berhasil login sebagai User!");
                return user;
            }
        }
        System.out.println("ID atau Password yang Anda masukkan salah. Silakan coba lagi!");
        return null;
    }

    static void lihatHistoryPemesanan(ArrayList<User> users) {
        System.out.println("========================================");
        System.out.println("|            HISTORY PEMESANAN         |");
        System.out.println("========================================");
        for (User user : users) {
            System.out.println("Masukkan ID User : " + user.getIdUser());
            ArrayList<HarryPotter> pesanan = user.getPesanan();
            if (pesanan.isEmpty()) {
                System.out.println("Belum ada barang yang dipesan!");
            } else {
                for (int i = 0; i < pesanan.size(); i++) {
                    HarryPotter hp = pesanan.get(i);
                    String jenisBarang = (hp instanceof Novel) ? "Novel" : "Pernak Pernik";
                    System.out.println("Nomor Pesanan    : " + (i + 1));
                    System.out.println("ID Barang        : " + hp.getIdBarang());
                    System.out.println("Jenis Barang     : " + jenisBarang);
                    System.out.println("Harga            : " + hp.getHargaBarang());
                }
            }
            System.out.println("========================================");
        }
    }

    static void tampilkanData(ArrayList<HarryPotter> Hp, ArrayList<HarryPotter> filteredHp) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===============================");
        System.out.println("|   MENAMPILKAN DATA BARANG   |");
        System.out.println("===============================");
        System.out.println("|         1. Novel            |");
        System.out.println("|         2. Pernak Pernik    |");
        System.out.println("===============================");
        System.out.println("Masukkan Pilihan (1/2) :");
        int pilihan = scanner.nextInt();
        HarryPotter.tampilkanHeader();
        filteredHp.clear(); // Membersihkan daftar sebelum menambahkan item baru
        if (pilihan == 1) {
            for (HarryPotter barang : Hp) {
                if (barang instanceof Novel) {
                    barang.tampil();
                    filteredHp.add(barang);
                }
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        } else if (pilihan == 2) {
            for (HarryPotter barang : Hp) {
                if (barang instanceof PernakPernik) {
                    barang.tampil();
                    filteredHp.add(barang);
                }
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }
    
    @Override
    public void tambahData(ArrayList<HarryPotter> Hp, BufferedReader br) {
        try {
            System.out.println("==========================================================");
            System.out.println("|           JENIS BARANG YANG INGIN DITAMBAHKAN          |");
            System.out.println("==========================================================");
            System.out.println("|                      1. Novel                          |");
            System.out.println("|                      2. Pernak Pernik                  |");
            System.out.println("==========================================================");
            System.out.print("Pilih jenis barang yang ingin ditambahkan (1/2): ");
            int jenisBarang = Integer.parseInt(br.readLine());

            switch (jenisBarang) {
                case 1:
                    tambahNovel(Hp, br);
                    break;
                case 2:
                    tambahPernakPernik(Hp, br);
                    break;
                default:
                    System.out.println("Pilihan Tidak Valid !");
                    break;
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca input: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Masukan tidak valid. Harap masukkan angka yang benar.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    static void tambahNovel(ArrayList<HarryPotter> Hp, BufferedReader br) throws IOException {
        System.out.println("===============================================================");
        System.out.println("| SILAHKAN MENAMBAHKAN DATA BARANG NOVEL DI TOKO WIZARDNEEDS |");
        System.out.println("===============================================================");
    
        try {
            int idNovel;
            boolean idExists;
            do {
                System.out.print("Masukkan ID Novel       : ");
                idNovel = Integer.parseInt(br.readLine());
                idExists = false;
                for (HarryPotter barang : Hp) {
                    if (barang.getIdBarang().equals(String.valueOf(idNovel))) {
                        idExists = true;
                        System.out.println("ID sudah ada. Silakan masukkan ID yang berbeda.");
                        break;
                    }
                }
            } while (idExists || idNovel < 0);
    
            System.out.print("Masukkan Judul Novel    : ");
            String judulNovel = br.readLine();
    
            int stokNovel;
            do {
                System.out.print("Masukkan Stok Novel     : ");
                stokNovel = Integer.parseInt(br.readLine());
                if (stokNovel <= 0) {
                    System.out.println("Stok harus lebih besar dari 0. Silakan coba lagi.");
                }
            } while (stokNovel <= 0);
    
            int hargaNovel;
            do {
                System.out.print("Masukkan Harga Novel    : ");
                hargaNovel = Integer.parseInt(br.readLine());
                if (hargaNovel <= 0) {
                    System.out.println("Harga harus lebih besar dari 0. Silakan coba lagi.");
                }
            } while (hargaNovel <= 0);
    
            Novel novel = new Novel(String.valueOf(idNovel), judulNovel, stokNovel, hargaNovel);
            Hp.add(novel);
            System.out.println("===============================================================");
            System.out.println("|             DATA BARANG NOVEL BERHASIL DITAMBAHKAN          |");
            System.out.println("===============================================================");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Harap masukkan angka yang benar.");
        }
    }
    
    
    static void tambahPernakPernik(ArrayList<HarryPotter> Hp, BufferedReader br) throws IOException {
        System.out.println("=======================================================================");
        System.out.println("| SILAHKAN MENAMBAHKAN DATA BARANG PERNAK PERNIK DI TOKO WIZARDNEEDS |");
        System.out.println("=======================================================================");
    
        try {
            int idPernakPernik;
            boolean idExists;
            do {
                System.out.print("Masukkan ID Pernak Pernik    : ");
                idPernakPernik = Integer.parseInt(br.readLine());
                idExists = false;
                for (HarryPotter barang : Hp) {
                    if (barang.getIdBarang().equals(String.valueOf(idPernakPernik))) {
                        idExists = true;
                        System.out.println("ID sudah ada. Silakan masukkan ID yang berbeda.");
                        break;
                    }
                }
            } while (idExists || idPernakPernik < 0);
    
            System.out.print("Masukkan Nama Pernak Pernik  : ");
            String namaPernakPernik = br.readLine();
    
            int stokPernakPernik;
            do {
                System.out.print("Masukkan Stok Pernak Pernik  : ");
                stokPernakPernik = Integer.parseInt(br.readLine());
                if (stokPernakPernik <= 0) {
                    System.out.println("Stok harus lebih besar dari 0. Silakan coba lagi.");
                }
            } while (stokPernakPernik <= 0);
    
            int hargaPernakPernik;
            do {
                System.out.print("Masukkan Harga Pernak Pernik : ");
                hargaPernakPernik = Integer.parseInt(br.readLine());
                if (hargaPernakPernik <= 0) {
                    System.out.println("Harga harus lebih besar dari 0. Silakan coba lagi.");
                }
            } while (hargaPernakPernik <= 0);
    
            PernakPernik pernakPernik = new PernakPernik(String.valueOf(idPernakPernik), namaPernakPernik, stokPernakPernik, hargaPernakPernik);
            Hp.add(pernakPernik);
            System.out.println("=======================================================================");
            System.out.println("|           DATA BARANG PERNAK PERNIK BERHASIL DITAMBAHKAN            |");
            System.out.println("=======================================================================");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Harap masukkan angka yang benar.");
        }
    }
    
    
    static void ubahData(ArrayList<HarryPotter> Hp, BufferedReader br) throws IOException {
        ArrayList<HarryPotter> filteredHp = new ArrayList<>();
        System.out.println("=================================================================");
        System.out.println("|           MENGUBAH DATA BARANG DI TOKO WIZARDNEEDS            |");
        System.out.println("=================================================================");
        System.out.println("=================================================");
        System.out.println("|        JENIS BARANG YANG INGIN DIUBAH         |");
        System.out.println("=================================================");
        System.out.println("|                  1. Novel                     |");
        System.out.println("|                  2. Pernak Pernik             |");
        System.out.println("=================================================");
        System.out.print("Masukkan pilihan (1/2) : ");
        int pilihan = Integer.parseInt(br.readLine());
    
        boolean dataDitemukan = false;
    
        if (pilihan == 1) {
            for (HarryPotter barang : Hp) {
                if (barang instanceof Novel) {
                    filteredHp.add(barang);
                    dataDitemukan = true;
                }
            }
        } else if (pilihan == 2) {
            for (HarryPotter barang : Hp) {
                if (barang instanceof PernakPernik) {
                    filteredHp.add(barang);
                    dataDitemukan = true;
                }
            }
        } else {
            System.out.println("Pilihan tidak valid.");
            return;
        }
    
        if (!dataDitemukan) {
            System.out.println("Tidak ada data barang yang tersedia.");
            return;
        }
    
        HarryPotter.tampilkanHeader();
        for (HarryPotter barang : filteredHp) {
            barang.tampil();
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        }
    
        System.out.print("Masukkan ID Barang Yang Akan Diubah : ");
        String idbarang = br.readLine();
    
        for (HarryPotter barang : filteredHp) {
            if (barang.getIdBarang().equals(idbarang)) {
                try {
                    System.out.print("Masukkan Nama Barang Baru      : ");
                    String namaBaru = br.readLine();
                    System.out.print("Masukkan Stok Barang Baru      : ");
                    int stokBaru = Integer.parseInt(br.readLine());
                    if (stokBaru <= 0) {
                        throw new NumberFormatException();
                    }
                    System.out.print("Masukkan Harga Barang Baru     : ");
                    int hargaBaru = Integer.parseInt(br.readLine());
                    if (hargaBaru <= 0) {
                        throw new NumberFormatException();
                    }
    
                    if (barang instanceof Novel) {
                        Novel novel = (Novel) barang;
                        novel.setJudulNovel(namaBaru); // Mengubah judul novel
                    } else if (barang instanceof PernakPernik) {
                        ((PernakPernik) barang).setNamaPernakPernik(namaBaru); // Mengubah nama pernak pernik
                    }
    
                    barang.setStokBarang(stokBaru); // Mengubah stok
                    barang.setHargaBarang(hargaBaru); // Mengubah harga
    
                    System.out.println("=================================================================");
                    System.out.println("|        DATA BARANG DI TOKO WIZARDNEEDS BERHASIL DIUBAH        |");
                    System.out.println("=================================================================");
                    return;
                } catch (NumberFormatException e) {
                    System.out.println("Input untuk stok dan harga harus berupa angka lebih dari 0. Silakan coba lagi.");
                    return;
                }
            }
        }
    
        System.out.println("Barang dengan ID " + idbarang + " tidak ditemukan.");
    }
    
    @Override
    public void hapusData(ArrayList<HarryPotter> Hp, BufferedReader br) throws IOException {
        ArrayList<HarryPotter> filteredHp = new ArrayList<>();
        System.out.println("=================================================================");
        System.out.println("|           MENGHAPUS DATA BARANG DI TOKO WIZARDNEEDS           |");
        System.out.println("=================================================================");
        System.out.println("=================================================");
        System.out.println("|        JENIS BARANG YANG INGIN DIHAPUS        |");
        System.out.println("=================================================");
        System.out.println("|                  1. Novel                     |");
        System.out.println("|                  2. Pernak Pernik             |");
        System.out.println("=================================================");
        System.out.print("Masukkan pilihan (1/2) : ");
        int pilihan = Integer.parseInt(br.readLine());

        boolean dataDitemukan = false;

        if (pilihan == 1) {
            for (HarryPotter barang : Hp) {
                if (barang instanceof Novel) {
                    filteredHp.add(barang);
                    dataDitemukan = true;
                }
            }
        } else if (pilihan == 2) {
            for (HarryPotter barang : Hp) {
                if (barang instanceof PernakPernik) {
                    filteredHp.add(barang);
                    dataDitemukan = true;
                }
            }
        } else {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        if (!dataDitemukan) {
            System.out.println("Tidak ada data barang yang tersedia.");
            return;
        }

        HarryPotter.tampilkanHeader();
        for (HarryPotter barang : filteredHp) {
            barang.tampil();
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        }
        System.out.print("Masukkan ID Barang Yang Akan Dihapus : ");
        String idbarang = br.readLine();

        for (int i = 0; i < Hp.size(); i++) {
            if (Hp.get(i).getIdBarang().equals(idbarang)) {
                Hp.remove(i);
                System.out.println("=================================================================");
                System.out.println("|       DATA BARANG DI TOKO WIZARDNEEDS BERHASIL DIHAPUS        |");
                System.out.println("=================================================================");
                return;
            }
        }

        System.out.println("Barang dengan ID " + idbarang + " tidak ditemukan.");
    }

    static void manajemenPesananAdmin(ArrayList<User> users, BufferedReader br) throws IOException {
        int pilihan;
        do {
            System.out.println("==========================================");
            System.out.println("|         MENU MANAJEMEN PESANAN         |");
            System.out.println("==========================================");
            System.out.println("| 1. Lihat Pesanan                       |");
            System.out.println("| 2. Konfirmasi Pesanan                  |");
            System.out.println("| 3. Hapus Pesanan                       |");
            System.out.println("| 4. Kembali ke Menu Utama Admin         |");
            System.out.println("==========================================");
            System.out.print("Pilih menu (1/2/3/4): ");
            try {
                pilihan = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Pilihan tidak valid. Masukkan nomor menu!");
                pilihan = 0; // Reset pilihan menjadi 0 agar loop tetap berlanjut
                continue;
            }

            switch (pilihan) {
                case 1:
                    lihatSemuaPesanan(users);
                    break;
                case 2:
                    konfirmasiPesanan(users, br);
                    break;
                case 3:
                    hapusPesananAdmin(users, br);
                    break;
                case 4:
                    System.out.println("Kembali ke Menu Utama Admin.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        } while (pilihan != 4);
    }

    static void lihatSemuaPesanan(ArrayList<User> users) {
        System.out.println("=============================================================");
        System.out.println("|                       DAFTAR PESANAN                      |");
        System.out.println("=============================================================");
        for (User user : users) {
            System.out.println("ID User : " + user.getIdUser());
            ArrayList<HarryPotter> pesanan = user.getPesanan();
            if (pesanan.isEmpty()) {
                System.out.println("Belum ada barang yang dipesan!");
            } else {
                System.out.println("------------------------------------------------------------");
                System.out.printf("| %-5s | %-15s | %-15s | %-12s |\n", "No.", "ID Barang", "Jenis Barang", "Harga");
                System.out.println("------------------------------------------------------------");
                for (int i = 0; i < pesanan.size(); i++) {
                    HarryPotter hp = pesanan.get(i);
                    String jenisBarang = (hp instanceof Novel) ? "Novel" : "Pernak Pernik";
                    System.out.printf("| %-5d | %-15s | %-15s | %-12d |\n", (i + 1), hp.getIdBarang(), jenisBarang,
                        hp.getHargaBarang());
                }
                System.out.println("------------------------------------------------------------");
            }
        }
    }

    static void konfirmasiPesanan(ArrayList<User> users, BufferedReader br) throws IOException {
        System.out.println("=========================================================");
        System.out.println("|                  KONFIRMASI PESANAN                   |");
        System.out.println("=========================================================");
        System.out.print("Masukkan ID User yang pesanannya ingin dikonfirmasi : ");
        String userId = br.readLine();

        for (User user : users) {
            if (user.getIdUser().equals(userId)) {
                ArrayList<HarryPotter> pesanan = user.getPesanan();
                if (pesanan.isEmpty()) {
                    System.out.println("User ini belum memesan barang!");
                    return;
                }

                // Check apakah transaksi sudah dilakukan
                if (!user.isTransaksiSelesai()) {
                    System.out.println("User ini belum menyelesaikan transaksi!");
                    return;
                }

                int totalHarga = 0;
                for (HarryPotter hp : pesanan) {
                    totalHarga += hp.getHargaBarang();
                }
                System.out.println("Total harga pesanan       : " + totalHarga);
                System.out.print("Konfirmasi pesanan? (y/n) : ");
                String konfirmasi = br.readLine();
                if (konfirmasi.equalsIgnoreCase("y")) {
                    System.out.println("Pesanan dikonfirmasi!");
                } else {
                    System.out.println("Konfirmasi pesanan dibatalkan!");
                }
                return;
            }
        }
        System.out.println("User dengan ID " + userId + " tidak ditemukan.");
    }

    static void hapusPesananAdmin(ArrayList<User> users, BufferedReader br) throws IOException {
        System.out.println("=================================================");
        System.out.println("|                  HAPUS PESANAN                |");
        System.out.println("=================================================");
        System.out.print("Masukkan ID User yang pesanan ingin dihapus : ");
        String userId = br.readLine();

        for (User user : users) {
            if (user.getIdUser().equals(userId)) {
                ArrayList<HarryPotter> pesanan = user.getPesanan();
                if (pesanan.isEmpty()) {
                    System.out.println("User ini belum memesan barang!");
                    return;
                }

                lihatPesanan(user);
                System.out.print("Pilih nomor barang yang akan dihapus dari pesanan : ");
                int nomorBarang;
                try {
                    nomorBarang = Integer.parseInt(br.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Nomor barang harus berupa angka!");
                    return;
                }

                if (nomorBarang < 1 || nomorBarang > pesanan.size()) {
                    System.out.println("Nomor barang tidak valid!");
                    return;
                }

                user.hapusPesanan(nomorBarang - 1);
                System.out.println("Barang berhasil dihapus dari pesanan!");
                return;
            }
        }
        System.out.println("User dengan ID " + userId + " tidak ditemukan.");
    }

    static void tampilkanJumlahBarang() {
        System.out.println("Jumlah barang saat ini : " + Hp.size());
    }

    static void pesanBarang(User user, BufferedReader br) throws IOException {
        ArrayList<HarryPotter> filteredHp = new ArrayList<>();
        tampilkanData(Hp, filteredHp);
        if (filteredHp.isEmpty()) {
            System.out.println("Tidak ada barang yang tersedia sesuai pilihan.");
            return;
        }
        System.out.print("Pilih ID barang yang akan dipesan : ");
        int idBarang;
        try {
            idBarang = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("ID barang harus berupa angka!");
            return;
        }

        HarryPotter barang = null;
        for (HarryPotter h : filteredHp) {
            if (h.getIdBarang().equals(Integer.toString(idBarang))) {
                barang = h;
            }
        }

        if (barang == null) {
            System.out.println("ID barang tidak valid!");
            return;
        }
    
        user.pesanBarang(barang);
        System.out.println("Barang berhasil dipesan!");
    
        // Tawarkan opsi untuk transaksi setelah memesan barang
        System.out.print("Lanjutkan transaksi? (y/n): ");
        String lanjutTransaksi = br.readLine();
        if (lanjutTransaksi.equalsIgnoreCase("y")) {
            transaksi(user, br); // Panggil metode untuk melakukan transaksi
        }
    }
    
    static void lihatPesanan(User user) {
        System.out.println("============================================================");
        System.out.println("|                    DAFTAR PESANAN ANDA                   |");
        System.out.println("============================================================");
        ArrayList<HarryPotter> pesanan = user.getPesanan();
        if (pesanan.isEmpty()) {
            System.out.println("Belum ada barang yang dipesan!");
        } else {
            System.out.printf("| %-5s | %-15s | %-15s | %-12s |\n", "No.", "ID Barang", "Jenis Barang", "Harga");
            System.out.println("------------------------------------------------------------");
            for (int i = 0; i < pesanan.size(); i++) {
                HarryPotter hp = pesanan.get(i);
                String jenisBarang = (hp instanceof Novel) ? "Novel" : "Pernak Pernik";
                System.out.printf("| %-5d | %-15s | %-15s | %-12d |\n", (i + 1), hp.getIdBarang(), jenisBarang,
                        hp.getHargaBarang());
            }
            System.out.println("------------------------------------------------------------");
        }
    }

    static void transaksi(User user, BufferedReader br) throws IOException {
        System.out.println("============================================================");
        System.out.println("|                        TRANSAKSI                         |");
        System.out.println("============================================================");
        ArrayList<HarryPotter> pesanan = user.getPesanan();
        if (pesanan.isEmpty()) {
            System.out.println("Belum ada barang yang dipesan.");
            return;
        }

        int totalHarga = 0;
        for (HarryPotter hp : pesanan) {
            totalHarga += hp.getHargaBarang();
        }
        System.out.println("Total harga                : " + totalHarga);
        System.out.print("Lanjutkan transaksi? (y/n) : ");
        String konfirmasi = br.readLine();
        if (konfirmasi.equalsIgnoreCase("y")) {
            user.transaksi();
            System.out.println("Transaksi berhasil!");
        } else {
            System.out.println("Transaksi dibatalkan!");
        }
    }

    static void hapusPesanan(User user, BufferedReader br) throws IOException {
        System.out.println("============================================================");
        System.out.println("|                  HAPUS PESANAN BARANG                    |");
        System.out.println("============================================================");
        ArrayList<HarryPotter> pesanan = user.getPesanan();
        if (pesanan.isEmpty()) {
            System.out.println("Tidak ada barang yang dipesan!");
            return;
        }

        lihatPesanan(user);
        System.out.print("Pilih nomor barang yang akan dihapus dari pesanan : ");
        int nomorBarang;
        try {
            nomorBarang = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Nomor barang harus berupa angka!");
            return;
        }

        if (nomorBarang < 1 || nomorBarang > pesanan.size()) {
            System.out.println("Nomor barang tidak valid!");
            return;
        }

        user.hapusPesanan(nomorBarang - 1);
        System.out.println("Barang berhasil dihapus dari pesanan!");
    }
}