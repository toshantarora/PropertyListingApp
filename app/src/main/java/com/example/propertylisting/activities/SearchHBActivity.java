package com.example.propertylisting.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.propertylisting.R;
import com.example.propertylisting.adapters.HBAdapter;
import com.example.propertylisting.adapters.SocietiesAdapter;
import com.example.propertylisting.database.DatabaseHandler;
import com.example.propertylisting.models.AddHousingBoard;
import com.example.propertylisting.models.AddSociety;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputLayout;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchHBActivity extends AppCompatActivity
{
    private static final String TAG = "SearchHBActivity";

    private TextInputLayout textInputSociety;
    private Spinner spinnerLocality;
    private Button btnSearchFlat;
    private RecyclerView recyclerSearch;
    private TextView tvMin, tvMax, tvCitySector;
    private SeekBar seekBarMin, seekBarMax;

    private DatabaseHandler db = new DatabaseHandler(this);
    private ArrayList<AddHousingBoard> housingBoards = new ArrayList<>();
    HBAdapter hbAdapter = new HBAdapter(this);

    private int startMin = 50000, startMax = 100000, endMin = 9000000, endMax = 10000000;
    private String sector, string, sCity;

    private BottomSheetBehavior bottomSheetBehavior;
    private LinearLayout llBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_h_b);

        setTitle("Housing Board Search");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initViews();
        setSeekBarFunctionality();
        settingListeners();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerSearch.setLayoutManager(linearLayoutManager);

        recyclerSearch.setAdapter(hbAdapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.search_locality));
        spinnerLocality.setAdapter(adapter);
    }

    private void displayAllCity(double min, double max)
    {
        Log.d(TAG, "displayAllSociety: called");

        Cursor cursor = db.getHbCityAll(max, min);
        displayDataIntoRecycler(cursor);
    }

    private void displayAllSector(double min, double max)
    {
        Log.d(TAG, "displayAllSector: called");

        Cursor cursor = db.getHbSectorAll(sCity, max, min);
        displayDataIntoRecycler(cursor);
    }

    private void displaySectorCity(double min, double max)
    {
        Log.d(TAG, "displaySectorCity: called");

        Cursor cursor = db.getHbCitySector(sCity, sector, max, min);
        displayDataIntoRecycler(cursor);
    }

    private void settingListeners()
    {
        btnSearchFlat.setOnClickListener(v ->
        {
            double min = 0, max = 0;
            try {
                min = getDoubleFromString(tvMin.getText().toString());
                max = getDoubleFromString(tvMax.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(sCity.equals("All"))
            {
                housingBoards.clear();
                hbAdapter.notifyDataSetChanged();
                displayAllCity(min, max);
            }
            else if(sector.equals("All"))
            {
                housingBoards.clear();
                hbAdapter.notifyDataSetChanged();
                displayAllSector(min, max);
            }
            else
            {
                housingBoards.clear();
                hbAdapter.notifyDataSetChanged();
                displaySectorCity(min, max);
            }
        });

        spinnerLocality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String label = parent.getItemAtPosition(position).toString();
                if(label.equals("Select City")) {
                    tvCitySector.setText("");
                    tvCitySector.setVisibility(View.GONE);
                } else if(label.equals("All")) {
                    sCity = label;
                } else {
                    sCity = label;
                    executeSpinnerItem(label);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void executeSpinnerItem(String label)
    {
        Log.d(TAG, "executeSpinnerItem: called");

        AlertDialog.Builder builder = new AlertDialog.Builder(SearchHBActivity.this);
        Cursor c;
        tvCitySector.setVisibility(View.VISIBLE);
        if(label.equals("Chandigarh"))
        {
            List<String> chandigarhLabel = new ArrayList<>();
            c = db.getChandigarh();

            while(c.moveToNext()){
                chandigarhLabel.add(c.getString(c.getColumnIndex("sector")));
            }
            final CharSequence[] items = chandigarhLabel.toArray(new CharSequence[chandigarhLabel.size()]);
            builder.setTitle("Chandigarh");

            builder.setItems(items, (dialog, item) -> {
                // Do something with the selection
                sector = (String) items[item];
                string = sector+" "+ label;
                tvCitySector.setText(string);
            });
        }

        if(label.equals("Panchkula"))
        {
            List<String> panchkulaLabel = new ArrayList<>();
            c = db.getPanchkula();
            int i=0;
            c.moveToFirst();

            while(c.moveToNext()){
                panchkulaLabel.add(c.getString(c.getColumnIndex("sector")));
                i++;
            }

            final CharSequence[]  items = panchkulaLabel.toArray(new CharSequence[panchkulaLabel.size()]);
            builder.setTitle("Panchkula");

            builder.setItems(items, (dialog, item) -> {
                // Do something with the selection
                sector = (String) items[item];
                string = sector+" "+ label;
                tvCitySector.setText(string);
            });
        }

        if(label.equals("Mohali"))
        {
            List<String> mohaliLabel= new ArrayList<>();
            c = db.getMohali();

            while(c.moveToNext()){
                mohaliLabel.add(c.getString(c.getColumnIndex("sector")));
            }

            final CharSequence[]  items=mohaliLabel.toArray(new CharSequence[mohaliLabel.size()]);
            builder.setTitle("Mohali");

            builder.setItems(items, (dialog, item) -> {
                // Do something with the selection
                sector = (String) items[item];
                string = sector+" "+ label;
                tvCitySector.setText(string);
            });
        }
        builder.create().show();
    }

    private void initViews()
    {
        Log.d(TAG, "initViews: started");
        textInputSociety = findViewById(R.id.textInputSociety);
        spinnerLocality = findViewById(R.id.spinnerLocality);
        btnSearchFlat = findViewById(R.id.btnSearchFlat);
        recyclerSearch = findViewById(R.id.recyclerSearch);
        tvMax = findViewById(R.id.tvMax);
        tvMin = findViewById(R.id.tvMin);
        seekBarMin = findViewById(R.id.seekBarMin);
        seekBarMax = findViewById(R.id.seekBarMax);
        tvCitySector = findViewById(R.id.tvCitySector);
        llBottomSheet = findViewById(R.id.llBottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);
    }

    private void setSeekBarFunctionality()
    {
        seekBarMin.setMax(endMin);
        seekBarMax.setMax(endMax);

        String min = NumberFormat.getNumberInstance(Locale.getDefault()).format(startMin);
        tvMin.setText(min);
        String max = NumberFormat.getNumberInstance(Locale.getDefault()).format(startMax);
        tvMax.setText(max);

        seekBarMin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if(startMin > progress) {
                    String numberFormat = NumberFormat.getNumberInstance(Locale.getDefault()).format(startMin);
                    tvMin.setText(numberFormat);
                } else {
                    String numberFormat = NumberFormat.getNumberInstance(Locale.getDefault()).format(progress);
                    tvMin.setText(numberFormat);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                try
                {
                    int min = getIntFromString(tvMin.getText().toString());
                    int max = getIntFromString(tvMax.getText().toString());
                    if(min > max) {
                        seekBarMax.setProgress(min+500000);
                    }
                } catch (ParseException e) {e.printStackTrace();}
            }
        });

        seekBarMax.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if(startMax > progress) {
                    String numberFormat = NumberFormat.getNumberInstance(Locale.getDefault()).format(startMax);
                    tvMax.setText(numberFormat);
                } else {
                    String numberFormat = NumberFormat.getNumberInstance(Locale.getDefault()).format(progress);
                    tvMax.setText(numberFormat);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                try
                {
                    int min = getIntFromString(tvMin.getText().toString());
                    int max = getIntFromString(tvMax.getText().toString());
                    if(max < min) {
                        seekBarMin.setProgress(max-500000);
                    }
                } catch (ParseException e) {e.printStackTrace();}
            }
        });
    }

    private int getIntFromString(final String value) throws ParseException
    {
        String newvalue = value.replace(",","");
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        Number number = format.parse(newvalue);
        return number.intValue();
    }

    private double getDoubleFromString(final String value) throws ParseException
    {
        String newvalue = value.replace(",","");
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        Number number = format.parse(newvalue);
        return number.doubleValue();
    }

    private void displayDataIntoRecycler(Cursor cursor)
    {
        if(cursor.moveToFirst())
        {
            do {
                String sellerName = cursor.getString(1);
                String pNumber = cursor.getString(2);
                String propertyAddress = cursor.getString(3);
                String city = cursor.getString(4);
                String sector = cursor.getString(5);
                String condition = cursor.getString(6);
                double price = Integer.parseInt(cursor.getString(7));
                String floor = cursor.getString(8);
                String category = cursor.getString(9);

                AddHousingBoard object = new AddHousingBoard(sellerName, propertyAddress, city, sector, condition, category, floor, pNumber, price);
                housingBoards.add(object);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        if(housingBoards.size() > 0)
        {
            if(bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                hbAdapter.setHousingBoards(housingBoards);
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        }
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}