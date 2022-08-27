package com.app.resaless.adapter;

import android.widget.LinearLayout;

import com.app.resaless.activity.MainActivity;
import com.app.resaless.fragment.DrinksFragment;
import com.app.resaless.fragment.FoodsFragment;
import com.app.resaless.fragment.SauceFragment;
import com.app.resaless.fragment.SnacksFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SearchAdapter extends FragmentStatePagerAdapter {

    private final FoodsFragment foodsFragment;
    private final DrinksFragment drinksFragment;
    private final SnacksFragment snacksFragment;
    private final SauceFragment sauceFragment;

    public SearchAdapter(FragmentManager fm, MainActivity mainActivity, LinearLayout linearLayout) {
        super(fm);
        foodsFragment = FoodsFragment.newInstance();
        drinksFragment = DrinksFragment.newInstance();
        snacksFragment = SnacksFragment.newInstance();
        sauceFragment = SauceFragment.newInstance();
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return foodsFragment;
            case 1:
                return drinksFragment;
            case 2:
                return snacksFragment;
            case 3:
                return sauceFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
