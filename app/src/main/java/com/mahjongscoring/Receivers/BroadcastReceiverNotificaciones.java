package com.mahjongscoring.Receivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

import com.mahjongscoring.activities.R;

public class BroadcastReceiverNotificaciones extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MM");
        wl.acquire();
        CrearNotificacion(context, intent);
        wl.release();
    }

    private void CrearNotificacion(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if(extras == null)
            return;

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_action_important)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher))
                        .setContentTitle(extras.getString("title"))
                        .setContentText(extras.getString("content"))
                        .setDefaults(Notification.DEFAULT_SOUND| Notification.DEFAULT_VIBRATE | Notification.FLAG_SHOW_LIGHTS);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
        MediaPlayer mp = MediaPlayer.create(context, Uri.parse(extras.getString("uri")));
        mp.start();
    }
}
