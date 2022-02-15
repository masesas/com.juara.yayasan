package com.juara.yayasan.InputData;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.juara.yayasan.BaseActivity;
import com.juara.yayasan.Database.AppDatabase;
import com.juara.yayasan.Database.DataAnakDAO;
import com.juara.yayasan.Database.DataAnakEntity;
import com.juara.yayasan.R;
import com.juara.yayasan.Utils.ConstantsUtils;

import java.util.Objects;

import butterknife.ButterKnife;

public class input_data_anak extends BaseActivity {
    TextInputEditText nama_anak, nama_orangtua, jenkel_anak, date_anak, kota_anak;
    Button simpan;
    RadioGroup radioGroup;

    private DataAnakDAO dataAnakDAO;
    private String jenisKelamin = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_input_data_anak);
        setCollapseToolbar("Tambah Data Anak");

        AppDatabase database = AppDatabase.getInstance(this);
        dataAnakDAO = database.dataAnakDAO();

        nama_anak = findViewById(R.id.nama_anak);
        nama_orangtua = findViewById(R.id.nama_orangtua);
        radioGroup = findViewById(R.id.jenkel_anak);
        kota_anak = findViewById(R.id.asal_anak);

        radioGroup.setOnCheckedChangeListener((radioGroup, id) -> {
            switch (id) {
                case R.id.lk_anak:
                    jenisKelamin = "Laki - Laki";
                    break;
                case R.id.pr_anak:
                    jenisKelamin = "Perempuan";
                    break;
            }
        });

        find(R.id.et_tgl_lahir).setOnClickListener(v -> showDatePicker(find(R.id.et_tgl_lahir, EditText.class)));
        simpan = findViewById(R.id.bt_simpan_anak);
        simpan.setOnClickListener(v -> {
            if (TextUtils.isEmpty(nama_anak.getText().toString())) {
                errorFocusET(nama_anak, "Nama Anak Harus Di Isi");
            } else if (getEditText(nama_orangtua).isEmpty()) {
                errorFocusET(nama_orangtua, "Nama Orang Tua Harus di Isi");
            } else if (jenisKelamin.isEmpty()) {
                showWarning("Jenis Kelamin" + ConstantsUtils.HARUS_ISI);
            } else if (getEditText(find(R.id.et_tgl_lahir, EditText.class)).isEmpty()) {
                errorFocusET(find(R.id.et_tgl_lahir, EditText.class), "Tanggal Lahir Harus Di Isi");
            } else {
                addDataAnak();
            }
        });
    }

    private void addDataAnak() {
        DataAnakEntity dataAnakEntity = new DataAnakEntity();
        dataAnakEntity.setNAMA_ANAK(getEditText(nama_anak));
        dataAnakEntity.setASAL_KOTA(getEditText(kota_anak));
        dataAnakEntity.setJENIS_KELAMIN(jenisKelamin);
        dataAnakEntity.setNAMA_ORANG_TUA(getEditText(nama_orangtua));
        dataAnakEntity.setTANGGAL_LAHIR(getEditText(find(R.id.et_tgl_lahir, EditText.class)));

        dataAnakDAO.insertOne(dataAnakEntity);
        showSuccess("Berhasil Menambahkan Data Anak");
        setResult(RESULT_OK);
        finish();
    }
}

