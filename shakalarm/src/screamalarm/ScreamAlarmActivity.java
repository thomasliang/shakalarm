package screamalarm;

import shakalarm.alarm.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ScreamAlarmActivity extends Activity {
	private static final int POLL_INTERVAL = 300;

	private boolean mRunning = false;

	private int mThreshold;

	private PowerManager.WakeLock mWakeLock;

	private Handler mHandler = new Handler();

	private TextView mStatusView;
	private SoundLevelView mDisplay;

	private SoundMeter mSensor;

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
			updateDisplay("Monitoring Voice...", amp);

			if ((amp > mThreshold)) {
				callForHelp();
				//Log.i("Noise", "==== onCreate ===");

			}

			// Runnable(mPollTask) will again execute after POLL_INTERVAL
			mHandler.postDelayed(mPollTask, POLL_INTERVAL);

		}
	};

	private boolean toast_flag1 = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Defined SoundLevelView in main.xml file
		setContentView(R.layout.screamalarm_activity);
		mStatusView = (TextView) findViewById(R.id.status);

		// Used to record voice
		mSensor = new SoundMeter();
		mDisplay = (SoundLevelView) findViewById(R.id.volume);

		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "NoiseAlert");
	}

	@Override
	public void onResume() {
		super.onResume();
		//Log.i("Noise", "==== onResume ===");

		initializeApplicationConstants();
		mDisplay.setLevel(0, mThreshold);

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
		mDisplay.setLevel(0, 0);
		updateDisplay("stopped...", 0.0);
		mRunning = false;

	}

	private void initializeApplicationConstants() {
		// Set Noise Threshold
		mThreshold = 8;

	}

	private void updateDisplay(String status, double signalEMA) {
		mStatusView.setText(status);
		// 
		mDisplay.setLevel((int) signalEMA, mThreshold);
	}

	private void callForHelp() {

		//stop();

		// Show alert when noise thersold crossed
		Toast.makeText(getApplicationContext(), "Noise Thersold Crossed, do here your stuff.", Toast.LENGTH_LONG).show();
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
	}

};