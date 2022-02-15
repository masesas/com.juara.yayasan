package com.juara.yayasan.Daftar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.juara.yayasan.BaseActivity;
import com.juara.yayasan.Database.AppDatabase;
import com.juara.yayasan.Database.BukaBersamaDAO;
import com.juara.yayasan.Database.BukaBersamaEntity;
import com.juara.yayasan.Database.DataPetugasEntity;
import com.juara.yayasan.InputData.InputBukaBersamaActivity;
import com.juara.yayasan.InputData.InputDataPetugasActivity;
import com.juara.yayasan.R;
import com.juara.yayasan.adapter.DaftarPetugasAdapter;
import com.juara.yayasan.adapter.DynamicRecyclerViewAdapter;
import com.juara.yayasan.adapter.DynamicViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DaftarBukaBersamaActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private BukaBersamaDAO bukaBersamaDAO;
    private List<BukaBersamaEntity> bukaBersamaEntityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_common_list);
        setCollapseToolbar("Daftar Buka Bersama");

        AppDatabase appDatabase = AppDatabase.getInstance(this);
        bukaBersamaDAO = appDatabase.bukaBersamaDAO();
        bukaBersamaEntityList = bukaBersamaDAO.getAll();
        recyclerView = findViewById(R.id.recyclerView);

        initRv();
        notifyData();

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_add);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, InputBukaBersamaActivity.class);
            startActivityForResult(intent, 1);

        });
    }

    private void initRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new DynamicRecyclerViewAdapter<BukaBersamaEntity>(bukaBersamaEntityList, R.layout.item_program) {
            @Override
            public void onBindViewHolder(@NonNull DynamicViewHolder viewHolder, int position) {
                super.onBindViewHolder(viewHolder, position);
                viewHolder.findTextView(R.id.tv_nama_donatur).setText(bukaBersamaEntityList.get(position).getNAMA_DONATUR());
                viewHolder.findTextView(R.id.tv_lokasi_acara).setText(bukaBersamaEntityList.get(position).getLOKASI_ACARA());
                viewHolder.findTextView(R.id.tv_tgl).setText(bukaBersamaEntityList.get(position).getTANGGAL());
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void notifyData(){
        bukaBersamaEntityList = bukaBersamaDAO.getAll();
        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            notifyData();
            initRv();
        }
    }
}