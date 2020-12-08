package com.example.propertylisting.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertylisting.R;
import com.example.propertylisting.database.DatabaseHandler;
import com.example.propertylisting.models.AddHousingBoard;
import com.example.propertylisting.models.AddSociety;
import com.example.propertylisting.utility.Const;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AddHousingBoardActivity extends AppCompatActivity
{
    private static final String TAG = "AddHousingBoardActivity";

    private TextInputLayout textInputSellerName, textInputContactNumber, textInputPropertyAddress, textInputAskPrice;
    private Spinner spinnerCity;
    private Button btnSubmitHB;
    private RadioGroup radioCondition, radioFloor, radioCategory;
    private TextView tvCitySector;

    private DatabaseHandler db = new DatabaseHandler(this);
    private String sector, string;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_housing_board);

        setTitle("HousingBoard Flat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initViews();
        settingListeners();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.locality));
        spinnerCity.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }

    private void submitHousingBoard()
    {
        Log.d(TAG, "submitHousingBoard: called");

        String sellerName = textInputSellerName.getEditText().getText().toString().trim();
        String contactNumber = textInputContactNumber.getEditText().getText().toString().trim();
        String propertyAddress = textInputPropertyAddress.getEditText().getText().toString().trim();
        String askPrice = textInputAskPrice.getEditText().getText().toString().trim();

        int position = spinnerCity.getSelectedItemPosition();
        String city = "";
        if(position > 0) {
            city = spinnerCity.getSelectedItem().toString().trim();
        }

        String floor = getRadioSelect(Const.FLOOR);
        String condition = getRadioSelect(Const.CONDITION);
        String category = getRadioSelect(Const.CATEGORY);

        if(sellerName.matches("") && contactNumber.matches("") && propertyAddress.matches("") && askPrice.matches(""))
        {
            Toast.makeText(this, "Please fill the fields to submit !", Toast.LENGTH_SHORT).show();
        }
        else if(sellerName.matches("") || contactNumber.matches("") || propertyAddress.matches("") || askPrice.matches(""))
        {
            Toast.makeText(this, "Please fill up all the fields !", Toast.LENGTH_SHORT).show();
        }
        else if(!sellerName.matches("") && !contactNumber.matches("") && !propertyAddress.matches("") &&
                !askPrice.matches("") && position == 0)
        {
            Toast.makeText(this, "Please select city !", Toast.LENGTH_SHORT).show();
        }
        else if(!sellerName.matches("") && !contactNumber.matches("") && !propertyAddress.matches("") &&
                !askPrice.matches(""))
        {
            if(contactNumber.length() < 10) {
                Toast.makeText(this, "Mobile Number can be of 10 digits !", Toast.LENGTH_SHORT).show();
            }
            else
            {
                double price = Double.parseDouble(askPrice);
                AddHousingBoard housingBoard = new AddHousingBoard(sellerName, propertyAddress, city, sector, condition, category,  floor, contactNumber, price);
                long check = db.newHb(housingBoard);
                if(check > 0) {
                    Toast.makeText(this, "Details Submitted Successfully.", Toast.LENGTH_SHORT).show();
                    clearFields();
                    textInputSellerName.requestFocus();
                } else {
                    Toast.makeText(this, "Details Submitted UnSuccessfully !", Toast.LENGTH_SHORT).show();
                    textInputSellerName.requestFocus();
                }
            }
        }
    }

    private String getRadioSelect(int value)
    {
        if(value == Const.CONDITION) {
            switch (radioCondition.getCheckedRadioButtonId()) {
                case R.id.rbNew:
                    return "New";
                case R.id.rbOld:
                    return "Old";
                case R.id.rbUnderConstruction:
                    return "Under Construction";
            }
            return "";
        } else if(value == Const.FLOOR) {
            switch(radioFloor.getCheckedRadioButtonId()){
                case R.id.rbFirst:
                    return "First";
                case R.id.rbSecond:
                    return "Second";
                case R.id.rbGround:
                    return "Ground";
                case R.id.rbTop:
                    return "Top";
            }
            return "";
        } else if(value == Const.CATEGORY) {
            switch(radioCategory.getCheckedRadioButtonId()){
                case R.id.rbHig:
                    return "HIG";
                case R.id.rbLig:
                    return "LIG";
                case R.id.rbMig:
                    return "MIG";
                case R.id.rbEws:
                    return "EWS";
            }
            return "";
        }
        return null;
    }

    private void initViews()
    {
        Log.d(TAG, "initViews: started");
        textInputSellerName = findViewById(R.id.textInputSellerName);
        textInputContactNumber = findViewById(R.id.textInputContactNumber);
        textInputPropertyAddress = findViewById(R.id.textInputPropertyAddress);
        textInputAskPrice = findViewById(R.id.textInputAskPrice);
        spinnerCity = findViewById(R.id.spinnerCity);
        btnSubmitHB = findViewById(R.id.btnSubmitHB);
        radioCondition = findViewById(R.id.radioCondition);
        radioFloor = findViewById(R.id.radioFloor);
        radioCategory = findViewById(R.id.radioCategory);
        tvCitySector = findViewById(R.id.tvCitySector);
    }

    private void settingListeners()
    {
        btnSubmitHB.setOnClickListener(v -> {
            submitHousingBoard();
        });

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String label = parent.getItemAtPosition(position).toString();
                if(label.equals("Select City")) {
                    tvCitySector.setText("");
                    tvCitySector.setVisibility(View.GONE);
                } else {
                    executeSinnerItem(label);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void executeSinnerItem(String label)
    {
        Log.d(TAG, "executeSinnerItem: called");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Cursor cursor;
        tvCitySector.setVisibility(View.VISIBLE);
        if(label.equals("Chandigarh"))
        {
            List<String> chandigarhLabel = new ArrayList<>();
            cursor = db.getChandigarh();
            while(cursor.moveToNext()) {
                chandigarhLabel.add(cursor.getString(cursor.getColumnIndex("sector")));
            }

            final CharSequence[] items = chandigarhLabel.toArray(new CharSequence[chandigarhLabel.size()]);

            builder.setTitle("Chandigarh");
            builder.setItems(items, (dialog, item) -> {
                // Do something with the selection
                sector = (String) items[item];
                string = sector+" "+label;
                tvCitySector.setText(string);
            });
        }

        if(label.equals("Panchkula"))
        {
            List<String> panchkulaLabel = new ArrayList<>();
            cursor = db.getPanchkula();
            int i=0;
            cursor.moveToFirst();
            while(cursor.moveToNext()) {
                panchkulaLabel.add(cursor.getString(cursor.getColumnIndex("sector")));
                i++;
            }

            final CharSequence[]  items = panchkulaLabel.toArray(new CharSequence[panchkulaLabel.size()]);
            builder.setTitle("Panchkula");
            builder.setItems(items, (dialog, item) -> {
                // Do something with the selection
                sector = (String) items[item];
                string = sector+" "+label;
                tvCitySector.setText(string);
            });
        }

        if(label.equals("Mohali"))
        {
            List<String> mohaliLabel = new ArrayList<>();
            cursor = db.getMohali();
            while(cursor.moveToNext()){
                mohaliLabel.add(cursor.getString(cursor.getColumnIndex("sector")));
            }

            final CharSequence[] items = mohaliLabel.toArray(new CharSequence[mohaliLabel.size()]);
            builder.setTitle("Mohali");
            builder.setItems(items, (dialog, item) -> {
                // Do something with the selection
                sector = (String) items[item];
                string = sector+" "+label;
                tvCitySector.setText(string);
            });
        }

        builder.create().show();
    }

    private void clearFields()
    {
        textInputSellerName.getEditText().setText("");
        textInputContactNumber.getEditText().setText("");
        textInputPropertyAddress.getEditText().setText("");
        spinnerCity.setSelection(0);
        textInputAskPrice.getEditText().setText("");
        radioCondition.check(R.id.rbNew);
        radioFloor.check(R.id.rbGround);
        radioCategory.check(R.id.rbHig);
        tvCitySector.setText("");
        tvCitySector.setVisibility(View.GONE);
    }
}