package com.example.shakalarm;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.Toast;

public class BlowalarmSettingActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Get the AlarmManager Service
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blowalarm_setting);
		
		//AnalogClock analogClock = (AnalogClock) findViewById(R.id.analog_clock);
		
		//press confirm button and go back to main activity
		final Button confirm_button = 
				(Button) findViewById(R.id.confirm_button);
		confirm_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentMain = new Intent(BlowalarmSettingActivity.this, MainActivity.class);
				BlowalarmSettingActivity.this.startActivity(intentMain);
	            //Log.i("Content "," Main layout ");
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
