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
import com.example.propertylisting.models.AddSociety;
import java.util.ArrayList;

public class SocietiesAdapter extends RecyclerView.Adapter<SocietiesAdapter.SocietyViewHolder>
{
    private static final String TAG = "SocietiesAdapter";

    private Context context;
    private ArrayList<AddSociety> societies = new ArrayList<>();

    public SocietiesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SocietyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_society_item, parent, false);
        return new SocietyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SocietyViewHolder holder, int position)
    {
        Log.d(TAG, "onBindViewHolder: called");
        AddSociety society = societies.get(position);

        holder.tvSellerName.setText(society.getSellerName());
        holder.tvContactNumber.setText(society.getNumber());
        holder.tvPropertyAddress.setText(society.getPropertyAddress());
        holder.tvSocietyName.setText(society.getSociety());
        holder.tvAskingPrice.setText(String.valueOf(society.getPrice()));
        holder.tvCondition.setText(society.getCondition());
        holder.tvFloor.setText(society.getFloor());
        holder.tvCategory.setText(society.getCategory());
        holder.tvCitySector.setText(String.format("%s %s", society.getSector(), society.getCity()));
    }

    @Override
    public int getItemCount()
    {
        return societies.size();
    }

    public class SocietyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvSellerName, tvContactNumber, tvPropertyAddress, tvSocietyName, tvAskingPrice, tvCondition, tvFloor, tvCategory, tvCitySector;
        public SocietyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvSellerName = itemView.findViewById(R.id.tvSellerName);
            tvContactNumber = itemView.findViewById(R.id.tvContactNumber);
            tvPropertyAddress = itemView.findViewById(R.id.tvPropertyAddress);
            tvSocietyName = itemView.findViewById(R.id.tvSocietyName);
            tvAskingPrice = itemView.findViewById(R.id.tvAskingPrice);
            tvCondition = itemView.findViewById(R.id.tvCondition);
            tvFloor = itemView.findViewById(R.id.tvFloor);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvCitySector = itemView.findViewById(R.id.tvCitySector);
        }
    }

    public void setSocieties(ArrayList<AddSociety> societies)
    {
        this.societies = societies;
        notifyDataSetChanged();
    }
}