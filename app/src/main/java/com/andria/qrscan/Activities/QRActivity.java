package com.andria.qrscan.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.andria.qrscan.R;
import com.andria.qrscan.Utils.SmartMenuUtil;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;


import java.io.File;
import java.io.IOException;

public class QRActivity extends AppCompatActivity {
    RelativeLayout ll_qr;
    SurfaceView surfaceView;
    boolean alreadyExecuted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r);

        ll_qr = findViewById(R.id.ll_qr);
        surfaceView = (SurfaceView) findViewById(R.id.camerap);

        createcamerasourse();

    }


    private void createcamerasourse() {
        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this).build();
        final CameraSource cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1600, 1024)
                .build();
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                /*if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {*/
                if (ContextCompat.checkSelfPermission(QRActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                    return;
                }
                try {
                    cameraSource.start(surfaceView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodeSparseArray = detections.getDetectedItems();

                if (barcodeSparseArray.size() > 0) {
                    Barcode barcode = barcodeSparseArray.valueAt(0);
                    String json = barcode.displayValue;
                    System.out.println(json);

                    if (!alreadyExecuted) {

                        download(json);
                        alreadyExecuted = true;
                        // SmartMenuUtil.setItem(QRActivity.this,json);
                        Intent i2 = new Intent(QRActivity.this, WelcomeActivity.class);
                        startActivity(i2);

                        finish();
                    }
                }
            }
        });
    }


    private void download(String json) {

        String url = json;
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("Downloading...");
        request.setTitle("Download");
// in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "menu.txt");

// get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }


}