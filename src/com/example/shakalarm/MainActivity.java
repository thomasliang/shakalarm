package com.example.shakalarm;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Get the AlarmManager Service
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button BasicAlarmButton = (Button) findViewById(R.id.buttontBasicAlarm);
		
		final Intent intent = new Intent(this, BasicAlarmSetting.class);
		
		BasicAlarmButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//startActivity(intent);
				setBasicAlarm(v);
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

	public void setBasicAlarm(View view) {
		Context context = this.getApplicationContext();
		Date date = new Date(114, 2, 14, 21, 30, 0); //Thomas please help here 
		boolean[] repetition = { false, false, false, false, false, false, false };
		
		BasicAlarm ba = new BasicAlarm(date,false,repetition);
		//ba.setOnetimeAlarm(context);
		ba.SetRepeatingAlarm(context);
	}

}