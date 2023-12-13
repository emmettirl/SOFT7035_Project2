package com.example.soft7035project2.models;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.soft7035project2.AppointmentsBooking;

public class ViewPageAdapter extends FragmentStateAdapter {
    private  static final int ITEM_SIZE = 2;
    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return AppointmentsBooking.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return ITEM_SIZE;
    }
}