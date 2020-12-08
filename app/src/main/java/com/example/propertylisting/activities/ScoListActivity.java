package com.example.propertylisting.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.propertylisting.R;
import com.example.propertylisting.adapters.KothiAdapter;
import com.example.propertylisting.adapters.ScoAdapter;
import com.example.propertylisting.database.DatabaseHandler;
import com.example.propertylisting.models.AddKothi;
import com.example.propertylisting.models.AddSco;

import java.util.ArrayList;
import java.util.List;

public class ScoListActivity extends AppCompatActivity
{
    private static final String TAG = "ScoListActivity";

    private RecyclerView recyclerSco;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sco_list);

        setTitle("Sco List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerSco = findViewById(R.id.recyclerSco);
        DatabaseHandler db = new DatabaseHandler(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerSco.setLayoutManager(linearLayoutManager);

        ScoAdapter adapter = new ScoAdapter(this);
        recyclerSco.setAdapter(adapter);

        List<AddSco> sco_s = db.getSco();
        adapter.setScos((ArrayList<AddSco>) sco_s);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}
