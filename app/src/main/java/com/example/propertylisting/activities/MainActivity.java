package com.example.propertylisting.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.propertylisting.R;
import com.example.propertylisting.app.SessionManager;
import com.example.propertylisting.dialogs.AddPropertyDialog;
import com.example.propertylisting.dialogs.ListPropertyDialog;
import com.example.propertylisting.dialogs.SearchPropertyDialog;
import com.example.propertylisting.utility.Utils;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";

    private CardView cvAddProperty, cvSearchProperty, cvListProperty;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Select Property Type");

        initViews();
        settingListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menuLogout:
                SessionManager sm = new SessionManager(this);
                sm.logoutUser();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.menuHelp:
                Toast.makeText(this, "☺ Help is Under Development ☺", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void initViews()
    {
        Log.d(TAG, "initViews: started");
        cvAddProperty = findViewById(R.id.cvAddProperty);
        cvSearchProperty = findViewById(R.id.cvSearchProperty);
        cvListProperty = findViewById(R.id.cvListProperty);

        Utils.applyOnClickEffect(cvAddProperty, cvSearchProperty, cvListProperty);
    }

    private void settingListeners()
    {
        cvAddProperty.setOnClickListener(v -> {
            AddPropertyDialog dialog = new AddPropertyDialog();
            dialog.show(getSupportFragmentManager(), "add_property_dialog");
        });

        cvSearchProperty.setOnClickListener(v -> {
            SearchPropertyDialog dialog = new SearchPropertyDialog();
            dialog.show(getSupportFragmentManager(), "search_property_dialog");
        });

        cvListProperty.setOnClickListener(v -> {
            ListPropertyDialog dialog = new ListPropertyDialog();
            dialog.show(getSupportFragmentManager(), "list_property_dialog");
        });
    }
}