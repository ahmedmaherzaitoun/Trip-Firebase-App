package com.example.tripa;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class TripReminderBroadcast extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {


        // start trip from notification

        String stPoint =  intent.getStringExtra("tripFrom");
        String enPoint = intent.getStringExtra("tripTo");
        Intent intentStart;
        try {
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + stPoint + "/" + enPoint);
             intentStart = new Intent(Intent.ACTION_VIEW, uri);
            intentStart.setPackage("com.google.android.apps.maps");

            intentStart.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.i("zatona", " start");
        }catch (ActivityNotFoundException e ){
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + stPoint + "/" + enPoint);


             intentStart = new Intent(Intent.ACTION_VIEW, uri);
            intentStart.setPackage("com.google.android.apps.maps");

            intentStart.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.i("zatona", " start");

        }




        //////






        PendingIntent action = PendingIntent.getActivity(context,0,  intentStart,0);

        Notification builder = new NotificationCompat.Builder(context,"notifyTrip")
                .setSmallIcon(R.drawable.ic_baseline_alarm_24)
                .setContentTitle("Remind " + intent.getStringExtra("tripName") + " trip")
                .setContentText( "From: " + intent.getStringExtra("tripFrom") + " To: " + intent.getStringExtra("tripTo"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .addAction(R.drawable.ic_baseline_alarm_24,"Start" , action)
                .build();


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(100,builder);
    }
}
