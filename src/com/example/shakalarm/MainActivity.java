package com.example.shakalarm;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.shakalarm.BasicAlarmSettingActivity;
import com.example.shakalarm.MainActivity;
import com.example.shakalarm.R;
import com.example.shakalarm.Alarm.Day;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Get the AlarmManager Service
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new Intent(this, AlarmSetting.class);
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
		
		//choose to set a basic alarm
		final Button selectBasicAlarmButton = 
				(Button) findViewById(R.id.basic_alarm_selection);
		selectBasicAlarmButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentMain = new Intent(MainActivity.this, BasicAlarmSettingActivity.class);
				MainActivity.this.startActivity(intentMain);
	            //Log.i("Content "," Main layout ");
			}			
		});
		
		//choose to set shakalarm
		final Button selectShakalarmButton = 
				(Button) findViewById(R.id.shakalarm_selection);
		selectShakalarmButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentMain = new Intent(MainActivity.this, ShakalarmSettingActivity.class);
				MainActivity.this.startActivity(intentMain);
	            //Log.i("Content "," Main layout ");
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
	 * @author thomasleung last modified: March 14th
	 */
	@SuppressWarnings("deprecation")
	public void setBasicAlarm(View view) {

		Context context = this.getApplicationContext();
		try {
			Alarm test = new Alarm();

			/*
			 * TODO User interface for inputing alarm time 
			 * TODO Maintain a list of Alarms
			 * 
			 * Due to XML file not consistent, cannot implement TODO functions
			 * above.
			 * 
			 * @Author: Wing
			 * Last modified: 3/15
			 */

			Calendar time = Calendar.getInstance();
			time.add((Calendar.SECOND), 5); //currently set to 5 seconds later
			test.setAlarmTime(time);
			Day[] days = { Alarm.Day.SATURDAY }; //currently set to SATURDAY 
			test.setDays(days);
			test.oneTimeSchedule(context);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

}
