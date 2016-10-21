package com.example.luissilva.poujamv;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;


public class MySendNotification
{
    private static MySendNotification ourInstance;
    private static Context context;

    public static MySendNotification getInstance(Context c)
    {
        if(ourInstance == null) {
            ourInstance = new MySendNotification();
            context = c;
        }

        return ourInstance;
    }

    private MySendNotification() {
    }
    public void DrinkNotification()
    {
        NotificationCompat.Builder drinkNot = new NotificationCompat.Builder(context).setSmallIcon(R.drawable.icone_esponja)
                                                                                     .setContentTitle("Tenho sede")
                                                                                     .setContentText("Me dê água, por favor!");
        Intent resultIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        drinkNot.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0,drinkNot.build());
    }

    public void FoodNotification()
    {
        NotificationCompat.Builder foodNot = new NotificationCompat.Builder(context).setSmallIcon(R.drawable.icone_esponja)
                                                                                    .setContentTitle("Tenho fome")
                                                                                    .setContentText("Me dê comida, por favor!");
        Intent resultIntent2 = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder2 = TaskStackBuilder.create(context);

        stackBuilder2.addParentStack(MainActivity.class);
        stackBuilder2.addNextIntent(resultIntent2);

        PendingIntent resultPendingIntent2 = stackBuilder2.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        foodNot.setContentIntent(resultPendingIntent2);
        NotificationManager fNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        fNotificationManager.notify(0,foodNot.build());
    }

}
