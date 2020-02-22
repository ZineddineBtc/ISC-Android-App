package com.example.isc.LoginSignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.isc.Core.CoreActivity;
import com.example.isc.Entry.EntryActivity;
import com.example.isc.R;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    EditText loginEmail, loginPassword;
    Button loginButtton;
    //private ProgressDialog progressDialog;
    //private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Objects.requireNonNull(getSupportActionBar()).hide();
        /*
        firebaseAuth = FirebaseAuth.getInstance();
        //means user is already logged in
        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), CoreActivity.class));
        }
        */
        //progressDialog = new ProgressDialog(this);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginButtton = findViewById(R.id.loginButton);
        loginPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    loginUser(loginButtton);
                }
                return false;
            }
        });

    }

    public void loginUser(View view){
        String email = loginEmail.getText().toString();
        String password  = loginPassword.getText().toString();

        /*
        if(isEmailValid(email)){
            Toast.makeText(this,"Invalid Email",Toast.LENGTH_LONG).show();
            return;
        }

        if(!password.isEmpty()){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog
        progressDialog.setMessage("logging in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), CoreActivity.class));
                        }
                    }
                });
        */
        Toast.makeText(getApplicationContext(), "valid email/password", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), CoreActivity.class));
    }

    public void toMain(View view){
        Intent intent = new Intent(getApplicationContext(), EntryActivity.class);
        intent.putExtra("interior", "true");
        startActivity(intent);
    }
    public void toSignUp(View view){
        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
    }
    public void toTermsAndConditions(View view){

    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), EntryActivity.class);
        intent.putExtra("interior", "true");
        startActivity(intent);
    }
}
