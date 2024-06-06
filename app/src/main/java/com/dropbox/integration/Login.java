package com.dropbox.integration;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dropbox.core.android.Auth;

public class Login extends AppCompatActivity {

    private static final String APP_KEY = "w60sjcvnud323w7";
    private static final int DROPBOX_AUTH_REQUEST_CODE = 101; // Define your custom request code here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Dropbox SDK
        Auth.startOAuth2Authentication(Login.this, APP_KEY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Handle Dropbox authentication callback
        if (requestCode == DROPBOX_AUTH_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Authentication successful, get access token
                String accessToken = Auth.getOAuth2Token();

                // Store the access token securely (e.g., in SharedPreferences)
                // Now you can use the access token to make Dropbox API requests

                // For demonstration, let's show the access token in a toast
                Toast.makeText(this, "Access Token: " + accessToken, Toast.LENGTH_LONG).show();
            } else {
                // Authentication failed or user canceled
                Toast.makeText(this, "Dropbox authentication failed.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
