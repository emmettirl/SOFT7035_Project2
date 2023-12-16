package com.example.soft7035project2.models;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.soft7035project2.AppointmentsFragments;

public class ViewPageAdapter extends FragmentStateAdapter {
    private static final String TAG = ViewPageAdapter.class.getSimpleName();
    private  static final int ITEM_SIZE = 2;
    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        Log.d(TAG, "ViewPageAdapter: constructor");
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d(TAG, "createFragment: " + position);
        return AppointmentsFragments.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return ITEM_SIZE;
    }
}