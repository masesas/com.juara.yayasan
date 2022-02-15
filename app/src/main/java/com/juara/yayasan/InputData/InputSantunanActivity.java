package com.juara.yayasan.InputData;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.juara.yayasan.BaseActivity;
import com.juara.yayasan.Database.AppDatabase;
import com.juara.yayasan.Database.BukaBersamaEntity;
import com.juara.yayasan.Database.SantunanDAO;
import com.juara.yayasan.Database.SantunanEntity;
import com.juara.yayasan.R;
import com.juara.yayasan.Utils.ConstantsUtils;

public class InputSantunanActivity extends BaseActivity {

    private SantunanDAO santunanDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_santunan);
        setCollapseToolbar("Tambah Data Santunan");

        AppDatabase database = AppDatabase.getInstance(this);
        santunanDAO = database.santunanDAO();
        initComponents();
    }

    private void initComponents() {
        find(R.id.et_tgl).setOnClickListener(v -> showDatePicker(findEditText(R.id.et_tgl)));
        find(R.id.btn_simpan).setOnClickListener(v -> {
            if (
                    textInputEditTextValue(R.id.et_nama_donatur).isEmpty() ||
                            textInputEditTextValue(R.id.et_lokasi_acara).isEmpty() ||
                            textInputEditTextValue(R.id.et_tgl).isEmpty()
            ) {
                if (textInputEditTextValue(R.id.et_nama_donatur).isEmpty()) {
                    errorFocusET(findEditText(R.id.et_nama_donatur), "Nama Donatur" + ConstantsUtils.HARUS_ISI);
                }
                if (textInputEditTextValue(R.id.et_lokasi_acara).isEmpty()) {
                    errorFocusET(findEditText(R.id.et_lokasi_acara), "Lokasi Acara" + ConstantsUtils.HARUS_ISI);
                }
                if (textInputEditTextValue(R.id.et_tgl).isEmpty()) {
                    errorFocusET(findEditText(R.id.et_tgl), "Tanggal" + ConstantsUtils.HARUS_ISI);
                }
            }else{
                saveData();
            }
        });
    }

    private void saveData() {
        SantunanEntity santunanEntity = new SantunanEntity();
        santunanEntity.setNAMA_DONATUR(textInputEditTextValue(R.id.et_nama_donatur));
        santunanEntity.setLOKASI_ACARA(textInputEditTextValue(R.id.et_lokasi_acara));
        santunanEntity.setTANGGAL(textInputEditTextValue(R.id.et_tgl));
        santunanDAO.insertOne(santunanEntity);

        showSuccess("Berhasil Menambahkan Data Santunan");
        setResult(RESULT_OK);
        finish();
    }
}