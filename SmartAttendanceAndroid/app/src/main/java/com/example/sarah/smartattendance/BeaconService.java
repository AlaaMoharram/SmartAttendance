package com.example.sarah.smartattendance;

/**
 * Created by sarah on 4/22/2017.
 */
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;


import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Models.Attendance;
import Models.Tutorial;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class BeaconService extends Service implements BeaconConsumer {
    private BeaconManager beaconManager;
    private static final String TAG = "BeaconReferenceApp";
    private SharedPreferences sharedPreferences;
    public static final String PREFS_NAME = "MyPrefs";
    private SharedPreferences.Editor editor;
    Beacon beacon1, beacon2;
    OurAPI api;
    Tutorial activeTutorial;

    Timer timer = new Timer ();
    TimerTask hourlyTask = new TimerTask () {
        @Override
        public void run() {
        //check if tutorial is still active
            api.getActiveTutorial(sharedPreferences.getString("username", "") + "", new Callback<Tutorial>() {
                @Override
                public void success(Tutorial tutorial, Response response) {
                    //in case the tutorial is active check if I'm inside
                    if (tutorial.getName() != null) {
                        activeTutorial = tutorial;
                        //tutorial is active and its the same as the one in my preferences
                        // -> check if i am inside and if yes add points
                        //check if you're inside!
                        if (sharedPreferences.contains("active_tutorial") &&
                                activeTutorial.getTutorial_id() == sharedPreferences.getInt("active_tutorial", -1)) {
                            api.getBeacons(activeTutorial.getRoom_id() + "", new Callback<List<Models.Beacon>>() {
                                @Override
                                public void success(List<Models.Beacon> beacons, Response response) {
                                    if (beacons.get(0).equals(beacon1) || beacons.get(1).equals(beacon1)) {
                                        if (beacons.get(1).equals(beacon2) || beacons.get(1).equals(beacon2)) {
                                            //i'm inside!
                                            editor.putInt("points", sharedPreferences.getInt("points", 0) + 5);
                                            Log.d("Points: ", sharedPreferences.getInt("points", -1) + "");
                                            editor.commit();

                                        }
                                    }
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    Log.d("Response", "You're a failure in life, get active tutorial");

                                }
                            });
                        }
                        //tutorial is active but its not the same as the one in my shared preferences
                        // so check if i get attendence for that one in my preferences
                        else if (sharedPreferences.contains("active_tutorial") &&
                                activeTutorial.getTutorial_id() != sharedPreferences.getInt("active_tutorial", -1)) {
                            //check if i get attendance for that tutorial in my shared preferences
                            //retrieve last attendance for that tutorial and check my current points against end and start time
                            // and add attendance then update active tutorial to the current active tutorial and reset points to 0
                            editor.putInt("active_tutorial", activeTutorial.getTutorial_id());
                            editor.putInt("points", 0);
                            editor.commit();

                        } else {
                            //there is an active tutorial but no active tutorials in my preferences
                            //so just add it so it matches the current active tutorial
                            editor.putInt("active_tutorial", activeTutorial.getTutorial_id());
                            editor.putInt("points", 0);
                            editor.commit();
                        }

                    }

                    //there are no active tutorials but my preferences have an active tutorial then
                    //it must mean that the tutorial i was in ended
                    //in case its not active add attendence only if time is 75% inside
                    else {
                        if (sharedPreferences.contains("active_tutorial")) {
                            int startTime, endTime;

                            // i think this one needs to be get last attendance for active tutorial
                            //then from it we get end time and start time and update accordingly the attendance record
                            api.getTutorial(activeTutorial.getName() + "", new Callback<Tutorial>() {
                                @Override
                                public void success(Tutorial tutorial, Response response) {
                                    //get total time + check if its attendence worthy
                                    //if it is, add attendence + clear sharedPreferences
                                    int totalTime = 0;
                                    if (sharedPreferences.getInt("points", 0) >= 60 / 100 * totalTime) {
                                        api.updateAttendance(sharedPreferences.getInt("user_id", 0) + "", "true", new Callback<Attendance>() {
                                            @Override
                                            public void success(Attendance attendance, Response response) {

                                            }

                                            @Override
                                            public void failure(RetrofitError error) {
                                                Log.d("Response", "You're a failure in life, attendance");
                                            }
                                        });
                                    }
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    Log.d("Response", "You're a failure in life, tutorial-fetcher");

                                }
                            });
                            editor.remove("active_tutorial");
                            editor.remove("points");
                            editor.commit();
                            //clear shared preferences
                        }

                    }

                }

                @Override
                //failure for get active tutorial
                public void failure(RetrofitError error) {
                    Log.d("Response", "Failed fel awel");
                }
            });
        }
    };
//        worker.schedule(task, 3, TimeUnit.SECONDS);





    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().clear();
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));
        sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        editor = sharedPreferences.edit();
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
        api = adapter.create(OurAPI.class);

        beaconManager.bind(this);
        //TODO do something useful
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //TODO for communication return IBinder implementation
        return null;
    }

    @Override
    public void onBeaconServiceConnect() {
        Log.d("Sucess", "Service Connected");
        timer.scheduleAtFixedRate(hourlyTask, 1, 300000);

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
            //beaconManager.startRangingBeaconsInRegion(new Region());
        } catch (RemoteException e) {
        }


        beaconManager.setRangeNotifier(new RangeNotifier() {
                                           @Override
                                           public void didRangeBeaconsInRegion(final Collection<Beacon> beacons, Region region) {
                                               Thread thread = new Thread(new Runnable() {
                                                   @Override
                                                   public void run() {
                                                       try {
                                                           if (beacons.size() > 0) {
                                                               Beacon beacon = beacons.iterator().next();
                                                               beacons.iterator().remove();
                                                               final int rssi = beacon.getRssi();
                                                               Log.d("RSSI", rssi +"");
                                                               if(sharedPreferences.getInt("beacon1",0)!= rssi && sharedPreferences.getInt("beacon2",0)!=rssi){
                                                                   if(sharedPreferences.getInt("beacon1",0)<rssi){
                                                                       editor.putInt("beacon1", rssi);
                                                                       editor.commit();
                                                                       beacon1 = beacon;
                                                                   }
                                                                   else if(sharedPreferences.getInt("beacon2", 0)<rssi){
                                                                       editor.putInt("beacon2", rssi);
                                                                       editor.commit();
                                                                       beacon2 = beacon;
                                                                   }
                                                               }
                                                           }

                                                       } catch (Exception e) {
                                                           e.printStackTrace();
                                                       }
                                                   }

                                               });
                                               thread.start();


                                           }
                                       }

        );
        beaconManager.setMonitorNotifier(new

                                                 MonitorNotifier() {
                                                     @Override
                                                     public void didEnterRegion(Region region) {
                                                         Log.i(TAG, "I just saw an beacon for the first time!");
                                                     }

                                                     @Override
                                                     public void didExitRegion(Region region) {
                                                         Log.i(TAG, "I no longer see an beacon");
                                                     }

                                                     @Override
                                                     public void didDetermineStateForRegion(int state, Region region) {
                                                         Log.i(TAG, "I have just switched from seeing/not seeing beacons: " + state);
                                                     }
                                                 }

        );
    }



}
