package accelerometer;

import shakalarm.alarm.R;
import alarm.Alarm;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ShakalarmActivity extends Activity implements AccelerometerListener {

	private Alarm alarm;

	private TextView acceleration_textView_x;
	private TextView acceleration_textView_y;
	private TextView acceleration_textView_z;

	private float acceleration_x;
	private float acceleration_y;
	private float acceleration_z;

	private ImageView picture_imageView;
	private Handler handler = new Handler();
	private Runnable runnable;

	private TextView ShakeCountDown_textview;
	private MediaPlayer mediaPlayer;
	private Vibrator vibrator;

	private boolean toast_flag1 = true;
	private boolean alarmActive = true;

	private int shakeCountDown = 150;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_alert);
		getActionBar().setDisplayHomeAsUpEnabled(false); //This function disables returning to app's Home by clicking the app icon above.
		
		//change textView on the screen
		acceleration_textView_x = (TextView) findViewById(R.id.acceleration_x);
		acceleration_textView_y = (TextView) findViewById(R.id.acceleration_y);
		acceleration_textView_z = (TextView) findViewById(R.id.acceleration_z);
		ShakeCountDown_textview = (TextView) findViewById(R.id.shakeCountDown);
		ShakeCountDown_textview.setText("" + shakeCountDown);
		
		//get the alarm info passed from the BroadcastReceiver
		Bundle bundle = this.getIntent().getExtras();
		alarm = (Alarm) bundle.getSerializable("alarm"); 
		this.setTitle(alarm.getAlarmName());

		//Seting the animation pictures
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int height = metrics.heightPixels;
		int width = metrics.widthPixels;
		picture_imageView = new ImageView(ShakalarmActivity.this);
		picture_imageView.setImageResource(R.drawable.kim_sung);
		LayoutParams param = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		picture_imageView.setLayoutParams(param);
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.alarm_alert_relativeLayout);
		layout.addView(picture_imageView, Math.max(width, height), Math.max(width, height));

		//----------------------------
		startAlarm();
	}

	private void startAlarm() {
		if (alarm.getAlarmTonePath() != "") {
			mediaPlayer = new MediaPlayer();
			if (alarm.getVibrate()) {
				vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
				long[] pattern = { 1000, 200, 200, 200 };
				vibrator.vibrate(pattern, 0);
			}
			try {
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.setDataSource(this, Uri.parse(alarm.getAlarmTonePath()));
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
				mediaPlayer.setLooping(true);
				mediaPlayer.prepare();
				mediaPlayer.start();
			} catch (Exception e) {
				mediaPlayer.release();
			}
		}
	}

	/**
	 * This function will be invoked (very frequently), when the force (by
	 * gravity) vector changes for the 3 dimension of the phone
	 */
	public void onAccelerationChanged(float x, float y, float z) {
		acceleration_textView_x.setText("Acceleration x: " + x + " m/s^2");
		acceleration_textView_y.setText("Acceleration y: " + y + " m/s^2");
		acceleration_textView_z.setText("Acceleration z: " + z + " m/s^2");
		acceleration_x = x;
		acceleration_y = y;
		acceleration_z = z;
	}

	/**
	 * This function will be invoked when the sensor detects a strong force
	 * (shaking)
	 */
	public void onShake(float force) {
		setFlashImage(); 

		//-----------------------
		shakeCountDown--;
		ShakeCountDown_textview.setText("" + shakeCountDown);
		if (shakeCountDown <= 0) {
			alarmActive = false;
			if (vibrator != null)
				vibrator.cancel();
			try {
				mediaPlayer.stop();
			} catch (IllegalStateException ise) {

			}
			try {
				mediaPlayer.release();
			} catch (Exception e) {
			}
			super.finish();
		}
	}

	/**
	 * Setting a flash of an image
	 */
	private void setFlashImage() {
		picture_imageView.setImageResource(R.drawable.oh_image);
		handler.removeCallbacks(runnable);
		runnable = new Runnable() {
			@Override
			public void run() {
				picture_imageView.setImageResource(R.drawable.kim_sung);
			}
		};
		handler.postDelayed(runnable, 500);
	}

	@Override
	public void onResume() {
		super.onResume();
		//Check device supported Accelerometer sessor or not
		if (AccelerometerManager.isSupported(this)) {

			//Start Accelerometer Listening
			AccelerometerManager.startListening(this);
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		
		//Check device supported Accelerometer sensor or not
		if (AccelerometerManager.isListening()) {
			//Start Accelerometer Listening
			AccelerometerManager.stopListening();
		}

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("Sensor", "Service  destroy");

		//Check device supported Accelerometer sensor or not
		if (AccelerometerManager.isListening()) {
			//Start Accelerometer Listening
			AccelerometerManager.stopListening();
		}

	}

	/**
	 * Overriding this function prevents the user from destroying the activity
	 * by pressing the backbutton
	 */
	@Override
	public void onBackPressed() {
		if (toast_flag1) {
			toast_flag1 = false;
			Toast.makeText(getBaseContext(), "Don't press! Shake it!", Toast.LENGTH_LONG).show();
		}
		if (!alarmActive)
			super.onBackPressed();
	}

}
