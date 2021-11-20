package com.example.demoprojet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.demoprojet.Api.ApiClient;
import com.example.demoprojet.R;
import com.example.demoprojet.Request.LoginRequest;
import com.example.demoprojet.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {
    Button signIn, signUpp;
    EditText edEmail, edPassword;
    SharedPreferences sPref;
    final String saveg = "key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signIn = findViewById(R.id.btnSignIn);
        signUpp = findViewById(R.id.btnSignUpp);
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);

        signUpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edEmail.getText().toString())||TextUtils.isEmpty(edPassword.getText().toString())){
                    String message = "Заполните все поля";
                    ShowAlertDialogWindow(message);
                }else {
                    if (android.util.Patterns.EMAIL_ADDRESS.matcher(edEmail.getText().toString()).matches()){

                    loginUser();

                    }else{
                        String message = "Введите коректный Email";
                        ShowAlertDialogWindow(message);
                    }

                }
            }
        });

    }
    public void ShowAlertDialogWindow(String text){
        final AlertDialog alertDialog = new AlertDialog.Builder(SignIn.this).setMessage(text).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create();
        alertDialog.show();
    }
    public void  loginUser(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(edEmail.getText().toString());
        loginRequest.setPassword(edPassword.getText().toString());
        Call<LoginResponse> loginResponseCall = ApiClient.getLogin().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    LoginResponse loginResponse = response.body();

                    sPref = getSharedPreferences("pref", MODE_PRIVATE);
                    SharedPreferences.Editor ed = sPref.edit();
                    int message = loginResponse.getToken();
                    ed.putString(saveg, String.valueOf(message));
                    ed.apply();



                    Intent intent = new Intent(SignIn.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    String message = "Вы ввели неверные данные";
                    ShowAlertDialogWindow(message);

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message = "Вы ввели неверные данные";
                ShowAlertDialogWindow(message);
            }
        });
    }

   }