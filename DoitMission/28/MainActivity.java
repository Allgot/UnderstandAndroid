package dev_allgot.understand.doitmission_28;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndPermission.with(this)
                .runtime()
                .permission(Permission.ACCESS_COARSE_LOCATION, Permission.ACCESS_FINE_LOCATION)
                .onGranted(data -> {
                    Toast.makeText(this, data.size() + " granted!", Toast.LENGTH_SHORT).show();
                })
                .onDenied(data -> {
                    Toast.makeText(this, data.size() + " denied", Toast.LENGTH_SHORT).show();
                })
                .start();
    }
}
