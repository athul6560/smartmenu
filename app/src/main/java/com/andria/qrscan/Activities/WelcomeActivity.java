package com.andria.qrscan.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;


import com.andria.qrscan.Model.ItemDetailModel;
import com.andria.qrscan.Model.ItemModel;
import com.andria.qrscan.R;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 5000;
    ItemDetailModel item;
    List<ItemDetailModel> itemDetailModel = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        String json = SmartMenuUtil.getItem(WelcomeActivity.this);
        String formattedjson = json.replaceAll("\\s", "");

        try {
            JSONObject mainObject = new JSONObject(formattedjson);
            JSONArray array = mainObject.getJSONArray("Items");
            for (int i = 0; i < array.length(); i++) {
                item = new ItemDetailModel();
                JSONObject itemobject = array.getJSONObject(i);
                item.setCategory(itemobject.getString("category"));
                item.setDishName(itemobject.getString("dishName"));
                item.setImage_url(itemobject.getString("image_url"));
                item.setOffer_price(itemobject.getInt("offer_price"));
                item.setPrice(itemobject.getInt("price"));
                itemDetailModel.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, ""+itemDetailModel.get(0).getCategory(), Toast.LENGTH_SHORT).show();

      /*  for(int i=0;i<itemDetailModel.size();i++){
            System.out.println("size:-"+itemDetailModel.get(i).getCategory());
        }*/
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i2 = new Intent(WelcomeActivity.this, DashboardActivity.class);
                startActivity(i2);
                finish();
            }
        }, SPLASH_TIME_OUT);
*/

    }
}