package com.pccoe_syrle.project_lsms.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pccoe_syrle.project_lsms.ModelClass;
import com.pccoe_syrle.project_lsms.R;
import com.pccoe_syrle.project_lsms.RecyclerViewAdapter;
import com.pccoe_syrle.project_lsms.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    EditText mSearch;
    RecyclerView displayServices;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        displayServices = binding.ServicesView;
        displayServices.setHasFixedSize(true);
        ArrayList<ModelClass> listitem = new ArrayList<ModelClass>();

        listitem.add(new ModelClass("Hello","3", R.drawable.baseline_person_outline_24));
        listitem.add(new ModelClass("Bello","2",R.drawable.baseline_person_outline_24));
        listitem.add(new ModelClass("Mello","1",R.drawable.baseline_person_outline_24));
        listitem.add(new ModelClass("Khelo","0",R.drawable.baseline_person_outline_24));

        LinearLayoutManager llm = new LinearLayoutManager(root.getContext(),LinearLayoutManager.HORIZONTAL,false);
        displayServices.setLayoutManager(llm);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(listitem);
        displayServices.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}