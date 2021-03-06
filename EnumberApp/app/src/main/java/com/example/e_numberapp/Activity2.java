package com.example.e_numberapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.e_numberapp.R.id.bottom_nav_history;


public class Activity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        BottomNavigationView botnav = findViewById(R.id.bottom_nav);
        botnav.setOnNavigationItemSelectedListener(navLis);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                ,new information()).commit();




    }
    private BottomNavigationView.OnNavigationItemSelectedListener navLis =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.bottom_nav_information:
                            selectedFragment = new information();
                            break;
                        case R.id.bottom_nav_search:
                            selectedFragment = new search();
                            break;
                        case R.id.bottom_nav_camera:
                            selectedFragment = new camera();
                            break;
                        case bottom_nav_history:
                            selectedFragment = new history();
                            break;
                        
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container
                            ,selectedFragment).commit();
                    return true;
                }
            };



}