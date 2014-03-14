package com.example.shakalarm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BasicAlarmSetting extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Get the AlarmManager Service
		super.onCreate(savedInstanceState);
		setContentView(R.layout.basic_alarm_setting_layout);

		final Button testButton = (Button) findViewById(R.id.buttonTest);
		testButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			}
		});

	}
}
