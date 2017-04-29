package com.example.sarah.smartattendance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import Models.Attendance;
import Models.Room;
import Models.Tutorial;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AttendanceView extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefs";
    public static SharedPreferences.Editor editor;
    public static SharedPreferences settings;
    public static ListView attendance;
    public static String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = new Intent(this, BeaconService.class);
        startService(i);

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
        final OurAPI api = adapter.create(OurAPI.class);
        settings = getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
        attendance = (ListView) findViewById(R.id.attendanceList);
        username = settings.getString("username", "");


        final Spinner dropdownTutorial = (Spinner)findViewById(R.id.tutorials);
        api.getAllTutorials(username, new Callback<List<Tutorial>>() {
            @Override
            public void success(List<Tutorial> tutorials, Response response) {
               if(tutorials.size() > 0) {
                   Log.d("Success", tutorials.get(tutorials.size() - 1).getName());
                   ArrayAdapter<Tutorial> adapterTutorials = new SimpleTutorialListAdapter(getApplicationContext(), tutorials);
                   dropdownTutorial.setAdapter(adapterTutorials);
               }
                else {
                   ArrayAdapter<Tutorial> adapterTutorials = new SimpleTutorialListAdapter(getApplicationContext(), new ArrayList<Tutorial>());
                   dropdownTutorial.setAdapter(adapterTutorials);
               }


            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Failure", error.toString());
                ArrayAdapter<Tutorial> adapterTutorials = new SimpleTutorialListAdapter(getApplicationContext(), new ArrayList<Tutorial>());
                dropdownTutorial.setAdapter(adapterTutorials);
            }
        });
        dropdownTutorial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                final String tutorialName = ((Tutorial) parentView.getItemAtPosition(position)).getName();
                editor.putString("SelectedTutorial", tutorialName).commit();
                api.getTutAttendances(username, tutorialName, new Callback<List<Attendance>>() {
                    @Override
                    public void success(List<Attendance> attendances, Response response) {
                        if(attendances.size() > 0) {
                            ArrayAdapter<Attendance> adapterAttendances = new SimpleAttendanceListAdapter(getApplicationContext(), tutorialName, attendances);
                            attendance.setAdapter(adapterAttendances);
                        }
                        else {
                            ArrayAdapter<Attendance> adapterAttendances = new SimpleAttendanceListAdapter(getApplicationContext(), tutorialName, new ArrayList<Attendance>());
                            attendance.setAdapter(adapterAttendances);
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("Failure", "You Failed");
                        ArrayAdapter<Attendance> adapterAttendances = new SimpleAttendanceListAdapter(getApplicationContext(), tutorialName, new ArrayList<Attendance>());
                        attendance.setAdapter(adapterAttendances);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });




    }

}
