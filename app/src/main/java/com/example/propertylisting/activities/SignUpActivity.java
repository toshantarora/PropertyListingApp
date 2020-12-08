package com.example.propertylisting.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertylisting.R;
import com.example.propertylisting.database.DatabaseHandler;
import com.example.propertylisting.dialogs.MyDatePickerDialog;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity
{
    private static final String TAG = "SignUpActivity";

    private TextInputLayout textInputFullName, textInputUsername, textInputDob, textInputPhone, textInputPassword, textInputReType;
    private Button btnSignUp;
    private TextView tvAlreadyLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();
        settingListeners();
    }

    private void getSignUp()
    {
        Log.d(TAG, "getSignUp: ");
        String fullName = textInputFullName.getEditText().getText().toString().trim();
        String userName = textInputUsername.getEditText().getText().toString().trim();
        String dateOfBirth = textInputDob.getEditText().getText().toString().trim();
        String phoneNumber = textInputPhone.getEditText().getText().toString().trim();
        String password = textInputPassword.getEditText().getText().toString().trim();
        String retypePassword = textInputReType.getEditText().getText().toString().trim();

        if(fullName.matches("") && userName.matches("") && dateOfBirth.matches("") &&
                phoneNumber.matches("") && password.matches("") && retypePassword.matches(""))
        {
            Toast.makeText(this, "Please fill the details to sign up !", Toast.LENGTH_SHORT).show();
        }
        else if(fullName.matches("") || userName.matches("") || dateOfBirth.matches("") ||
                phoneNumber.matches("") || password.matches("") || retypePassword.matches(""))
        {
            Toast.makeText(this, "Please fill entire details !", Toast.LENGTH_SHORT).show();
        }
        else if(!fullName.matches("") && !userName.matches("") && !dateOfBirth.matches("") &&
                !phoneNumber.matches("") && !password.matches("") && !retypePassword.matches(""))
        {
            if(userName.length() < 5) {
                Toast.makeText(this, "Username cannot be too small !", Toast.LENGTH_SHORT).show();
            } else if(phoneNumber.length() < 10) {
                Toast.makeText(this, "Mobile Number can be of 10 digits !", Toast.LENGTH_SHORT).show();
            } else if(password.length() < 5) {
                Toast.makeText(this, "Password can be minimum of 5 digits !", Toast.LENGTH_SHORT).show();
            } else if(!password.equals(retypePassword)) {
                Toast.makeText(this, "Password and Retype Password must be same !", Toast.LENGTH_SHORT).show();
            } else
            {
                DatabaseHandler db = new DatabaseHandler(this);

                String day = dateOfBirth.split("/")[0];
                String month = dateOfBirth.split("/")[1];
                String year = dateOfBirth.split("/")[2];
                dateOfBirth = day+month+year;

                long result = db.insertSignup(fullName, userName, password, retypePassword, dateOfBirth, phoneNumber);
                if(result > 0) {
                    Toast.makeText(this, "Signed Up Successfully.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Sign Up UnSuccessful !", Toast.LENGTH_SHORT).show();
                    textInputFullName.requestFocus();
                }
            }
        }
    }

    private void initViews()
    {
        Log.d(TAG, "initViews: started");
        textInputFullName = findViewById(R.id.textInputFullName);
        textInputUsername = findViewById(R.id.textInputUsername);
        textInputDob = findViewById(R.id.textInputDob);
        textInputPhone = findViewById(R.id.textInputPhone);
        textInputPassword = findViewById(R.id.textInputPassword);
        textInputReType = findViewById(R.id.textInputReType);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvAlreadyLogin = findViewById(R.id.tvAlreadyLogin);
    }

    private void settingListeners()
    {
        textInputDob.getEditText().setOnClickListener(v ->
        {
            DialogFragment newFragment = new MyDatePickerDialog();
            newFragment.show(getSupportFragmentManager(), "date_picker");
        });

        // On Long Press Set Date Field Empty
        textInputDob.getEditText().setOnLongClickListener(v -> {
            textInputDob.getEditText().setText("");
            return true;
        });

        btnSignUp.setOnClickListener(v -> {
            getSignUp();
        });

        tvAlreadyLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}