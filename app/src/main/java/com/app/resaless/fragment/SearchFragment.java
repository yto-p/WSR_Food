package com.app.resaless.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.app.resaless.R;
import com.app.resaless.activity.MainActivity;
import com.app.resaless.adapter.FoodAdapter;
import com.app.resaless.adapter.SearchAdapter;
import com.app.resaless.item.FoodItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    public static SearchFragment newInstance(){
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = requireActivity().getLayoutInflater().inflate(R.layout.fragment_search, container, false);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        LinearLayout linearLayout = view.findViewById(R.id.linearLayout);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        LinearLayout searchContent = view.findViewById(R.id.linearLayout1);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerFood);
        TextView notFound = view.findViewById(R.id.not_found);
        recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        FoodAdapter foodAdapter = new FoodAdapter(requireActivity());
        recyclerView.setAdapter(foodAdapter);
        LinearLayout menuContent = view.findViewById(R.id.linearLayout2);
        SearchAdapter searchAdapter = new SearchAdapter(requireActivity().getSupportFragmentManager(), (MainActivity) requireActivity(), linearLayout);
        viewPager.setAdapter(searchAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        SearchView searchView = view.findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")){
                    menuContent.setVisibility(View.VISIBLE);
                    searchContent.setVisibility(View.GONE);
                    return false;
                }
                menuContent.setVisibility(View.GONE);
                searchContent.setVisibility(View.VISIBLE);
                ArrayList<FoodItem> allItems = ((MainActivity) requireActivity()).foodItems.getValue();
                if (allItems == null){
                    allItems = new ArrayList<>();
                }
                ArrayList<FoodItem> filteredItems = new ArrayList<>();
                for (FoodItem foodItem: allItems){
                    if (foodItem.title.toLowerCase().contains(newText.toLowerCase())){
                        filteredItems.add(foodItem);
                    }
                }
                if (filteredItems.size() == 0){
                    notFound.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    notFound.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
                foodAdapter.foodItems = filteredItems;
                foodAdapter.notifyDataSetChanged();

                return false;
            }
        });
        return view;
    }
}
