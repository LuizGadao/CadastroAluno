package com.luizgadao.cadastroaluno.app.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.luizgadao.cadastroaluno.app.dao.StudentDAO;
import com.luizgadao.cadastroaluno.app.model.Student;
import com.luizgadao.cadastroaluno.app.utils.StudetsConverterToJson;
import com.luizgadao.cadastroaluno.app.utils.WebClient;

import java.util.List;

/**
 * Created by luizcarlos on 19/05/14.
 */
public class SendStudentTask extends AsyncTask<Integer, Double, String>
{
    private final Activity context;
    private ProgressDialog progress;

    public SendStudentTask( Activity context )
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute()
    {
        progress = ProgressDialog.show( context, "Aguarde.", "Enviado dados...", true, true );
    }

    @Override
    protected String doInBackground(Integer... integers)
    {
        StudentDAO studentDAO = new StudentDAO( context );
        List<Student> students = studentDAO.getListStudents();
        studentDAO.close();

        String urlPost = "http://www.caelum.com.br/mobile";
        String jsonDadaStudents = new StudetsConverterToJson().toJson( students );
        try
        {
            WebClient client    = new WebClient( urlPost );
            String respJson     = client.post( jsonDadaStudents );
            return respJson;
        }
        catch ( Exception e )
        {
            throw new RuntimeException( e );
        }
    }

    @Override
    protected void onPostExecute(String respJson)
    {
        progress.dismiss();
        Toast.makeText(context, respJson, Toast.LENGTH_SHORT).show();
    }
}
