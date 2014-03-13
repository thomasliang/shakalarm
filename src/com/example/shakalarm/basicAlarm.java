package com.example.shakalarm;

import java.io.File;

import android.text.format.Time;

public class basicAlarm {
	boolean enabled,repetitive;
    boolean[] reptition = new boolean[7];
    Time time = new Time();
    File ringTone;
    
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
