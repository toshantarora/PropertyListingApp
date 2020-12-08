package com.example.propertylisting.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.propertylisting.R;
import com.example.propertylisting.models.AddSco;
import java.util.ArrayList;

public class ScoAdapter extends RecyclerView.Adapter<ScoAdapter.ScoViewHolder>
{
    private static final String TAG = "ScoAdapter";

    private Context context;
    private ArrayList<AddSco> sco_s = new ArrayList<>();

    public ScoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ScoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_sco_item, parent, false);
        return new ScoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoViewHolder holder, int position)
    {
        Log.d(TAG, "onBindViewHolder: called");
        AddSco sco = sco_s.get(position);

        holder.tvSellerName.setText(sco.getSellerName());
        holder.tvContactNumber.setText(sco.getNumber());
        holder.tvPropertyAddress.setText(sco.getPropertyAddress());
        holder.tvAskingPrice.setText(String.valueOf(sco.getPrice()));
        holder.tvCondition.setText(sco.getCondition());
        holder.tvCitySector.setText(String.format("%s %s", sco.getSector(), sco.getCity()));
        holder.tvStorey.setText(sco.getStorey());
        holder.tvBasement.setText(sco.getBasement());

        if (sco.getForSale().equals("")) {
            holder.llForSale.setVisibility(View.GONE);
        } else {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.llBasement.getLayoutParams();
            params.setMargins(10, 4, 10, 0); // Left, Top, Right, Bottom
            holder.llBasement.setLayoutParams(params);

            holder.llForSale.setVisibility(View.VISIBLE);

            String forSale = sco.getForSale().trim();
            forSale = forSale.replace("  ", ", ");
            holder.tvForSale.setText(forSale);
        }
    }

    @Override
    public int getItemCount()
    {
        return sco_s.size();
    }

    public class ScoViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvSellerName, tvContactNumber, tvPropertyAddress, tvAskingPrice, tvCondition, tvStorey, tvCitySector, tvBasement, tvForSale;
        LinearLayout llForSale, llBasement;
        public ScoViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvSellerName = itemView.findViewById(R.id.tvSellerName);
            tvContactNumber = itemView.findViewById(R.id.tvContactNumber);
            tvPropertyAddress = itemView.findViewById(R.id.tvPropertyAddress);
            tvAskingPrice = itemView.findViewById(R.id.tvAskingPrice);
            tvCondition = itemView.findViewById(R.id.tvCondition);
            tvStorey = itemView.findViewById(R.id.tvStorey);
            tvCitySector = itemView.findViewById(R.id.tvCitySector);
            tvBasement = itemView.findViewById(R.id.tvBasement);
            tvForSale = itemView.findViewById(R.id.tvForSale);
            llForSale = itemView.findViewById(R.id.llForSale);
            llBasement = itemView.findViewById(R.id.llBasement);
        }
    }

    public void setScos(ArrayList<AddSco> sco_s)
    {
        this.sco_s = sco_s;
        notifyDataSetChanged();
    }
}