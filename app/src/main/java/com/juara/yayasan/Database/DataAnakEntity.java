package com.juara.yayasan.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DataAnakEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int ID;

    @ColumnInfo(name = "NAMA_ANAK")
    private String NAMA_ANAK;

    @ColumnInfo(name = "NAMA_ORANG_TUA")
    private String NAMA_ORANG_TUA;

    @ColumnInfo(name = "JENIS_KELAMIN")
    private String JENIS_KELAMIN;

    @ColumnInfo(name = "TANGGAL_LAHIR")
    private String TANGGAL_LAHIR;

    @ColumnInfo(name = "ASAL_KOTA")
    private String ASAL_KOTA;

    @ColumnInfo(name = "CREATED_DATE")
    private String CREATED_DATE;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAMA_ANAK() {
        return NAMA_ANAK;
    }

    public void setNAMA_ANAK(String NAMA_ANAK) {
        this.NAMA_ANAK = NAMA_ANAK;
    }

    public String getNAMA_ORANG_TUA() {
        return NAMA_ORANG_TUA;
    }

    public void setNAMA_ORANG_TUA(String NAMA_ORANG_TUA) {
        this.NAMA_ORANG_TUA = NAMA_ORANG_TUA;
    }

    public String getJENIS_KELAMIN() {
        return JENIS_KELAMIN;
    }

    public void setJENIS_KELAMIN(String JENIS_KELAMIN) {
        this.JENIS_KELAMIN = JENIS_KELAMIN;
    }

    public String getTANGGAL_LAHIR() {
        return TANGGAL_LAHIR;
    }

    public void setTANGGAL_LAHIR(String TANGGAL_LAHIR) {
        this.TANGGAL_LAHIR = TANGGAL_LAHIR;
    }

    public String getASAL_KOTA() {
        return ASAL_KOTA;
    }

    public void setASAL_KOTA(String ASAL_KOTA) {
        this.ASAL_KOTA = ASAL_KOTA;
    }

    public String getCREATED_DATE() {
        return CREATED_DATE;
    }

    public void setCREATED_DATE(String CREATED_DATE) {
        this.CREATED_DATE = CREATED_DATE;
    }
}
