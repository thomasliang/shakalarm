package com.example.shakalarm;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
		final Intent intent = new Intent(this, BasicAlarmSetting.class);
		final Context context = this.getApplicationContext();
		final Button BasicAlarmButton = (Button) findViewById(R.id.buttontBasicAlarm);
		
		BasicAlarmButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//for testing the switching screen
				//startActivity(intent); 

				setBasicAlarm(v);
			}
		});
		
		final Button testButton = (Button) findViewById(R.id.buttonTest2);
		testButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "" + BasicAlarm.id_count, Toast.LENGTH_SHORT).show();
				
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
	 * repetiton - all set false means no repetition in a week date - the time
	 * that we set for the alarm to ring
	 * 
	 * @param view
	 * @author thomas last modified: March 14th
	 */

	public void setBasicAlarm(View view) {

		Context context = this.getApplicationContext();
		Calendar calendar = new GregorianCalendar();
		//calendar.set(2014, 2, 15, 12, 20, 30);
		calendar.setTimeInMillis(System.currentTimeMillis() + 5000); //currently ring after 5 seconds
		boolean[] repetition = { false, false, false, false, false, false, false };
		
		try {
			BasicAlarm ba = new BasicAlarm(calendar, repetition);
			ba.setOnetimeAlarm(context);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		//ba.SetRepeatingAlarm(context);
	}

}
