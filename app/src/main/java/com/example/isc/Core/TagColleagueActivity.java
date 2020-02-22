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
import android.widget.SearchView;

import com.example.isc.R;

import java.util.ArrayList;
import java.util.Objects;

public class TagColleagueActivity extends AppCompatActivity {

    static ArrayList<String> taggedColleagues;
    ArrayList<MyUser> colleagues;
    TagColleagueAdapter tagColleagueAdapter;
    ListView colleagueListView;
    Button tagColleagueButton;
    SearchView searchColleague;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_colleague);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(
                Html.fromHtml("<font color=\"#1976D2\"> Tag a colleague </font>")
        );
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);

        taggedColleagues = new ArrayList<>();
        colleagues = new ArrayList<MyUser>(){{
            add(new MyUser(R.drawable.img0, "Zineddine", "Head"));
            add(new MyUser(R.drawable.img0, "Mohamed", "Head"));
            add(new MyUser(R.drawable.img0, "Islem", "Member"));
            add(new MyUser(R.drawable.img0, "Bettouche", "Head"));
            add(new MyUser(R.drawable.img0, "Adel", "Member"));
            add(new MyUser(R.drawable.img0, "Nedjem Eddine", "Head"));
            add(new MyUser(R.drawable.img0, "Saad Eddine", "Member"));
        }};
        tagColleagueAdapter = new TagColleagueAdapter(getApplicationContext(), R.layout.activity_tag_colleague_list_adapter, colleagues);
        colleagueListView = findViewById(R.id.colleagueListView);
        colleagueListView.setAdapter(tagColleagueAdapter);

        tagColleagueButton = findViewById(R.id.tagColleagueButton);
        tagColleagueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder events = new StringBuilder();
                for(int i=0; i<taggedColleagues.size(); i++){
                    events.append("@");
                    events.append(taggedColleagues.get(i));
                    events.append("\n");
                }
                startActivity(new Intent(getApplicationContext(), CreatePostActivity.class).putExtra("colleagues", events.toString()));
            }
        });
        searchColleague = findViewById(R.id.searchColleague);
        searchColleague.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                tagColleagueAdapter.filter(newText);
                return true;
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
