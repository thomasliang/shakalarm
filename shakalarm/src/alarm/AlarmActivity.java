package alarm;
import shakalarm.alarm.R;
import stopwatch.StopWatchActivity;
import timer.TimerActivity;
import alarm.database.Database;
import alarm.preference.AlarmPreferencesActivity;
import alarm.service.AlarmServiceBroadcastReciever;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import facebook.FacebookSampleActivity;

public class AlarmActivity extends ListActivity implements android.view.View.OnClickListener {

	ImageButton newButton;
	ListView alarmListView;
	AlarmListAdapter alarmListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.alarm_activity);

		////The alarm button on the bottom  (adding new alarm)
		newButton = (ImageButton) findViewById(shakalarm.alarm.R.id.Alarm_tab);
		newButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					newButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
					break;
				case MotionEvent.ACTION_UP:
					newButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
					v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
					Intent newAlarmIntent = new Intent(AlarmActivity.this, AlarmPreferencesActivity.class);
					newAlarmIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					startActivity(newAlarmIntent);
				case MotionEvent.ACTION_MOVE:
				case MotionEvent.ACTION_CANCEL:
					newButton.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
					break;
				}
				return true;
			}
		});
		
	  	

		//The timer button on the bottom (2nd from the left)
		final View stopWatchButton = (ImageButton) findViewById(shakalarm.alarm.R.id.Timer_tab);
		stopWatchButton.setOnTouchListener(new OnTouchListener() {
			@SuppressLint("InlinedApi")
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					stopWatchButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
					break;
				case MotionEvent.ACTION_UP:
					stopWatchButton.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
					v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
					Intent newAlarmIntent = new Intent(AlarmActivity.this, StopWatchActivity.class);
					newAlarmIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					startActivity(newAlarmIntent);

				case MotionEvent.ACTION_MOVE:
				case MotionEvent.ACTION_CANCEL:
					stopWatchButton.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
					break;
				}
				return true;
			}
		});

		//The count down timer button (3rd from the left)
		final View timerButton = (ImageButton) findViewById(shakalarm.alarm.R.id.Counter_tab);
		timerButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					timerButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
					break;
				case MotionEvent.ACTION_UP:
					timerButton.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
					Intent newAlarmIntent = new Intent(AlarmActivity.this, TimerActivity.class);
					newAlarmIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					startActivity(newAlarmIntent);

				case MotionEvent.ACTION_MOVE:
				case MotionEvent.ACTION_CANCEL:
					timerButton.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
					break;
				}
				return true;
			}
		});
		
		final View settingButton = (ImageButton) findViewById(shakalarm.alarm.R.id.Setting_tab);
		settingButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					settingButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
					break;
				case MotionEvent.ACTION_UP:
					settingButton.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
					v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
      				Intent newAlarmIntent = new Intent(AlarmActivity.this, FacebookSampleActivity.class);
      				newAlarmIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
         			//startActivity(newAlarmIntent);
					//Intent newAlarmIntent = new Intent(AlarmActivity.this, ScreamAlarmActivity.class);
					startActivity(newAlarmIntent);

				case MotionEvent.ACTION_MOVE:
				case MotionEvent.ACTION_CANCEL:
					settingButton.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
					break;
				}
				return true;
			}
		});

		
		
		//The alarm list in the center
		alarmListView = (ListView) findViewById(android.R.id.list);
		alarmListView.setLongClickable(true);
		
		//Long click: prompt "delete alarm?" message box
		alarmListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
				view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
				final Alarm alarm = (Alarm) alarmListAdapter.getItem(position);
				Builder dialog = new AlertDialog.Builder(AlarmActivity.this);
				dialog.setTitle("Delete");
				dialog.setMessage("Delete this alarm?");
				dialog.setPositiveButton("Ok", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						alarmListAdapter.getAlarms().remove(alarm);
						alarmListAdapter.notifyDataSetChanged();

						Database.init(AlarmActivity.this);
						Database.deleteEntry(alarm);

						AlarmActivity.this.callAlarmScheduleService();
					}
				});
				dialog.setNegativeButton("Cancel", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

				dialog.show();

				return true;
			}
		});

		//-----------------------
		callAlarmScheduleService();
	}

	private void callAlarmScheduleService() {
		Intent alarmServiceIntent = new Intent(AlarmActivity.this, AlarmServiceBroadcastReciever.class);
		sendBroadcast(alarmServiceIntent, null);
	}

	@Override
	protected void onPause() {
		// setListAdapter(null);
		Database.deactivate();
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();

		@SuppressWarnings("deprecation")
		final Object data = getLastNonConfigurationInstance();
		if (data == null) {
			alarmListAdapter = new AlarmListAdapter(this);
		} else {
			alarmListAdapter = (AlarmListAdapter) data;
		}

		this.setListAdapter(alarmListAdapter);

	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		return alarmListAdapter;
	}

	/**
	 * Go to the alarm preference activity when an item of the list is clicked
	 * @author Wing
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
		Alarm alarm = (Alarm) alarmListAdapter.getItem(position);
		Intent intent = new Intent(AlarmActivity.this, AlarmPreferencesActivity.class);
		intent.putExtra("alarm", alarm);
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		//Toast.makeText(AlarmActivity.this, "AlarmActivity: onClick", Toast.LENGTH_LONG).show();
		if (v.getId() == R.id.checkBox_alarm_active) {
			CheckBox checkBox = (CheckBox) v;
			Alarm alarm = (Alarm) alarmListAdapter.getItem((Integer) checkBox.getTag());
			alarm.setAlarmActive(checkBox.isChecked());
			Database.update(alarm);
			AlarmActivity.this.callAlarmScheduleService();
			if (checkBox.isChecked()) {
				Toast.makeText(AlarmActivity.this, alarm.getTimeUntilNextAlarmMessage(), Toast.LENGTH_LONG).show();
			}
		}

	}

	@Override
	//Inflate the option menu when the device option button is clicked
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	//When an option of the option menu is selected, this method will be called
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_item_rate:
			Uri uri = Uri.parse("market://details?id=" + getPackageName());
			Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
			try {
				startActivity(goToMarket);
			} catch (ActivityNotFoundException e) {
				Toast.makeText(this, "Couldn't launch the market", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.menu_item_website:
			String url = "github.com/thomasleung/shakalarm";
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
			break;
		case R.id.menu_item_report:
			Intent send = new Intent(Intent.ACTION_SENDTO);
			String uriText;

			String emailAddress = "handsomeming@live.cn";
			String subject = R.string.app_name + " Bug Report";
			String body = "Debug:";
			body += "\n OS Version: " + System.getProperty("os.version") + "(" + android.os.Build.VERSION.INCREMENTAL + ")";
			body += "\n OS API Level: " + android.os.Build.VERSION.SDK_INT;
			body += "\n Device: " + android.os.Build.DEVICE;
			body += "\n Model (and Product): " + android.os.Build.MODEL + " (" + android.os.Build.PRODUCT + ")";
			body += "\n Screen Width: " + getWindow().getWindowManager().getDefaultDisplay().getWidth();
			body += "\n Screen Height: " + getWindow().getWindowManager().getDefaultDisplay().getHeight();
			body += "\n Hardware Keyboard Present: " + (getResources().getConfiguration().keyboard != Configuration.KEYBOARD_NOKEYS);

			uriText = "mailto:" + emailAddress + "?subject=" + subject + "&body=" + body;

			uriText = uriText.replace(" ", "%20");
			Uri emalUri = Uri.parse(uriText);

			send.setData(emalUri);
			startActivity(Intent.createChooser(send, "Send mail..."));
			break;
		}
		return super.onOptionsItemSelected(item);

	}

}
