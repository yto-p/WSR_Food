package com.app.resaless.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.resaless.R;
import com.app.resaless.activity.AddActivity;
import com.app.resaless.activity.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ItemsFragment extends Fragment {

    private View view;

    public static ItemsFragment newInstance(MainActivity mainActivity, ViewGroup container){
        ItemsFragment itemsFragment = new ItemsFragment();
        itemsFragment.view = mainActivity.getLayoutInflater().inflate(R.layout.fragment_items, container, false);
        return itemsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }
}
