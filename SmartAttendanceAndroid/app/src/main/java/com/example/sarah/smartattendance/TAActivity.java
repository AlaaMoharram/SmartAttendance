package com.example.sarah.smartattendance;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import Models.Room;
import Models.Tutorial;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TAActivity extends AppCompatActivity {

    List<Tutorial> tutorials;
    public static final String PREFS_NAME = "MyPrefs";
    public static SharedPreferences.Editor editor;
    public static SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Spinner dropdownTutorial = (Spinner)findViewById(R.id.choose_tutorial);
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
        OurAPI api = adapter.create(OurAPI.class);

        settings = getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
        api.getAllTutorials("sarah", new Callback<List<Tutorial>>() {
            @Override
            public void success(List<Tutorial> tutorials, Response response) {
                Log.d("Success", tutorials.get(tutorials.size() - 1).getName());
                ArrayAdapter<Tutorial> adapterTutorials = new SimpleTutorialListAdapter(getApplicationContext(), tutorials);
                dropdownTutorial.setAdapter(adapterTutorials);

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Failure", error.toString());
                ArrayAdapter<Tutorial> adapterTutorials = new SimpleTutorialListAdapter(getApplicationContext(), new ArrayList<Tutorial>());
                dropdownTutorial.setAdapter(adapterTutorials);
            }
        });
        final Spinner dropdownRoom = (Spinner)findViewById(R.id.choose_room);

        api.getAllRooms(new Callback<List<Room>>() {
            @Override
            public void success(List<Room> rooms, Response response) {
                ArrayAdapter<Room> adapterRooms = new SimpleRoomListAdapter(getApplicationContext(), rooms);
                dropdownRoom.setAdapter(adapterRooms);
            }

            @Override
            public void failure(RetrofitError error) {
                ArrayAdapter<Room> adapterRooms = new SimpleRoomListAdapter(getApplicationContext(), new ArrayList<Room>());
                dropdownRoom.setAdapter(adapterRooms);
            }
        });
        dropdownTutorial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                String tutorialName = ((Tutorial)parentView.getItemAtPosition(position)).getName();
                editor.putString("ActivateTutorial", tutorialName).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        dropdownRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                Room room = (Room) parentView.getItemAtPosition(position);
                String roomName = room.getName();
                String roomID = String.valueOf(room.getRoom_id());
                editor.putString("SelectedRoom", roomID).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        Button activateButton = (Button) findViewById(R.id.activate);
        activateButton.setOnClickListener(buttonClickListener);

    }
    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
            OurAPI api = adapter.create(OurAPI.class);
            String roomName = settings.getString("SelectedRoom", "");
            String tutorialName = settings.getString("ActivateTutorial", "");
//            Log.d("here", "here");
//            Log.d("Selected Tutorial", settings.getString("ActivateTutorial", ""));
            Log.d("SelectedRoom", settings.getString("SelectedRoom", ""));
            api.updateTutorialStatus(tutorialName, true, new Callback<Tutorial>() {
                @Override
                public void success(Tutorial tutorial, Response response) {
//                    Log.d("Success", tutorial.getName());
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
//            api.updateTutorialRoom(tutorialName, roomName, new Callback<Room>() {
//                @Override
//                public void success(Room room, Response response) {
//                    Log.d("Name", room.getName());
//                }
//
//                @Override
//                public void failure(RetrofitError error) {
//
//                }
//            });
        }
    };
}
