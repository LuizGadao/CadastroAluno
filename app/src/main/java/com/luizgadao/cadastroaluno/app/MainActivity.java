package com.luizgadao.cadastroaluno.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.luizgadao.cadastroaluno.app.adapter.ListStudentAdapter;
import com.luizgadao.cadastroaluno.app.dao.StudentDAO;
import com.luizgadao.cadastroaluno.app.model.Student;
import com.luizgadao.cadastroaluno.app.task.SendStudentTask;
import com.luizgadao.cadastroaluno.app.utils.Extra;

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
                intent.putExtra(Extra.STUDENT_SELECTED, studentSelected );

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

        //int layout = android.R.layout.simple_list_item_1;
        //ArrayAdapter<Student> adapter = new ArrayAdapter<Student>( this, layout, students );

        ListStudentAdapter adapter = new ListStudentAdapter( this, students );

        listView.setAdapter( adapter );
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem callItem = ( MenuItem ) menu.add( "Call" );
        MenuItem smsItem = menu.add( "Send SMS" );
        menu.add( "Send E-MAIL" );
        MenuItem mapItem =  menu.add( "See on the map" );
        MenuItem deleteItem =  menu.add( "Delete" );

        callItem.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent callIntent = new Intent( Intent.ACTION_CALL );
                Uri phoneToCall = Uri.parse( "tel:" + student.getPhone() );
                callIntent.setData( phoneToCall );

                startActivity( callIntent );

                return false;
            }
        });

        smsItem.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                Intent intentSms = new Intent( Intent.ACTION_VIEW );
                intentSms.setData( Uri.parse( "sms:" + student.getPhone() ) );
                intentSms.putExtra( "sms_body", "um teste de sms..." );

                startActivity(intentSms);

                return false;
            }
        });

        MenuItem siteItem = menu.add( "Go to site" );
        siteItem.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                String site = student.getSite();
                if( site.startsWith("http://") == false )
                    site = "http://" + site;

                Intent openSite = new Intent( Intent.ACTION_VIEW );
                Uri siteToGo = Uri.parse( site );
                openSite.setData( siteToGo );

                startActivity( openSite );

                return false;
            }
        });

        mapItem.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                Intent mapIntent = new Intent( Intent.ACTION_VIEW );
                mapIntent.setData( Uri.parse( "geo:0,0?z=14&q=" + student.getAddress() ) );

                startActivity( mapIntent );

                return false;
            }
        });

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

            case R.id.action_synchronize:
                SendStudentTask task = new SendStudentTask( this );
                task.execute();
                break;

            case R.id.action_received_tests:
                Intent intentReceived = new Intent( this, TestsActivity.class );
                startActivity( intentReceived );
                break;

            case R.id.action_mapa:
                Intent intentMap = new Intent(this, MapActivity.class);
                startActivity(intentMap);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
