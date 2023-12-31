package com.example.soft7035project2.models;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.soft7035project2.R;

import java.util.ArrayList;


public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {
    private static final String TAG = AppointmentAdapter.class.getSimpleName();
    private ArrayList<Appointment> appointments;


    public AppointmentAdapter(ArrayList<Appointment> appointments, Context context) {
        this.appointments = appointments;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Appointment appointment = appointments.get(position);
        holder.tvUserName.setText(appointment.getUser());
        holder.tvTime.setText(appointment.getTime());
        holder.tvDuration.setText(appointment.getDuration() + " hours");

        holder.rv_delete_button.setTag(appointment.getId());
        holder.rv_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current_position = holder.getAdapterPosition();
                BookingDBHelper dbHelper = new BookingDBHelper(v.getContext());
                Log.d(TAG, "onClick: " + v.getTag());
                dbHelper.deleteAppointmentById((Integer) v.getTag());
                appointments.remove(current_position);
                notifyItemRemoved(current_position);
                notifyItemRangeChanged(current_position, appointments.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUserName, tvTime, tvDuration;
        Button rv_delete_button;


        public ViewHolder(View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            rv_delete_button = itemView.findViewById(R.id.rv_delete_button);
        }
    }
}
