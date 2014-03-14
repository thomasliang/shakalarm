package com.example.shakalarm;

import java.io.File;
import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.widget.Toast;

public class BasicAlarm {
	final public static String BASIC_ALARM = "basicAlarm";

	boolean enabled = false;
	boolean repetitive = false;
	boolean[] repetition = { false, false, false, false, false, false, false };
	Date date = new Date();
	File ringTone;

	public BasicAlarm(Date date, boolean enabled, boolean[] repetition) {
		if (repetition.length == 7) {
			this.repetition = repetition;
		}
		this.enabled = enabled;
		this.date = date;
	}

	/**
	 * set a one time alarm, date.getTime() return the time set, minus the current time
	 * @param context
	 * @author Wing
	 * last modified: 3/14
	 */
	public void setOnetimeAlarm(Context context) {
		Toast.makeText(context, "ready to set alarm", Toast.LENGTH_SHORT)
				.show();
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		intent.putExtra(BASIC_ALARM, Boolean.TRUE);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);

		// difference of time between the time set and the current time
		am.set(AlarmManager.RTC_WAKEUP,
				date.getTime() - System.currentTimeMillis(), pi); //time difference in milliseconds
	}

	/**
	 * set a repeating a alarm, currently ring every 5 seconds
	 * @param context
	 * @author Wing
	 * last modified: 3/14
	 */
	public void SetRepeatingAlarm(Context context) {
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		intent.putExtra(BASIC_ALARM, Boolean.FALSE);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		// After after every 5 seconds
		am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
				1000 * 5, pi);
	}

	public File getRingTone() {
		return ringTone;
	}

	public void setRingTone(File ringTone) {
		this.ringTone = ringTone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isRepetitive() {
		return repetitive;
	}

	public void setRepetitive(boolean repetitive) {
		this.repetitive = repetitive;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void activate() {
	};

}
