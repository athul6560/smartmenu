package com.andria.qrscan.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andria.qrscan.Model.ItemDetailModel;
import com.andria.qrscan.R;
import com.bumptech.glide.Glide;


import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {
    Context context;
    List<ItemDetailModel> itemDetailModel;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txt_cat.setText(itemDetailModel.get(position).getCategory());
        Glide.with(context)
                .load(itemDetailModel.get(position).image_url)
                .centerCrop()
                .placeholder(R.drawable.no_img)
                .into(holder.img_cat);

        holder.ll_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, itemDetailModel.get(position).getCategory(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public int getItemCount() {
        return itemDetailModel.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txt_cat;
        private final ImageView img_cat;
        private final LinearLayout ll_category;

        public ViewHolder(View view) {
            super(view);
            txt_cat = (TextView) view.findViewById(R.id.txt_cat);
            img_cat = view.findViewById(R.id.img_cat);
            ll_category=view.findViewById(R.id.ll_category);

        }


    }

    public CategoryListAdapter(Context context, List<ItemDetailModel> itemDetailModel) {
        this.context = context;
        this.itemDetailModel = itemDetailModel;

    }

}
