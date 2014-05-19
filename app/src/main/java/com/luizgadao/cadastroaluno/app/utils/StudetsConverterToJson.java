package com.luizgadao.cadastroaluno.app.utils;

import com.luizgadao.cadastroaluno.app.model.Student;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

/**
 * Created by luizcarlos on 19/05/14.
 */
public class StudetsConverterToJson
{

    public String toJson( List<Student> students )
    {
        try
        {
            //{ "list":[ {"aluno":[ { "nome":"Maria", "nota":10 }, { "nome":"Maria", "nota":10 } ] } ] }
            JSONStringer js = new JSONStringer();
            js.key("list").array();
            js.key("aluno").array();

            for ( Student student : students )
            {
                js.key("nome").value( student.getName() );
                js.key("nota").value( student.getTestValue() );
            }

            js.endArray().endObject();
            js.endArray().endObject();

            return js.toString();
        }
        catch ( JSONException e )
        {
            throw new RuntimeException( e );
        }
    }

}
