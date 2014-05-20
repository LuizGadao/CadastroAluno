package com.luizgadao.cadastroaluno.app.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.luizgadao.cadastroaluno.app.R;
import com.luizgadao.cadastroaluno.app.TestsActivity;
import com.luizgadao.cadastroaluno.app.model.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by luizcarlos on 20/05/14.
 */
public class ListTestFragment extends Fragment
{

    private Test firstTest;

    public ListTestFragment()
    {
        firstTest = new Test();
        firstTest.setName( "Matemática" );
        firstTest.setData( "22/06/2014" );
        firstTest.setDescription( "prova de matemática, sobre todo conteúdo abordado em sala de aula" );
        firstTest.setTopics( Arrays.asList("Tópico-1", "Tópico-2", "Tópico-3", "Tópico-4", "Tópico-1", "Tópico-2", "Tópico-3", "Tópico-4", "Tópico-1", "Tópico-2", "Tópico-3", "Tópico-4") );
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        View view = (View) inflater.inflate(R.layout.activity_tests, null);

        ListView listViewTest = (ListView) view.findViewById(R.id.list_view_test);

        Log.d("DETAIL-FRAGMENT", "TEST");

        int layout = android.R.layout.simple_list_item_1;

        Test test2 = new Test();
        test2.setName( "Português" );
        test2.setData( "07/06/2014" );
        test2.setDescription( "prova de matemática, sobre todo conteúdo abordado em sala de aula" );
        test2.setTopics( Arrays.asList( "Tópico-1", "Tópico-2", "Tópico-3", "Tópico-4", "Tópico-1", "Tópico-2", "Tópico-3", "Tópico-4", "Tópico-1", "Tópico-2", "Tópico-3", "Tópico-4" ) );

        Test test3 = new Test();
        test3.setName( "Física" );
        test3.setData( "10/06/2014" );
        test3.setDescription( "prova de matemática, sobre todo conteúdo abordado em sala de aula" );
        test3.setTopics( Arrays.asList( "Tópico-1", "Tópico-2", "Tópico-3", "Tópico-4", "Tópico-1", "Tópico-2", "Tópico-3", "Tópico-4", "Tópico-1", "Tópico-2", "Tópico-3", "Tópico-4" ) );

        Test test4 = new Test();
        test4.setName( "Geografia" );
        test4.setData( "09/06/2014" );
        test4.setDescription( "prova de matemática, sobre todo conteúdo abordado em sala de aula" );
        test4.setTopics( Arrays.asList( "Tópico-1", "Tópico-2", "Tópico-3", "Tópico-4", "Tópico-1", "Tópico-2", "Tópico-3", "Tópico-4", "Tópico-1", "Tópico-2", "Tópico-3", "Tópico-4" ) );

        Test test5 = new Test();
        test5.setName( "Inglês" );
        test5.setData( "12/06/2014" );
        test5.setDescription( "prova de matemática, sobre todo conteúdo abordado em sala de aula" );
        test5.setTopics( Arrays.asList( "Tópico-1", "Tópico-2", "Tópico-3", "Tópico-4", "Tópico-1", "Tópico-2", "Tópico-3", "Tópico-4", "Tópico-1", "Tópico-2", "Tópico-3", "Tópico-4" ) );


        final List<Test> listTests = Arrays.asList( firstTest, test2, test3, test4, test5 );

        FragmentActivity context = getActivity();

        ArrayAdapter adapter = new ArrayAdapter( context, layout, listTests );

        listViewTest.setAdapter( adapter );

        listViewTest.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Test testClicked = listTests.get( i );

                Log.d( "test-clicked: ", testClicked.getName() );

                TestsActivity testsActivity = ( TestsActivity ) getActivity();
                testsActivity.selectTest( testClicked );
            }
        });

        return view;
    }

    public Test getFirstTest() {
        return firstTest;
    }
}
