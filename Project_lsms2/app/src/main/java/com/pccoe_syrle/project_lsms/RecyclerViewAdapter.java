package com.pccoe_syrle.project_lsms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    List<ModelClass> mList;

    public RecyclerViewAdapter(List<ModelClass> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_display_layout,parent,false);
        RecyclerViewAdapter.MyViewHolder vh = new RecyclerViewAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.names.setText(mList.get(position).getName());
        holder.imgs.setImageResource(mList.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView names;
        ImageView imgs;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            names = itemView.findViewById(R.id.Names);
            imgs = itemView.findViewById(R.id.imgs);
        }
    }
}
