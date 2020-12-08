package com.example.propertylisting.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.propertylisting.R;
import com.example.propertylisting.models.AddBooth;
import java.util.ArrayList;

public class BoothAdapter extends RecyclerView.Adapter<BoothAdapter.BoothViewHolder>
{
    private static final String TAG = "BoothAdapter";

    private Context context;
    private ArrayList<AddBooth> booths = new ArrayList<>();

    public BoothAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BoothViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_booth_item, parent, false);
        return new BoothViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoothViewHolder holder, int position)
    {
        Log.d(TAG, "onBindViewHolder: called");
        AddBooth booth = booths.get(position);

        holder.tvSellerName.setText(booth.getSellerName());
        holder.tvContactNumber.setText(booth.getNumber());
        holder.tvPropertyAddress.setText(booth.getPropertyAddress());
        holder.tvAskingPrice.setText(String.valueOf(booth.getPrice()));
        holder.tvCondition.setText(booth.getCondition());
        holder.tvCitySector.setText(String.format("%s %s", booth.getSector(), booth.getCity()));
    }

    @Override
    public int getItemCount()
    {
        return booths.size();
    }

    public class BoothViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvSellerName, tvContactNumber, tvPropertyAddress, tvAskingPrice, tvCondition, tvCitySector;
        public BoothViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvSellerName = itemView.findViewById(R.id.tvSellerName);
            tvContactNumber = itemView.findViewById(R.id.tvContactNumber);
            tvPropertyAddress = itemView.findViewById(R.id.tvPropertyAddress);
            tvAskingPrice = itemView.findViewById(R.id.tvAskingPrice);
            tvCondition = itemView.findViewById(R.id.tvCondition);
            tvCitySector = itemView.findViewById(R.id.tvCitySector);
        }
    }

    public void setBooths(ArrayList<AddBooth> booths)
    {
        this.booths = booths;
        notifyDataSetChanged();
    }
}