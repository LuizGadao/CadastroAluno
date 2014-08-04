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

        // antes de fazer requisição das coordenadas do gps, verificar se o gps esta disponível.
        // se não estiver pergunta o usuário se ele deseja ativar o gps.
        // se ele escolher que sim, chamar a intent do OS para faciliar para o usuário habilitar o recurso.
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 4000, 20, this);

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
