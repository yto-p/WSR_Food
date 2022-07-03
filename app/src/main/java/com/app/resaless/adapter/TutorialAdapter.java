package com.app.resaless.adapter;

import com.app.resaless.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TutorialAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> tutorials;


    public TutorialAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> tutorials) {
        super(fm);
        this.tutorials=tutorials;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return tutorials.get(position);
    }

    @Override
    public int getCount() {
        return tutorials.size();
    }
}
