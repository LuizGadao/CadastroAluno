package com.luizgadao.cadastroaluno.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.luizgadao.cadastroaluno.app.model.Test;


public class DetailTest extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_test);

        Log.d("DETAIL-Activity", "TEST");

        Test testName = (Test) getIntent().getSerializableExtra( TestsActivity.TEST_SELECTED );

        TextView nameText = (TextView) findViewById(R.id.test_name);
        nameText.setText(testName.getName());

        TextView dateText = (TextView) findViewById( R.id.date_test );
        dateText.setText(testName.getData());

        int layout = android.R.layout.simple_list_item_1;

        ArrayAdapter adapter = new ArrayAdapter( this, layout,testName.getTopics() );

        ListView listView = (ListView) findViewById( R.id.list_topics );
        listView.setAdapter( adapter );
    }

}
