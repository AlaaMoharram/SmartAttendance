package com.example.sarah.smartattendance;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import Models.Attendance;
import Models.Room;
import Models.Tutorial;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ActivateTutorialActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefs";
    public static SharedPreferences.Editor editor;
    public static SharedPreferences settings;
    public static Tutorial activeTutorial;
    public static boolean active;
    public static Button activateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_tutorial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        settings = getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();


        final String SelectedTutorialName = settings.getString("SelectedTutorial", "");
        ((TextView)findViewById(R.id.tutorial_name)).setText(SelectedTutorialName);

        activateButton = (Button) findViewById(R.id.activate_tutorial);
        activateButton.setOnClickListener(buttonClickListener);


        activeTutorial = null;

//        String username = "fadwa"; //later retrieve from shared preferences

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
        final OurAPI api = adapter.create(OurAPI.class);

        //get active tutorial
        api.getActiveTutorial(settings.getString("username", ""), new Callback<Tutorial>() {
            @Override
            public void success(Tutorial tutorial, Response response) {
                activeTutorial = tutorial;
                if(tutorial.getName()==null)
                    activeTutorial = null;
                //if active tutorial is null you can activate normally
                //if active tutorial is not null and the selected tutorial is not the one active disable the button
                //if active tutorial is noot null and the selected tutorial is the one active, give option to disable

                if(activeTutorial == null) {
                    active = true; // button should be active
                    activateButton.setText("Activate Tutorial");
                    activateButton.setActivated(true);
                }
                else if(activeTutorial!=null && !activeTutorial.getName().equals(SelectedTutorialName)) {
                    //disable button
                    //button should say activate but be disabled
                    activateButton.setText("Activate Tutorial");
                    activateButton.setEnabled(false);

                }
                else if(activeTutorial!=null && activeTutorial.getName().equals(SelectedTutorialName)) {
                    active = false; //button should say deactivate
                    activateButton.setText("Deactivate Tutorial");
                    activateButton.setEnabled(true);
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });




    }
    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
            final OurAPI api = adapter.create(OurAPI.class);
            String roomID = settings.getString("SelectedRoom", "");
            final String roomName = settings.getString("SelectedRoomName", "");
            final String SelectedtutorialName = settings.getString("SelectedTutorial", "");



            if(active == false) { // means we are deactivating
                //we should also add the part that inserts end time into attendance records for this session
                final Context context = getApplicationContext();
                api.updateTutorialStatus(SelectedtutorialName, false, new Callback<Tutorial>() {
                    @Override
                    public void success(Tutorial tutorial, Response response) {
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                        String formattedDate = df.format(c.getTime());
//                        Log.d("Time", formattedDate);
                        api.AddTutorialEndTime(SelectedtutorialName, formattedDate, new Callback<Attendance>() {
                            @Override
                            public void success(Attendance attendance, Response response) {
//                                Log.d("Sucess Adding End Time", "Added");
                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }
                        });
                        CharSequence text = "Tutorial: " + SelectedtutorialName + " is now  Inactive";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        active = true; // button should be active again because we activated
                        activateButton.setText("Activate Tutorial");
                        activateButton.setEnabled(true);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }
            else { // we are activating a tutorial
                api.updateTutorialRoom(SelectedtutorialName, roomID, new Callback<Room>() {
                    @Override
                    public void success(Room room, Response response) {
                        api.updateTutorialStatus(SelectedtutorialName, true, new Callback<Tutorial>() {
                            @Override
                            public void success(Tutorial tutorial, Response response) {
                                Context context = getApplicationContext();
                                CharSequence text = "Tutorial: " + SelectedtutorialName + " is now Active at Room " + roomName;
                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                                api.generateAttendances(SelectedtutorialName, SelectedtutorialName, new Callback<Attendance>() {
                                    @Override
                                    public void success(Attendance attendance, Response response) {
                                        active = false; //button should say deactivate
                                        activateButton.setText("Deactivate Tutorial");
                                        activateButton.setEnabled(true);
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        Log.d("Failure", error.toString());
                                    }
                                });

                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }

        }
    };

}
