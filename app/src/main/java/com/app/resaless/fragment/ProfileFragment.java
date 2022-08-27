package com.app.resaless.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.resaless.R;
import com.app.resaless.activity.LoginActivity;
import com.app.resaless.activity.MainActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private View view;
    private TextView textExit;
    SharedPreferences sPref;

    public static ProfileFragment newInstance(MainActivity mainActivity, ViewGroup container){
        ProfileFragment profileFragment = new ProfileFragment();
        profileFragment.view = mainActivity.getLayoutInflater().inflate(R.layout.fragment_profile, container, false);
        profileFragment.textExit = profileFragment.view.findViewById(R.id.textExit);
        profileFragment.textExit.setOnClickListener(v -> {
            mainActivity.sPref.edit().remove(LoginActivity.TOKEN_KEY).apply();
            mainActivity.startActivity(new Intent(mainActivity, LoginActivity.class));
        });
        return profileFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }
}
