package com.pccoe_syrle.project_lsms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.pccoe_syrle.project_lsms.ModelClass;
import com.pccoe_syrle.project_lsms.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity
{
  RecyclerView serviceView;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_home);
    serviceView = findViewById(R.id.Services_view);
    serviceView.setHasFixedSize(true);

    ArrayList<ModelClass> listitem = new ArrayList<ModelClass>();

    listitem.add(new ModelClass("Hello","3",R.drawable.baseline_person_outline_24));
    listitem.add(new ModelClass("Bello","2",R.drawable.baseline_person_outline_24));
    listitem.add(new ModelClass("Mello","1",R.drawable.baseline_person_outline_24));
    listitem.add(new ModelClass("Khelo","0",R.drawable.baseline_person_outline_24));

    LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
    serviceView.setLayoutManager(llm);
    recyclerAdapter adapter = new recyclerAdapter(listitem);
    serviceView.setAdapter(adapter);
  }

  class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder>{
    List<ModelClass> mList;

    public recyclerAdapter(List<ModelClass> list)
    {
      mList = list;
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
      View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_display_layout,parent,false);
      recyclerAdapter.MyViewHolder vh = new recyclerAdapter.MyViewHolder(v);
      return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position)
    {
      holder.names.setText(mList.get(position).getName());
      holder.ids.setText(mList.get(position).getId());
//      Picasso.with(getApplicationContext()).load(mList.get(position).getImg()).into(holder.imgs);
    }

    @Override
    public int getItemCount()
    {
      return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
      TextView names, ids;
      ImageView imgs;
      public MyViewHolder(@NonNull View itemView)
      {
        super(itemView);
        names = itemView.findViewById(R.id.Names);
        ids = itemView.findViewById(R.id.ids);
        imgs = itemView.findViewById(R.id.imgs);
      }
    }
  }
}