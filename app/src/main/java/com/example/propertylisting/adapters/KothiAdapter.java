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
import com.example.propertylisting.models.AddKothi;
import java.util.ArrayList;

public class KothiAdapter extends RecyclerView.Adapter<KothiAdapter.KothiViewHolder>
{
    private static final String TAG = "KothiAdapter";

    private Context context;
    private ArrayList<AddKothi> kothis = new ArrayList<>();

    public KothiAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public KothiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_kothi_item, parent, false);
        return new KothiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KothiViewHolder holder, int position)
    {
        Log.d(TAG, "onBindViewHolder: called");
        AddKothi kothi = kothis.get(position);

        holder.tvSellerName.setText(kothi.getSellerName());
        holder.tvContactNumber.setText(kothi.getNumber());
        holder.tvPropertyAddress.setText(kothi.getPropertyAddress());
        holder.tvAskingPrice.setText(String.valueOf(kothi.getPrice()));
        holder.tvCondition.setText(kothi.getCondition());
        holder.tvCitySector.setText(String.format("%s %s", kothi.getSector(), kothi.getCity()));
        holder.tvStorey.setText(kothi.getStorey());
        holder.tvBasement.setText(kothi.getBasement());

        if (kothi.getForSale().equals("")) {
            holder.llForSale.setVisibility(View.GONE);
        } else {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.llBasement.getLayoutParams();
            params.setMargins(10, 4, 10, 0); // Left, Top, Right, Bottom
            holder.llBasement.setLayoutParams(params);

            holder.llForSale.setVisibility(View.VISIBLE);

            String forSale = kothi.getForSale().trim();
            forSale = forSale.replace("  ", ", ");
            holder.tvForSale.setText(forSale);
        }
    }

    @Override
    public int getItemCount()
    {
        return kothis.size();
    }

    public class KothiViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvSellerName, tvContactNumber, tvPropertyAddress, tvAskingPrice, tvCondition, tvStorey, tvCitySector, tvBasement, tvForSale;
        LinearLayout llForSale, llBasement;
        public KothiViewHolder(@NonNull View itemView)
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

    public void setKothis(ArrayList<AddKothi> kothis)
    {
        this.kothis = kothis;
        notifyDataSetChanged();
    }
}