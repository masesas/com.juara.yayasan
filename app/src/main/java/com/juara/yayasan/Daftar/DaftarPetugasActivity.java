package com.juara.yayasan.Daftar;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.juara.yayasan.BaseActivity;
import com.juara.yayasan.Database.AppDatabase;
import com.juara.yayasan.Database.DataPetugasDAO;
import com.juara.yayasan.Database.DataPetugasEntity;
import com.juara.yayasan.InputData.InputDataPetugasActivity;
import com.juara.yayasan.R;
import com.juara.yayasan.adapter.DaftarPetugasAdapter;

import java.util.List;

public class DaftarPetugasActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private DataPetugasDAO dataPetugasDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_common_list);

        AppDatabase appDatabase = AppDatabase.getInstance(this);

        recyclerView = findViewById(R.id.recyclerView);
        dataPetugasDAO = appDatabase.dataPetugasDAO();

        setCollapseToolbar("Daftar Data Petugas");
        getData();

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_add);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, InputDataPetugasActivity.class);
            startActivityForResult(intent, 1);

        });
    }

    private void getData() {
        try {
            List<DataPetugasEntity> dataPetugasEntities = dataPetugasDAO.getAll();
            DaftarPetugasAdapter daftarPetugasAdapter = new DaftarPetugasAdapter(this, dataPetugasEntities);
            daftarPetugasAdapter.notifyDataSetChanged();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(daftarPetugasAdapter);
        } catch (Exception e) {
            showError(e.getMessage());
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            getData();
        }
    }
}