package com.setyo.movie.moviekatalog;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.setyo.movie.moviekatalog.Fragment.HomeFragment;
import com.setyo.movie.moviekatalog.Fragment.TopFragment;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Katalog Movie");
        getSupportFragmentManager().beginTransaction().replace(R.id.layoutFragment,new HomeFragment())
                .commit();

        bottomNav();
    }


    private void bottomNav() {
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_action_name, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_action_pop, R.color.colorPrimary);
//        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_action_account, R.color.colorPrimaryDark);

        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
//        bottomNavigation.addItem(item3);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (position == 0){
                    getSupportFragmentManager().beginTransaction().replace(R.id.layoutFragment,new HomeFragment())
                            .commit();
                }else if(position == 1){
                    getSupportFragmentManager().beginTransaction().replace(R.id.layoutFragment,new TopFragment())
                            .commit();
                }
                return true;
            }
        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override public void onPositionChange(int y) {
                // Manage the new y position
            }
        });
    //end bottomNav
    }

}
