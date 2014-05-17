package com.luizgadao.cadastroaluno.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.luizgadao.cadastroaluno.app.R;
import com.luizgadao.cadastroaluno.app.dao.StudentDAO;

import java.util.Objects;

/**
 * Created by luizcarlos on 16/05/14.
 */
public class  SmsReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Object[] message = ( Object[] ) intent.getExtras().get( "pdus" );

        byte[] firsMessage = ( byte[] ) message[0];

        SmsMessage sms = SmsMessage.createFromPdu( firsMessage );

        String phoneNumber = sms.getDisplayOriginatingAddress();

        StudentDAO studentsDAO = new StudentDAO( context );
        boolean isStudent = studentsDAO.isPhoneStudent( phoneNumber );
        studentsDAO.close();

        if ( isStudent )
        {
            MediaPlayer player = MediaPlayer.create( context, R.raw.msg );
            player.start();
            Toast.makeText( context, "=> SMS-Receiver: " + phoneNumber, Toast.LENGTH_SHORT ).show();
        }

    }
}
