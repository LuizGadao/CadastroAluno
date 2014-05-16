package com.luizgadao.cadastroaluno.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by luizcarlos on 16/05/14.
 */
public class SmsReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText( context, "=> SMS-Receiver: ", Toast.LENGTH_SHORT ).show();
    }
}
