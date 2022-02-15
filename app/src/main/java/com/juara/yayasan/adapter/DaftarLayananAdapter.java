package com.juara.yayasan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juara.yayasan.Database.LayananEntity;
import com.juara.yayasan.R;
import com.juara.yayasan.Utils.NumberFormatUtils;

import java.util.List;

public class DaftarLayananAdapter extends RecyclerView.Adapter<DaftarLayananAdapter.ViewHolder>{
    List<LayananEntity> layananModelList;
    private Context context;

    public DaftarLayananAdapter(Context context1, List<LayananEntity> layananModelList1){
        this.layananModelList = layananModelList1;
        this.context = context1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_daftar_layanan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNamaDonatur.setText( layananModelList.get(position).getNAMA_DONATUR());
        holder.tvNoRekening.setText(layananModelList.get(position).getNO_REKENING());
        holder.tvNamaBank.setText(layananModelList.get(position).getNAMA_BANK());
        holder.tvJumlahDonasi.setText(NumberFormatUtils.formatRp(String.valueOf(layananModelList.get(position).getJUMLAH_DONASI())));
        holder.tvTanggal.setText(layananModelList.get(position).getTANGGAL());
    }

    @Override
    public int getItemCount() {
        return layananModelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaDonatur;
        TextView tvNoRekening;
        TextView tvNamaBank;
        TextView tvJumlahDonasi;
        TextView tvTanggal;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            tvNamaDonatur = itemView.findViewById(R.id.Donatur);
            tvNoRekening = itemView.findViewById(R.id.NoRekening);
            tvNamaBank = itemView.findViewById(R.id.NamaBank);
            tvJumlahDonasi = itemView.findViewById(R.id.JumlahDonasi);
            tvTanggal = itemView.findViewById(R.id.Tanggal);
        }


    }
}
