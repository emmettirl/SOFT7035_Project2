package com.example.soft7035project2.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.soft7035project2.R;



public class TimeAdapter extends BaseAdapter {
    private Context context;
    private final String[] times;

    public TimeAdapter(Context context) {
        this.context = context;
        this.times = new String[22]; // 11 hours * 2 slots per hour
        int hour = 7;
        for (int i = 0; i < times.length; i += 2) {
            times[i] = hour + ":00 - " + hour + ":30";
            times[i + 1] = hour + ":30 - " + (hour + 1) + ":00";
            hour++;
        }
    }

    @Override
    public int getCount() {
        return times.length;
    }

    @Override
    public Object getItem(int position) {
        return times[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item, null);
        }

        TextView textView = convertView.findViewById(R.id.grid_item_label);
        textView.setText(times[position]);
        return convertView;
    }
}

