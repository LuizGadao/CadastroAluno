package com.luizgadao.cadastroaluno.app;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.luizgadao.cadastroaluno.app.dao.StudentDAO;
import com.luizgadao.cadastroaluno.app.model.Student;
import com.luizgadao.cadastroaluno.app.utils.Extra;

import java.io.File;
import java.net.URI;


public class FormAddActivity extends ActionBarActivity {

    private FormHelper formHelper;
    private String pathFile;
    private static final int TAKE_PHOTO = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add);

        final Student studentSelected = (Student) getIntent().getSerializableExtra(Extra.STUDENT_SELECTED );

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
            buttonSave.setText( "SAVE" );
            formHelper.setStudentData( studentSelected );
        }

        ImageView photo = formHelper.getImageView();
        photo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentCam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                // criar um caminho onde a imagem vai ser armazenada.
                pathFile = Environment.getExternalStorageDirectory().toString() + "/" + System.currentTimeMillis() + ".png  ";

                //criar um arquivo baseado no caminho
                File file = new File( pathFile );
                //criar uma uri baseada no arquivo, para o android reconhecer onde o arquivo vai ser armazenado
                Uri photoLocal = Uri.fromFile( file );
                //informa a intent onde vai ser salvo o caminho da imagem.
                intentCam.putExtra( MediaStore.EXTRA_OUTPUT, photoLocal );

                startActivityForResult(intentCam, TAKE_PHOTO);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d( "FormActivity => ", "RESULT: " + requestCode );
        if( requestCode == TAKE_PHOTO )
        {
            // Se o resulstado da intent for ok, salva o path da image, senão, anula o path.
            if ( resultCode == Activity.RESULT_OK )
            {
                Log.d( "FormActivity => ", "RESULT_OK" );
                formHelper.loadImage( pathFile );
            }
            else
            {
                pathFile = null;
            }
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
