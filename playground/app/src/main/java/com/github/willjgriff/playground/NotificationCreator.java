package com.github.willjgriff.playground;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by Will on 14/08/2016.
 */
public class NotificationCreator {

	public static void createNotification(Context context, CharSequence title, CharSequence description, Intent intent) {

		int requestID = (int) System.currentTimeMillis();
		int flags = PendingIntent.FLAG_CANCEL_CURRENT;
		PendingIntent pendingIntent = PendingIntent.getActivity(
			PlaygroundApplication.app().getApplicationContext(), requestID, intent, flags);

		Notification notification = new NotificationCompat.Builder(context)
			.setSmallIcon(R.drawable.ic_pickaxe)
			.setContentTitle(title)
			.setContentText(description)
			.setContentIntent(pendingIntent)
			.setAutoCancel(true)
			.build();

		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(0, notification);
	}


}
