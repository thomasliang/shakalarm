package com.example.shakalarm;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;

import com.example.shakalarm.alert.AlarmManagerBroadcastReceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.widget.Toast;

//TODO Weekly repetition - boolean array created, how to use it?

public class Alarm implements Serializable {

	public enum Mode{
		NORMAL,
		SHAKING,
		BLOWING;
		
		@Override
		public String toString() {
			switch(this.ordinal()){
				case 0:
					return "NORMAL";
				case 1:
					return "SHAKING";
				case 2:
					return "BLOWING";
			}
			return super.toString();
		}
	}
	
	
	private static final long serialVersionUID = 847097409445317427L; //Class serialization ID

	public final static String BASIC_ALARM = "basicAlarm";
	public final static String PACKAGE_PREFIX = "com.example.shakalarm.";

	private int id;	
	public static int id_count = 0;
	
	private Boolean alarmActive = true;
	private Calendar alarmTime = Calendar.getInstance();
	private String alarmTonePath = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM).toString();
	private Boolean vibrate = true;
	private String alarmName = "Alarm Clock";
	private Mode mode = Mode.NORMAL;

	//private Boolean[] repetition = {false,false,false,false,false,false,false,};
	public Alarm() {
		
		
	}


	/**
	 * @return the alarmActive
	 */
	public Boolean getAlarmActive() {
		return alarmActive;
	}

	/**
	 * @param alarmActive the alarmActive to set
	 */
	public void setAlarmActive(Boolean alarmActive) {
		this.alarmActive = alarmActive;
	}

	/**
	 * @return the alarmTime
	 * If the alarmTime is before current time,
	 * then return the next-day calendar value for alarmTime.
	 * (for example, alarmTime is set to be 8:00 today,
	 * but now is 9:00 today, it returns 8:00 tomorrow)
	 * 
	 * schedule() function that set the AlarmManager broadcast
	 * will take this return value for setting
	 * 
	 * @author thomasleung
	 */
	
	public Calendar getAlarmTime() {
		
		if (alarmTime.before(Calendar.getInstance()))
			alarmTime.add(Calendar.DAY_OF_MONTH, 1);
		
		return alarmTime;
	}
	
	/**
	 * @param alarmTime the alarmTime to set
	 */
	public void setAlarmTime(Calendar alarmTime) {
		this.alarmTime = alarmTime;
	}
	
	/**
	 * Set alarmTime from user's input
	 * @author thomasleung
	 */
	public void setAlarmTime(String alarmTime) {

		String[] timePieces = alarmTime.split(":");

		Calendar newAlarmTime = Calendar.getInstance();
		newAlarmTime.set(Calendar.HOUR_OF_DAY,
				Integer.parseInt(timePieces[0]));
		newAlarmTime.set(Calendar.MINUTE, Integer.parseInt(timePieces[1]));
		newAlarmTime.set(Calendar.SECOND, 0);
		setAlarmTime(newAlarmTime);		
	}

	/**
	 * @return the alarmTonePath
	 */
	public String getAlarmTonePath() {
		return alarmTonePath;
	}

	/**
	 * @param alarmTonePath the alarmTonePath to set
	 */
	public void setAlarmTonePath(String alarmTonePath) {
		this.alarmTonePath = alarmTonePath;
	}

	/**
	 * @return the vibrate
	 */
	public Boolean getVibrate() {
		return vibrate;
	}

	/**
	 * @param vibrate the vibrate to set
	 */
	public void setVibrate(Boolean vibrate) {
		this.vibrate = vibrate;
	}

	/**
	 * @return the alarmName
	 */
	public String getAlarmName() {
		return alarmName;
	}

	/**
	 * @param alarmName the alarmName to set
	 */
	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}

	/**
	 * @return the mode
	 */
	public Mode getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
	}

	/**
	 * @param the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * the same function as the "setOnetimeAlarm",
	 * but this one is generalized
	 * @param context
	 */
	public void oneTimeSchedule(Context context) {
		setAlarmActive(true);
		
		Intent myIntent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		myIntent.putExtra("alarm", this);

		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent,PendingIntent.FLAG_CANCEL_CURRENT);

		AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

		alarmManager.set(AlarmManager.RTC_WAKEUP, getAlarmTime().getTimeInMillis(), pendingIntent);		
		
		String stringToDisplay = "One Time Alarm Set";
		Toast.makeText(context, stringToDisplay, Toast.LENGTH_SHORT).show();
	}
	public void dailySchedule(Context context) {
		setAlarmActive(true);
		
		Intent myIntent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		myIntent.putExtra("daily_alarm", this);

		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent,PendingIntent.FLAG_CANCEL_CURRENT);

		AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, getAlarmTime().getTimeInMillis(),86400000, pendingIntent);
		
		String stringToDisplay = "daily Time Alarm Set";
		Toast.makeText(context, stringToDisplay, Toast.LENGTH_SHORT).show();
	}
	
	public void weeklySchedule(Context context) {
		setAlarmActive(true);
		
		Intent myIntent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		myIntent.putExtra("weekly_alarm", this);

		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent,PendingIntent.FLAG_CANCEL_CURRENT);

		AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, getAlarmTime().getTimeInMillis(),604800000, pendingIntent);
		
		String stringToDisplay = "Weekly Time Alarm Set";
		Toast.makeText(context, stringToDisplay, Toast.LENGTH_SHORT).show();
	}

}
