package com.juara.yayasan.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BukaBersamaEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int ID;

    @ColumnInfo(name = "NAMA_DONATUR")
    private String NAMA_DONATUR;

    @ColumnInfo(name = "LOKASI_ACARA")
    private String LOKASI_ACARA;

    @ColumnInfo(name = "TANGGAL")
    private String TANGGAL;

    public BukaBersamaEntity() {
    }

    public BukaBersamaEntity(String NAMA_DONATUR, String LOKASI_ACARA, String TANGGAL) {
        this.NAMA_DONATUR = NAMA_DONATUR;
        this.LOKASI_ACARA = LOKASI_ACARA;
        this.TANGGAL = TANGGAL;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAMA_DONATUR() {
        return NAMA_DONATUR;
    }

    public void setNAMA_DONATUR(String NAMA_DONATUR) {
        this.NAMA_DONATUR = NAMA_DONATUR;
    }

    public String getLOKASI_ACARA() {
        return LOKASI_ACARA;
    }

    public void setLOKASI_ACARA(String LOKASI_ACARA) {
        this.LOKASI_ACARA = LOKASI_ACARA;
    }

    public String getTANGGAL() {
        return TANGGAL;
    }

    public void setTANGGAL(String TANGGAL) {
        this.TANGGAL = TANGGAL;
    }
}
