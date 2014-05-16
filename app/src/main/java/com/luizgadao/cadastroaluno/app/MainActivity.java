package com.luizgadao.cadastroaluno.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.luizgadao.cadastroaluno.app.dao.StudentDAO;
import com.luizgadao.cadastroaluno.app.model.Student;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView listView;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // nome que queremos exibir no listview
        //final String[] names = { "Luiz Carlos", "Luiz Carlos", "Luiz Carlos", "Luiz Carlos", "Luiz Carlos" };

        /*
        Não tem como setar diretamentento os nome na listview, por isso é necessário utilizar o ArrayAdapter
        O ArrayAdapter, necessita de um contexto(Activity), layout(no caso estamos utilizando um padrão do android) e o array de nomes.
        Depois é setar o array adapter na listView
         */

        listView = ( ListView ) findViewById( R.id.list_view );
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long id) {

                Student studentSelected = (Student) listView.getItemAtPosition( index );

                Intent intent = new Intent( MainActivity.this, FormAddActivity.class );
                intent.putExtra( "student-selected", studentSelected );

                startActivity( intent );

                //Toast.makeText( MainActivity.this, "click-index: " + index, Toast.LENGTH_SHORT ).show();
            }
        });

        listView.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long id) {

                student = (Student) listView.getItemAtPosition( index );
                Toast.makeText( MainActivity.this, "long-clic item: " + student.getId(), Toast.LENGTH_SHORT ).show();

                // se o retorno for true é para mata o cliclo do evento.
                return false;
            }
        });

        registerForContextMenu( listView );
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadListStudents();
    }

    private void loadListStudents()
    {
        StudentDAO studentsDao = new StudentDAO( this );
        List<Student> students = studentsDao.getListStudents();
        studentsDao.close();

        //capturando o layout padrão do android dos itens que vão ficar na lista.
        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>( this, layout, students );

        listView.setAdapter( adapter );
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.add( "Call" );
        menu.add( "Send SMS" );
        menu.add( "Send E-MAIL" );
        menu.add( "Go to site" );
        menu.add( "See on the map" );
        MenuItem deleteItem =  menu.add( "Delete" );

        deleteItem.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                StudentDAO studentDAO = new StudentDAO( MainActivity.this );
                studentDAO.delete( student );
                studentDAO.close();

                loadListStudents();

                return false;
            }
        });

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_add:
                Intent intent = new Intent( this, FormAddActivity.class );
                startActivity( intent );
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
