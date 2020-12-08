package com.example.propertylisting.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.propertylisting.R;
import com.example.propertylisting.adapters.HBAdapter;
import com.example.propertylisting.adapters.KothiAdapter;
import com.example.propertylisting.database.DatabaseHandler;
import com.example.propertylisting.models.AddHousingBoard;
import com.example.propertylisting.models.AddKothi;

import java.util.ArrayList;
import java.util.List;

public class KothiListActivity extends AppCompatActivity
{
    private static final String TAG = "KothiListActivity";

    private RecyclerView recyclerKothi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kothi_list);

        setTitle("Kothi List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerKothi = findViewById(R.id.recyclerKothi);
        DatabaseHandler db = new DatabaseHandler(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerKothi.setLayoutManager(linearLayoutManager);

        KothiAdapter adapter = new KothiAdapter(this);
        recyclerKothi.setAdapter(adapter);

        List<AddKothi> kothis = db.getKothis();
        adapter.setKothis((ArrayList<AddKothi>) kothis);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}
