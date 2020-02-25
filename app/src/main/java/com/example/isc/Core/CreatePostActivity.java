package com.example.isc.Core;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.*;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isc.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class CreatePostActivity extends AppCompatActivity {

    EditText cpEditText;
    ImageView cpImage;
    Button postButton;
    ImageButton showPostLevelImageButton;
    LinearLayout createPostLL, optionsLL, photoLL, includeEventLL, tagColleagueLL, specifyDepartmentLL;
    TextView photoLLTextView, eventsTextView;
    public static final int PICK_IMAGE = 1;

    static String checkedDepartments="none", events="", colleagues="none";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(
                Html.fromHtml("<font color=\"#1976D2\"> Create post </font>")
        );
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);


        eventsTextView = findViewById(R.id.eventsTextView);
        eventsTextView.setText(events);


        postButton = findViewById(R.id.postButton);
        postButton.setTextColor(Color.GRAY);
        cpImage = findViewById(R.id.cpImage);
        showPostLevelImageButton = findViewById(R.id.showPostLevelImageButton);
        cpEditText = findViewById(R.id.cpEditText);
        cpEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                        postButton.setTextColor(Color.GRAY);
                }else{
                    postButton.setTextColor(Color.BLACK);
                }
            }
        });

        createPostLL = findViewById(R.id.createPostLL);
        optionsLL = findViewById(R.id.optionsLinearLayout);
        photoLL = findViewById(R.id.photoLinearLayout);
        photoLLTextView = findViewById(R.id.photoLLTextView);
        includeEventLL = findViewById(R.id.includeEventLinearLayout);
        tagColleagueLL = findViewById(R.id.tagColleagueLinearLayout);
        specifyDepartmentLL = findViewById(R.id.specifyDepartmentLinearLayout);

        photoLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        includeEventLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                includeEvent();
            }
        });
        tagColleagueLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagColleague();
            }
        });
        specifyDepartmentLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                specifyDepartment();
            }
        });

        if (savedInstanceState!=null){
            checkedDepartments = savedInstanceState.getString("checkedDepartments");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("checkedDepartments", checkedDepartments);
    }

    public void post(View view){
        if(cpEditText.getText().toString().length()>0 || cpImage.getDrawable()!=null){
            Toast.makeText(getApplicationContext(), "Posted", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Discard the post")
                .setMessage("Are you sure you want to discard this post?")
                .setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), CoreActivity.class);
                        intent.putExtra("to", "home");
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    @Override
    public boolean onSupportNavigateUp(){
        new AlertDialog.Builder(this)
                .setTitle("Discard the post")
                .setMessage("Are you sure you want to discard this post?")
                .setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), CoreActivity.class);
                        intent.putExtra("to", "home");
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
        return true;
    }

    public void selectImage(){
        if(photoLLTextView.getText().equals("Photo")){
            Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
            getIntent.setType("image/*");

            Intent pickIntent = new Intent(Intent.ACTION_PICK);
            pickIntent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

            Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

            startActivityForResult(chooserIntent, PICK_IMAGE);
        }else{
            cpImage.setImageDrawable(null);
            photoLLTextView.setText("Photo");
            if(cpEditText.getText().toString().trim().length()==0){
                postButton.setTextColor(Color.GRAY);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                return;
            }
            InputStream inputStream = null;
            try {
                inputStream = getApplicationContext().getContentResolver().openInputStream(Objects.requireNonNull(data.getData()));
            } catch (FileNotFoundException e) {
                Toast.makeText(getApplicationContext(), "File not found exception", Toast.LENGTH_SHORT).show();
            }
            if(inputStream!=null){
                cpImage.setImageBitmap(BitmapFactory.decodeStream(inputStream));
                photoLLTextView.setText("Remove photo");
                postButton.setTextColor(Color.BLACK);
            }else{
                Toast.makeText(getApplicationContext(), "inputStream is null", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void includeEvent(){
        Intent intent = new Intent(getApplicationContext(), IncludeEventActivity.class);
        intent.putExtra("from", "create");
        startActivity(intent);
    }

    public void tagColleague(){
        Intent intent = new Intent(getApplicationContext(), TagColleagueActivity.class);
        intent.putExtra("from", "create");
        startActivity(intent);
    }

    public void specifyDepartment(){
        Intent intent = new Intent(getApplicationContext(), PostLevelActivity.class);
        intent.putExtra("from", "create");
        startActivity(intent);
    }

    public void showPostLevel(View view){
        if (checkedDepartments==null) {
            checkedDepartments = "None";
        }
        new AlertDialog.Builder(this)
                .setTitle("Your post is visible to the departments of:")
                .setMessage(checkedDepartments)
                .setPositiveButton("OK", null)
                .setNegativeButton(null, null)
                .show();
    }

    public void showTagColleague(View view){
        if(colleagues.isEmpty()) colleagues = "None";
        new AlertDialog.Builder(this)
                .setTitle("The following colleagues are tagged in your post:")
                .setMessage(colleagues)
                .setPositiveButton("OK", null)
                .setNegativeButton(null, null)
                .show();
    }


}
