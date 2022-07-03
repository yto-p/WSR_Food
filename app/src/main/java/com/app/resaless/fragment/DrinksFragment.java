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

public class DrinksFragment extends Fragment {

    private View view;

    public static DrinksFragment newInstance(MainActivity mainActivity, ViewGroup container){
        DrinksFragment drinks = new DrinksFragment();
        drinks.view = mainActivity.getLayoutInflater().inflate(R.layout.fragment_drinks, container, false);
        return drinks;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }
}
