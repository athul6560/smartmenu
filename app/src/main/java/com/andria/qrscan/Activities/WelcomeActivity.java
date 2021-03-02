package com.andria.qrscan.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;


import com.andria.qrscan.Model.ItemDetailModel;
import com.andria.qrscan.Model.ItemModel;
import com.andria.qrscan.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        readfile();
       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i2 = new Intent(WelcomeActivity.this, DashboardActivity.class);
                startActivity(i2);
                finish();
            }
        }, SPLASH_TIME_OUT);*/

    }

    private void readfile() {
        //File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "menu.txt");
        String yourFilePath = WelcomeActivity.this.getExternalFilesDir(null) + "/" + "menu.txt";
        File file = new File(yourFilePath);
        if (file.exists()) {//File Exists};}

//Read text from file
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                br.close();
            } catch (IOException e) {
                System.out.println("sfdvsvedf"+e);
            }

            Toast.makeText(this, ""+text, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "no file", Toast.LENGTH_SHORT).show();
        }
    }


}