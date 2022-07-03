package com.app.resaless.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.resaless.R;
import com.app.resaless.activity.MainActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FoodsFragment extends Fragment {

    private View view;

    public static FoodsFragment newInstance(MainActivity mainActivity, ViewGroup container){
        FoodsFragment foodsFragment = new FoodsFragment();
        foodsFragment.view = mainActivity.getLayoutInflater().inflate(R.layout.fragment_foods, container, false);
        return foodsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }
}
