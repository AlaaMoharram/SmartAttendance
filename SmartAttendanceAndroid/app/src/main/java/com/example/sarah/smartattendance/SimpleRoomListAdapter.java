package com.example.sarah.smartattendance;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import Models.Room;
import Models.Tutorial;

/**
 * Created by sarah on 4/24/2017.
 */
public class SimpleRoomListAdapter extends ArrayAdapter<Room>{
    List<Room> rooms;


    SimpleRoomListAdapter(Context context, List<Room> rooms) {
        super(context, R.layout.custom_row, rooms);
        this.rooms = rooms;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View CustomView = inflater.inflate(R.layout.custom_row, parent, false);
        TextView nameView = (TextView)CustomView.findViewById(R.id.name);
        Room room = rooms.get(position);

        String name = room.getName();
        nameView.setText(name);
        Log.d("Name", name);
        return CustomView;
    }
    public View getDropDownView(int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View CustomView = inflater.inflate(R.layout.custom_row, parent, false);
        TextView nameView = (TextView)CustomView.findViewById(R.id.name);
        Room room = rooms.get(position);

        String name = room.getName();
        nameView.setText(name);
        Log.d("Name", name);
        return CustomView;
    }

}
