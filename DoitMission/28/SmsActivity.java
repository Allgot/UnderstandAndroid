package dev_allgot.understand.doitmission_28;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import java.util.function.Consumer;

public class SmsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Uri smsUri = Uri.parse("sms:010-1234-5678");
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, smsUri);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한이 없습니다!", Toast.LENGTH_LONG).show();
            finish();
        }

        if (Build.VERSION.SDK_INT >= 31) {
            LocationRequest locationRequest = new LocationRequest.Builder(0).setMinUpdateDistanceMeters(0).build();

            Consumer<Location> locationConsumer = new Consumer<Location>() {
                @Override
                public void accept(Location location) {
                    Toast.makeText(getApplicationContext(), "SMS Sent!", Toast.LENGTH_LONG).show();
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    smsIntent.putExtra("sms_body", "The current location is " + latitude + ", " + longitude);
                    startActivity(smsIntent);
                }
            };

            locationManager.getCurrentLocation(LocationManager.GPS_PROVIDER, locationRequest, null, getMainExecutor(), locationConsumer);
        }

        else {
            LocationListener listener = new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    Toast.makeText(getApplicationContext(), "SMS Sent!", Toast.LENGTH_LONG).show();
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    smsIntent.putExtra("sms_body", "The current location is " + latitude + ", " + longitude);
                    startActivity(smsIntent);
                }
            };

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
        }
    }
}
