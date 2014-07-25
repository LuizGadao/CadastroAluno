package com.luizgadao.cadastroaluno.app.map;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by luizcarlos on 25/07/14.
 */
public class AnddressLocator
{

    private Context context;

    public AnddressLocator( Context context )
    {
        this.context = context;
    }

    public LatLng getCoords(String endereco)
    {
        Geocoder geocoder = new Geocoder( context );

        try {
            List<Address> addresses = geocoder.getFromLocationName(endereco, 1);

            if ( ! addresses.isEmpty() )
            {
                Address address = addresses.get(0);
                return new LatLng( address.getLatitude(), address.getLongitude() );
            }

        } catch (IOException e) {
            return null;
        }

        return null;
    }
}
