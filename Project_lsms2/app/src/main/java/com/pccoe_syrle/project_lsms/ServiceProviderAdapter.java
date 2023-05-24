package com.pccoe_syrle.project_lsms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServiceProviderAdapter extends RecyclerView.Adapter<ServiceProviderAdapter.ServiceHolder>{

    List<ServiceProviderClass> mList;
    SharedPreferences sharedPreferences;

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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Show a confirmation dialogue
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setMessage("Do you want to buy this service?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ServiceProviderClass serviceProvider = mList.get(getAdapterPosition());


                                    Intent intent = new Intent(itemView.getContext(), ServiceDetailsActivity.class);
                                    itemView.getContext().startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
            name = itemView.findViewById(R.id.providerName);
            service = itemView.findViewById(R.id.service);
        }
    }
}
