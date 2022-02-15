package com.juara.yayasan.Daftar;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.juara.yayasan.BaseActivity;
import com.juara.yayasan.Database.AppDatabase;
import com.juara.yayasan.Database.DataAnakDAO;
import com.juara.yayasan.Database.DataAnakEntity;
import com.juara.yayasan.InputData.input_data_anak;
import com.juara.yayasan.R;
import com.juara.yayasan.adapter.DataAnakAdapter;

import java.util.ArrayList;
import java.util.List;

public class DaftarDataAnakActivity extends BaseActivity {

    List<DataAnakEntity> dataAnakModelList = new ArrayList<>();
    RecyclerView recyclerView;
    DataAnakAdapter dataAnakAdapter;

    private DataAnakDAO dataAnakDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_data_anak);
        AppDatabase appDatabase = AppDatabase.getInstance(this);

        recyclerView = findViewById(R.id.recyclerViewDataAnak);
        dataAnakDAO = appDatabase.dataAnakDAO();

        setCollapseToolbar("Daftar Data Anak");
        getDataAnak();

        FloatingActionButton floatingActionButton = findViewById(R.id.add_anak);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(DaftarDataAnakActivity.this, input_data_anak.class);
            startActivityForResult(intent, 1);

        });
    }

    private void getDataAnak() {
        try {
            dataAnakModelList = dataAnakDAO.getAll();
            dataAnakAdapter = new DataAnakAdapter(this, dataAnakModelList);
            dataAnakAdapter.notifyDataSetChanged();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(dataAnakAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            getDataAnak();
        }
    }


}
