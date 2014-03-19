package com.example.shakalarm;

import java.util.Calendar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.TimePickerDialog;
import android.app.AlertDialog.Builder;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.res.Configuration;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class BasicAlarmSettingActivity extends ListActivity {
	ImageButton deleteButton;
	TextView okButton;
	TextView cancelButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_settings);
		//don't know yet what this do
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		deleteButton = (ImageButton) findViewById(R.id.toolbar).findViewById(R.id.button_delete);
		deleteButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					deleteButton.setBackgroundColor(getResources().getColor(R.color.holo_blue_light));
					break;
				case MotionEvent.ACTION_UP:
					v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
					Builder dialog = new AlertDialog.Builder(BasicAlarmSettingActivity.this);
					dialog.setTitle("Delete");
					dialog.setMessage("Delete this alarm?");
					//function to be added
					dialog.setPositiveButton("Ok", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					dialog.setNegativeButton("Cancel", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
					dialog.show();
				//not yet sure what this do						
				case MotionEvent.ACTION_MOVE:
				case MotionEvent.ACTION_CANCEL:
					deleteButton.setBackgroundColor(getResources().getColor(android.R.color.transparent));
					break;
				}
				return true;
			}
		});
		
		okButton = (TextView) findViewById(R.id.textView_OK);
		okButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					okButton.setBackgroundColor(getResources().getColor(R.color.holo_blue_light));
					break;
				case MotionEvent.ACTION_UP:
					v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
					finish();
				case MotionEvent.ACTION_MOVE:
				case MotionEvent.ACTION_CANCEL:
					okButton.setBackgroundColor(getResources().getColor(android.R.color.transparent));
					break;
				}
				return true;
			}
		});

		cancelButton = (TextView) findViewById(R.id.textView_cancel);
		cancelButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					cancelButton.setBackgroundColor(getResources().getColor(R.color.holo_blue_light));
					break;
				case MotionEvent.ACTION_UP:
					v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
					//go back to main activity
					finish();
				case MotionEvent.ACTION_MOVE:
				case MotionEvent.ACTION_CANCEL:
					cancelButton.setBackgroundColor(getResources().getColor(android.R.color.transparent));
					break;
				}
				return true;
			}
		});
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		AlertDialog.Builder alert;
		v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
		
		//temparary use
		int iamuseless = 0;
		switch (iamuseless) {
		case 0:
			CheckedTextView checkedTextView = (CheckedTextView) v;
			boolean checked = !checkedTextView.isChecked();
			((CheckedTextView) v).setChecked(checked);
			break;
		case 1:

			alert = new AlertDialog.Builder(this);

			alert.setTitle("Hello I'm Useless");
			// alert.setMessage(message);

			// Set an EditText view to get user input
			final EditText input = new EditText(this);

			//input.setText(alarmPreference.getValue().toString());

			alert.setView(input);
			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
				}
			});
			alert.show();
			break;
		case 2:
			alert = new AlertDialog.Builder(this);

			alert.setTitle("I'm 2");
			// alert.setMessage(message);
			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
				}
			});
			alert.show();
			break;
		case 3:
			alert = new AlertDialog.Builder(this);
			// alert.setMessage(message);

			CharSequence[] multiListItems = new CharSequence[3];

			boolean[] checkedItems = new boolean[multiListItems.length];
			
			alert.setMultiChoiceItems(multiListItems, checkedItems, new OnMultiChoiceClickListener() {

				@Override
				public void onClick(final DialogInterface dialog, int which, boolean isChecked) {

				}
			});
			alert.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {

				}
			});
			alert.show();
			break;
		case 4:
			TimePickerDialog timePickerDialog = new TimePickerDialog(this, new OnTimeSetListener() {

				@Override
				public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
					Calendar newAlarmTime = Calendar.getInstance();
					newAlarmTime.set(Calendar.HOUR_OF_DAY, hours);
					newAlarmTime.set(Calendar.MINUTE, minutes);
					newAlarmTime.set(Calendar.SECOND, 0);
				}
			}, Calendar.HOUR_OF_DAY, Calendar.MINUTE, true);
			timePickerDialog.show();
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
