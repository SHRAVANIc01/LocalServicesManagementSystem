package com.pccoe_syrle.project_lsms;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SettingsArrayAdapter extends ArrayAdapter<ModelClass2> {

    public SettingsArrayAdapter(@NonNull Context context, ArrayList<ModelClass2> mc) {
        super(context, 0, mc);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.settings_array_layout, parent, false);
        }

        ModelClass2 mitem = getItem(position);

        ImageView imageView = convertView.findViewById(R.id.imgset);
        TextView textView = convertView.findViewById(R.id.nameset);

        if(mitem != null){
            imageView.setImageResource(mitem.getImg());
            textView.setText(mitem.getName());
        }

        return convertView;
    }
}
