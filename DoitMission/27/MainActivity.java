package dev_allgot.understand.doitmission_27;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        if(mapFragment != null) mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng friend1 = new LatLng(59.906099, 10.685519);
        LatLng friend2 = new LatLng(59.903284, 10.675217);
        LatLng camera = new LatLng(59.905768, 10.678570);

        googleMap.addMarker(new MarkerOptions().position(friend1).title("친구 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_friend)));
        googleMap.addMarker(new MarkerOptions().position(friend2).title("친구 2").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_friend)));
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                String title = marker.getTitle();
                if(title != null && title.equals("친구 1")) Toast.makeText(getApplicationContext(), "친구 1, 노르웨이", Toast.LENGTH_LONG).show();
                if(title != null && title.equals("친구 2")) Toast.makeText(getApplicationContext(), "친구 2, 노르웨이", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(camera, 15));
    }
}
