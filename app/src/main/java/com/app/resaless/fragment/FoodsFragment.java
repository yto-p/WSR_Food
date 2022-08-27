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
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FoodsFragment extends Fragment {

    public static FoodsFragment newInstance(){
        return new FoodsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity mainActivity = (MainActivity) requireActivity();
        View view = mainActivity.getLayoutInflater().inflate(R.layout.fragment_foods, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerFood);
        recyclerView.setLayoutManager(new GridLayoutManager(mainActivity, 2));
        FoodAdapter foodAdapter = new FoodAdapter(mainActivity);
        recyclerView.setAdapter(foodAdapter);
        mainActivity.foodItems.observe(getViewLifecycleOwner(), foodItems -> {
            ArrayList<FoodItem> filteredItems = new ArrayList<>();
            for (FoodItem item: foodItems){
                if (item.category.equals("Foods"))
                    filteredItems.add(item);
            }
            foodAdapter.foodItems = filteredItems;
            foodAdapter.notifyItemRangeInserted(0, foodItems.size());
        });
        return view;
    }

}
