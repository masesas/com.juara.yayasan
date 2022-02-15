package com.juara.yayasan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.juara.yayasan.Daftar.DaftarLayanan;
import com.juara.yayasan.Menu.masterdata;


public class MainActivity extends AppCompatActivity {
    CardView master, layanan, program, laporan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        master = findViewById(R.id.cv_master_data);
        layanan = findViewById(R.id.cv_layanan);
        program = findViewById(R.id.cv_program);
        laporan = findViewById(R.id.cv_laporan);

        initlistener();
    }

    private void initlistener() {
        master.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, masterdata.class);
                startActivity(intent);
            }
        });

        layanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, DaftarLayanan.class);
                startActivity(intent);
            }
        });

        program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.juara.yayasan.Menu.program.class);
                startActivity(intent);
            }
        });

        laporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.juara.yayasan.Menu.laporan.class);
                startActivity(intent);

            }
        });
    }
}