package com.example.propertylisting.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertylisting.R;
import com.example.propertylisting.app.SessionManager;
import com.example.propertylisting.database.DatabaseHandler;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity
{
    private static final String TAG = "LoginActivity";

    private TextInputLayout textInputUsername, textInputPassword;
    private TextView tvForgotPassword, tvSignUp;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        settingListeners();
    }

    private void getLogin()
    {
        Log.d(TAG, "checkLogin: started");

        String username = textInputUsername.getEditText().getText().toString().trim();
        String password = textInputPassword.getEditText().getText().toString().trim();

        if(!username.matches("") && !password.matches("") && password.length() >= 5)
        {
            DatabaseHandler db = new DatabaseHandler(this);
            boolean check = db.ifUserExist(username, password, "password");
            if(check)
            {
                SessionManager session = new SessionManager(this);
                session.createLoginSession(username, password);

                Intent intent = new Intent().setClass(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Invalid Username and Password !", Toast.LENGTH_SHORT).show();
                textInputUsername.getEditText().setText("");
                textInputPassword.getEditText().setText("");
            }
        }
        else Toast.makeText(this, "Please enter username and password !", Toast.LENGTH_SHORT).show();
    }

    private void initViews()
    {
        Log.d(TAG, "initViews: started");
        textInputUsername = findViewById(R.id.textInputUsername);
        textInputPassword = findViewById(R.id.textInputPassword);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvSignUp = findViewById(R.id.tvSignUp);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void settingListeners()
    {
        btnLogin.setOnClickListener(v -> getLogin());

        tvSignUp.setOnClickListener(v -> {
            Intent intent = new Intent().setClass(this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });

        tvForgotPassword.setOnClickListener(v -> {
            Intent intent1 = new Intent().setClass(this, ForgotPasswordActivity.class);
            startActivity(intent1);
            finish();
        });
    }
}
