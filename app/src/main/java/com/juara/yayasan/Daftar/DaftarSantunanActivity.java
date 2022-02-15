package com.juara.yayasan.Daftar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.juara.yayasan.BaseActivity;
import com.juara.yayasan.Database.AppDatabase;
import com.juara.yayasan.Database.SantunanDAO;
import com.juara.yayasan.Database.SantunanEntity;
import com.juara.yayasan.InputData.InputSantunanActivity;
import com.juara.yayasan.R;
import com.juara.yayasan.adapter.DynamicRecyclerViewAdapter;
import com.juara.yayasan.adapter.DynamicViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DaftarSantunanActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private SantunanDAO santunanDAO;
    private List<SantunanEntity> santunanEntityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_common_list);
        setCollapseToolbar("Daftar Santunan");

        AppDatabase appDatabase = AppDatabase.getInstance(this);
        santunanDAO = appDatabase.santunanDAO();
        santunanEntityList = santunanDAO.getAll();
        recyclerView = findViewById(R.id.recyclerView);

        initRv();
        notifyData();

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_add);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, InputSantunanActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    private void initRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new DynamicRecyclerViewAdapter<SantunanEntity>(santunanEntityList, R.layout.item_program) {
            @Override
            public void onBindViewHolder(@NonNull DynamicViewHolder viewHolder, int position) {
                super.onBindViewHolder(viewHolder, position);
                viewHolder.findTextView(R.id.tv_nama_donatur).setText(santunanEntityList.get(position).getNAMA_DONATUR());
                viewHolder.findTextView(R.id.tv_lokasi_acara).setText(santunanEntityList.get(position).getLOKASI_ACARA());
                viewHolder.findTextView(R.id.tv_tgl).setText(santunanEntityList.get(position).getTANGGAL());
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void notifyData(){
        santunanEntityList = santunanDAO.getAll();
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