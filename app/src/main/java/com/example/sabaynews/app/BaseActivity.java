package com.example.sabaynews.app;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sabaynews.data.local.UserSharePreference;
import com.example.sabaynews.uis.LoginActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        if(UserSharePreference.getAccessToken(this).equals("")){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
    public void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }
}
