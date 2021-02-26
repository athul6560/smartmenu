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
    private static int SPLASH_TIME_OUT = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i2 = new Intent(WelcomeActivity.this, DashboardActivity.class);
                startActivity(i2);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}