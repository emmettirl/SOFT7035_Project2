package com.example.soft7035project2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointmentsBooking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointmentsBooking extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private Integer counter;
    private static final String ARG_COUNT = "param1";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AppointmentsBooking() {
        // Required empty public constructor
    }

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
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AppointmentsBooking.
     */
    // TODO: Rename and change types and number of parameters
    public static AppointmentsBooking newInstance(String param1, String param2) {
        AppointmentsBooking fragment = new AppointmentsBooking();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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


//        TextView textViewCounter = view.findViewById(R.id.appBooking_tv);
//        textViewCounter.setText("Fragment No " + counter);
    }
}