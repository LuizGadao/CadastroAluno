package com.luizgadao.cadastroaluno.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;


import com.luizgadao.cadastroaluno.app.model.Student;

/**
 * Created by luizcarlos on 15/05/14.
 */
public class FormHelper
{

    private EditText editName;
    private EditText editSite;
    private EditText editPhone;
    private EditText editAddress;
    private RatingBar ratingBar;
    private ImageView imageView;
    private Student student;


    public FormHelper( FormAddActivity formAdd )
    {
        editName    = (EditText) formAdd.findViewById( R.id.edit_name );
        editSite    = (EditText) formAdd.findViewById( R.id.edit_site );
        editPhone   = (EditText) formAdd.findViewById( R.id.edit_phone );
        editAddress = (EditText)formAdd.findViewById( R.id.edit_address );
        ratingBar   = (RatingBar) formAdd.findViewById( R.id.ratingBar );
        imageView   = (ImageView) formAdd.findViewById( R.id.photo );

        student = new Student();
    }

    public Student getStudentData()
    {
        Student student = new Student();

        student.setName( editName.getText().toString() );
        student.setSite( editSite.getText().toString() );
        student.setPhone( editPhone.getText().toString() );
        student.setAddress( editAddress.getText().toString() );
        student.setTestValue( Double.valueOf(ratingBar.getRating()) );

        return student;
    }

    public void setStudentData( Student student )
    {
        this.student = student;

        editName.setText(student.getName());
        editAddress.setText( student.getAddress() );
        editPhone.setText( student.getPhone() );
        editSite.setText( student.getSite() );
        ratingBar.setRating( student.getTestValue().floatValue() );

        if ( student.getPhoto() != null )
            loadImage( student.getPhoto() );
    }

    public ImageView getImageView()
    {
        return imageView;
    }

    public void loadImage( String pathPhoto )
    {
        student.setPhoto( pathPhoto );

        Bitmap image = BitmapFactory.decodeFile( pathPhoto );
        Bitmap thumb = Bitmap.createScaledBitmap( image, 100, 100, true );

        imageView.setImageBitmap( thumb );
    }
}
