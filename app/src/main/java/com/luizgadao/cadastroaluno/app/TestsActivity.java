package com.luizgadao.cadastroaluno.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.luizgadao.cadastroaluno.app.fragment.DetailTestFragment;
import com.luizgadao.cadastroaluno.app.fragment.ListTestFragment;
import com.luizgadao.cadastroaluno.app.model.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by luizcarlos on 19/05/14.
 */
public class TestsActivity extends FragmentActivity
{

    public static final String TEST_SELECTED = "teste-selected";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.tests );

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Log.d( "TestActivity", "is-tablet? " + isTabletLandScape() );

        if( isTabletLandScape() )
        {
            ListTestFragment listTestFragment = new ListTestFragment();
            fragmentTransaction.replace( R.id.left_frame_test, listTestFragment );

            Bundle args = new Bundle();
            args.putSerializable( TEST_SELECTED, listTestFragment.getFirstTest() );
            DetailTestFragment detailTestFragment = new DetailTestFragment();
            detailTestFragment.setArguments( args );

            fragmentTransaction.replace( R.id.right_frame_test, detailTestFragment );
        }
        else
        {
            fragmentTransaction.replace( R.id.only_frame_test, new ListTestFragment() );
        }

        fragmentTransaction.commit();
    }

    public void selectTest( Test testSelected ) {
        Log.d("TESTS-ACTIVITY", "SELECT-TEST");

        Bundle args = new Bundle();
        args.putSerializable(TEST_SELECTED, testSelected);

        DetailTestFragment detailTestFragment = new DetailTestFragment();
        detailTestFragment.setArguments(args);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if (!isTabletLandScape()) {
            transaction.replace(R.id.only_frame_test, detailTestFragment);
            transaction.addToBackStack(null);
        } else
        {
            transaction.replace( R.id.right_frame_test, detailTestFragment );
        }

        transaction.commit();
    }

    private boolean isTabletLandScape()
    {
        return getResources().getBoolean( R.bool.isTablet );
    }
}
