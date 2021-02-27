package com.andria.qrscan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.andria.qrscan.Model.ItemDetailModel;
import com.andria.qrscan.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {
    Context context;

    String dishName;
    int dishPrice;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_layout, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.item_name.setText(dishName);
        holder.rupee_txt.setText(R.string.Rs);
        holder.rate_txt.setText(String.valueOf(dishPrice));





    }


    @Override
    public int getItemCount() {
        return 2;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView item_name,rupee_txt,rate_txt;


        public ViewHolder(View view) {
            super(view);
            item_name = (TextView) view.findViewById(R.id.item_name);
            rupee_txt = view.findViewById(R.id.rupee_txt);
            rate_txt = view.findViewById(R.id.rate_txt);

        }


    }

    public ItemListAdapter(Context context, String dishName, int dishPrice) {
        this.context = context;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
    }
}
