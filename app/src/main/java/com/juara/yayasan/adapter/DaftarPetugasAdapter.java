package com.juara.yayasan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juara.yayasan.Database.DataAnakEntity;
import com.juara.yayasan.Database.DataPetugasEntity;
import com.juara.yayasan.R;
import com.juara.yayasan.Utils.NumberFormatUtils;

import java.util.List;

public class DaftarPetugasAdapter extends RecyclerView.Adapter<DaftarPetugasAdapter.ViewHolder>{
    List<DataPetugasEntity> dataPetugasEntities;
    private Context context;

    public DaftarPetugasAdapter(Context context1, List<DataPetugasEntity> dataPetugasEntities){
        this.dataPetugasEntities = dataPetugasEntities;
        this.context = context1;
    }

    @NonNull
    @Override
    public DaftarPetugasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_data_petugas, parent, false);
        return new DaftarPetugasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarPetugasAdapter.ViewHolder holder, int position) {
        holder.tvNamaPetugas.setText(dataPetugasEntities.get(position).getNAMA_PETUGAS());
        holder.tvAlamat.setText(dataPetugasEntities.get(position).getALAMAT());
        holder.tvAlasanPetugas.setText(dataPetugasEntities.get(position).getALASAN_JADI_PETUGAS());
        holder.tvJenisKelamin.setText(dataPetugasEntities.get(position).getJENIS_KELAMIN());
        holder.tvJumlahDonasi.setText(NumberFormatUtils.formatRp(dataPetugasEntities.get(position).getJUMLAH_DONASI()));
    }

    @Override
    public int getItemCount() {
        return dataPetugasEntities.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaPetugas;
        TextView tvAlamat;
        TextView tvAlasanPetugas;
        TextView tvJenisKelamin;
        TextView tvJumlahDonasi;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvNamaPetugas = itemView.findViewById(R.id.tv_nama_petugas);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvAlasanPetugas = itemView.findViewById(R.id.tv_alasan_jadi_petugas);
            tvJenisKelamin = itemView.findViewById(R.id.tv_jenis_kelamin);
            tvJumlahDonasi = itemView.findViewById(R.id.tv_jumlah_donasi);
        }

    }
}
