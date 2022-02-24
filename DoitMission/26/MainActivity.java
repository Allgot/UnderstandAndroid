package dev_allgot.understand.doitmission_26;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.util.concurrent.ListenableFuture;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    PreviewView cameraPreview;
    TextView coupon1Text;
    TextView coupon2Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraPreview = findViewById(R.id.cameraPreview);
        coupon1Text = findViewById(R.id.coupon1Text);
        coupon2Text = findViewById(R.id.coupon2Text);

        Button showButton = findViewById(R.id.showButton);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coupon1Text.setVisibility(View.VISIBLE);
                coupon2Text.setVisibility(View.VISIBLE);
            }
        });

        Button hideButton = findViewById(R.id.hideButton);
        hideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coupon1Text.setVisibility(View.INVISIBLE);
                coupon2Text.setVisibility(View.INVISIBLE);
            }
        });

        coupon1Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), coupon1Text.getText(), Toast.LENGTH_LONG).show();
            }
        });

        coupon2Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), coupon2Text.getText(), Toast.LENGTH_LONG).show();
            }
        });

        AndPermission.with(this)
                .runtime()
                .permission(Permission.CAMERA)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Toast.makeText(getApplicationContext(), "Permission granted: " + Permission.CAMERA, Toast.LENGTH_LONG).show();
                        startCamera();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Toast.makeText(getApplicationContext(), "Permission denied: " + Permission.CAMERA, Toast.LENGTH_LONG).show();
                    }
                }).start();
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            ProcessCameraProvider cameraProvider;
            try {
                cameraProvider = cameraProviderFuture.get();
            } catch(Exception e) { e.printStackTrace(); return; }

            Preview preview = new Preview.Builder().build();
            preview.setSurfaceProvider(cameraPreview.getSurfaceProvider());

            CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;

            try {
                cameraProvider.unbindAll();
                cameraProvider.bindToLifecycle(this, cameraSelector, preview);
            } catch (Exception e) { e.printStackTrace(); }
        }, ContextCompat.getMainExecutor(this));
    }
}
