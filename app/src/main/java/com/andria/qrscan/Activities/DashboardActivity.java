package com.andria.qrscan.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.andria.qrscan.Adapter.CategoryListAdapter;
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
    RecyclerView rl_category;
    CategoryListAdapter categoryListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        rl_category=findViewById(R.id.rl_category);


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
    }
}