package com.luizgadao.cadastroaluno.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.luizgadao.cadastroaluno.app.model.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by luizcarlos on 19/05/14.
 */
public class TestsActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_tests );

        ListView listViewTest = (ListView) this.findViewById( R.id.list_view_test );

        int layout = android.R.layout.simple_list_item_1;

        Test test1 = new Test();
        test1.setName( "Matemática" );
        test1.setData( "22/06/2014" );
        test1.setDescription( "prova de matemática, sobre todo conteúdo abordado em sala de aula" );
        test1.setTopics( Arrays.asList( "Tópico-1", "Tópico-2", "Tópico-3", "Tópico-4" ) );

        Test test2 = new Test();
        test2.setName( "Português" );
        test2.setData( "22/06/2014" );
        test2.setDescription( "prova de matemática, sobre todo conteúdo abordado em sala de aula" );
        test2.setTopics( Arrays.asList( "Tópico-1", "Tópico-2", "Tópico-3", "Tópico-4" ) );

        List<Test> listTests = Arrays.asList( test1, test2 );
        ArrayAdapter adapter = new ArrayAdapter( this, layout, listTests );

        listViewTest.setAdapter( adapter );
    }
}
