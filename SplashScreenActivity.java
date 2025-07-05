package com.example.bsn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Ensure this layout exists

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}