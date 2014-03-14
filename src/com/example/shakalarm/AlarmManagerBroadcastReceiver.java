package com.example.shakalarm;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.Toast;

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {
	final public static String BASIC_ALARM = "basicAlarm";

	@Override
	public void onReceive(Context context, Intent intent) {
		PowerManager pm = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);
		PowerManager.WakeLock wl = pm.newWakeLock(
				PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
		// Acquire the lock
		wl.acquire();

		// You can do the processing here.
		Bundle extras = intent.getExtras();
		StringBuilder msgStr = new StringBuilder();

		if (extras != null && extras.getBoolean(BASIC_ALARM, Boolean.FALSE)) {
			// Make sure this intent has been sent by the one-time timer button.
			msgStr.append("One time Timer : ");
		}
		Format formatter = new SimpleDateFormat("hh:mm:ss a");
		msgStr.append(formatter.format(new Date()));
		
		//Play alarm sound "Ding"~
		Toast.makeText(context, msgStr, Toast.LENGTH_LONG).show();
		Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		Ringtone r = RingtoneManager.getRingtone(context, notification);
		r.play();
		// Release the lock
		wl.release();
	}
}
