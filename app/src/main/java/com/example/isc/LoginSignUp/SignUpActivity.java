package com.example.isc.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.isc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    EditText signUpEmail, signUpPassword, signUpConfirmPassword;
    //private ProgressDialog progressDialog;

    //private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Objects.requireNonNull(getSupportActionBar()).hide();
        /*
        firebaseAuth = FirebaseAuth.getInstance();

        //if getCurrentUser does not returns null
        if(firebaseAuth.getCurrentUser() != null){
            //that means user is already logged in => to CoreActivity
            finish();
            startActivity(new Intent(getApplicationContext(), CoreActivity.class));
        }
        */
        signUpEmail = findViewById(R.id.signUpEmail);
        signUpPassword = findViewById(R.id.signUpPassword);
        signUpConfirmPassword = findViewById(R.id.signUpConfirmPassword);

        //progressDialog = new ProgressDialog(this);

    }

    public void authenticateUser(View view){
        //getting email and password from edit texts
        String email = signUpEmail.getText().toString();
        String password  = signUpPassword.getText().toString();
        String confirmPassword  = signUpPassword.getText().toString();
        /*
        //checking if email and passwords are empty
        if(!isEmailValid(email)){
            Toast.makeText(this,"Invalid Email",Toast.LENGTH_LONG).show();
            return;
        }
        if(!confirmPassword.equals(password)){
            Toast.makeText(this,"Confirm Password does not match Password",Toast.LENGTH_LONG).show();
            return;
        }
        if(password.length()<7){
            Toast.makeText(this,"Password length should be greater than 7 characters",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog
        progressDialog.setMessage("Signing up...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), FinishSignUpActivity.class));
                        }else{
                            FirebaseAuthException e =  (FirebaseAuthException) task.getException();
                            if(e.getMessage() != null){
                                Toast.makeText(SignUpActivity.this, e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
        Toast.makeText(getApplicationContext(), "valid email/password", Toast.LENGTH_LONG).show();
        */
        startActivity(new Intent(getApplicationContext(), FinishSignUpActivity.class));
    }

    public void toLogin(View view){
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    public void toTermsAndConditions(View view){

    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
