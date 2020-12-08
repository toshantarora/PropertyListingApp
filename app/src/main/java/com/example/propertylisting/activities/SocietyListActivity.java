package com.example.propertylisting.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.propertylisting.R;
import com.example.propertylisting.adapters.SocietiesAdapter;
import com.example.propertylisting.database.DatabaseHandler;
import com.example.propertylisting.models.AddBooth;
import com.example.propertylisting.models.AddSociety;

import java.util.ArrayList;
import java.util.List;

public class SocietyListActivity extends AppCompatActivity
{
    private static final String TAG = "SocietyListActivity";

    private RecyclerView recyclerSocieties;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_list);

        setTitle("Societies List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerSocieties = findViewById(R.id.recyclerSocieties);
        DatabaseHandler db = new DatabaseHandler(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerSocieties.setLayoutManager(linearLayoutManager);

        SocietiesAdapter adapter = new SocietiesAdapter(this);
        recyclerSocieties.setAdapter(adapter);

        List<AddSociety> societies = db.getSocieties();
        adapter.setSocieties((ArrayList<AddSociety>) societies);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}
