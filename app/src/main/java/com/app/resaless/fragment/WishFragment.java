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

public class WishFragment extends Fragment {

    private View view;

    public static WishFragment newInstance(MainActivity mainActivity, ViewGroup container){
        WishFragment wishFragment = new WishFragment();
        wishFragment.view = mainActivity.getLayoutInflater().inflate(R.layout.fragment_wish, container, false);
        return wishFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }
}
