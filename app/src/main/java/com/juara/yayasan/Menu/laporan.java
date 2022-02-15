package com.juara.yayasan.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import com.juara.yayasan.BaseActivity;
import com.juara.yayasan.Daftar.DaftarBukaBersamaActivity;
import com.juara.yayasan.Daftar.DaftarSantunanActivity;
import com.juara.yayasan.Laporan.LaporanDataAnakActivity;
import com.juara.yayasan.Laporan.LaporanDataDonasiActivity;
import com.juara.yayasan.Laporan.LaporanDataProgram;
import com.juara.yayasan.R;

import java.util.Objects;

public class laporan extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);
        setCollapseToolbar("Laporan");

        find(R.id.cv_lap_data_anak).setOnClickListener(v ->
                startActivity(new Intent(this, LaporanDataAnakActivity.class)));
        find(R.id.cv_lap_data_donasi).setOnClickListener(v ->
                startActivity(new Intent(this, LaporanDataDonasiActivity.class)));
        find(R.id.cv_lap_data_program).setOnClickListener(v ->
                startActivity(new Intent(this, LaporanDataProgram.class)));

    }

}