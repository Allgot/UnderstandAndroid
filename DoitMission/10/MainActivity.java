package dev_allgot.understand.navigation.doitmission_10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    DrawerLayout drawerLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigationView);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).setReorderingAllowed(true).addToBackStack(null).commit();
                        bottomNavigation.setSelectedItemId(R.id.action_home);

                        return true;
                    case R.id.nav_gallery:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).setReorderingAllowed(true).addToBackStack(null).commit();
                        bottomNavigation.setSelectedItemId(R.id.action_gallery);

                        return true;
                    case R.id.nav_homepage:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).setReorderingAllowed(true).addToBackStack(null).commit();
                        bottomNavigation.setSelectedItemId(R.id.action_homepage);

                        return true;
                    default:
                        return false;
                }
            }
        });


        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.action_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).setReorderingAllowed(true).addToBackStack(null).commit();
                        navigationView.setCheckedItem(R.id.nav_home);

                        return true;
                    case R.id.action_gallery:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).setReorderingAllowed(true).addToBackStack(null).commit();
                        navigationView.setCheckedItem(R.id.nav_gallery);

                        return true;
                    case R.id.action_homepage:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).setReorderingAllowed(true).addToBackStack(null).commit();
                        navigationView.setCheckedItem(R.id.nav_homepage);

                        return true;
                }
                return false;
            }
        });

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();
    }
}