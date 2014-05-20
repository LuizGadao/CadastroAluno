package com.luizgadao.cadastroaluno.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.luizgadao.cadastroaluno.app.R;
import com.luizgadao.cadastroaluno.app.TestsActivity;
import com.luizgadao.cadastroaluno.app.model.Test;

import org.w3c.dom.Text;

/**
 * Created by luizcarlos on 20/05/14.
 */
public class DetailTestFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View detailTest = inflater.inflate( R.layout.activity_detail_test, null );

        //Test testSelected = (Test) savedInstanceState.getSerializable(TestsActivity.TEST_SELECTED );
        if ( getArguments() != null  )
        {
            Test testSelected = (Test) getArguments().getSerializable( TestsActivity.TEST_SELECTED );

            TextView nameText = (TextView) detailTest.findViewById( R.id.test_name );
            nameText.setText( testSelected.getName() );

            TextView dateText = (TextView) detailTest.findViewById( R.id.date_test );
            dateText.setText( testSelected.getData() );

            int layout = android.R.layout.simple_list_item_1;
            ArrayAdapter adapter = new ArrayAdapter( getActivity(), layout, testSelected.getTopics() );

            ListView listTopics = (ListView) detailTest.findViewById( R.id.list_topics );
            listTopics.setAdapter( adapter );

            Log.d( "DETAIL-TEST-FRAGMENT", "FRAGMENT-OPEN: " + testSelected.getName() );
        }

        return detailTest;
    }
}
