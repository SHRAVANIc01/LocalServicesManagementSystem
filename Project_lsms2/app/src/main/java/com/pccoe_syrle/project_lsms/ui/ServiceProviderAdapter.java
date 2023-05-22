package com.pccoe_syrle.project_lsms.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pccoe_syrle.project_lsms.R;
import com.pccoe_syrle.project_lsms.RecyclerViewAdapter;
import com.pccoe_syrle.project_lsms.ServiceProviderClass;

import java.util.List;

public class ServiceProviderAdapter extends RecyclerView.Adapter<ServiceProviderAdapter.ServiceHolder>{

    List<ServiceProviderClass> mList;

    public ServiceProviderAdapter(List<ServiceProviderClass> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public ServiceProviderAdapter.ServiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_providers,parent,false);
        ServiceProviderAdapter.ServiceHolder vh = new ServiceProviderAdapter.ServiceHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceProviderAdapter.ServiceHolder holder, int position) {
        holder.name.setText(mList.get(position).getName());
        holder.service.setText(mList.get(position).getService());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ServiceHolder extends RecyclerView.ViewHolder{
        TextView name, service;

        public ServiceHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.providerName);
            service = itemView.findViewById(R.id.service);
        }
    }
}
