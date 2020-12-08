package com.example.propertylisting.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertylisting.R;
import com.example.propertylisting.database.DatabaseHandler;
import com.google.android.material.textfield.TextInputLayout;

public class ForgotPasswordActivity extends AppCompatActivity
{
    private static final String TAG = "ForgotPasswordActivity";

    private RelativeLayout oldPassRelLayout, newPassRelLayout;
    private TextInputLayout textInputUsername, textInputPhone, textInputNewPassword, textInputNewReType;
    private Button btnChangePassword, btnSetPassword;
    private TextView tvOptional, tvName;

    private String userName, phone;
    private DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate: started ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        setTitle("Forgot Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initViews();
        settingListeners();
    }

    private void initViews()
    {
        Log.d(TAG, "initViews: started");
        textInputUsername = findViewById(R.id.textInputUsername);
        textInputPhone = findViewById(R.id.textInputPhone);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        tvOptional = findViewById(R.id.tvOptional);
        textInputNewPassword = findViewById(R.id.textInputNewPassword);
        textInputNewReType = findViewById(R.id.textInputNewReType);
        tvName = findViewById(R.id.tvName);
        btnSetPassword = findViewById(R.id.btnSetPassword);
        oldPassRelLayout = findViewById(R.id.oldPassRelLayout);
        newPassRelLayout = findViewById(R.id.newPassRelLayout);
    }

    private void settingListeners()
    {
        btnChangePassword.setOnClickListener(v -> changePassword());
        tvOptional.setOnClickListener(v -> {
            Toast.makeText(this, "If you enter username then Username and Phone both will be considered.", Toast.LENGTH_SHORT).show();
        });

        btnSetPassword.setOnClickListener(v -> setNewPassword());
    }

    private void changePassword()
    {
        Log.d(TAG, "getPassword: called");
        userName = textInputUsername.getEditText().getText().toString().trim();
        phone = textInputPhone.getEditText().getText().toString().trim();

        if(userName.equals("") && !phone.equals(""))
        {
            if (db.findUserByPhone(phone)) {
                oldPassRelLayout.setVisibility(View.GONE);
                newPassRelLayout.setVisibility(View.VISIBLE);
                setName(db.getName(phone, null));
            }
            else Toast.makeText(this, "No such user !", Toast.LENGTH_SHORT).show();
        }
        if(!userName.equals("") && !phone.equals(""))
        {
            boolean check = db.ifUserExist(userName, phone, "mobile_no");
            if(check) {
                oldPassRelLayout.setVisibility(View.GONE);
                newPassRelLayout.setVisibility(View.VISIBLE);
                setName(db.getName(phone, userName));
            }
            else Toast.makeText(this, "No Such User !", Toast.LENGTH_SHORT).show();
        }
    }

    private void setNewPassword()
    {
        Log.d(TAG, "setNewPassword: called");
        String newPassword = textInputNewPassword.getEditText().getText().toString().trim();
        String retypePassword = textInputNewReType.getEditText().getText().toString().trim();

        if(newPassword.equals("") && retypePassword.equals("")) {
            Toast.makeText(this, "Please enter your new password !", Toast.LENGTH_SHORT).show();
        } else if(newPassword.length() < 5 && retypePassword.length() < 5) {
            Toast.makeText(this, "Password can be minimum of 5 digits !", Toast.LENGTH_SHORT).show();
        } else if (!newPassword.equals(retypePassword)) {
            Toast.makeText(this, "Password and Retype Password must be same !", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(userName.equals("") && !phone.equals(""))
            {
                String[] oldPassword = db.getOldPassword(phone, null);
                if(oldPassword[0].equals(newPassword) && oldPassword[1].equals(retypePassword)) {
                    Toast.makeText(this, "Cannot set old password. Please enter new one !", Toast.LENGTH_SHORT).show();
                } else {
                    int n = db.updatePassword(newPassword, retypePassword,"mobile_no = ?", new String[]{phone});
                    if(n > 0) {
                        Toast.makeText(this, "Password changed successfully.", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }
            }
            else if(!userName.equals("") && !phone.equals(""))
            {
                String[] oldPassword = db.getOldPassword(phone, null);
                if(oldPassword[0].equals(newPassword) && oldPassword[1].equals(retypePassword)) {
                    Toast.makeText(this, "Cannot set old password. Please enter new one !", Toast.LENGTH_SHORT).show();
                } else {
                    int n = db.updatePassword(newPassword, retypePassword, "mobile_no = ? and user_id = ?", new String[]{phone, userName});
                    if(n > 0) {
                        Toast.makeText(this, "Password changed successfully.", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }
            }
        }
    }

    private void setName(String name)
    {
        SpannableString content = new SpannableString(name);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tvName.setVisibility(View.VISIBLE);
        tvName.setText(content);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
