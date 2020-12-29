package com.example.ste.parkcar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Ste on 31/05/2017.
 */
public class Alarm_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Alarm_Receiver","YEAHH !!");

        // creo un intent per il servizio di suoneria
        Intent service_intent = new Intent (context, RingtonePlayingService.class);

        // do inizio al ringtone service
        context.startService(service_intent);
    }
}
