package accelerometer;

import facebook.HelloFacebookSampleActivity;
import facebook.HelloFacebookSampleActivity.*;
import shakalarm.alarm.R;
import stopwatch.StopWatchActivity;
import alarm.Alarm;
import alarm.AlarmActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
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
	
	public static final String message = "OK";

	private Alarm alarm;
    public final static String EXTRA_MESSAGE = "accelerometer.ShakalarmActivity.MESSAGE";


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
    private CountDownTimer timer = new CountDownTimer(10000, 1000000) {
    	@Override
    	public void onFinish() {
    		Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_LONG).show();
    		Intent intent = new Intent(ShakalarmActivity.this, HelloFacebookSampleActivity.class);
    		String message = "ok";
    	    intent.putExtra(EXTRA_MESSAGE, message);
			startActivity(intent);
    	}

		@Override
		public void onTick(long arg0) {
			
		}
    };
    
	private boolean toast_flag1 = true;
	private boolean alarmActive = true;

	private int shakeCountDown = 200;
	private final int INITIAL_SHAKECOUNTDOWN = 200;
	private int[] animation_picture_series_id = new int[15];

	private final int LAST_IMAGE_REMAINING_SHAKE_COUNT = 40;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_alert);
		getActionBar().setDisplayHomeAsUpEnabled(false); //This function disables returning to app's Home by clicking the app icon above.
		timer.start();
		
		//change textView on the screen
		ShakeCountDown_textview = (TextView) findViewById(R.id.shakeCountDown);
		ShakeCountDown_textview.setText("" + getShakeCountDown());
		
		//get the alarm info passed from the BroadcastReceiver
		Bundle bundle = this.getIntent().getExtras();
		alarm = (Alarm) bundle.getSerializable("alarm"); 
		this.setTitle(alarm.getAlarmName());

		//Seting the animation pictures
		init_animation_pic();

		//----------------------------
		startAlarm();
	}


	private void init_animation_pic() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int height = metrics.heightPixels;
		int width = metrics.widthPixels;
		picture_imageView = new ImageView(ShakalarmActivity.this);
		picture_imageView.setImageResource(R.drawable.a15);
		LayoutParams param = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		picture_imageView.setLayoutParams(param);
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.alarm_alert_relativeLayout);
		layout.addView(picture_imageView, Math.max(width, height), Math.max(width, height));
		animation_picture_series_id[0] = R.drawable.a15;
		animation_picture_series_id[1] = R.drawable.a14;
		animation_picture_series_id[2] = R.drawable.a13;
		animation_picture_series_id[3] = R.drawable.a12;
		animation_picture_series_id[4] = R.drawable.a11;
		animation_picture_series_id[5] = R.drawable.a10;
		animation_picture_series_id[6] = R.drawable.a9;
		animation_picture_series_id[7] = R.drawable.a8;
		animation_picture_series_id[8] = R.drawable.a7;
		animation_picture_series_id[9] = R.drawable.a6;
		animation_picture_series_id[10] = R.drawable.a5;
		animation_picture_series_id[11] = R.drawable.a4;
		animation_picture_series_id[12] = R.drawable.a3;
		animation_picture_series_id[13] = R.drawable.a2;
		animation_picture_series_id[14] = R.drawable.a1;
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
		acceleration_x = x;
		acceleration_y = y;
		acceleration_z = z;
	}

	/**
	 * This function will be invoked when the sensor detects a strong force
	 * (shaking)
	 */
	public void onShake(float force) {
		setAnimationImage(); 
		//-----------------------
		setShakeCountDown(getShakeCountDown() - 1);
		ShakeCountDown_textview.setText("" + getShakeCountDown());
		if (getShakeCountDown() <= 0) { 
			setShakeCountDown(0);
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
	 * Setting the animation image
	 */
	private void setAnimationImage() {
		int partitions = (INITIAL_SHAKECOUNTDOWN - LAST_IMAGE_REMAINING_SHAKE_COUNT) / animation_picture_series_id.length; // (200 - 40)/ 15; in last 40 shake countdowns, show the last image; other images occupy same amount of countdowns
		int image_no = (INITIAL_SHAKECOUNTDOWN - shakeCountDown) / partitions;
		if (image_no > 14) {
			image_no = 14;
		}		
		picture_imageView.setImageResource(animation_picture_series_id[image_no]);
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

	public int getShakeCountDown() {
		return shakeCountDown;
	}

	public void setShakeCountDown(int shakeCountDown) {
		this.shakeCountDown = shakeCountDown;
	}

}
