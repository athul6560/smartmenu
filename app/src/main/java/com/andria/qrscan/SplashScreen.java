package com.andria.qrscan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {


    private ImageView firstBox, secondBox, thirdBox;
    private GestureDetectorCompat gestureDetectorCompat;
    private final static int PERMISSION_REQUEST_CODE = 200;
    private final static int TIME = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstBox = findViewById(R.id.imageView2);
        secondBox = findViewById(R.id.imageView3);
        thirdBox = findViewById(R.id.imageView4);


        animation(firstBox);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animation(secondBox);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animation(thirdBox);
                    }
                }, TIME);
            }
        }, TIME);





        gestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());

        //push to check the branch creation

    }

    private void animation(ImageView firstbox) {
        firstbox.setVisibility(View.VISIBLE);
        ScaleAnimation fade_in = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        fade_in.setDuration(500);
        fade_in.setFillAfter(true);
        firstbox.startAnimation(fade_in);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        //handle 'swipe left' action only

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            if (event2.getY() < event1.getY()) {
                if (checkPermission()) {
                    Intent i2 = new Intent(SplashScreen.this, QRActivity.class);
                    startActivity(i2);
                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                    finish();

                } else {
                    requestPermission();
                }
            }

            return true;
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (checkPermission()) {


        } else {
            requestPermission();
        }

    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();

                    // main logic

                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(SplashScreen.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

}