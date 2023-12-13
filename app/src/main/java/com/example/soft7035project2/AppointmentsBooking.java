package com.example.soft7035project2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.soft7035project2.models.Appointment;
import com.example.soft7035project2.models.BookingDBHelper;

import java.util.ArrayList;
import java.util.List;

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
            return inflater.inflate(R.layout.fragment_current_appointments, container, false);
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




    }
}