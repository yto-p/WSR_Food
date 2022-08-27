package com.app.resaless.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.resaless.R;
import com.app.resaless.api.FoodApi;
import com.app.resaless.dialog.AddressDialog;
import com.app.resaless.fragment.ItemsFragment;
import com.app.resaless.fragment.ProfileFragment;
import com.app.resaless.fragment.SearchFragment;
import com.app.resaless.fragment.WishFragment;
import com.app.resaless.item.FoodItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    TextView address;
    ProgressBar pb;

    FragmentTransaction transaction;

    SearchFragment searchFragment;
    WishFragment wishFragment;
    ItemsFragment itemsFragment;
    ProfileFragment profileFragment;
    public SharedPreferences sPref;
    int token;
    public MutableLiveData<ArrayList<FoodItem>> foodItems = new MutableLiveData<>(new ArrayList<>());
    private final FoodApi foodApi = new FoodApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLogic();
        setUserPermissions();
        initViews();
        initFragments();
        initNavigation();
        getDishes();
        setSupportActionBar(toolbar);
        address.setOnClickListener( v -> {
            AddressDialog addressDialog = new AddressDialog(this, address);
            addressDialog.show();
        });
    }

    public void initFragments(){
        searchFragment = SearchFragment.newInstance(this, frameLayout);
        wishFragment = WishFragment.newInstance(this, frameLayout);
        itemsFragment = ItemsFragment.newInstance(this, frameLayout);
        profileFragment = ProfileFragment.newInstance(this, frameLayout);
    }

    public void setUserPermissions(){
        int permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionStatus!= PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                },123);
            }
        }
    }

    public void initViews(){
        pb = findViewById(R.id.progress_bar);
        frameLayout = findViewById(R.id.frameLayout);
        bottomNavigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);
        address = findViewById(R.id.toolbar_title);
    }

    public void initNavigation(){
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()){
                case R.id.nav_search:
                    transaction.replace(R.id.frameLayout, searchFragment).commit();
                    return true;
                case R.id.nav_wish:
                    transaction.replace(R.id.frameLayout, wishFragment).commit();
                    return true;
                case R.id.nav_items:
                    transaction.replace(R.id.frameLayout, itemsFragment).commit();
                    return true;
                case R.id.nav_profile:
                    transaction.replace(R.id.frameLayout, profileFragment).commit();
                    return true;
            }
            return false;
        });
        bottomNavigationView.setSelectedItemId(R.id.nav_search);
    }

    public void initLogic(){
        sPref=getSharedPreferences("data", MODE_PRIVATE);
        token=sPref.getInt(LoginActivity.TOKEN_KEY, 0);
        if (token == 0){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    public void getDishes(){
        pb.setVisibility(View.VISIBLE);
        foodApi.getDishes(new FoodApi.DishesCallback() {
            @Override
            public void onSuccess(ArrayList<FoodItem> dishes) {
                foodItems.postValue(dishes);
                runOnUiThread(() ->{
                    pb.setVisibility(View.GONE);
                });
            }

            @Override
            public void onFailure() {
                runOnUiThread(() ->{
                    pb.setVisibility(View.GONE);
                });
            }
        });
    }
}
