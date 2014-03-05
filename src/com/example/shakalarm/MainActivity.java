package com.example.shakalarm;

import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements android.view.View.OnClickListener{
	Button btnLeft, btnRight;
	TextView textDisplay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//initialize instances
		btnLeft = (Button) findViewById(R.id.btnLeft);
		btnRight = (Button) findViewById(R.id.btnRight);
		textDisplay = (TextView) findViewById(R.id.textDisplay);
		
		//Set listener
		btnLeft.setOnClickListener(this);
		btnRight.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		if (view == btnLeft) {
			textDisplay.setText(R.string.left);
		} else if (view == btnRight) {
			textDisplay.setText(R.string.right);
		}
	}
	

}
