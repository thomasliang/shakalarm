package com.example.shakalarm.alert;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.Toast;

import com.example.shakalarm.Alarm;

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {
	final public static String BASIC_ALARM = "basicAlarm";
	final public static String PACKAGE_PREFIX = "com.example.shakalarm.";

	private static PowerManager.WakeLock wl = null;

	/*
	 * lockOn & lockOff functions are encapsulated version of w1.acquire() &
	 * w1.release(), Reason: 1. Enable the w1.release() operation in next
	 * activity by invoking lockOff(getBaseContext()) (not tested yet) 2. More
	 * organized and structured
	 * 
	 * @author thomasleung
	 */
	public static void lockOn(Context context) {
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		//Object flags;
		if (wl == null)
			wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "SHAKALARM");
		wl.acquire();
	}

	/*
	 * TODO deal with the deprecated wake lock in lockOn()
	 */
	public static void lockOff(Context context) {
		//		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		try {
			if (wl != null)
				wl.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
	 * android.content.Intent) When broadcast message is received, move to
	 * another activity to show the shaking interface
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "ringing", Toast.LENGTH_SHORT).show();

		lockOn(context);
		
		try {
			Bundle bundle = intent.getExtras();
			final Alarm alarm = (Alarm) bundle.getSerializable("alarm");

			Intent AlarmAlertActivityIntent;

			AlarmAlertActivityIntent = new Intent(context, AlarmAlertActivity.class);

			AlarmAlertActivityIntent.putExtra("alarm_alert", alarm);

			AlarmAlertActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			context.startActivity(AlarmAlertActivityIntent);
		} catch (Exception e) {
			Toast.makeText(context, "exception detected: " + e.toString(), Toast.LENGTH_LONG).show();

			e.printStackTrace();
		} finally {
			lockOff(context);
		}
		//lockOff operation (w1.release()) is done inside new activity, can? NO!!
	}
}