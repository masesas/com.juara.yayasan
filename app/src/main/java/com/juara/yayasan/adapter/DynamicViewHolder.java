package com.juara.yayasan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DynamicViewHolder extends RecyclerView.ViewHolder {
    public View v;

    public DynamicViewHolder(@NonNull View itemView) {
        super(itemView);
        v = itemView;
    }

    public static DynamicViewHolder getInstance(ViewGroup viewParent, int view) {
        View v = LayoutInflater.from(viewParent.getContext()).inflate(view, viewParent, false);
        return new DynamicViewHolder(v);
    }

    public <T extends View> T find(int id) {
        return (T) v.findViewById(id);
    }

    public <T extends View> T find(int id, Class<? super T> s) {
        return (T) v.findViewById(id);
    }

    public <T extends View> T findView(View v, int id, Class<? super T> s) {
        return (T) v.findViewById(id);
    }
    public TextView findTextView(int viewID){
        return find(viewID, TextView.class);
    }


}
