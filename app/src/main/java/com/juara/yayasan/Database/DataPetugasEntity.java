package com.juara.yayasan.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DataPetugasEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int ID;

    @ColumnInfo(name = "NAMA_PETUGAS")
    private String NAMA_PETUGAS;

    @ColumnInfo(name = "ALAMAT")
    private String ALAMAT;

    @ColumnInfo(name = "JENIS_KELAMIN")
    private String JENIS_KELAMIN;

    @ColumnInfo(name = "JUMLAH_DONASI")
    private String JUMLAH_DONASI;

    @ColumnInfo(name = "ALASAN_JADI_PETUGAS")
    private String ALASAN_JADI_PETUGAS;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAMA_PETUGAS() {
        return NAMA_PETUGAS;
    }

    public void setNAMA_PETUGAS(String NAMA_PETUGAS) {
        this.NAMA_PETUGAS = NAMA_PETUGAS;
    }

    public String getALAMAT() {
        return ALAMAT;
    }

    public void setALAMAT(String ALAMAT) {
        this.ALAMAT = ALAMAT;
    }

    public String getJENIS_KELAMIN() {
        return JENIS_KELAMIN;
    }

    public void setJENIS_KELAMIN(String JENIS_KELAMIN) {
        this.JENIS_KELAMIN = JENIS_KELAMIN;
    }

    public String getJUMLAH_DONASI() {
        return JUMLAH_DONASI;
    }

    public void setJUMLAH_DONASI(String JUMLAH_DONASI) {
        this.JUMLAH_DONASI = JUMLAH_DONASI;
    }

    public String getALASAN_JADI_PETUGAS() {
        return ALASAN_JADI_PETUGAS;
    }

    public void setALASAN_JADI_PETUGAS(String ALASAN_JADI_PETUGAS) {
        this.ALASAN_JADI_PETUGAS = ALASAN_JADI_PETUGAS;
    }
}
