package com.juara.yayasan.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import com.juara.yayasan.BaseActivity;
import com.juara.yayasan.Daftar.DaftarBukaBersamaActivity;
import com.juara.yayasan.Daftar.DaftarSantunanActivity;
import com.juara.yayasan.R;

import java.util.Objects;

public class program extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_program);
        setToolbarJul("Program");

        find(R.id.cv_santunan).setOnClickListener(v ->
                startActivity(new Intent(this, DaftarSantunanActivity.class)));
        find(R.id.cv_buka_bersama).setOnClickListener(v ->
                startActivity(new Intent(this, DaftarBukaBersamaActivity.class)));

    }

}