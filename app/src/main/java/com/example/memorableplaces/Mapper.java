package com.example.memorableplaces;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class Mapper extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapper);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
       //final String address = "Could not find address";

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng)
            {
                String address="";

                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try
                {
                    List<Address> listAddress = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

                    if(listAddress != null && listAddress.size()>0)
                    {
                        //address= "Address:\n";

                        if(listAddress.get(0).getThoroughfare() != null)
                        {
                            address += listAddress.get(0).getThoroughfare() + "\n";
                        }
                        if(listAddress.get(0).getLocality() != null)
                        {
                           address += listAddress.get(0).getLocality() + " ";
                        }
                        if(listAddress.get(0).getAdminArea() != null)
                        {
                            address += listAddress.get(0).getAdminArea();
                        }

                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                LatLng lati = new LatLng(latLng.latitude,latLng.longitude);
                mMap.addMarker(new MarkerOptions().position(lati).title(address));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(lati));

                Intent favlocat = new Intent(getApplicationContext(), MainActivity.class);
                favlocat.putExtra("Clicked location",address);
                startActivity(favlocat);
                MainActivity.memPlaces.add(address);
            }
        });

    }


}