package com.example.isc.Core.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.isc.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class ProfileFragment extends Fragment {

    ImageView profileImage;
    LinearLayout nameLayout, positionLayout, studentNumberLayout, emailLayout, postLayout;
    TextView nameTextView, positionTextView, studentNumberTextView, emailTextView, postTextView;
    EditText nameEditText, positionEditText, studentNumberEditText, emailEditText;
    ImageView editName, editPosition, editStudentNumber, editEmail;
    public static final int PICK_IMAGE = 1;

    Boolean n, p, s, e;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);

        profileImage = fragmentView.findViewById(R.id.profileImage);
        nameLayout = fragmentView.findViewById(R.id.nameLayout);
        positionLayout = fragmentView.findViewById(R.id.positionLayout);
        studentNumberLayout = fragmentView.findViewById(R.id.studentNumberLayout);
        emailLayout = fragmentView.findViewById(R.id.emailLayout);
        postLayout = fragmentView.findViewById(R.id.postLayout);

        nameTextView = fragmentView.findViewById(R.id.nameTextView);
        positionTextView = fragmentView.findViewById(R.id.positionTextView);
        studentNumberTextView = fragmentView.findViewById(R.id.studentNumberTextView);
        emailTextView = fragmentView.findViewById(R.id.emailTextView);

        nameEditText = fragmentView.findViewById(R.id.nameEditText);
        positionEditText = fragmentView.findViewById(R.id.positionEditText);
        studentNumberEditText = fragmentView.findViewById(R.id.studentNumberEditText);
        emailEditText = fragmentView.findViewById(R.id.emailEditText);

        editName = fragmentView.findViewById(R.id.editName);
        editPosition = fragmentView.findViewById(R.id.editPosition);
        editStudentNumber = fragmentView.findViewById(R.id.editStudentNumber);
        editEmail = fragmentView.findViewById(R.id.editEmail);

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        nameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameTextView.setVisibility(View.GONE);
                nameEditText.setVisibility(View.VISIBLE);
                nameEditText.setText(nameTextView.getText());
                editName.setImageResource(R.drawable.ic_add_gray_24dp);
                n = true;
            }
        });
        editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(n){
                    nameTextView.setVisibility(View.VISIBLE);
                    nameEditText.setVisibility(View.GONE);
                    editName.setImageResource(R.drawable.ic_edit_black_24dp);
                    n = false;
                }
            }
        });

        positionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionTextView.setVisibility(View.GONE);
                positionEditText.setVisibility(View.VISIBLE);
                positionEditText.setText(positionTextView.getText());
                editPosition.setImageResource(R.drawable.ic_add_gray_24dp);
                p = true;
            }
        });
        editPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p){
                    positionTextView.setVisibility(View.VISIBLE);
                    positionEditText.setVisibility(View.GONE);
                    editPosition.setImageResource(R.drawable.ic_edit_black_24dp);
                    p = false;
                }
            }
        });

        studentNumberLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentNumberTextView.setVisibility(View.GONE);
                studentNumberEditText.setVisibility(View.VISIBLE);
                studentNumberEditText.setText(studentNumberTextView.getText());
                editStudentNumber.setImageResource(R.drawable.ic_add_gray_24dp);
                s = true;
            }
        });
        editStudentNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s){
                    studentNumberTextView.setVisibility(View.VISIBLE);
                    studentNumberEditText.setVisibility(View.GONE);
                    editStudentNumber.setImageResource(R.drawable.ic_edit_black_24dp);
                    s = false;
                }
            }
        });

        emailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailTextView.setVisibility(View.GONE);
                emailEditText.setVisibility(View.VISIBLE);
                emailEditText.setText(emailTextView.getText());
                editEmail.setImageResource(R.drawable.ic_add_gray_24dp);
                e = true;
            }
        });
        editEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e){
                    emailTextView.setVisibility(View.VISIBLE);
                    emailEditText.setVisibility(View.GONE);
                    editEmail.setImageResource(R.drawable.ic_edit_black_24dp);
                    e = false;
                }
            }
        });
        return fragmentView;
    }

    public void selectImage(){
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK);
        pickIntent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        startActivityForResult(chooserIntent, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                return;
            }
            InputStream inputStream = null;
            try {
                inputStream = Objects.requireNonNull(getContext()).getContentResolver().openInputStream(Objects.requireNonNull(data.getData()));
            } catch (FileNotFoundException e) {
                Toast.makeText(getContext(), "File not found exception", Toast.LENGTH_SHORT).show();
            }
            if(inputStream!=null){
                profileImage.setImageBitmap(BitmapFactory.decodeStream(inputStream));
            }else{
                Toast.makeText(getContext(), "inputStream is null", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    public ProfileFragment() {

    }
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

}