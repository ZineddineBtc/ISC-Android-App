package com.example.isc.Core;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isc.R;

import java.util.ArrayList;
import java.util.Objects;

public class PostLevelActivity extends AppCompatActivity {
    Button postLevelButton;
    LinearLayout level_0_LL, level_1_LL,level_2_LL, level_3_LL, level_4_LL, level_5_LL;
    ImageView level_0, level_1, level_2, level_3, level_4, level_5;
    static ArrayList<String> myDepartments;
    static char[] checkedArr = {'0', '0', '0', '0', '0', '0'};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_level);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(
                Html.fromHtml("<font color=\"#1976D2\"> Departments </font>")
        );
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);

        myDepartments = new ArrayList<String>() {{
            add("Communication");
            add("Media");
            add("External Relations");
            add("Technicals");
            add("Human Resources");
            add("Logistics");
        }};

        level_0_LL = findViewById(R.id.level_0_LL);
        level_1_LL = findViewById(R.id.level_1_LL);
        level_2_LL = findViewById(R.id.level_2_LL);
        level_3_LL = findViewById(R.id.level_3_LL);
        level_4_LL = findViewById(R.id.level_4_LL);
        level_5_LL = findViewById(R.id.level_5_LL);

        level_0 = findViewById(R.id.level_0);
        level_1 = findViewById(R.id.level_1);
        level_2 = findViewById(R.id.level_2);
        level_3 = findViewById(R.id.level_3);
        level_4 = findViewById(R.id.level_4);
        level_5 = findViewById(R.id.level_5);

        level_0_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(level_0.getDrawable() == null){
                    level_0.setImageResource(R.drawable.ic_check_24dp);
                    checkedArr[0] = '1';
                }else{
                    level_0.setImageDrawable(null);
                    checkedArr[0] = '0';
                }
            }
        });
        level_1_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(level_1.getDrawable() == null){
                    level_1.setImageResource(R.drawable.ic_check_24dp);
                    checkedArr[1] = '1';
                }else{
                    level_1.setImageDrawable(null);
                    checkedArr[1] = '0';
                }
            }
        });
        level_2_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(level_2.getDrawable() == null){
                    level_2.setImageResource(R.drawable.ic_check_24dp);
                    checkedArr[2] = '1';
                }else{
                    level_2.setImageDrawable(null);
                    checkedArr[2] = '0';
                }
            }
        });
        level_3_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(level_3.getDrawable() == null){
                    level_3.setImageResource(R.drawable.ic_check_24dp);
                    checkedArr[3] = '1';
                }else{
                    level_3.setImageDrawable(null);
                    checkedArr[3] = '0';
                }
            }
        });
        level_4_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(level_4.getDrawable() == null){
                    level_4.setImageResource(R.drawable.ic_check_24dp);
                    checkedArr[4] = '1';
                }else{
                    level_4.setImageDrawable(null);
                    checkedArr[4] = '0';
                }
            }
        });
        level_5_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(level_5.getDrawable() == null){
                    level_5.setImageResource(R.drawable.ic_check_24dp);
                    checkedArr[5] = '1';
                }else{
                    level_5.setImageDrawable(null);
                    checkedArr[5] = '0';
                }
            }
        });

        postLevelButton = findViewById(R.id.postLevelButton);
        postLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreatePostActivity.class);
                StringBuilder departments = new StringBuilder();
                for (int i=0; i<PostLevelActivity.myDepartments.size(); i++){
                    if (checkedArr[i]=='1'){
                        departments.append(PostLevelActivity.myDepartments.get(i));
                        if(i != PostLevelActivity.myDepartments.size()-1){
                            departments.append(", ");
                        }
                    }
                }
                departments.deleteCharAt(departments.length()-1);
                departments.deleteCharAt(departments.length()-1);
                departments.append('.');
                intent.putExtra("checkedDepartments", departments.toString());
                startActivity(intent);
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
