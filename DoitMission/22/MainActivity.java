package dev_allgot.understand.doitmission_22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private SBDBViewModel viewModel;

    TabLayout tabs;
    FrameLayout fragmentFrame;

    SingletonBookDB sbdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbdb = new SingletonBookDB(getApplicationContext());

        tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText(R.string.save));
        tabs.addTab(tabs.newTab().setText(R.string.load));
        fragmentFrame = findViewById(R.id.fragmentFrame);

        viewModel = new ViewModelProvider(this).get(SBDBViewModel.class);
        viewModel.setSBDB(sbdb);

        SaveFragment saveFragment = new SaveFragment();
        LoadFragment loadFragment = new LoadFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentFrame, saveFragment).commit();

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int selectedItem = tab.getPosition();
                switch(selectedItem) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, saveFragment).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, loadFragment).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
