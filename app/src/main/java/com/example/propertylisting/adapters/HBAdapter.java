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
import com.example.propertylisting.models.AddHousingBoard;
import java.util.ArrayList;

public class HBAdapter extends RecyclerView.Adapter<HBAdapter.HBViewHolder>
{
    private static final String TAG = "HBAdapter";

    private Context context;
    private ArrayList<AddHousingBoard> housingBoards = new ArrayList<>();

    public HBAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_hb_item, parent, false);
        return new HBViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HBViewHolder holder, int position)
    {
        Log.d(TAG, "onBindViewHolder: called");
        AddHousingBoard housingBoard = housingBoards.get(position);

        holder.tvSellerName.setText(housingBoard.getSellerName());
        holder.tvContactNumber.setText(housingBoard.getNumber());
        holder.tvPropertyAddress.setText(housingBoard.getPropertyAddress());
        holder.tvAskingPrice.setText(String.valueOf(housingBoard.getPrice()));
        holder.tvCondition.setText(housingBoard.getCondition());
        holder.tvFloor.setText(housingBoard.getFloor());
        holder.tvCategory.setText(housingBoard.getCategory());
        holder.tvCitySector.setText(String.format("%s %s", housingBoard.getSector(), housingBoard.getCity()));
    }

    @Override
    public int getItemCount()
    {
        return housingBoards.size();
    }

    public class HBViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvSellerName, tvContactNumber, tvPropertyAddress, tvAskingPrice, tvCondition, tvFloor, tvCategory, tvCitySector;
        public HBViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvSellerName = itemView.findViewById(R.id.tvSellerName);
            tvContactNumber = itemView.findViewById(R.id.tvContactNumber);
            tvPropertyAddress = itemView.findViewById(R.id.tvPropertyAddress);
            tvAskingPrice = itemView.findViewById(R.id.tvAskingPrice);
            tvCondition = itemView.findViewById(R.id.tvCondition);
            tvFloor = itemView.findViewById(R.id.tvFloor);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvCitySector = itemView.findViewById(R.id.tvCitySector);
        }
    }

    public void setHousingBoards(ArrayList<AddHousingBoard> housingBoards)
    {
        this.housingBoards = housingBoards;
        notifyDataSetChanged();
    }
}