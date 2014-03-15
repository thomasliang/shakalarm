package com.example.shakalarm;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BasicAlarm implements Serializable {
	
	private static final long serialVersionUID = 847097409445317427L; //Class serialization ID
	final public static String BASIC_ALARM = "basicAlarm";
	final public static String PACKAGE_PREFIX = "com.example.shakalarm.";

	private boolean repetitive = false;
	private boolean[] repetition = { false, false, false, false, false, false, false };
	private Calendar calendar = new GregorianCalendar();
	
	public BasicAlarm(Calendar calendar, boolean[] repetition) throws IllegalArgumentException {
		if (repetition.length != 7) {
			throw new IllegalArgumentException("repetition array size not 7");
		}
		this.repetition = repetition;
		this.calendar = calendar;
	}

	public BasicAlarm() { //debug only, will be deleted

	}

	/**
	 * set a one time alarm, date.getTime() return the time set, minus the
	 * current time
	 * 
	 * @param context
	 * @author Wing last modified: 3/14
	 */
	public void setOnetimeAlarm(Context context) {
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		intent.putExtra(BASIC_ALARM, Boolean.TRUE);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);

		// difference of time between the time set and the current time
		long timeDifference = calendar.getTimeInMillis() - System.currentTimeMillis();
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + timeDifference, pi); //time difference in milliseconds
		String stringToDisplay = "Basic Alarm: one time alarm: time difference = " + timeDifference;
		Toast.makeText(context, stringToDisplay, Toast.LENGTH_SHORT).show();

	}

	/**
	 * set a repeating a alarm, currently ring every 5 seconds
	 * 
	 * @param context
	 * @author Wing last modified: 3/14
	 */
	public void SetRepeatingAlarm(Context context) {
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		intent.putExtra(BASIC_ALARM, Boolean.FALSE);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		// After after every 5 seconds
		am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 5, pi);
	}
}
