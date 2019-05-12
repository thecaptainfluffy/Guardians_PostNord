package com.example.trypackagemanager.guardians_postnord;

import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
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
        addmarker(mMap);
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

    private ArrayList<String> stringAddresses = new ArrayList<>(Arrays.asList("Järnvägsgatan 35", "Test"));

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        current = new LatLng(56.062263, 12.709618);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(current));

        mMap.setOnMarkerClickListener(omcl);
        // Add a marker in Sydney and move the camera
        MarkerOptions mo = new MarkerOptions();
        /*mo.title(stringAddresses.get(i));
        mMap.addMarker(mo);*/


    }

    GoogleMap.OnMarkerClickListener omcl = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {


            return false;
        }
    };

    int i = 0;
    private void onClick() throws IOException {
        Geocoder geocoder = new Geocoder(this);
        List<Address> addresses;
        addresses = geocoder.getFromLocationName(stringAddresses.get(i), 1);
        if(addresses.size() > 0) {
            double lat = addresses.get(0).getLatitude();
            double lng = addresses.get(0).getLongitude();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lng)));
        }
        i++;
    }

    private Marker postnord;
    private Marker förstadress;
    private Marker andraadress;


    public void addmarker(GoogleMap map){
       postnord=map.addMarker(new MarkerOptions().position(new LatLng
               (10,10)).title("Arne " +" Kontakt: 0734244419 "));

       postnord.setTag(0);



    }

}
