package com.example.soft7035project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.soft7035project2.models.BookingDBHelper;

public class SettingsActivity extends AppCompatActivity {
    Button clearButton;
    Button dummyButton;
    Button dropButton;
    Button listButton;
    TextView testText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        clearButton = findViewById(R.id.clearTestButton);
        dummyButton = findViewById(R.id.dummyTestButton);
        dropButton = findViewById(R.id.dropTestButton);
        listButton = findViewById(R.id.listTestButton);
        testText = findViewById(R.id.textTextView);



        BookingDBHelper bookingDBHelper = new BookingDBHelper(SettingsActivity.this);
        SQLiteDatabase db = bookingDBHelper.getWritableDatabase();


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookingDBHelper.clearTable(db);
            }
        });


        dummyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookingDBHelper.insertDummyData(db);

            }
        });


        dropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookingDBHelper.dropTable(db);

            }
        });


        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookingDBHelper.getAllAppointments(db);
                testText.setText(toString());

            }
        });


        Toolbar toolbar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);
        Button toolbarButton = findViewById(R.id.settings_toolbar_button);
        toolbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(SettingsActivity.this, toolbarButton);
                popup.getMenuInflater().inflate(R.menu.main_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();

                        if (id == R.id.action_home) {
                            // Intent for the Main Activity
                            Intent mainIntent = new Intent(SettingsActivity.this, MainActivity.class);
                            startActivity(mainIntent);
                        } else if (id == R.id.action_about) {
                            // Intent for the Settings Activity
                            Intent settingsIntent = new Intent(SettingsActivity.this, SettingsActivity.class);
                            startActivity(settingsIntent);
                        }

                        return true;
                    }
                });
                popup.show();
            }
        });
    }
}