package com.app.resaless.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.app.resaless.R;
import com.app.resaless.adapter.TutorialAdapter;
import com.app.resaless.fragment.tutorial.Tutorial1;
import com.app.resaless.fragment.tutorial.Tutorial2;
import com.app.resaless.fragment.tutorial.Tutorial3;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;

public class TutorialActivity extends AppCompatActivity {

    public SharedPreferences sPref;
    public String tutorial;
    final String TUTORIAL_TEXT="tutorial_text";
    public TextView ok;
    public ViewPager viewPager;
    public WormDotsIndicator dots;
    public TutorialAdapter tutorialAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        ok = findViewById(R.id.ok);
        viewPager = findViewById(R.id.view_pager);

        ArrayList<Fragment> tutorials = new ArrayList<>();
        tutorials.add(new Tutorial1());
        tutorials.add(new Tutorial2());
        tutorials.add(new Tutorial3());

        tutorialAdapter = new TutorialAdapter(getSupportFragmentManager(), tutorials);
        dots = findViewById(R.id.dots);
        sPref = getSharedPreferences("data", MODE_PRIVATE);

        viewPager.setAdapter(tutorialAdapter);
        dots.setViewPager(viewPager);
        ok.setOnClickListener( v -> {
            SharedPreferences.Editor ed=sPref.edit();
            ed.putString(TUTORIAL_TEXT,"1");
            ed.apply();
            finish();
        });

    }
}
