package com.example.isc.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.isc.Core.CoreActivity;
import com.example.isc.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class FinishSignUpActivity extends AppCompatActivity {

    ImageView signUpProfileImage, addSignUpProfileImageIV;
    EditText signUpFullName, signUpStudentNumber;
    Spinner departmentSpinner;
    RadioGroup positionRadio;

    ArrayList<String> departments;

    public static final int PICK_IMAGE = 1;

    //FirebaseAuth firebaseAuth;
    //FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_sign_up);

        Objects.requireNonNull(getSupportActionBar()).hide();

        //firebaseAuth = FirebaseAuth.getInstance();
        //firebaseUser = firebaseAuth.getCurrentUser();

        signUpProfileImage = findViewById(R.id.signUpProfileImage);
        addSignUpProfileImageIV = findViewById(R.id.addSignUpProfileImageIV);
        signUpFullName = findViewById(R.id.signUpFullName);
        signUpStudentNumber = findViewById(R.id.signUpStudentNumber);
        departmentSpinner = findViewById(R.id.departmentSpinner);
        positionRadio = findViewById(R.id.position);

        departments = new ArrayList<>();
        departments.add("Media");
        departments.add("HR");
        departments.add("ER");
        departments.add("Tech");
        departments.add("Communication");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departments);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(dataAdapter);

        signUpProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
                addSignUpProfileImageIV.setVisibility(View.GONE);
            }
        });
    }

    public void signUpUser(View view){
        String name = signUpFullName.getText().toString();
        String department = departments.get(departmentSpinner.getSelectedItemPosition());
        int positionSelected = positionRadio.getCheckedRadioButtonId();
        /*
        if(!isValidName(name)){
            Toast.makeText(this,"Invalid Full Name",Toast.LENGTH_LONG).show();
            return;
        }

        if(department == null){
            Toast.makeText(this,"Please select a department",Toast.LENGTH_LONG).show();
            return;
        }


        if(positionSelected != 0 && positionSelected != 1){
            Toast.makeText(this,"Please select a position",Toast.LENGTH_LONG).show();
            return;
        }

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

        firebaseUser.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), CoreActivity.class));
                        }
                    }
                });

        Toast.makeText(getApplicationContext(), "valid email/password\nposition selected: "+positionSelected,
                Toast.LENGTH_LONG).show();
         */
        startActivity(new Intent(getApplicationContext(), CoreActivity.class));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    public void toLogin(View view){
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    public void toTermsAndConditions(View view){

    }

    public static boolean isValidName(String str) {
        if(str.length()<3){
            return false;
        }
        String expression = "^[a-zA-Z\\s]+";
        return str.matches(expression);
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
                Bitmap imageBitmap = BitmapFactory.decodeStream(inputStream);
                signUpProfileImage.setImageBitmap(imageBitmap);
            }else{
                Toast.makeText(getApplicationContext(), "inputStream is null", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
