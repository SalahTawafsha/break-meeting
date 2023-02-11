package com.example.break_meet;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.widget.RemoteViews;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public void generateNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        String channelID = "notification_channel";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelID)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.icon)
                .setVibrate(new long[]{1000, 1000, 1000, 1000})
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent);

        String channelName = "com.example.break_meet";
        builder = builder.setContent(new RemoteViews(channelName, R.layout.notification));

        NotificationChannel notificationChannel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);

        NotificationManagerCompat.from(this).notify(0, builder.build());


    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        if(message.getNotification() != null){
            generateNotification();
        }
    }

}
