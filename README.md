UAS Pemrograman Berorientasi Obyek 2

Mata Kuliah: Pemrograman Berorientasi Obyek 2
Dosen Pengampu: Muhammad Ikhwan Fathulloh

--------------------------------------------------------------------------------------------------------

Profil
Nama: Roy Elisa

NIM: 23552011119

Studi Kasus: Kasir Asuransi

--------------------------------------------------------------------------------------------------------
Judul Studi Kasus

Kasir Asuransi

--------------------------------------------------------------------------------------------------------

Penjelasan Studi Kasus
Kasir untuk pengelolaan premi dan klaim asuransi.

--------------------------------------------------------------------------------------------------------

Implementasi OOP dalam Aplikasi Kasir Asuransi
Berikut penjelasan detail implementasi konsep OOP (Object-Oriented Programming) dalam aplikasi:

1. Encapsulation (Enkapsulasi)
Konsep menyembunyikan data dan mengekspos hanya apa yang diperlukan.

--------------------------------------------------------------------------------------------------------
Contoh dalam kode :

public class Polis {
    private String nomorPolis;
    private Date tanggalMulai;
    private boolean sudahKlaim;

    // Getter dan setter
    public String getNomorPolis() {
        return nomorPolis;
    }

    public void setNomorPolis(String nomorPolis) {
        this.nomorPolis = nomorPolis;
    }

    // Metode lainnya...
}
--------------------------------------------------------------------------------------------------------


Penjelasan :
- Data seperti nomorPolis, tanggalMulai, dan sudahKlaim dienkapsulasi sebagai private
- Akses hanya melalui metode publik (getter/setter)
- Mencegah modifikasi langsung dari luar kelas



2. Inheritance (Pewarisan)
Konsep penurunan sifat dari kelas induk ke kelas anak.

Contoh dalam kode :
--------------------------------------------------------------------------------------------------------
public class BerandaForm extends javax.swing.JFrame {
    // ...
}

Penjelasan :
- BerandaForm mewarisi semua sifat dan perilaku dari JFrame
- Dapat menggunakan semua metode yang dimiliki JFrame
- Dapat menambahkan fitur khusus untuk aplikasi asuransi



3. Polymorphism (Polimorfisme)
Kemampuan objek untuk memiliki banyak bentuk.

Contoh dalam kode:

java
DefaultTableModel model = new DefaultTableModel(...) {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
};
--------------------------------------------------------------------------------------------------------


Penjelasan:
- Meng-override metode isCellEditable untuk mengubah perilaku default
- Meski menggunakan tipe DefaultTableModel, perilakunya diubah sesuai kebutuhan
- Implementasi yang berbeda untuk metode yang sama



4. Abstraction (Abstraksi)
Menyembunyikan detail kompleksitas dan menampilkan fungsionalitas esensial.

Contoh dalam kode:
--------------------------------------------------------------------------------------------------------
java
public class PolisDAO {
    public List<Polis> getAllPolisWithKlaimStatus() {
        // Implementasi kompleks akses database
    }
}

// Di BerandaForm
PolisDAO polisDAO = new PolisDAO();
List<Polis> listPolis = polisDAO.getAllPolisWithKlaimStatus();
--------------------------------------------------------------------------------------------------------


Penjelasan:
- Kelas BerandaForm tidak perlu tahu detail cara mengambil data dari database
- Hanya perlu memanggil metode getAllPolisWithKlaimStatus()
- Detail implementasi disembunyikan di balik abstraksi DAO (Data Access Object)


--------------------------------------------------------------------------------------------------------

Demo Proyek
Github: https://github.com/Roy-prog1/UAS_PBO2_23KB_23552011119

Drive : https://drive.google.com/drive/folders/1LBCKMk_-NjTjgnlh4rfI0wKtznKhTOus?usp=sharing
