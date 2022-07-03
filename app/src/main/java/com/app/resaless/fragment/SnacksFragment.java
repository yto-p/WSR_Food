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

public class SnacksFragment extends Fragment {

    private View view;

    public static SnacksFragment newInstance(MainActivity mainActivity, ViewGroup container){
        SnacksFragment snacksFragment = new SnacksFragment();
        snacksFragment.view = mainActivity.getLayoutInflater().inflate(R.layout.fragment_snacks, container, false);
        return snacksFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }
}
