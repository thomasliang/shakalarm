package screamalarm;

import shakalarm.alarm.R;
import alarm.Alarm;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ScreamAlarmActivity extends Activity {
	private Alarm alarm;
	private MediaPlayer mediaPlayer;
	private Vibrator vibrator;
	private boolean pressed = false;

	private static final int POLL_INTERVAL = 300;

	private boolean mRunning = false;

	private int mThreshold;

	private PowerManager.WakeLock mWakeLock;

	private Handler mHandler = new Handler();

	//private TextView mStatusView; //The status textView showing "monitoring voice..." or "stopped"
	//private SoundLevelView mDisplay;

	private SoundMeter mSensor;

	private int screamCount = 14;
	private int INIT_SCREAM_COUNT = 14;

	private boolean toast_flag1 = true;

	private int[] animation_picture_series_id = new int[9];


	private ImageView picture_imageView;

	private Runnable mSleepTask = new Runnable() {
		public void run() {
			//Log.i("Noise", "runnable mSleepTask");

			start();
		}
	};

	private Runnable mPollTask = new Runnable() {
		public void run() {

			double amp = mSensor.getAmplitude();
			//Log.i("Noise", "runnable mPollTask");
			updateDisplay("SCREAM!: " + screamCount, amp);

			if ((amp > mThreshold)) {
				callForHelp();
				//Log.i("Noise", "==== onCreate ===");

			}

			// Runnable(mPollTask) will again execute after POLL_INTERVAL
			mHandler.postDelayed(mPollTask, POLL_INTERVAL);

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Defined SoundLevelView in main.xml file
		setContentView(R.layout.screamalarm_activity);
		//mStatusView = (TextView) findViewById(R.id.status);

		// Used to record voice
		mSensor = new SoundMeter();
		//mDisplay = (SoundLevelView) findViewById(R.id.volume);
		Bundle bundle = this.getIntent().getExtras();
		alarm = (Alarm) bundle.getSerializable("alarm");
		this.setTitle(alarm.getAlarmName());
		if(alarm.getMode() == Alarm.AlarmMode.BLOWALARM)
		{
			PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
			mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "NoiseAlert");

			init_animation_pic_blow();
			pressed = false;
			startAlarm();
		}
		else{
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "NoiseAlert");

		init_animation_pic();
		pressed = false;
		startAlarm();
		}
		
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

	@Override
	public void onResume() {
		super.onResume();
		//Log.i("Noise", "==== onResume ===");

		initializeApplicationConstants();
		//mDisplay.setLevel(0, mThreshold);

		if (!mRunning) {
			mRunning = true;
			start();
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		// Log.i("Noise", "==== onStop ===");

		//Stop noise monitoring
		stop();

	}

	private void start() {
		//Log.i("Noise", "==== start ===");

		mSensor.start();
		if (!mWakeLock.isHeld()) {
			mWakeLock.acquire();
		}

		//Noise monitoring start
		// Runnable(mPollTask) will execute after POLL_INTERVAL
		mHandler.postDelayed(mPollTask, POLL_INTERVAL);
	}

	private void stop() {
		Log.i("Noise", "==== Stop Noise Monitoring===");
		if (mWakeLock.isHeld()) {
			mWakeLock.release();
		}
		mHandler.removeCallbacks(mSleepTask);
		mHandler.removeCallbacks(mPollTask);
		mSensor.stop();
		//mDisplay.setLevel(0, 0);
		updateDisplay("stopped...", 0.0);
		mRunning = false;

	}

	private void initializeApplicationConstants() {
		// Set Noise Threshold
		mThreshold = 8;

	}

	private void updateDisplay(String status, double signalEMA) {
		//mStatusView.setText(status);
		// 
		//mDisplay.setLevel((int) signalEMA, mThreshold);
	}

	private void init_animation_pic() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int height = metrics.heightPixels;
		int width = metrics.widthPixels;
		picture_imageView = new ImageView(ScreamAlarmActivity.this);
		picture_imageView.setImageResource(R.drawable.screamalarm1);
		LayoutParams param = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		picture_imageView.setLayoutParams(param);
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.screamalarm_activity_relativeLayout);
		layout.addView(picture_imageView, Math.max(width, height), Math.max(width, height));
		animation_picture_series_id[0] = R.drawable.screamalarm1;
		animation_picture_series_id[1] = R.drawable.screamalarm2;
		animation_picture_series_id[2] = R.drawable.screamalarm3;
		animation_picture_series_id[3] = R.drawable.screamalarm4;
		animation_picture_series_id[4] = R.drawable.screamalarm5;
		animation_picture_series_id[5] = R.drawable.screamalarm6;
		animation_picture_series_id[6] = R.drawable.screamalarm7;

		picture_imageView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//int action = event.getAction();
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					pressed = true;
					Toast.makeText(getApplicationContext(), "Keep pressing and scream!", Toast.LENGTH_LONG).show();
					stopAlarm();
					break;

				case MotionEvent.ACTION_MOVE:
					//User is moving around on the screen
					break;

				case MotionEvent.ACTION_UP:
					pressed = false;
					startAlarm();
					break;
				}
				return pressed;
			}
		});
	}
	
	private void init_animation_pic_blow() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int height = metrics.heightPixels;
		int width = metrics.widthPixels;
		picture_imageView = new ImageView(ScreamAlarmActivity.this);
		picture_imageView.setImageResource(R.drawable.blowalarm1);
		LayoutParams param = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		picture_imageView.setLayoutParams(param);
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.screamalarm_activity_relativeLayout);
		layout.addView(picture_imageView, Math.max(width, height), Math.max(width, height));
		animation_picture_series_id[0] = R.drawable.blowalarm1;
		animation_picture_series_id[1] = R.drawable.blowalarm2;
		animation_picture_series_id[2] = R.drawable.blowalarm3;
		animation_picture_series_id[3] = R.drawable.blowalarm4;
		animation_picture_series_id[4] = R.drawable.blowalarm5;
		animation_picture_series_id[5] = R.drawable.blowalarm6;
		animation_picture_series_id[6] = R.drawable.blowalarm7;
		animation_picture_series_id[7] = R.drawable.blowalarm8;
		animation_picture_series_id[8] = R.drawable.blowalarm9;



		picture_imageView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//int action = event.getAction();
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					pressed = true;
					Toast.makeText(getApplicationContext(), "Keep pressing and scream!", Toast.LENGTH_LONG).show();
					stopAlarm();
					break;

				case MotionEvent.ACTION_MOVE:
					//User is moving around on the screen
					break;

				case MotionEvent.ACTION_UP:
					pressed = false;
					startAlarm();
					break;
				}
				return pressed;
			}
		});
	}

	private void callForHelp() {
		if (pressed) {
			int partitions = (INIT_SCREAM_COUNT) / animation_picture_series_id.length;
			int image_no = (INIT_SCREAM_COUNT - screamCount) / partitions;
			if (image_no > animation_picture_series_id.length - 1) {
				picture_imageView.setImageResource(animation_picture_series_id[animation_picture_series_id.length - 1]);
			} else {
				picture_imageView.setImageResource(animation_picture_series_id[image_no]);
			}

			//stop();

			// Show alert when noise threshold crossed
			Toast.makeText(getApplicationContext(), "Noise Threshold Crossed! Continue!.", Toast.LENGTH_LONG).show();
			--screamCount;
			if (screamCount <= 0) {
				screamCount = 0;
				stopAlarm();
				super.finish();
			}
		}
	}

	private void stopAlarm() {
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
	}

	/**
	 * Overriding this function prevents the user from destroying the activity
	 * by pressing the backbutton
	 */
	@Override
	public void onBackPressed() {
		if (toast_flag1) {
			toast_flag1 = false;
			Toast.makeText(getBaseContext(), "Don't press! SCREAM!", Toast.LENGTH_LONG).show();
		}
	}

};