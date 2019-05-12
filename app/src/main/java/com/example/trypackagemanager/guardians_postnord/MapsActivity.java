package com.example.trypackagemanager.guardians_postnord;

import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    private ArrayList<String> stringAddresses = new ArrayList<>(Arrays.asList("Järnvägsgatan 35", "Carl krooks 54", "Karlsgatan 4", "Hjälmhultsgatan 14" , "Rektorsgatan 20" ));
    private ArrayList<LatLng> latLngs = new ArrayList<>();
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Log.d("test","hej");
        // Add a marker in Sydney and move the camera
        current = new LatLng(56.027697, 12.708219);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
        mMap.setMinZoomPreference(14);

        Geocoder geocoder = new Geocoder(this);
        for(String addr : stringAddresses) {
            try {




                List<Address> addresses = geocoder.getFromLocationName(addr, 1);
                if (addresses.size() > 0) {
                    double lat = addresses.get(0).getLatitude();
                    double lng = addresses.get(0).getLongitude();
                    MarkerOptions mo = new MarkerOptions();
                    latLngs.add(new LatLng(lat, lng));
                    mo.position(new LatLng(lat, lng));
                    mo.title(addresses.get(0).getAddressLine(0));
                    mo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

                    mMap.addMarker(mo);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mMap.setOnMarkerClickListener(omcl);
    }

    GoogleMap.OnMarkerClickListener omcl = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
            return false;
        }
    };
}
