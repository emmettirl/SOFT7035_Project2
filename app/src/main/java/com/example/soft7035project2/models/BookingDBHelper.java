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
    public static final  String COLUMN_DATE = "date";
    public static final  String COLUMN_TIME = "time";
    public static final  String COLUMN_DURATION = "duration";


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
                        + COLUMN_DATE + " TEXT,"
                        + COLUMN_TIME + " TEXT,"
                        + COLUMN_DURATION + " TEXT"
                        + ")";
        db.execSQL(SQL_CREATE_TABLE);
    }

    public void dropTable(SQLiteDatabase db){
        String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(SQL_DROP_TABLE);
    }

    public void clearTable(SQLiteDatabase db){
        String SQL_CLEAR_TABLE = "DELETE FROM " + TABLE_NAME;
        db.execSQL(SQL_CLEAR_TABLE);
    }

    public void insertDummyData(SQLiteDatabase db) {
        createTable(db);

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER, "John Doe");
        values.put(COLUMN_DATE, "01-12-2023");
        values.put(COLUMN_TIME, "10:00");
        values.put(COLUMN_DURATION, "01:30");

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

    public boolean deleteAppointmentById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Define the where clause
        String selection = COLUMN_ID + " = ?";

        // Specify arguments in placeholder order
        String[] selectionArgs = { String.valueOf(id) };

        // Perform the deletion
        int deletedRows = db.delete(TABLE_NAME, selection, selectionArgs);

        db.close();

        // Check if any rows were deleted
        return deletedRows > 0;
    }

    public boolean insertAppointment(Appointment appointment) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER, appointment.getUser());
        values.put(COLUMN_DATE, appointment.getDate());
        values.put(COLUMN_TIME, appointment.getTime());
        values.put(COLUMN_DURATION, appointment.getDuration());
        long newRowId = db.insert(TABLE_NAME, null, values);

        db.close();

        return newRowId != -1;
    }

}
