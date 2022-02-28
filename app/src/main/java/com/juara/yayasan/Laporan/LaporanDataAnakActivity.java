package com.juara.yayasan.Laporan;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.juara.yayasan.BaseActivity;
import com.juara.yayasan.Database.AppDatabase;
import com.juara.yayasan.Database.DataAnakDAO;
import com.juara.yayasan.Database.DataAnakEntity;
import com.juara.yayasan.R;
import com.juara.yayasan.Pdf.PDFUtility;
import com.juara.yayasan.adapter.DynamicRecyclerViewAdapter;
import com.juara.yayasan.adapter.DynamicViewHolder;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LaporanDataAnakActivity extends BaseLaporanActivity {


    private List<DataAnakEntity> dataAnakEntityList;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_data_anak);
        setCollapseToolbar("Laporan Data Anak");

        reqPermission();
        initProgress();

        AppDatabase appDatabase = AppDatabase.getInstance(this);
        DataAnakDAO dataAnakDAO = appDatabase.dataAnakDAO();

        dataAnakEntityList = dataAnakDAO.getAll();
        if (dataAnakEntityList.size() == 0) {
            find(R.id.tv_info).setVisibility(View.VISIBLE);
            find(R.id.vg_table_data).setVisibility(View.GONE);
        } else {
            find(R.id.tv_info).setVisibility(View.GONE);
            find(R.id.vg_table_data).setVisibility(View.VISIBLE);
        }

        List<String> column = Arrays.asList("Nama Anak", "Nama Orang Tua", "Jenis Kelamin", "Tanggal Lahir", "Asal Kota");
        initPdfUtil(generateData(), column, "Data Anak");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new DynamicRecyclerViewAdapter<DataAnakEntity>(dataAnakEntityList, R.layout.item_data_laporan_anak) {
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
        if (dataAnakEntityList.size() > 0) {
            for (int i = 0; i < dataAnakEntityList.size(); i++) {
                data.add(new String[]
                        {
                                dataAnakEntityList.get(i).getNAMA_ANAK(),
                                dataAnakEntityList.get(i).getNAMA_ORANG_TUA(),
                                dataAnakEntityList.get(i).getJENIS_KELAMIN(),
                                dataAnakEntityList.get(i).getTANGGAL_LAHIR(),
                                dataAnakEntityList.get(i).getASAL_KOTA()
                        }
                );
            }
        }
        return data;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}