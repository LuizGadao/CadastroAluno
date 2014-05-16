package com.luizgadao.cadastroaluno.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.luizgadao.cadastroaluno.app.dao.StudentDAO;
import com.luizgadao.cadastroaluno.app.model.Student;


public class FormAddActivity extends ActionBarActivity {

    private FormHelper formHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add);

        final Student studentSelected = (Student) getIntent().getSerializableExtra( "student-selected" );

        formHelper = new FormHelper(this);

        Button buttonSave = (Button) findViewById( R.id.button_add );
        buttonSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Student student = formHelper.getStudentData();

                StudentDAO studentDAO = new StudentDAO( FormAddActivity.this );

                if ( studentSelected == null )
                    studentDAO.save( student );
                else
                {
                    student.setId( studentSelected.getId() );
                    studentDAO.edit( student );
                }

                studentDAO.close();

                finish();
            }
        });

        if ( studentSelected != null )
        {
            buttonSave.setText( "Edit" );
            formHelper.setStudentData( studentSelected );
        }

    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.form_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */
}
