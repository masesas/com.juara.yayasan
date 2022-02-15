package com.juara.yayasan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juara.yayasan.Database.DataAnakEntity;
import com.juara.yayasan.R;

import java.util.List;

public class DataAnakAdapter extends RecyclerView.Adapter<DataAnakAdapter.ViewHolder>{
    List<DataAnakEntity> dataAnakModelList;
    private Context context;

    public DataAnakAdapter(Context context1, List<DataAnakEntity> dataAnakModelList1){
        this.dataAnakModelList = dataAnakModelList1;
        this.context = context1;
    }

    @NonNull
    @Override
    public DataAnakAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_data_anak, parent, false);
        return new DataAnakAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAnakAdapter.ViewHolder holder, int position) {
        holder.tvNamaAnak.setText(dataAnakModelList.get(position).getNAMA_ANAK());
        holder.tvNamaOrangtua.setText(dataAnakModelList.get(position).getNAMA_ORANG_TUA());
        holder.tvJenKelAnak.setText(dataAnakModelList.get(position).getJENIS_KELAMIN());
        holder.tvTglLahir.setText(dataAnakModelList.get(position).getTANGGAL_LAHIR());
        holder.tvKotaAnak.setText(dataAnakModelList.get(position).getASAL_KOTA());
    }

    @Override
    public int getItemCount() {
        return dataAnakModelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaAnak;
        TextView tvNamaOrangtua;
        TextView tvJenKelAnak;
        TextView tvTglLahir;
        TextView tvKotaAnak;

        public ViewHolder(@NonNull View itemView){

            super(itemView);
            tvNamaAnak = itemView.findViewById(R.id.tv_nama_anak);
            tvNamaOrangtua = itemView.findViewById(R.id.tv_nama_orang_tua);
            tvJenKelAnak = itemView.findViewById(R.id.tv_jenis_kelamin);
            tvTglLahir = itemView.findViewById(R.id.tv_tgl_lahir);
            tvKotaAnak = itemView.findViewById(R.id.tv_asal_kota);
        }

    }
}
