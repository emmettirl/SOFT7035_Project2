package com.example.soft7035project2.models;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.soft7035project2.R;

import java.time.LocalTime;
import java.util.ArrayList;


public class TimeAdapter extends BaseAdapter {
    private static final String TAG = TimeAdapter.class.getSimpleName();
    private int selectedPosition = -1;
    private Context context;
    private final String[] times;
    private ArrayList<Appointment> appointments;

    public TimeAdapter(Context context, ArrayList<Appointment> appointments) {
        this.appointments = appointments;
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
    public View getView(int position, View gridItem, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (gridItem == null) {
            gridItem = inflater.inflate(R.layout.grid_item, null);
        }

        TextView textView = gridItem.findViewById(R.id.grid_item_label);
        textView.setText(times[position]);
        if (isTimeSlotBooked(times[position])) {
            gridItem.setBackgroundColor(Color.GRAY);
            gridItem.setTag("booked");
        } else if (selectedPosition == position) {
            gridItem.setBackgroundColor(Color.BLUE);
        } else {
            gridItem.setBackgroundColor(Color.WHITE);
            gridItem.setTag("open"); /// TODO: 16/12/2023 will use this to check if adjacent slots are open for longer meeting durations
        }

        return gridItem;
    }

    private boolean isTimeSlotBooked(String timeSlot) {
        String[] parts = timeSlot.split(" - ");

        String startTimeStr = formatShortTimes(parts[0]);
        String endTimeStr = formatShortTimes(parts[1]);

        LocalTime startTime = LocalTime.parse(startTimeStr);
        LocalTime endTime = LocalTime.parse(endTimeStr);

        for (Appointment appointment : appointments) {
            LocalTime appointmentStartTime = LocalTime.parse(appointment.getTime());
            LocalTime duration = LocalTime.parse(formatShortTimes(appointment.getDuration()));
            LocalTime appointmentEndTime = appointmentStartTime.plusHours(duration.getHour()).plusMinutes(duration.getMinute());

            boolean startsInSlot = !appointmentStartTime.isBefore(startTime) && appointmentStartTime.isBefore(endTime);
            boolean endsInSlot = appointmentEndTime.isAfter(startTime) && !appointmentEndTime.isAfter(endTime);
            boolean overlapsSlot = appointmentStartTime.isBefore(startTime) && appointmentEndTime.isAfter(endTime);

            if (startsInSlot || endsInSlot || overlapsSlot) {
                return true;
            }
        }
        return false;
    }


    private String formatShortTimes(String time) {
        if (time.length() == 4) {
            return "0" + time;
        }
        return time;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged(); // Refresh the grid views
    }



}



