package com.example.soft7035project2.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class BookingDBHelper extends SQLiteOpenHelper {

    private static final String TAG = BookingDBHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bookings.db";
    public static final  String TABLE_NAME = "appointments";
    public static final  String COLUMN_ID = "id";
    public static final  String COLUMN_USER = "user";
    public static final  String COLUMN_TIME = "time";
    public static final  String COLUMN_DURATION = "duration";


//    public static final String SQL_DELETE_TABLE =
//        "DROP TABLE IF EXISTS " + TABLE_NAME;


    public BookingDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: db created");
        createTable(db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: run");


    }

    public void createTable(SQLiteDatabase db){
        String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_USER + " TEXT,"
                        + COLUMN_TIME + " TEXT,"
                        + COLUMN_DURATION + " TEXT"
                        + ")";
        db.execSQL(SQL_CREATE_TABLE);
    }

    public void insertDummyData(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER, "John Doe");
        values.put(COLUMN_TIME, "2023-12-01 10:00:00");
        values.put(COLUMN_DURATION, "30");

        long newRowId = db.insert(TABLE_NAME, null, values);

        if (newRowId == -1) {
            Log.e(TAG, "Inserting dummy data failed");
        } else {
            Log.d(TAG, "Dummy data inserted with row ID: " + newRowId);
        }
    }

    public ArrayList<Appointment> getAllAppointments(SQLiteDatabase db) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int userIndex = cursor.getColumnIndex(COLUMN_USER);
            int timeIndex = cursor.getColumnIndex(COLUMN_TIME);
            int durationIndex = cursor.getColumnIndex(COLUMN_DURATION);

            if (idIndex != -1 && userIndex != -1 && timeIndex != -1 && durationIndex != -1) {
                do {
                    Appointment appointment = new Appointment();
                    appointment.setId(cursor.getInt(idIndex));
                    appointment.setUser(cursor.getString(userIndex));
                    appointment.setTime(cursor.getString(timeIndex));
                    appointment.setDuration(cursor.getString(durationIndex));
                    appointments.add(appointment);
                } while (cursor.moveToNext());
            }
        }

        cursor.close();
        return appointments;
    }
}
