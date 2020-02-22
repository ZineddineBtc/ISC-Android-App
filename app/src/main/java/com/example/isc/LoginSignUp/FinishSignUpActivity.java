package com.example.isc.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.isc.Core.CoreActivity;
import com.example.isc.R;

import java.util.ArrayList;
import java.util.Objects;

public class FinishSignUpActivity extends AppCompatActivity {

    EditText signUpFullName;
    Spinner departmentSpinner;
    RadioGroup positionRadio;

    ArrayList<String> departments;

    //FirebaseAuth firebaseAuth;
    //FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_sign_up);

        Objects.requireNonNull(getSupportActionBar()).hide();

        //firebaseAuth = FirebaseAuth.getInstance();
        //firebaseUser = firebaseAuth.getCurrentUser();

        signUpFullName = findViewById(R.id.signUpFullName);
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
}
