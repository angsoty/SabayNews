package com.example.sabaynews.uis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sabaynews.API.APIClient;
import com.example.sabaynews.Interface.APIInterface;
import com.example.sabaynews.R;
import com.example.sabaynews.data.local.UserSharePreference;
import com.example.sabaynews.models.LoginRequest;
import com.example.sabaynews.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText username,password;
    private APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView create = findViewById(R.id.btnLogin);
        username= findViewById(R.id.username);
        password= findViewById(R.id.password);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        create.setOnClickListener(v -> {
            if (username.getText().toString().equals(""))
            {
                showMessage("Please input username");
                return;
            }
            if ( password.getText().toString().equals(""))
            {
                showMessage("Please input password");
            }
            login();
        });
    }
    private void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }
    private void login(){
        LoginRequest req= new LoginRequest();
        req.setPhoneNumber(username.getText().toString().trim());
        req.setPassword(password.getText().toString().trim());
        apiInterface.login(req).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    UserSharePreference.setToken(
                            response.body().getAccessToken(),
                            response.body().getRefreshToken(),
                            LoginActivity.this);
                    onBackPressed();
                }
                else
                {
                    showMessage("Your username and password incorrect");
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                showMessage(t.getLocalizedMessage());
            }
        });
    }
}