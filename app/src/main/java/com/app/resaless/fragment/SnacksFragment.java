package com.app.resaless.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.resaless.R;
import com.app.resaless.activity.MainActivity;
import com.app.resaless.adapter.FoodAdapter;
import com.app.resaless.item.FoodItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SnacksFragment extends Fragment {

    private View view;

    public static SnacksFragment newInstance(){
        return new SnacksFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity mainActivity = (MainActivity) requireActivity();
        View view = mainActivity.getLayoutInflater().inflate(R.layout.fragment_snacks, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerFood);
        recyclerView.setLayoutManager(new GridLayoutManager(mainActivity, 2));
        FoodAdapter foodAdapter = new FoodAdapter(mainActivity);
        recyclerView.setAdapter(foodAdapter);
        mainActivity.foodItems.observe(getViewLifecycleOwner(), foodItems -> {
            ArrayList<FoodItem> filteredItems = new ArrayList<>();
            for (FoodItem item: foodItems){
                if (item.category.equals("Snacks"))
                    filteredItems.add(item);
            }
            foodAdapter.foodItems = filteredItems;
            foodAdapter.notifyItemRangeInserted(0, foodItems.size());
        });
        return view;
    }
}
