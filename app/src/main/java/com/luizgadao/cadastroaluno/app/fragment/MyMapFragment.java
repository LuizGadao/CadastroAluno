package com.luizgadao.cadastroaluno.app.fragment;



import android.support.v4.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.luizgadao.cadastroaluno.app.dao.StudentDAO;
import com.luizgadao.cadastroaluno.app.map.AnddressLocator;
import com.luizgadao.cadastroaluno.app.model.Student;

import java.util.List;

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

        //centerMap( new AnddressLocator(getActivity()).getCoords( "Patos de Minas avenida Paranaíba 1495" ) );

        StudentDAO studentDAO = new StudentDAO(getActivity());
        List<Student> listStudents = studentDAO.getListStudents();
        for ( Student student : listStudents )
        {
            LatLng latLng = new AnddressLocator( getActivity() ).getCoords( student.getAddress() );
            MarkerOptions options = new MarkerOptions();
            options.title( student.getName() );
            options.position( latLng );

            getMap().addMarker( options );
        }

    }

    public void centerMap( LatLng latLng ) {
        GoogleMap map = getMap();
        map.animateCamera( CameraUpdateFactory.newLatLngZoom(latLng, 14) );

        MarkerOptions options = new MarkerOptions();
        options.position( latLng );
        options.title("Location device");
        map.addMarker( options );

    }
}
