package com.app.resaless.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.resaless.R;
import com.app.resaless.activity.MainActivity;
import com.app.resaless.adapter.SearchAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class SearchFragment extends Fragment {

    private View view;
    private LinearLayout linearLayout;
    private TabLayout tabLayout;

    private ViewPager viewPager;
    private SearchAdapter searchAdapter;

    public static SearchFragment newInstance(MainActivity mainActivity, ViewGroup container){
        SearchFragment searchFragment = new SearchFragment();
        searchFragment.view = mainActivity.getLayoutInflater().inflate(R.layout.fragment_search, container, false);
        searchFragment.viewPager = searchFragment.view.findViewById(R.id.viewPager);
        searchFragment.linearLayout = searchFragment.view.findViewById(R.id.linearLayout);
        searchFragment.tabLayout = searchFragment.view.findViewById(R.id.tabLayout);
        searchFragment.searchAdapter = new SearchAdapter(mainActivity.getSupportFragmentManager(), mainActivity, searchFragment.linearLayout);
        searchFragment.viewPager.setAdapter(searchFragment.searchAdapter);
        searchFragment.viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(searchFragment.tabLayout));
        searchFragment.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                searchFragment.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return searchFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }
}
