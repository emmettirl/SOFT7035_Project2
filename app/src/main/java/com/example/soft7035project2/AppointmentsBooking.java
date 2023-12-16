package com.example.soft7035project2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.soft7035project2.models.BookingDBHelper;
import com.example.soft7035project2.models.TimeAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointmentsBooking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointmentsBooking extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private static final String TAG = AppointmentsBooking.class.getSimpleName();

    private Integer counter;
    private static final String ARG_COUNT = "param1";
    private EditText editTextDate;

    public AppointmentsBooking() {
        // Required empty public constructor
    }

    Button testButton;
    TextView testText;

    public static AppointmentsBooking newInstance(Integer counter) {
        AppointmentsBooking fragment = new AppointmentsBooking();
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, counter);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AppointmentsBooking.
     */
    // TODO: Rename and change types and number of parameters
    public static AppointmentsBooking newInstance() {
        AppointmentsBooking fragment = new AppointmentsBooking();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            counter = getArguments().getInt(ARG_COUNT);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (counter == 0) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_appointments_booking, container, false);
        }
        else {
            return inflater.inflate(R.layout.fragment_appointments_current, container, false);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = this.getContext();

        try {
            Log.d(TAG, "onViewCreated: bookingDB about to be created");
            BookingDBHelper bookingDBHelper = new BookingDBHelper(context);

            SQLiteDatabase db = bookingDBHelper.getWritableDatabase();

            bookingDBHelper.insertDummyData(db);

            Log.d(TAG, "onViewCreated:" + view.toString());

            testButton = view.findViewById(R.id.testbutton);
            testText = view.findViewById(R.id.textTextView);

            testButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        testText.setText(bookingDBHelper.getAllAppointments(db).toString());
                    }
                }
            );

            Log.d(TAG, "onViewCreated: bookingDB created ");
        } catch (Exception e) {
            Log.d(TAG, "onViewCreated: ", e);
        }

        editTextDate = view.findViewById(R.id.editTextDate);
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


        GridView gridView = view.findViewById(R.id.gridView);
        TimeAdapter timeAdapter = new TimeAdapter(getContext());
        gridView.setAdapter(timeAdapter);



    }

    private void showDatePickerDialog() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Set the date chosen by the user
                        editTextDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }
}