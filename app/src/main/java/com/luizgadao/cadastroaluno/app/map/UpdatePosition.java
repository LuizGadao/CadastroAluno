package com.luizgadao.cadastroaluno.app.map;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.luizgadao.cadastroaluno.app.fragment.MyMapFragment;

/**
 * Created by luizcarlos on 02/08/14.
 */
public class UpdatePosition implements LocationListener
{

    private final LocationManager locationManager;
    private final MyMapFragment mapFragment;


    public UpdatePosition( Context context, MyMapFragment mapFragment)
    {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 20, this);

        this.mapFragment = mapFragment;
    }

    @Override
    public void onLocationChanged(Location newLocation) {
        mapFragment.centerMap( new LatLng( newLocation.getLatitude(), newLocation.getLongitude() ));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void cancel() {
        locationManager.removeUpdates(this);
    }
}
