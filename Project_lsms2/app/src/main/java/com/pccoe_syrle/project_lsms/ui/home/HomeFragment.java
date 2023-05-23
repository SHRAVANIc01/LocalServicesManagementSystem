package com.pccoe_syrle.project_lsms.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pccoe_syrle.project_lsms.DBhelper.DBhelperService;
import com.pccoe_syrle.project_lsms.ModelClass;
import com.pccoe_syrle.project_lsms.R;
import com.pccoe_syrle.project_lsms.RecyclerViewAdapter;
import com.pccoe_syrle.project_lsms.ServiceProviderAdapter;
import com.pccoe_syrle.project_lsms.ServiceProviderClass;
import com.pccoe_syrle.project_lsms.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    JSONArray jsonArray;
    LinearLayout trending;
    RecyclerView displayServices, topRated, recentlyViewed;
    private FragmentHomeBinding binding;
    ServiceProviderClass people;
    ServiceProviderAdapter serviceProviderAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        displayServices = binding.ServicesView;
        topRated = binding.topRated;
        recentlyViewed = binding.recentlyViewed;
        displayServices.setHasFixedSize(true);
        topRated.setHasFixedSize(true);
        recentlyViewed.setHasFixedSize(true);
        ArrayList<ServiceProviderClass> serviceProviderList = new ArrayList<>();
        people = new ServiceProviderClass();
        trending = binding.trendingButton;

        LinearLayoutManager llm = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
        topRated.setLayoutManager(llm);
        serviceProviderAdapter = new ServiceProviderAdapter(serviceProviderList);
        topRated.setAdapter(serviceProviderAdapter);

        LinearLayoutManager llm2 = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerViewAdapter adap = new RecyclerViewAdapter(getDummyModelList());
        displayServices.setLayoutManager(llm2);
        displayServices.setAdapter(adap);

        LinearLayoutManager llm3 = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerViewAdapter adapter1 = new RecyclerViewAdapter(getDummyModelList());
        recentlyViewed.setLayoutManager(llm3);
        recentlyViewed.setAdapter(adapter1);

        DBhelperService.fetchData(getContext(), new DBhelperService.DataCallback() {
            @Override
            public void onDataFetched(ArrayList<ServiceProviderClass> listitem) {
                serviceProviderList.addAll(listitem);
                serviceProviderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    private ArrayList<ModelClass> getDummyModelList() {
        ArrayList<ModelClass> listitem = new ArrayList<>();
        listitem.add(new ModelClass("Home Cleaning" , R.drawable.baseline_cleaning_services_24));
        listitem.add(new ModelClass("Designing" , R.drawable.baseline_design_services_24));
        listitem.add(new ModelClass("Electrician Service", R.drawable.baseline_electrical_services_24));
        listitem.add(new ModelClass("Home Repair", R.drawable.baseline_home_repair_service_24));
        listitem.add(new ModelClass("Medical", R.drawable.baseline_medical_services_24));
        return listitem;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
