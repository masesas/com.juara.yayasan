package com.juara.yayasan.Laporan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.juara.yayasan.BaseActivity;
import com.juara.yayasan.Database.AppDatabase;
import com.juara.yayasan.Database.DataAnakDAO;
import com.juara.yayasan.Database.DataAnakEntity;
import com.juara.yayasan.R;
import com.juara.yayasan.adapter.DynamicRecyclerViewAdapter;
import com.juara.yayasan.adapter.DynamicViewHolder;

import java.util.List;
import java.util.Objects;

public class LaporanDataDonasiActivity extends BaseActivity {

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_data_donasi);
        setCollapseToolbar("Laporan Transaksi Donasi");

        AppDatabase appDatabase = AppDatabase.getInstance(this);
        DataAnakDAO dataAnakDAO = appDatabase.dataAnakDAO();

        List<DataAnakEntity> dataAnakEntityList = dataAnakDAO.getAll();
        if(dataAnakEntityList.size() == 0){
            find(R.id.tv_info).setVisibility(View.VISIBLE);
            find(R.id.vg_table_data).setVisibility(View.GONE);
        }else{
            find(R.id.tv_info).setVisibility(View.GONE);
            find(R.id.vg_table_data).setVisibility(View.VISIBLE);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new DynamicRecyclerViewAdapter<DataAnakEntity>(dataAnakEntityList, R.layout.item_data_laporan_anak){
            @Override
            public void onBindViewHolder(@NonNull DynamicViewHolder viewHolder, int position) {
                super.onBindViewHolder(viewHolder, position);
                viewHolder.findTextView(R.id.tv_nama_anak).setText(dataAnakEntityList.get(position).getNAMA_ANAK());
                viewHolder.findTextView(R.id.tv_nama_orang_tua).setText(dataAnakEntityList.get(position).getNAMA_ORANG_TUA());
                viewHolder.findTextView(R.id.tv_jenis_kelamin).setText(dataAnakEntityList.get(position).getJENIS_KELAMIN());
                viewHolder.findTextView(R.id.tv_tgl_lahir).setText(dataAnakEntityList.get(position).getTANGGAL_LAHIR());
                viewHolder.findTextView(R.id.tv_asal_kota).setText(dataAnakEntityList.get(position).getASAL_KOTA());
            }
        });
        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
    }
}