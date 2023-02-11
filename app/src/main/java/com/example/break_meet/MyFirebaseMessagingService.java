package com.example.break_meet;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private final String channelID = "notification_channel";
    private final String channelName = "com.example.break_meet";

    public void generateNotification(String title, String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelID)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.icon)
                .setVibrate(new long[]{1000, 1000, 1000, 1000})
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent);

        builder = builder.setContent(new RemoteViews(channelName, R.layout.notification));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
            getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);

        }
        NotificationManagerCompat.from(this).notify(0, builder.build());


    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        if(message.getNotification() != null){
            generateNotification(message.getNotification().getTitle(),message.getNotification().getBody());
        }
//        String title = message.getNotification().getTitle();
//        String text = message.getNotification().getBody();
//
//        final String CHANEL_ID = "fast_notification";
//
//        NotificationChannel channel = new NotificationChannel(CHANEL_ID
//                , "FAST_NOTIFICATION"
//                , NotificationManager.IMPORTANCE_HIGH);
//
//        getSystemService(NotificationManager.class).createNotificationChannel(channel);
//
//        Notification.Builder notification = new Notification.Builder(this, CHANEL_ID)
//                .setAutoCancel(true)
//                .setContentTitle(title)
//                .setContentText(text);
//
//        NotificationManagerCompat.from(this).notify(1, notification.build());
//
//        super.onMessageReceived(message);

    }

}
