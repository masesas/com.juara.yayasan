package com.juara.yayasan.Menu;

import android.content.Intent;
import android.os.Bundle;

import com.juara.yayasan.BaseActivity;
import com.juara.yayasan.Daftar.DaftarDataAnakActivity;
import com.juara.yayasan.Daftar.DaftarPetugasActivity;
import com.juara.yayasan.R;

public class masterdata extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masterdata);
        setToolbarJul("Master Data");
        initListener();
    }

    private void initListener() {
        find(R.id.cv_data_anak).setOnClickListener(v -> {
            Intent intent = new Intent(masterdata.this, DaftarDataAnakActivity.class);
            startActivity(intent);
        });

        find(R.id.cv_data_petugas).setOnClickListener(v -> {
            Intent intent = new Intent(masterdata.this, DaftarPetugasActivity.class);
            startActivity(intent);
        });
    }
}