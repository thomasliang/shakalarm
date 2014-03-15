package com.example.shakalarm.alert;

import com.example.shakalarm.alert.AlarmManagerBroadcastReceiver;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
<<<<<<< HEAD
import android.widget.Button;
=======
>>>>>>> FETCH_HEAD
import android.widget.Toast;

import com.example.shakalarm.Alarm;
import com.example.shakalarm.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class AlarmAlertActivity extends Activity implements OnClickListener {

	private Alarm alarm;

	//private MediaPlayer mediaPlayer;
	//private Vibrator vibrator;

	private boolean alarmActive;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
<<<<<<< HEAD

		//TODO alarm_alert layout 
		setContentView(R.layout.alarm_alert);	
		
		final Button testButton = (Button) findViewById(R.id.buttonTest3);
	
	    testButton.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) {
               kill();
		}
	    });
	    
		try {
			startAlarm();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void kill()
	{
		this.finish();
	}
=======
		//		final Window window = getWindow();
		//		window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
		//				| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		//		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
		//				| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

		//TODO alarm_alert layout 
		setContentView(R.layout.alarm_alert);

		//		Bundle bundle = this.getIntent().getExtras();
		//		alarm = (Alarm) bundle.getSerializable("alarm");
		//
		//		this.setTitle(alarm.getAlarmName());
		//
		//		//Mode selection
		//		switch (alarm.getMode()) {
		//		case NORMAL:
		//            //...
		//			break;
		//		case SHAKING:
		//            //...
		//			break;
		//		case BLOWING:
		//			//...
		//			break;
		//		}
		
				startAlarm();
				Thread thread = new Thread() {
					
				}
				this.finish();				

	}
>>>>>>> FETCH_HEAD

	
	   private void startAlarm() throws InterruptedException {

		Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		Ringtone r = RingtoneManager.getRingtone(getBaseContext(), notification);
		r.play();

		//w1.release() here, please test it @Wing
		//AlarmManagerBroadcastReceiver.lockOff(getBaseContext());

	}

	/*
	 * for vibrating and ringTone playing use, as those haven't finished, not
	 * enabled now.
	 * 
	 * @author thomasleung last modified 15/3/2014
	 */
<<<<<<< HEAD
	
  
//       private void startAlarm() {
//
//		if (alarm.getAlarmTonePath() != "") {
//			mediaPlayer = new MediaPlayer();
//			if (alarm.getVibrate()) {
//				vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
//				long[] pattern = { 1000, 200, 200, 200 };
//				vibrator.vibrate(pattern, 0);
//			}
//			try {
//				mediaPlayer.setVolume(1.0f, 1.0f);
//				mediaPlayer.setDataSource(this,
//						Uri.parse(alarm.getAlarmTonePath()));
//				mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
//				mediaPlayer.setLooping(true);
//				mediaPlayer.prepare();
//				mediaPlayer.start();
//
//			} catch (Exception e) {
//				mediaPlayer.release();
//				alarmActive = false;
//			}
//		}	
//
//	}

	
=======

	/*
	 * private void startAlarm() {
	 * 
	 * if (alarm.getAlarmTonePath() != "") { mediaPlayer = new MediaPlayer(); if
	 * (alarm.getVibrate()) { vibrator = (Vibrator)
	 * getSystemService(VIBRATOR_SERVICE); long[] pattern = { 1000, 200, 200,
	 * 200 }; vibrator.vibrate(pattern, 0); } try { mediaPlayer.setVolume(1.0f,
	 * 1.0f); mediaPlayer.setDataSource(this,
	 * Uri.parse(alarm.getAlarmTonePath()));
	 * mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
	 * mediaPlayer.setLooping(true); mediaPlayer.prepare(); mediaPlayer.start();
	 * 
	 * } catch (Exception e) { mediaPlayer.release(); alarmActive = false; } }
	 * 
	 * }
	 */

>>>>>>> FETCH_HEAD
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		alarmActive = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		if (!alarmActive)
			super.onBackPressed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		AlarmManagerBroadcastReceiver.lockOff(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
<<<<<<< HEAD

//	@Override
//	protected void onDestroy() {
//		try {
//			if (vibrator != null)
//				vibrator.cancel();
//		} catch (Exception e) {
//
//		}
//		try {
//			mediaPlayer.stop();
//		} catch (Exception e) {
//
//		}
//		try {
//			mediaPlayer.release();
//		} catch (Exception e) {
//
//		}
//		super.onDestroy();
//	}
 
	
=======

	@Override
	protected void onDestroy() {
		//		try {
		//			if (vibrator != null)
		//				vibrator.cancel();
		//		} catch (Exception e) {
		//
		//		}
		//		try {
		//			mediaPlayer.stop();
		//		} catch (Exception e) {
		//
		//		}
		//		try {
		//			mediaPlayer.release();
		//		} catch (Exception e) {
		//
		//		}

		super.onDestroy();
	}

>>>>>>> FETCH_HEAD
	/*
	 * Functions for detecting shaking & blowing
	 */
	public boolean isShaked() {
		boolean correct = false;
		//...
		return correct;
	}

	public boolean isBlown() {
		boolean correct = false;
		//...
		return correct;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
