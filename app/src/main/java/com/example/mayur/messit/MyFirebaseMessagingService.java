package com.example.mayur.messit;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseMessagingService";
    private static final String ChannelID = "Firebase Database Update";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String notificationTitle = null;
        String notificationBody = null;
        setupNotificationChannel();

        //Check for notification payload in remoteMessage
        if(remoteMessage.getNotification() != null){
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            notificationTitle = remoteMessage.getNotification().getTitle();
            notificationBody = remoteMessage.getNotification().getBody();

            sendNotification(notificationTitle, notificationBody);
        }
    }

    //Method to generate notification on the basis of received SCM message
    private void sendNotification(String notificationTitle, String notificationBody){
        Intent intent = new Intent(this, FoodMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getActivity(this,
                0,
                intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder;
        notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this, ChannelID)
                .setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setContentTitle(notificationTitle)
                .setContentText(notificationBody)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSound(defaultSoundUri);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }

    private void setupNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence channelName = getString(R.string.notification_channelName);
            String channelDescription = getString(R.string.notification_channelDesc);
            int notify_importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel;
            notificationChannel = new NotificationChannel(
                    ChannelID,
                    channelName,
                    notify_importance);
            notificationChannel.setDescription(channelDescription);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
