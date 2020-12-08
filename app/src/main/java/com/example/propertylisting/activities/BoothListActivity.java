package com.example.propertylisting.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.propertylisting.R;
import com.example.propertylisting.adapters.BoothAdapter;
import com.example.propertylisting.adapters.KothiAdapter;
import com.example.propertylisting.database.DatabaseHandler;
import com.example.propertylisting.models.AddBooth;
import com.example.propertylisting.models.AddKothi;

import java.util.ArrayList;
import java.util.List;

public class BoothListActivity extends AppCompatActivity
{
    private static final String TAG = "BoothListActivity";

    private RecyclerView recyclerBooth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booth_list);

        setTitle("Booth List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerBooth = findViewById(R.id.recyclerBooth);
        DatabaseHandler db = new DatabaseHandler(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerBooth.setLayoutManager(linearLayoutManager);

        BoothAdapter adapter = new BoothAdapter(this);
        recyclerBooth.setAdapter(adapter);

        List<AddBooth> booths = db.getBooths();
        adapter.setBooths((ArrayList<AddBooth>) booths);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}