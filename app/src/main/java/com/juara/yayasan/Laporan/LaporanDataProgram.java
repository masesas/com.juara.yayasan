package com.juara.yayasan.Laporan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.juara.yayasan.BaseActivity;
import com.juara.yayasan.Database.AppDatabase;
import com.juara.yayasan.Database.BukaBersamaDAO;
import com.juara.yayasan.Database.BukaBersamaEntity;
import com.juara.yayasan.Database.DataAnakDAO;
import com.juara.yayasan.Database.DataAnakEntity;
import com.juara.yayasan.Database.SantunanDAO;
import com.juara.yayasan.Database.SantunanEntity;
import com.juara.yayasan.R;
import com.juara.yayasan.adapter.DynamicRecyclerViewAdapter;
import com.juara.yayasan.adapter.DynamicViewHolder;

import java.util.List;
import java.util.Objects;

public class LaporanDataProgram extends BaseActivity {

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_data_program);
        setCollapseToolbar("Laporan Data Program");

        AppDatabase appDatabase = AppDatabase.getInstance(this);
        BukaBersamaDAO bukaBersamaDAO = appDatabase.bukaBersamaDAO();
        SantunanDAO santunanDAO = appDatabase.santunanDAO();

        List<BukaBersamaEntity> bukaBersamaEntityList = bukaBersamaDAO.getAll();
        List<SantunanEntity> santunanEntityList = santunanDAO.getAll();
        if (santunanEntityList.size() > 0) {
            for (int i = 0; i < santunanEntityList.size(); i++) {
                bukaBersamaEntityList.add(new BukaBersamaEntity(
                        santunanEntityList.get(i).getNAMA_DONATUR(),
                        santunanEntityList.get(i).getLOKASI_ACARA(),
                        santunanEntityList.get(i).getTANGGAL())
                );
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new DynamicRecyclerViewAdapter<BukaBersamaEntity>(bukaBersamaEntityList, R.layout.item_data_laporan_program) {
            @Override
            public void onBindViewHolder(@NonNull DynamicViewHolder viewHolder, int position) {
                super.onBindViewHolder(viewHolder, position);
                viewHolder.findTextView(R.id.tv_nama_donatur).setText(bukaBersamaEntityList.get(position).getNAMA_DONATUR());
                viewHolder.findTextView(R.id.tv_lokasi_acara).setText(bukaBersamaEntityList.get(position).getLOKASI_ACARA());
                viewHolder.findTextView(R.id.tv_tgl).setText(bukaBersamaEntityList.get(position).getTANGGAL());
            }
        });

        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
    }
}