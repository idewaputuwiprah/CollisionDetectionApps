package com.example.speed2.database.model;

public class User {
    public static final String TABLE_NAME = "dataset";

    public static final String COLUMN_ID = "user_id";
    public static final String COLUMN_NIK = "nik";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_TGLLAHIR = "tanggal_lahir";
    public static final String COLUMN_ALAMAT = "alamat";
    public static final String COLUMN_NOHP = "no_hp";
    public static final String COLUMN_NOALERT = "no_alert";

    private int user_id;
    private String nik;
    private String nama;
    private String tanggal_lahir;
    private String alamat;
    private String no_hp;
    private String no_alert;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NIK + " VARCHAR,"
                    + COLUMN_NAMA + " VARCHAR,"
                    + COLUMN_TGLLAHIR + " VARCHAR,"
                    + COLUMN_ALAMAT + " VARCHAR,"
                    + COLUMN_NOHP + " VARCHAR,"
                    + COLUMN_NOALERT + " VARCHAR,"
                    + ")";

    public User(String nik, String nama, String tanggal_lahir, String alamat, String no_hp, String no_alert) {
        this.nik = nik;
        this.nama = nama;
        this.tanggal_lahir = tanggal_lahir;
        this.alamat = alamat;
        this.no_hp = no_hp;
        this.no_alert = no_alert;
    }

    public User(String no_alert) {
        this.no_alert = no_alert;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getNo_alert() {
        return no_alert;
    }

    public void setNo_alert(String no_alert) {
        this.no_alert = no_alert;
    }
}