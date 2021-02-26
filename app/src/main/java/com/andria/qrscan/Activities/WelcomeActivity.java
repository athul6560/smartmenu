package com.andria.qrscan.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.andria.qrscan.Activities.Model.ItemDetailModel;
import com.andria.qrscan.Activities.Model.ItemModel;
import com.andria.qrscan.R;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 5000;
    ItemModel myObject = new ItemModel();
    List<ItemDetailModel> itemDetailModel=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Gson gson = new Gson();
        String json = SmartMenuUtil.getItem(WelcomeActivity.this);


        myObject= gson.fromJson(json, ItemModel.class);
        itemDetailModel=myObject.getItems();



        for(int i=0;i<itemDetailModel.size();i++){
            System.out.println("size:-"+itemDetailModel.get(i).getCategory());
        }
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