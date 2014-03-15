package com.example.shakalarm;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.example.shakalarm.Alarm.Day;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Get the AlarmManager Service
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Intent intent = new Intent(this, AlarmSetting.class);
		final Context context = this.getApplicationContext();
		final Button BasicAlarmButton = (Button) findViewById(R.id.buttontBasicAlarm);
		
		BasicAlarmButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				setBasicAlarm(v);
			}
		});
		
		final Button testButton = (Button) findViewById(R.id.buttonTest2);
		testButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "" + Alarm.id_count, Toast.LENGTH_SHORT).show();
				
			}
		});

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	/**
	 * 
	 * @param view
	 * @author thomasleung 
	 * last modified: March 14th
	 */
	public void setBasicAlarm(View view) {

		Context context = this.getApplicationContext();		
		try {
			Alarm test = new Alarm();
			
			/*
			 * Can input any time string here for testing,
			 * since I've overloaded the setAlarmTime function
			 * (not tested, sorry, please try it)
			 * 
			 * TODO User interface for inputing alarm time
			 * TODO Maintain a list of Alarms
			 * 
			 * Due to XML file not consistent,
			 * cannot implement TODO functions above.
			 * 
			 * @author thomasleung
			 * last modified 15/3/2014
			 */
			
			Calendar time = Calendar.getInstance();
			time.add((Calendar.MINUTE),1);
			test.setAlarmTime(time);
			Day[] days= {Alarm.Day.SATURDAY};
			test.setDays(days);
			test.oneTimeSchedule(context);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

}
