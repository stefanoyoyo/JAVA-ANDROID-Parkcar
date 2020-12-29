package com.example.ste.parkcar;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Ste on 31/05/2017.
 */
public class RingtonePlayingService extends Service {

    MediaPlayer media_song;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public int onStartCommand(Intent intent, int flags, int startid) {

        // creo un'istanza di media player
        media_song = MediaPlayer.create(this, R.raw.cucu);
        media_song.start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "On Destroy Called",Toast.LENGTH_SHORT).show();
    }
}