package com.example.shakalarm;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {
	private AlarmManagerBroadcastReceiver alarm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Get the AlarmManager Service
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// AnalogClock analogClock = (AnalogClock)
		// findViewById(R.id.analog_clock);

		// setting alarm button
		final Button settingButton = (Button) findViewById(R.id.setting_alarm_button);
		settingButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Single Alarm Set",
						Toast.LENGTH_LONG).show();
			}
		});
		
		final Button setOneTimeAlarmButton = (Button) findViewById(R.id.btOneTime);
		alarm = new AlarmManagerBroadcastReceiver();
		setOneTimeAlarmButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onetimeTimer(v);
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

	public void onetimeTimer(View view) {
		Context context = this.getApplicationContext();
		if (alarm != null) {
			alarm.setOnetimeTimer(context);
		} else {
			Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
		}
	}

}