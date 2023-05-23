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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pccoe_syrle.project_lsms.DBhelper.DBhelperService;
import com.pccoe_syrle.project_lsms.ModelClass;
import com.pccoe_syrle.project_lsms.R;
import com.pccoe_syrle.project_lsms.RecyclerViewAdapter;
import com.pccoe_syrle.project_lsms.ServiceProviderClass;
import com.pccoe_syrle.project_lsms.databinding.FragmentHomeBinding;
import com.pccoe_syrle.project_lsms.ServiceProviderAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {
    JSONArray jsonArray;
    LinearLayout trending;
    RecyclerView displayServices, topRated, recevetlyViewed;
    private FragmentHomeBinding binding;
    ServiceProviderClass people;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        displayServices = binding.ServicesView;
        topRated = binding.topRated;
        recevetlyViewed = binding.recentlyViewed;
        displayServices.setHasFixedSize(true);
        topRated.setHasFixedSize(true);
        recevetlyViewed.setHasFixedSize(true);
        ArrayList<ServiceProviderClass> listitem2 = DBhelperService.fetchData(getContext());
        people = new ServiceProviderClass();
        trending = binding.trendingButton;

        ArrayList<ModelClass> listitem = new ArrayList<>();

        listitem.add(new ModelClass("Hello","3", R.drawable.baseline_person_outline_24));
        listitem.add(new ModelClass("Bello","2",R.drawable.baseline_person_outline_24));
        listitem.add(new ModelClass("Mello","1",R.drawable.baseline_person_outline_24));
        listitem.add(new ModelClass("Khelo","0",R.drawable.baseline_person_outline_24));

        LinearLayoutManager llm = new LinearLayoutManager(root.getContext(),LinearLayoutManager.HORIZONTAL,false);
        topRated.setLayoutManager(llm);
        ServiceProviderAdapter adapter = new ServiceProviderAdapter(listitem2);
        topRated.setAdapter(adapter);

        listitem2.add(people);

        LinearLayoutManager llm2 = new LinearLayoutManager(root.getContext(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerViewAdapter adap = new RecyclerViewAdapter(listitem);
        displayServices.setLayoutManager(llm2);
        displayServices.setAdapter(adap);

        ArrayList<ModelClass> listitem3 = new ArrayList<ModelClass>();

        listitem3.add(new ModelClass("Hello","3", R.drawable.baseline_person_outline_24));
        listitem3.add(new ModelClass("Bello","2",R.drawable.baseline_person_outline_24));
        listitem3.add(new ModelClass("Mello","1",R.drawable.baseline_person_outline_24));
        listitem3.add(new ModelClass("Khelo","0",R.drawable.baseline_person_outline_24));

        LinearLayoutManager llm3 = new LinearLayoutManager(root.getContext(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerViewAdapter adapter1 = new RecyclerViewAdapter(listitem3);
        recevetlyViewed.setLayoutManager(llm3);
        recevetlyViewed.setAdapter(adapter1);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}