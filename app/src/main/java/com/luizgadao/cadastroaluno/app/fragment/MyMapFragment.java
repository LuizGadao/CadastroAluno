package com.luizgadao.cadastroaluno.app.fragment;



import android.support.v4.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.luizgadao.cadastroaluno.app.map.AnddressLocator;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class MyMapFragment extends SupportMapFragment {


    public MyMapFragment()
    {

    }

    @Override
    public void onStart() {
        super.onStart();

        centerMap( new AnddressLocator(getActivity()).getCoords( "Patos de Minas rua Paraná 52" ) );
    }

    private void centerMap( LatLng latLng ) {
        GoogleMap map = getMap();
        map.animateCamera( CameraUpdateFactory.newLatLngZoom(latLng, 15) );

        map.addMarker( new MarkerOptions().position(latLng).title("Marcador") );
    }
}
