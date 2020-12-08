package com.example.propertylisting.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.propertylisting.R;
import com.example.propertylisting.activities.AddBoothActivity;
import com.example.propertylisting.activities.AddHousingBoardActivity;
import com.example.propertylisting.activities.AddKothiActivity;
import com.example.propertylisting.activities.AddScoActivity;
import com.example.propertylisting.activities.AddSocietyActivity;
import com.example.propertylisting.activities.SearchBoothActivity;
import com.example.propertylisting.activities.SearchHBActivity;
import com.example.propertylisting.activities.SearchKothiActivity;
import com.example.propertylisting.activities.SearchScoActivity;
import com.example.propertylisting.activities.SearchSocietyActivity;

public class SearchPropertyDialog extends DialogFragment
{
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_search_property, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final Spinner spinnerSelectProperty = view.findViewById(R.id.spinnerSelectProperty);
        final TextView tvOkDismiss = view.findViewById(R.id.tvOkDismiss);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.properties));
        spinnerSelectProperty.setAdapter(adapter);

        tvOkDismiss.setOnClickListener(v -> {
            int position = spinnerSelectProperty.getSelectedItemPosition();
            executeIntentOnOptionSelect(position);
            dismiss();
        });

        builder.setOnKeyListener((dialog, keyCode, event) -> {
            if(keyCode == KeyEvent.KEYCODE_BACK) {
                dismiss();
                return true;
            } else return false;
        });

        builder.setView(view);
        setCancelable(false);

        return builder.create();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog)
    {
        super.onDismiss(dialog);
    }

    private void executeIntentOnOptionSelect(int position)
    {
        switch (position)
        {
            case 1: // On select: Society Flat
                Intent sf = new Intent(getActivity(), SearchSocietyActivity.class);
                startActivity(sf);
                dismiss();
                break;
            case 2: // On select: HousingBoard Flat
                Intent hb = new Intent(getActivity(), SearchHBActivity.class);
                startActivity(hb);
                dismiss();
                break;
            case 3: // On select: Kothi
                Intent intent = new Intent(getActivity(), SearchKothiActivity.class);
                startActivity(intent);
                dismiss();
                break;
            case 4: // On select: Sco
                Intent sco = new Intent(getActivity(), SearchScoActivity.class);
                startActivity(sco);
                dismiss();
                break;
            case 5: // On select: Booth
                Intent b = new Intent(getActivity(), SearchBoothActivity.class);
                startActivity(b);
                dismiss();
                break;
            default:
                Toast.makeText(getActivity(), "Please select a property to proceed !", Toast.LENGTH_SHORT).show();
        }
    }
}