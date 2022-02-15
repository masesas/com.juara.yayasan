package com.juara.yayasan.InputData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewUtils;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.juara.yayasan.BaseActivity;
import com.juara.yayasan.Database.AppDatabase;
import com.juara.yayasan.Database.LayananDAO;
import com.juara.yayasan.Database.LayananEntity;
import com.juara.yayasan.R;
import com.juara.yayasan.Utils.NumberFormatUtils;

import java.util.Objects;

public class input_layanan extends BaseActivity {
    TextInputEditText nama_donatur, norek, nama_bank, jumlah;
    EditText etTanggal;
    Button simpan;
    Button tampil;

    private LayananDAO layananDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_layanan);
        setCollapseToolbar("Tambah Layanan");

        AppDatabase appDatabase = AppDatabase.getInstance(this);
        layananDAO = appDatabase.layananDAO();

        initComponent();
    }

    private void initComponent() {
        nama_donatur = findViewById(R.id.input_nama);
        norek = findViewById(R.id.input_rekening);
        nama_bank = findViewById(R.id.input_bank);
        jumlah = findViewById(R.id.input_jumlah);
        etTanggal = findViewById(R.id.input_tanggal);
        simpan = findViewById(R.id.bt_input_donasi);

        jumlah.addTextChangedListener(NumberFormatUtils.rupiahTextWatcher(jumlah));
        etTanggal.setOnClickListener(v -> showDatePicker(etTanggal));
        simpan.setOnClickListener(v -> {
            if (TextUtils.isEmpty(Objects.requireNonNull(nama_donatur.getText()).toString())) {
                errorFocusET(nama_donatur, "Nama Harus Di Isi");
            } else if (TextUtils.isEmpty(Objects.requireNonNull(jumlah.getText()).toString())) {
                errorFocusET(jumlah, "Jumlah Donasi Harus Di Isi");
            } else if (TextUtils.isEmpty(Objects.requireNonNull(norek.getText()).toString())) {
                errorFocusET(norek, "No. Rekening Harus Di Isi");
            } else {
                addLayanan();
            }
        });
    }

    private void addLayanan() {
        LayananEntity layananEntity = new LayananEntity();
        layananEntity.setNAMA_DONATUR(Objects.requireNonNull(nama_donatur.getText()).toString());
        layananEntity.setJUMLAH_DONASI(Integer.parseInt(NumberFormatUtils.formatOnlyNumber(Objects.requireNonNull(jumlah.getText()).toString())));
        layananEntity.setNAMA_BANK(Objects.requireNonNull(nama_bank.getText()).toString());
        layananEntity.setTANGGAL(Objects.requireNonNull(etTanggal.getText()).toString());
        layananEntity.setNO_REKENING(Objects.requireNonNull(norek.getText()).toString());
        layananDAO.insertOne(layananEntity);

        showSuccess("Berhasil Menambahkan Layanan");
        setResult(RESULT_OK);
        finish();
    }
}