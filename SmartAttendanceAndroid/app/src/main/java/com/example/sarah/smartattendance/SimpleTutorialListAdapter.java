package com.example.sarah.smartattendance;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import Models.Tutorial;

public class SimpleTutorialListAdapter extends ArrayAdapter<Tutorial> {
    List<Tutorial> tutorials;


    SimpleTutorialListAdapter(Context context, List<Tutorial> tutorials) {
        super(context, R.layout.custom_row, tutorials);
        this.tutorials = tutorials;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View CustomView = inflater.inflate(R.layout.custom_row, parent, false);
        TextView nameView = (TextView)CustomView.findViewById(R.id.name);
        Tutorial tut = tutorials.get(position);

        String name = tut.getName();
        nameView.setText(name);
        Log.d("Name", name);
        return CustomView;
    }
    public View getDropDownView(int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View CustomView = inflater.inflate(R.layout.custom_row, parent, false);
        TextView nameView = (TextView)CustomView.findViewById(R.id.name);
        Tutorial tut = tutorials.get(position);

        String name = tut.getName();
        nameView.setText(name);
        Log.d("Name", name);
        return CustomView;
    }




}
