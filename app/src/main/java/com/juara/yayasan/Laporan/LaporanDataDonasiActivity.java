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
import com.juara.yayasan.Database.LayananDAO;
import com.juara.yayasan.Database.LayananEntity;
import com.juara.yayasan.R;
import com.juara.yayasan.Utils.NumberFormatUtils;
import com.juara.yayasan.adapter.DynamicRecyclerViewAdapter;
import com.juara.yayasan.adapter.DynamicViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LaporanDataDonasiActivity extends BaseLaporanActivity {

        private List<LayananEntity> layananEntityList;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_data_donasi);
        setCollapseToolbar("Laporan Transaksi Donasi");

        reqPermission();
        initProgress();

        AppDatabase appDatabase = AppDatabase.getInstance(this);
        LayananDAO layananDAO = appDatabase.layananDAO();

        layananEntityList = layananDAO.getAll();
        if(layananEntityList.size() == 0){
            find(R.id.tv_info).setVisibility(View.VISIBLE);
            find(R.id.vg_table_data).setVisibility(View.GONE);
        }else{
            find(R.id.tv_info).setVisibility(View.GONE);
            find(R.id.vg_table_data).setVisibility(View.VISIBLE);
        }

        List<String> column = Arrays.asList("Nama Donatur", "No. Rek", "Nama Bank", "Jumlah", "Tanggal");
        initPdfUtil(generateData(), column, "Data Donasi");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new DynamicRecyclerViewAdapter<LayananEntity>(layananEntityList, R.layout.item_data_laporan_donasi){
            @Override
            public void onBindViewHolder(@NonNull DynamicViewHolder viewHolder, int position) {
                super.onBindViewHolder(viewHolder, position);
                viewHolder.findTextView(R.id.tv_nama_donatur).setText(layananEntityList.get(position).getNAMA_DONATUR());
                viewHolder.findTextView(R.id.tv_no_rek).setText(layananEntityList.get(position).getNO_REKENING());
                viewHolder.findTextView(R.id.tv_nama_bank).setText(layananEntityList.get(position).getNAMA_BANK());
                viewHolder.findTextView(R.id.tv_jumlah_donasi).setText(NumberFormatUtils.formatRp(String.valueOf(layananEntityList.get(position).getJUMLAH_DONASI())));
                viewHolder.findTextView(R.id.tv_tgl).setText(layananEntityList.get(position).getTANGGAL());
            }
        });
        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();

        findViewById(R.id.btn_generated_pdf).setOnClickListener(v -> {
            if (isAllowStoragePermission()) {
                reqPermission();
            } else {
                generatePDF();
            }
        });
    }

    private List<String[]> generateData() {
        List<String[]> data = new ArrayList<>();
        if (layananEntityList.size() > 0) {
            for (int i = 0; i < layananEntityList.size(); i++) {
                data.add(new String[]
                        {
                                layananEntityList.get(i).getNAMA_DONATUR(),
                                layananEntityList.get(i).getNO_REKENING(),
                                layananEntityList.get(i).getNAMA_BANK(),
                                NumberFormatUtils.formatRp(String.valueOf(layananEntityList.get(i).getJUMLAH_DONASI())),
                                layananEntityList.get(i).getTANGGAL()
                        }
                );
            }
        }
        return data;
    }
}