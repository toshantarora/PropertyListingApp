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
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.propertylisting.R;
import com.example.propertylisting.database.DatabaseHandler;
import com.example.propertylisting.models.AddKothi;
import com.example.propertylisting.models.AddSco;
import com.example.propertylisting.utility.Const;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AddScoActivity extends AppCompatActivity
{
    private static final String TAG = "AddScoActivity";

    private TextInputLayout textInputSellerName, textInputContactNumber, textInputPropertyAddress, textInputAskPrice;
    private Spinner spinnerCity;
    private Button btnSubmitSco;
    private RadioGroup radioCondition, radioStorey;
    private CheckBox checkBoxBasement, checkboxFull, checkboxGround, checkboxFirst, checkboxSecond;
    private TextView tvCitySector;

    private DatabaseHandler db = new DatabaseHandler(this);
    private String sector, string, sBasement = "", sForSale = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sco);

        setTitle("Sco");
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

    private void submitSco()
    {
        Log.d(TAG, "submitKothi: called");
        String sellerName = textInputSellerName.getEditText().getText().toString().trim();
        String contactNumber = textInputContactNumber.getEditText().getText().toString().trim();
        String propertyAddress = textInputPropertyAddress.getEditText().getText().toString().trim();
        String askPrice = textInputAskPrice.getEditText().getText().toString().trim();

        int position = spinnerCity.getSelectedItemPosition();
        String city = "";
        if(position > 0) {
            city = spinnerCity.getSelectedItem().toString().trim();
        }

        String storey = getRadioSelect(Const.STOREY);
        String condition = getRadioSelect(Const.CONDITION);
        setCheckBoxValue();

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
                AddSco sco = new AddSco(sellerName, propertyAddress, city, sector, condition, storey, sBasement, sForSale, contactNumber, price);
                long check = db.newSco(sco);
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
        } else if(value == Const.STOREY) {
            switch(radioStorey.getCheckedRadioButtonId()){
                case R.id.rbSingle:
                    return "Single";
                case R.id.rbDouble:
                    return "Double";
                case R.id.rbTriple:
                    return "Triple";
            }
            return "";
        }
        return null;
    }

    private void setCheckBoxValue()
    {
        if(checkboxFull.isChecked()) {
            if(!sForSale.contains("Full")) {
                sForSale += " Full ";
            }
        } else {
            if(!sForSale.trim().isEmpty()) {
                sForSale = removeWord(sForSale, "Full");
            } else sForSale = "";
        }

        if(checkboxGround.isChecked()) {
            if(!sForSale.contains("Ground")) {
                sForSale +=" Ground ";
            }
        }  else {
            if(!sForSale.trim().isEmpty()) {
                sForSale = removeWord(sForSale, "Ground");
            } else sForSale = "";
        }

        if(checkboxFirst.isChecked()) {
            if(!sForSale.contains("First")) {
                sForSale +=" First ";
            }
        } else {
            if(!sForSale.trim().isEmpty()) {
                sForSale = removeWord(sForSale, "First");
            } else sForSale = "";
        }

        if(checkboxSecond.isChecked()) {
            if(!sForSale.contains("Second")) {
                sForSale +=" Second ";
            }
        } else {
            if(!sForSale.trim().isEmpty()) {
                sForSale = removeWord(sForSale, "Second");
            } else sForSale = "";
        }

        if(checkBoxBasement.isChecked()) {
            sBasement = "Yes";
        } else {sBasement = "No";}
    }

    private void initViews()
    {
        Log.d(TAG, "initViews: started");
        textInputSellerName = findViewById(R.id.textInputSellerName);
        textInputContactNumber = findViewById(R.id.textInputContactNumber);
        textInputPropertyAddress = findViewById(R.id.textInputPropertyAddress);
        textInputAskPrice = findViewById(R.id.textInputAskPrice);
        spinnerCity = findViewById(R.id.spinnerCity);
        btnSubmitSco = findViewById(R.id.btnSubmitSco);
        radioCondition = findViewById(R.id.radioCondition);
        radioStorey = findViewById(R.id.radioStorey);
        checkBoxBasement = findViewById(R.id.checkboxBasement);
        checkboxFull = findViewById(R.id.checkboxFull);
        checkboxGround = findViewById(R.id.checkboxGround);
        checkboxFirst = findViewById(R.id.checkboxFirst);
        checkboxSecond = findViewById(R.id.checkboxSecond);
        tvCitySector = findViewById(R.id.tvCitySector);
    }

    private void settingListeners()
    {
        btnSubmitSco.setOnClickListener(v -> {
            submitSco();
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
        radioStorey.check(R.id.rbSingle);
        tvCitySector.setText("");
        tvCitySector.setVisibility(View.GONE);
        checkBoxBasement.setChecked(false);
        checkboxFull.setChecked(false);
        checkboxGround.setChecked(false);
        checkboxFirst.setChecked(false);
        checkboxSecond.setChecked(false);
    }

    private String removeWord(String string, String word)
    {
        if (string.contains(word)) {

            String tempWord = word + " ";
            string = string.replaceAll(tempWord, "");

            tempWord = " " + word;
            string = string.replaceAll(tempWord, "");
        }

        // Return the resultant string
        return string;
    }
}