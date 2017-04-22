package com.example.sarah.smartattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class TAActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Spinner dropdownTutorial = (Spinner)findViewById(R.id.choose_tutorial);
        String[] itemsTutorial = new String[]{"1", "2", "three"};
        ArrayAdapter<String> adapterTutorial = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemsTutorial);
        dropdownTutorial.setAdapter(adapterTutorial);
        dropdownTutorial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
//                startActivity(new Intent(getApplicationContext(), AttendanceView.class));

//                if (selectedItem.equals("Add new category")) {
//                    // do your stuff
//                }
            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner dropdownRoom = (Spinner)findViewById(R.id.choose_room);
        String[] itemsRoom = new String[]{"1", "2", "three"};
        ArrayAdapter<String> adapterRoom = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, itemsRoom);
        dropdownRoom.setAdapter(adapterRoom);

        Button activateButton = (Button) findViewById(R.id.activate);
        activateButton.setOnClickListener(buttonClickListener);
    }
    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            startActivity(new Intent(getApplicationContext(), AttendanceView.class));
        }
    };
}
