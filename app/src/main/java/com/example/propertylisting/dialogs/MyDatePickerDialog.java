package com.example.propertylisting.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.propertylisting.R;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDatePickerDialog extends DialogFragment
{
    private static final String TAG = "MyDatePicker";
    private int year = 0, month = 0, day = 0;
    private DatePickerDialog datePickerDialog;
    TextInputLayout textInputDob;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreateDialog: started");

        textInputDob = getActivity().findViewById(R.id.textInputDob);

        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        showDate(year, month, day);

        return datePickerDialog;
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, day) ->
    {
        String selectedDate = new StringBuilder().append(day).append("/").append((month+1)).append("/").append(year).toString();
        try {
            if(isValidDate(selectedDate)) {
                textInputDob.getEditText().setText(selectedDate);
            } else Toast.makeText(getContext(), "Date of Birth cannot be in the Future !", Toast.LENGTH_SHORT).show();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    };

    private void showDate(int year, int month, int day)
    {
        TextInputLayout textInputDob = getActivity().findViewById(R.id.textInputDob);

        if(textInputDob.getEditText().getText().toString().matches(""))
        {
            datePickerDialog = new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
        }
        else if(!textInputDob.getEditText().getText().toString().matches(""))
        {
            String date = textInputDob.getEditText().getText().toString();
            int textDay = Integer.parseInt(date.split("/")[0]);
            int textMonth = Integer.parseInt(date.split("/")[1]);
            int textYear = Integer.parseInt(date.split("/")[2]);

            datePickerDialog = new DatePickerDialog(getActivity(), dateSetListener, textYear, textMonth-1, textDay);
        }
    }

    private  boolean isValidDate(String pDateString) throws ParseException
    {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(pDateString);
        return new Date().after(date);
    }
}