package com.juara.yayasan.InputData;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.juara.yayasan.BaseActivity;
import com.juara.yayasan.Database.AppDatabase;
import com.juara.yayasan.Database.DataPetugasDAO;
import com.juara.yayasan.Database.DataPetugasEntity;
import com.juara.yayasan.R;
import com.juara.yayasan.Utils.ConstantsUtils;
import com.juara.yayasan.Utils.NumberFormatUtils;

public class InputDataPetugasActivity extends BaseActivity {

    private String jenisKelamin;
    private DataPetugasDAO dataPetugasDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_petugas);
        setCollapseToolbar("Tambah Data Petugas");
        initComponent();

        AppDatabase database = AppDatabase.getInstance(this);
        dataPetugasDAO = database.dataPetugasDAO();
    }

    private void initComponent() {
        findEditText(R.id.et_jumlah_donasi).addTextChangedListener(NumberFormatUtils.rupiahTextWatcher(findEditText(R.id.et_jumlah_donasi)));
        find(R.id.jenkel, RadioGroup.class).setOnCheckedChangeListener((radioGroup, id) -> {
            switch (id) {
                case R.id.lk_anak:
                    jenisKelamin = "Laki - Laki";
                    break;
                case R.id.pr_anak:
                    jenisKelamin = "Perempuan";
                    break;
            }
        });
        find(R.id.btn_simpan).setOnClickListener(v -> {
            if (
                    textInputEditTextValue(R.id.et_nama_petugas).isEmpty() ||
                            textInputEditTextValue(R.id.et_alamat).isEmpty() ||
                            jenisKelamin.isEmpty() ||
                            textInputEditTextValue(R.id.et_jumlah_donasi).isEmpty() ||
                            textInputEditTextValue(R.id.et_alasan_jadi_petugas).isEmpty()
            ) {
                if (textInputEditTextValue(R.id.et_nama_petugas).isEmpty()) {
                    errorFocusET(findEditText(R.id.et_nama_petugas), "Nama Petugas" + ConstantsUtils.HARUS_ISI);
                }
                if (textInputEditTextValue(R.id.et_alamat).isEmpty()) {
                    errorFocusET(findEditText(R.id.et_alamat), "Nama Petugas" + ConstantsUtils.HARUS_ISI);
                }
                if (jenisKelamin.isEmpty()) {
                    showWarning("Jenis Kelamin" + ConstantsUtils.HARUS_ISI);
                }
                if (textInputEditTextValue(R.id.et_jumlah_donasi).isEmpty()) {
                    errorFocusET(findEditText(R.id.et_jumlah_donasi), "Nama Petugas" + ConstantsUtils.HARUS_ISI);
                }
                if (textInputEditTextValue(R.id.et_alasan_jadi_petugas).isEmpty()) {
                    errorFocusET(findEditText(R.id.et_alasan_jadi_petugas), "Nama Petugas" + ConstantsUtils.HARUS_ISI);
                }
            } else {
                saveData();
            }
        });
    }

    private void saveData() {
        DataPetugasEntity dataPetugasEntity = new DataPetugasEntity();
        dataPetugasEntity.setNAMA_PETUGAS(getEditText(find(R.id.et_nama_petugas, TextInputEditText.class)));
        dataPetugasEntity.setALAMAT(getEditText(find(R.id.et_alamat, TextInputEditText.class)));
        dataPetugasEntity.setJENIS_KELAMIN(jenisKelamin);
        dataPetugasEntity.setJUMLAH_DONASI(NumberFormatUtils.formatOnlyNumber(getEditText(find(R.id.et_jumlah_donasi, TextInputEditText.class))));
        dataPetugasEntity.setALASAN_JADI_PETUGAS(getEditText(find(R.id.et_alasan_jadi_petugas, TextInputEditText.class)));
        dataPetugasDAO.insertOne(dataPetugasEntity);

        showSuccess("Berhasil Menambahkan Data Petugas");
        setResult(RESULT_OK);
        finish();
    }
}