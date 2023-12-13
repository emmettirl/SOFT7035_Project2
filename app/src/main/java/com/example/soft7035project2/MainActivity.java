package com.example.soft7035project2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.soft7035project2.models.ViewPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.mainActivity_tabLayout);
        ViewPager2 viewPager = findViewById(R.id.mainActivity_viewPager);


        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(this);
        viewPager.setAdapter(viewPageAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        // Here, you can set the tab titles based on the position
                        switch (position) {
                            case 0:
                                tab.setText("New Booking");
                                break;
                            case 1:
                                tab.setText("Current Bookings");
                                break;
                            // Add more cases if you have more tabs
                        }
                    }
                }).attach();
    }
}
