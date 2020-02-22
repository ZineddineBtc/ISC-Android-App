package com.example.isc.Core;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.isc.R;

import java.util.ArrayList;
import java.util.Objects;

public class IncludeEventActivity extends AppCompatActivity {

    ArrayList<String> eventsList;
    IncludeEventAdapter includeEventAdapter;
    ListView eventsListView;
    Button includeEventButton;
    static ArrayList<String> includedEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_include_event);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(
                Html.fromHtml("<font color=\"#1976D2\"> Include Event </font>")
        );
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);

        eventsList = new ArrayList<String>(){{
            add("INETech");
            add("Welcome Day");
            add("Innovation Day");
            add("Indjaz");
        }};
        includedEvents = new ArrayList<>();
        includeEventAdapter = new IncludeEventAdapter(getApplicationContext(), R.layout.activity_include_event_list_adapter, eventsList);
        eventsListView = findViewById(R.id.eventsListView);
        eventsListView.setAdapter(includeEventAdapter);

        includeEventButton = findViewById(R.id.includeEventButton);
        includeEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder events = new StringBuilder();
                for(int i=0; i<includedEvents.size(); i++){
                    events.append("#");
                    events.append(includedEvents.get(i));
                    events.append(" ");
                }
                startActivity(new Intent(getApplicationContext(), CreatePostActivity.class).putExtra("event", events.toString()));
            }
        });
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Discard the selection")
                .setMessage("Are you sure you want to discard the selection?")
                .setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), CreatePostActivity.class));
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
