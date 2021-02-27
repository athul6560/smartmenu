package com.andria.qrscan.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.andria.qrscan.Adapter.CategoryListAdapter;
import com.andria.qrscan.Adapter.ItemListAdapter;
import com.andria.qrscan.Model.ItemDetailModel;
import com.andria.qrscan.R;
import com.andria.qrscan.Utils.SmartMenuUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    ItemDetailModel item;
    List<ItemDetailModel>  itemDetailModel = new ArrayList<>();
    RecyclerView rl_category,rl_detail_cat;
    CategoryListAdapter categoryListAdapter;
    ItemListAdapter itemListAdapter;
    private ImageView qrBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        rl_category=findViewById(R.id.rl_category);
        qrBtn=findViewById(R.id.qr_btn);
        rl_detail_cat=findViewById(R.id.rl_detail_cat);
        qrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this,QRActivity.class));
                finish();
            }
        });
        String json = SmartMenuUtil.getItem(DashboardActivity.this);
        String formattedjson = json.replaceAll("\\s", "");

        try {
            JSONObject mainObject = new JSONObject(formattedjson);
            JSONArray array = mainObject.getJSONArray("Items");
            System.out.println("itemlength"+array.length());

            for (int i = 0; i < array.length(); i++) {
                item = new ItemDetailModel();

                JSONObject itemobject = array.getJSONObject(i);
                item.setCategory(itemobject.getString("category"));
                item.setDishName(itemobject.getString("dishName"));
                item.setImage_url(itemobject.getString("image_url"));
              //  item.setOffer_price(itemobject.getInt("offer_price"));
                item.setPrice(itemobject.getInt("price"));
                itemDetailModel.add(item);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("itemsize"+itemDetailModel.get(1).getImage_url());
        categoryListAdapter=new CategoryListAdapter(DashboardActivity.this,itemDetailModel);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rl_category.setLayoutManager(mLayoutManager);
        rl_category.setItemAnimator(new DefaultItemAnimator());
        rl_category.setAdapter(categoryListAdapter);
       //Toast.makeText(this, ""+itemDetailModel.get(0).getCategory(), Toast.LENGTH_SHORT).show();

        itemListAdapter=new ItemListAdapter(DashboardActivity.this,itemDetailModel.get(0).getDishName(),
                itemDetailModel.get(0).getPrice());
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        rl_detail_cat.setLayoutManager(mLayoutManager1);
        rl_detail_cat.setItemAnimator(new DefaultItemAnimator());
        rl_detail_cat.setAdapter(itemListAdapter);


    }
}