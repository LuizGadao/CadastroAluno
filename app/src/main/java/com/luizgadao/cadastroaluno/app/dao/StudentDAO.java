package com.luizgadao.cadastroaluno.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.luizgadao.cadastroaluno.app.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luizcarlos on 15/05/14.
 */
public class StudentDAO extends SQLiteOpenHelper
{
    private static final String[] COLUNS = { "id", "name", "phone", "address", "site", "photo", "test" };
    private static final String DATABASE = "DB_STUDENT";
    private static final String NAMETABLE = "Students";
    private static final int VERSION = 2;

    public StudentDAO(Context context )
    {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + NAMETABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT UNIQUE NOT NULL,"+
                "phone TEXT,"+
                "address TEXT,"+
                "site TEXT,"+
                "photo TEXT,"+
                "test REAL);";

        db.execSQL( query );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2)
    {
        String query = "DROP TABLE IF EXISTS " + NAMETABLE;
        db.execSQL( query );

        this.onCreate(db);
    }

    public List<Student> getListStudents()
    {
        String[] coluns = { "id", "name", "phone", "address", "site", "photo", "test" };
                            //0     1       2           3       4       5         6
        Cursor cursor = getWritableDatabase().query(NAMETABLE, coluns, null, null, null, null, null);

        ArrayList<Student> listStudents = new ArrayList<Student>();

        while( cursor.moveToNext() )
        {
            Student student = new Student();
            //Log.d( "Student-dao, get id: ",  "" + cursor.getInt( 0 ) );
            student.setId( cursor.getLong( 0 ) );
            student.setName( cursor.getString( 1 ) );
            student.setPhone( cursor.getString( 2 ) );
            student.setAddress( cursor.getString( 3 ) );
            student.setSite( cursor.getString( 4 ) );
            student.setPhoto( cursor.getString( 5 ) );
            student.setTestValue( cursor.getDouble( 6 ) );

            listStudents.add( student );
        }

        return listStudents;
    }

    public void save( Student student )
    {
        ContentValues values = toValues( student );
        //Log.d( "SAVE-CONTENT-VALUES: ", values.toString() );
        getWritableDatabase().insert(NAMETABLE, null, values);
    }

    public void delete( Student student )
    {
        //Log.d("Delete-student, id: ", student.getId().toString());
        String[] args = { student.getId().toString() };
        getWritableDatabase().delete(NAMETABLE, "id=?", args);
    }

    public void edit( Student student )
    {
        ContentValues values = toValues( student );
        String[] args = { student.getId().toString() };
        getWritableDatabase().update( NAMETABLE, values, "id=?", args );
    }

    private ContentValues toValues( Student student )
    {
        ContentValues values = new ContentValues();
        // name collun and att
        values.put( "name",     student.getName() );
        values.put( "phone",    student.getPhone() );
        values.put( "address",  student.getAddress() );
        values.put( "site",     student.getSite() );
        values.put( "photo",    student.getPhoto() );
        values.put( "test",     student.getTestValue() );

        return values;
    }
}
