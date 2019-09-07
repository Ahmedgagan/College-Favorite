package com.collegefavourite;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Favourite App");
        loadFragment(new FragmentHome());
        BottomNavigationView nav = findViewById(R.id.navigationn);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        toolbar.setTitle("Home");
                        fragment = new FragmentHome();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_list:
                        toolbar.setTitle("List");
                        fragment = new FragmentList();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_favorite:
                        toolbar.setTitle("Favourite");
                        fragment = new FragmentFavourite();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
    }
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
        //super.onBackPressed();
    }
}