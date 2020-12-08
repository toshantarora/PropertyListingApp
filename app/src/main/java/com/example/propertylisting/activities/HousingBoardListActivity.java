package com.example.propertylisting.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.propertylisting.R;
import com.example.propertylisting.adapters.HBAdapter;
import com.example.propertylisting.adapters.SocietiesAdapter;
import com.example.propertylisting.database.DatabaseHandler;
import com.example.propertylisting.models.AddHousingBoard;
import com.example.propertylisting.models.AddSociety;

import java.util.ArrayList;
import java.util.List;

public class HousingBoardListActivity extends AppCompatActivity
{
    private static final String TAG = "HousingBoardListActivit";

    private RecyclerView recyclerHB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing_board_list);

        setTitle("Housing Board List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerHB = findViewById(R.id.recyclerHB);
        DatabaseHandler db = new DatabaseHandler(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerHB.setLayoutManager(linearLayoutManager);

        HBAdapter adapter = new HBAdapter(this);
        recyclerHB.setAdapter(adapter);

        List<AddHousingBoard> housingBoards = db.getHousingBoard();
        adapter.setHousingBoards((ArrayList<AddHousingBoard>) housingBoards);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}