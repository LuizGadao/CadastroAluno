package com.luizgadao.cadastroaluno.app.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.luizgadao.cadastroaluno.app.R;
import com.luizgadao.cadastroaluno.app.model.Student;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by luizcarlos on 17/05/14.
 */
public class ListStudentAdapter extends BaseAdapter
{

    private Activity activity;
    private List<Student> students;

    public ListStudentAdapter( Activity activity, List<Student> students )
    {
        this.activity = activity;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get( i );
    }

    @Override
    public long getItemId(int i) {
        return students.get( i ).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        Student student = students.get( i );

        //get view row
        View row = this.activity.getLayoutInflater().inflate( R.layout.custom_row, null );
        if ( i % 2 == 0 )
            row.setBackgroundColor( activity.getResources().getColor( R.color.pair ) );

        TextView textName = (TextView) row.findViewById(R.id.text_row);
        textName.setText( student.getName() );

        ImageView thumb = (ImageView) row.findViewById(R.id.thumb_row);

        if ( student.getPhoto() != null )
        {
            Bitmap image = BitmapFactory.decodeFile( student.getPhoto() );
            Bitmap thumbImage = Bitmap.createScaledBitmap( image, 90, 90, true );
            thumb.setImageBitmap( thumbImage );
        }
        else
        {
            Drawable drawable = activity.getResources().getDrawable( R.drawable.ic_no_image );
            thumb.setImageDrawable( drawable );
        }

        if ( row.findViewById( R.id.text_site ) != null )
        {
            TextView textSite = (TextView) row.findViewById( R.id.text_site );
            textSite.setText( student.getSite() );

            TextView textPhone = (TextView) row.findViewById( R.id.text_phone );
            textPhone.setText( student.getPhone() );
        }

        return row;
    }
}
