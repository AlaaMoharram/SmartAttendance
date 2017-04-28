package com.example.sarah.smartattendance;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;

import Models.Attendance;
import Models.Room;

/**
 * Created by sarah on 4/24/2017.
 */
public class SimpleAttendanceListAdapter extends ArrayAdapter<Attendance> {
    List<Attendance> attendances;
    String tutorialName;


    SimpleAttendanceListAdapter(Context context, String tutorialName, List<Attendance> attendances) {
        super(context, R.layout.custom_row, attendances);
        this.attendances = attendances;
        this.tutorialName = tutorialName;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View CustomView = inflater.inflate(R.layout.custom_attendance_row, parent, false);
        TextView attendedView = (TextView)CustomView.findViewById(R.id.attended);
        TextView descView = (TextView) CustomView.findViewById(R.id.description);
        Attendance attendance = attendances.get(position);

        String date = attendance.getTut_date();
        String time = attendance.getTut_start_time();
        boolean attended = attendance.isAttended();
        String description = tutorialName + "  " + date + "  " + time;
        String attendendString = attended? "Attended" : "Absent";
        attendedView.setText(attendendString);
        descView.setText(description);

        return CustomView;
    }
    public View getDropDownView(int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View CustomView = inflater.inflate(R.layout.custom_attendance_row, parent, false);
        TextView attendedView = (TextView)CustomView.findViewById(R.id.attended);
        TextView descView = (TextView) CustomView.findViewById(R.id.description);
        Attendance attendance = attendances.get(position);

        String date = attendance.getTut_date();
        String time = attendance.getTut_start_time();
        boolean attended = attendance.isAttended();
        String description = tutorialName + "  " + date + "  " + time;
        String attendendString = attended? "Attended" : "Absent";
        attendedView.setText(attendendString);
        descView.setText(description);


        return CustomView;
    }

}
