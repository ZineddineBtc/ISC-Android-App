package com.example.isc.Core.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.isc.Core.MyPost;
import com.example.isc.Core.MyUser;
import com.example.isc.Core.NonScrollListView;
import com.example.isc.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class ProfileFragment extends Fragment {

    private ImageView profileImage;
    private LinearLayout nameLayout, positionLayout, studentNumberLayout, emailLayout, postLayout;
    private TextView nameTextView, positionTextView, studentNumberTextView, emailTextView;
    private EditText nameEditText, positionEditText, studentNumberEditText, emailEditText;
    private ImageView editNameIV, editPositionIV, editStudentNumberIV, editEmailIV, showProfilePostIV;
    private static final int PICK_IMAGE = 1;

    private Boolean n=false, p=false, s=false, e=false, postIsVisible = false;

    FloatingActionButton profilePostsUpButton;
    ScrollView profileScrollView;

    NonScrollListView profilePostsListView;
    ProfilePostsListAdapter adapter;
    public static ArrayList<MyPost> postArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);

        profileImage = fragmentView.findViewById(R.id.profileImage);
        nameLayout = fragmentView.findViewById(R.id.profileNameLayout);
        positionLayout = fragmentView.findViewById(R.id.profilePositionLayout);
        studentNumberLayout = fragmentView.findViewById(R.id.profileStudentNumberLayout);
        emailLayout = fragmentView.findViewById(R.id.profileEmailLayout);
        postLayout = fragmentView.findViewById(R.id.profilePostLayout);

        nameTextView = fragmentView.findViewById(R.id.profileNameTextView);
        positionTextView = fragmentView.findViewById(R.id.profilePositionTextView);
        studentNumberTextView = fragmentView.findViewById(R.id.profileStudentNumberTextView);
        emailTextView = fragmentView.findViewById(R.id.profileEmailTextView);

        nameEditText = fragmentView.findViewById(R.id.nameEditText);
        positionEditText = fragmentView.findViewById(R.id.positionEditText);
        studentNumberEditText = fragmentView.findViewById(R.id.studentNumberEditText);
        emailEditText = fragmentView.findViewById(R.id.emailEditText);

        editNameIV = fragmentView.findViewById(R.id.editName);
        editPositionIV = fragmentView.findViewById(R.id.editPosition);
        editStudentNumberIV = fragmentView.findViewById(R.id.editStudentNumber);
        editEmailIV = fragmentView.findViewById(R.id.editEmail);
        showProfilePostIV = fragmentView.findViewById(R.id.showProfilePostIV);

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
                editNameIV.setImageResource(R.drawable.ic_add_gray_24dp);
                n = true;
            }
        });
        editNameIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(n){
                    nameTextView.setVisibility(View.VISIBLE);
                    nameEditText.setVisibility(View.GONE);
                    editNameIV.setImageResource(R.drawable.ic_edit_black_24dp);
                    n = false;
                }else{
                    nameTextView.setVisibility(View.GONE);
                    nameEditText.setVisibility(View.VISIBLE);
                    nameEditText.setText(nameTextView.getText());
                    editNameIV.setImageResource(R.drawable.ic_add_gray_24dp);
                    n = true;
                }
            }
        });

        positionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionTextView.setVisibility(View.GONE);
                positionEditText.setVisibility(View.VISIBLE);
                positionEditText.setText(positionTextView.getText());
                editPositionIV.setImageResource(R.drawable.ic_add_gray_24dp);
                p = true;
            }
        });
        editPositionIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p){
                    positionTextView.setVisibility(View.VISIBLE);
                    positionEditText.setVisibility(View.GONE);
                    editPositionIV.setImageResource(R.drawable.ic_edit_black_24dp);
                    p = false;
                }else{
                    positionTextView.setVisibility(View.GONE);
                    positionEditText.setVisibility(View.VISIBLE);
                    positionEditText.setText(positionTextView.getText());
                    editPositionIV.setImageResource(R.drawable.ic_add_gray_24dp);
                    p = true;
                }
            }
        });

        studentNumberLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentNumberTextView.setVisibility(View.GONE);
                studentNumberEditText.setVisibility(View.VISIBLE);
                studentNumberEditText.setText(studentNumberTextView.getText());
                editStudentNumberIV.setImageResource(R.drawable.ic_add_gray_24dp);
                s = true;
            }
        });
        editStudentNumberIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s){
                    studentNumberTextView.setVisibility(View.VISIBLE);
                    studentNumberEditText.setVisibility(View.GONE);
                    editStudentNumberIV.setImageResource(R.drawable.ic_edit_black_24dp);
                    s = false;
                }else{
                    studentNumberTextView.setVisibility(View.GONE);
                    studentNumberEditText.setVisibility(View.VISIBLE);
                    studentNumberEditText.setText(studentNumberTextView.getText());
                    editStudentNumberIV.setImageResource(R.drawable.ic_add_gray_24dp);
                    s = true;
                }
            }
        });

        emailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailTextView.setVisibility(View.GONE);
                emailEditText.setVisibility(View.VISIBLE);
                emailEditText.setText(emailTextView.getText());
                editEmailIV.setImageResource(R.drawable.ic_add_gray_24dp);
                e = true;
            }
        });
        editEmailIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e){
                    emailTextView.setVisibility(View.VISIBLE);
                    emailEditText.setVisibility(View.GONE);
                    editEmailIV.setImageResource(R.drawable.ic_edit_black_24dp);
                    e = false;
                }else{
                    emailTextView.setVisibility(View.GONE);
                    emailEditText.setVisibility(View.VISIBLE);
                    emailEditText.setText(emailTextView.getText());
                    editEmailIV.setImageResource(R.drawable.ic_add_gray_24dp);
                    e = true;
                }
            }
        });
        profilePostsUpButton = fragmentView.findViewById(R.id.profileUpButton);

        MyUser myUser0 = new MyUser(R.drawable.img0, "Bettouche", "Member");

        final MyPost myPost0 = new MyPost(
                myUser0,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc eget rhoncus erat, ac tristique nisi. Praesent ipsum erat, pulvinar pellentesque arcu quis, pellentesque maximus elit. Curabitur ullamcorper blandit magna in aliquet. In sit amet ipsum nec odio semper vehicula eu ut tellus. Ut quam libero, posuere id nisl tristique, dignissim ornare libero. Vestibulum arcu metus, convallis eu ipsum ac, lobortis vehicula ex. Vivamus sit amet velit nibh. Fusce ac lectus at augue mollis fringilla non nec odio. Nunc elementum suscipit egestas. Sed scelerisque posuere placerat. Proin a iaculis urna, in sagittis tortor. Morbi orci urna, venenatis ac rhoncus vel, volutpat sed lacus.",
                R.drawable.img1,
                "Communication",
                "@Anis",
                "#Injaz");
        final MyPost myPost1 = new MyPost(
                myUser0,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc eget rhoncus erat, ac tristique nisi. Praesent ipsum erat, pulvinar pellentesque arcu quis, pellentesque maximus elit. Curabitur ullamcorper blandit magna in aliquet. In sit amet ipsum nec odio semper vehicula eu ut tellus. Ut quam libero, posuere id nisl tristique, dignissim ornare libero. Vestibulum arcu metus, convallis eu ipsum ac, lobortis vehicula ex. Vivamus sit amet velit nibh. Fusce ac lectus at augue mollis fringilla non nec odio. Nunc elementum suscipit egestas. Sed scelerisque posuere placerat. Proin a iaculis urna, in sagittis tortor. Morbi orci urna, venenatis ac rhoncus vel, volutpat sed lacus.",
                R.drawable.img1,
                "Communication",
                "Anis",
                "#Injaz");
        final MyPost myPost2 = new MyPost(
                myUser0,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc eget rhoncus erat, ac tristique nisi. Praesent ipsum erat, pulvinar pellentesque arcu quis, pellentesque maximus elit. Curabitur ullamcorper blandit magna in aliquet. In sit amet ipsum nec odio semper vehicula eu ut tellus. Ut quam libero, posuere id nisl tristique, dignissim ornare libero. Vestibulum arcu metus, convallis eu ipsum ac, lobortis vehicula ex. Vivamus sit amet velit nibh. Fusce ac lectus at augue mollis fringilla non nec odio. Nunc elementum suscipit egestas. Sed scelerisque posuere placerat. Proin a iaculis urna, in sagittis tortor. Morbi orci urna, venenatis ac rhoncus vel, volutpat sed lacus.",
                R.drawable.img1,
                "Communication",
                "Anis",
                "#Injaz");
        final MyPost myPost3 = new MyPost(
                myUser0,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc eget rhoncus erat, ac tristique nisi. Praesent ipsum erat, pulvinar pellentesque arcu quis, pellentesque maximus elit. Curabitur ullamcorper blandit magna in aliquet. In sit amet ipsum nec odio semper vehicula eu ut tellus. Ut quam libero, posuere id nisl tristique, dignissim ornare libero. Vestibulum arcu metus, convallis eu ipsum ac, lobortis vehicula ex. Vivamus sit amet velit nibh. Fusce ac lectus at augue mollis fringilla non nec odio. Nunc elementum suscipit egestas. Sed scelerisque posuere placerat. Proin a iaculis urna, in sagittis tortor. Morbi orci urna, venenatis ac rhoncus vel, volutpat sed lacus.",
                R.drawable.img1,
                "Communication",
                "Anis",
                "#Injaz");
        postArrayList = new ArrayList<MyPost>(){{
            add(myPost0);
            add(myPost1);
            add(myPost2);
            add(myPost3);
        }};

        adapter = new ProfilePostsListAdapter(getContext(), R.layout.activity_profile_post_list_adapter, postArrayList);
        profilePostsListView = fragmentView.findViewById(R.id.profilePostsListView);
        profilePostsListView.setAdapter(adapter);
        profilePostsListView.setScrollContainer(false);

        postLayout = fragmentView.findViewById(R.id.profilePostLayout);
        postLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                if(!postIsVisible){
                    postIsVisible = true;
                    showProfilePostIV.setImageResource(R.drawable.ic_arrow_drop_up_24dp);
                    profilePostsListView.setVisibility(View.VISIBLE);
                    profilePostsUpButton.setVisibility(View.VISIBLE);
                }else{
                    postIsVisible = false;
                    showProfilePostIV.setImageResource(R.drawable.ic_arrow_drop_down_24dp);
                    profilePostsListView.setVisibility(View.GONE);
                    profilePostsUpButton.setVisibility(View.GONE);
                }
            }
        });
        profileScrollView = fragmentView.findViewById(R.id.profileScrollView);
        profilePostsUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileScrollView.fullScroll(ScrollView.FOCUS_UP);
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