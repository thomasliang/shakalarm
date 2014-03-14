package com.example.shakalarm;

import java.util.Date;

public class BlowAlarm extends BasicAlarm
{
    public BlowAlarm(Date date, boolean enabled, boolean[] repetition) {
		super(date, enabled, repetition);
		// TODO Auto-generated constructor stub
	}
	int blowingLevel;
    public void activate(){};
}
