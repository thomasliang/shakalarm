package com.example.shakalarm;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
	final public static String PACKAGE_PREFIX = "com.example.shakalarm.";

	@Override
	public void onReceive(Context context, Intent intent) {
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
		// Acquire the lock
		wl.acquire();

		try {
			// You can do the processing here.
			Bundle extras = intent.getExtras();
			StringBuilder msgStr = new StringBuilder();
			msgStr.append("Basic Alarm: One time Timer : ");

			if (extras != null) {
				if (intent.getSerializableExtra(PACKAGE_PREFIX + BASIC_ALARM) != null) {
					BasicAlarm ba= (BasicAlarm) (intent.getSerializableExtra(PACKAGE_PREFIX + BASIC_ALARM));
					msgStr.append("true: ");
					msgStr.append("ID: " + ba.getId() + ": ");
				} else {
					msgStr.append("false: ");
				}
				intent.removeExtra(PACKAGE_PREFIX + BASIC_ALARM);
			}
			Format formatter = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault());
			msgStr.append(formatter.format(new Date()));

			//Play alarm sound "Ding"~
			Toast.makeText(context, msgStr, Toast.LENGTH_LONG).show();
			Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			Ringtone r = RingtoneManager.getRingtone(context, notification);
			r.play();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			wl.release();
		}
	}
}