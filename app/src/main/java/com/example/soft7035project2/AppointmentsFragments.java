package com.example.soft7035project2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soft7035project2.models.Appointment;
import com.example.soft7035project2.models.AppointmentAdapter;
import com.example.soft7035project2.models.BookingDBHelper;
import com.example.soft7035project2.models.TimeAdapter;

import java.util.ArrayList;
import java.util.Calendar;


public class AppointmentsFragments extends Fragment {
    private static final String TAG = AppointmentsFragments.class.getSimpleName();
    private Integer counter;
    private static final String ARG_COUNT = "param1";
    private EditText editTextDate;
    private ArrayList<Appointment> appointments;
    private String selectedDate;
    private String selectedDuration;
    private String selectedStartTime;
    Button confirmButton;


    public AppointmentsFragments() {
        // Required empty public constructor
    }

    public static AppointmentsFragments newInstance(Integer counter) {
        AppointmentsFragments fragment = new AppointmentsFragments();
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, counter);
        fragment.setArguments(args);

        Log.d(TAG, "newInstance: " + counter.toString() + " created");
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            counter = getArguments().getInt(ARG_COUNT);
            Log.d(TAG, "onCreate: " + counter.toString() + " created");
        }
        else {
            Log.d(TAG, "onCreate: no arguments ");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (counter == 0) {
            Log.d(TAG, "onCreateView: booking");
            return inflater.inflate(R.layout.fragment_appointments_booking, container, false);
        }
        else {
            Log.d(TAG, "onCreateView: current");
            return inflater.inflate(R.layout.fragment_appointments_current, container, false);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: pre super");
        super.onViewCreated(view, savedInstanceState);


        Log.d(TAG, "onViewCreated: pre context");
//        Context context = requireContext();
        Context context = getContext();
        try {
            Log.d(TAG, "onViewCreated: bookingDB about to be created");
            BookingDBHelper bookingDBHelper = new BookingDBHelper(context);

            SQLiteDatabase db = bookingDBHelper.getWritableDatabase();

            Log.d(TAG, "onViewCreated:" + view.toString());

            appointments = bookingDBHelper.getAllAppointments(db);
            AppointmentAdapter adapter = new AppointmentAdapter(appointments, getContext());


            if (counter == 0) {
                Log.d(TAG, "onViewCreated: booking");

                editTextDate = view.findViewById(R.id.editTextDate);
                editTextDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDatePickerDialog();
                    }
                });

                GridView gridView = view.findViewById(R.id.gridView);
                TimeAdapter timeAdapter = new TimeAdapter(getContext(), appointments);
                gridView.setAdapter(timeAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        timeAdapter.setSelectedPosition(position);
                        selectedStartTime = timeAdapter.getSelectedTime();
                    }
                });

                Log.d(TAG, "onViewCreated: bookingDB created ");



                RadioGroup radioGroupDuration = view.findViewById(R.id.radioGroupDuration);
                Log.d(TAG, "onViewCreated: radioGroupDuration found");
                radioGroupDuration.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        Log.d(TAG, "onCheckedChanged: radioGroupDuration changed" + checkedId );
                        RadioButton selectedRadioButton = group.findViewById(checkedId);
                        if (selectedRadioButton != null) {

                            selectedDuration = (selectedRadioButton.getText().toString().split(" ")[0] + ":00");
                        }
                    }
                });

                confirmButton = view.findViewById(R.id.bookButton);
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Appointment appointment = new Appointment("John Doe", selectedDate, selectedStartTime, selectedDuration);
                        Log.d(TAG, "onClick: " + appointment.toString());

                        bookingDBHelper.insertAppointment(appointment);
                    }
                });



            } else {
                Log.d(TAG, "onViewCreated: current");

                RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
            }

        } catch (Exception e) {
            Log.d(TAG, "onViewCreated: ", e);
        }
    }

    private void showDatePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Set the date chosen by the user
                        selectedDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        editTextDate.setText(selectedDate);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }
}
