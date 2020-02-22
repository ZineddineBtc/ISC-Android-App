package com.example.isc.Entry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.isc.R;

import java.util.Objects;

public class EntryActivity extends AppCompatActivity {

    LogoFragment logoFragment;
    SliderFragment sliderFragment;

    int displayedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        Objects.requireNonNull(getSupportActionBar()).hide();

        logoFragment = new LogoFragment();
        sliderFragment = new SliderFragment();

        Intent intent = getIntent();
        if (Objects.equals(intent.getStringExtra("interior"), "true")){
            openFragment(sliderFragment);
            displayedFragment = 1;
        }else{
            openFragment(logoFragment);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    openFragment(sliderFragment);
                    displayedFragment = 1;
                }
            }, 3000);
        }
    }
    public void openFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.entryFrameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
