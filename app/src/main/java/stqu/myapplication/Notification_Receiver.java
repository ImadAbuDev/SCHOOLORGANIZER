package stqu.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Imad Abu on 30.09.2016.
 */
public class Notification_Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

       NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent repeating_Intent = new Intent(context,HausaufgabenActivity.class);

        repeating_Intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent =PendingIntent.getActivity(context,100,repeating_Intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.haicon)
                .setContentTitle("ERINNERUNG ZUM SCHOOLORGANIZER!")
                .setContentText("Schon deine Hausaufgaben gemacht ? Jetzt überprüfen!")
                .setAutoCancel(true);

        notificationManager.notify(100,builder.build());


    }
    }

