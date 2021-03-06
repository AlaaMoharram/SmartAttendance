package com.example.sarah.smartattendance;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        TextView nameView = (TextView)CustomView.findViewById(R.id.name);
        TextView timeView = (TextView)CustomView.findViewById(R.id.time);
        TextView dateView = (TextView)CustomView.findViewById(R.id.date);
        TextView attendedView = (TextView)CustomView.findViewById(R.id.attended);
        Attendance attendance = attendances.get(position);

        String date = attendance.getTut_date();
        String time = attendance.getTut_time();
        boolean attended = attendance.isAttended();
        nameView.setText(tutorialName);
        timeView.setText(time);
        dateView.setText(date);
        String attendendString = attended? "Attended" : "Absent";
        attendedView.setText(attendendString);

        return CustomView;
    }
    public View getDropDownView(int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View CustomView = inflater.inflate(R.layout.custom_attendance_row, parent, false);
        TextView nameView = (TextView)CustomView.findViewById(R.id.name);
        TextView timeView = (TextView)CustomView.findViewById(R.id.time);
        TextView dateView = (TextView)CustomView.findViewById(R.id.date);
        Attendance attendance = attendances.get(position);

        String date = attendance.getTut_date();
        String time = attendance.getTut_time();
        nameView.setText(tutorialName);
        timeView.setText(time);
        dateView.setText(date);

        return CustomView;
    }

}
