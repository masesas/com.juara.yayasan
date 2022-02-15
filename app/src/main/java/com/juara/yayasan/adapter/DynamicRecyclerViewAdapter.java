package com.juara.yayasan.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class DynamicRecyclerViewAdapter<T> extends RecyclerView.Adapter<DynamicViewHolder> {
    List<T> data;
    int rlayout;

    public DynamicRecyclerViewAdapter(List<T> data, int rlayout) {
        this.data = data;
        this.rlayout = rlayout;
    }

    public List<T> getItem() {
        return data;
    }

    @Override
    public final int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public DynamicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        DynamicViewHolder nikitaViewHolder = DynamicViewHolder.getInstance(viewGroup, rlayout);
        if (onitemClickListener != null) {
            nikitaViewHolder.itemView.setTag(String.valueOf(position));
            nikitaViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (onitemClickListener != null) {
                        onitemClickListener.onItemClick(data, v, Integer.parseInt(String.valueOf(v.getTag())));
                    }
                }
            });
        }
        return nikitaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DynamicViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private OnItemClickListener<T> onitemClickListener;

    public DynamicRecyclerViewAdapter<T> setOnitemClickListener(OnItemClickListener<T> onitemClickListener) {
        this.onitemClickListener = onitemClickListener;
        return this;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(List<T> parent, View view, int position);
    }
}
