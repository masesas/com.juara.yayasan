package com.juara.yayasan.Daftar;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.juara.yayasan.BaseActivity;
import com.juara.yayasan.Database.AppDatabase;
import com.juara.yayasan.Database.LayananDAO;
import com.juara.yayasan.Database.LayananEntity;
import com.juara.yayasan.InputData.input_layanan;
import com.juara.yayasan.R;
import com.juara.yayasan.adapter.DaftarLayananAdapter;

import java.util.ArrayList;
import java.util.List;

public class DaftarLayanan extends BaseActivity {

    List<LayananEntity> layananEntityList = new ArrayList<>();
    RecyclerView recyclerView;
    DaftarLayananAdapter daftarLayananAdapter;

    private LayananDAO layananDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_common_list);
        setCollapseToolbar("Daftar Layanan");

        AppDatabase appDatabase = AppDatabase.getInstance(this);
        layananDAO = appDatabase.layananDAO();

        recyclerView = findViewById(R.id.recyclerView);

        setData();

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_add);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(DaftarLayanan.this, input_layanan.class);
            startActivityForResult(intent, 1);
        });
    }

    private void setData(){
        layananEntityList = layananDAO.getAll();
        daftarLayananAdapter = new DaftarLayananAdapter(this, layananEntityList);
        daftarLayananAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(daftarLayananAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            setData();
        }
    }

}


















