package com.juara.yayasan.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LayananEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int ID;

    @ColumnInfo(name = "NAMA_DONATUR")
    private String NAMA_DONATUR;
    @ColumnInfo(name = "NO_REKENING")
    private String NO_REKENING;
    @ColumnInfo(name = "NAMA_BANK")
    private String NAMA_BANK;
    @ColumnInfo(name = "JUMLAH_DONASI")
    private int JUMLAH_DONASI;
    @ColumnInfo(name = "TANGGAL")
    private String TANGGAL;

    public LayananEntity() {
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

    public String getNO_REKENING() {
        return NO_REKENING;
    }

    public void setNO_REKENING(String NO_REKENING) {
        this.NO_REKENING = NO_REKENING;
    }

    public String getNAMA_BANK() {
        return NAMA_BANK;
    }

    public void setNAMA_BANK(String NAMA_BANK) {
        this.NAMA_BANK = NAMA_BANK;
    }

    public int getJUMLAH_DONASI() {
        return JUMLAH_DONASI;
    }

    public void setJUMLAH_DONASI(int JUMLAH_DONASI) {
        this.JUMLAH_DONASI = JUMLAH_DONASI;
    }

    public String getTANGGAL() {
        return TANGGAL;
    }

    public void setTANGGAL(String TANGGAL) {
        this.TANGGAL = TANGGAL;
    }
}
