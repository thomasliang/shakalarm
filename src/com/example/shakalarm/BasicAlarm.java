package com.example.shakalarm;

import java.io.File;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.widget.Toast;

public class BasicAlarm {
	boolean enabled,repetitive;
    boolean[] reptition = new boolean[7];
    Time time = new Time();
    File ringTone;
    
    final public static String BASIC_ALARM = "basicAlarm";

    public void setOnetimeTimer(Context context) {
		Toast.makeText(context, "ready to set alarm", Toast.LENGTH_SHORT).show();
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		intent.putExtra(BASIC_ALARM, Boolean.TRUE);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		//ring after 5 second
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+5000, pi);
	}
    
	public File getRingTone() {
		return ringTone;
	}
	public void setRingTone(File ringTone) {
		this.ringTone = ringTone;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
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
	public boolean[] getReptition() {
		return reptition;
	}
	public void setReptition(boolean[] reptition) {
		this.reptition = reptition;
	}
	public void activate(){};
    

}
