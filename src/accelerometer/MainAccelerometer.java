package accelerometer;

import alarm.Alarm;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import za.co.neilson.alarm.R;

public class MainAccelerometer extends Activity implements AccelerometerListener {

	private Alarm alarm;

	private TextView acc_x;
	private TextView acc_y;
	private TextView acc_z;
	private TextView ShakeCountDown_textview;
	private MediaPlayer mediaPlayer;
	private Vibrator vibrator;

	private boolean alarmActive = true;

	private static int shakeCountDown = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_alert);
		acc_x = (TextView) findViewById(R.id.acceleration_x);
		acc_y = (TextView) findViewById(R.id.acceleration_y);
		acc_z = (TextView) findViewById(R.id.acceleration_z);
		ShakeCountDown_textview = (TextView) findViewById(R.id.shakeCountDown);

		Bundle bundle = this.getIntent().getExtras();
		alarm = (Alarm) bundle.getSerializable("alarm");

		this.setTitle(alarm.getAlarmName());

		getActionBar().setDisplayHomeAsUpEnabled(false);
		//This function disables returning to app's Home by clicking the app icon above.

		startAlarm();
		// Check onResume Method to start accelerometer listener
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

	public void onAccelerationChanged(float x, float y, float z) {
		// TODO Auto-generated method stub
		acc_x.setText("Acceleration x: " + x + " m/s^2");
		acc_y.setText("Acceleration y: " + y + " m/s^2");
		acc_z.setText("Acceleration z: " + z + " m/s^2");

	}

	public void onShake(float force) {

		// Called when Motion Detected
		Toast.makeText(getBaseContext(), "Motion detected", Toast.LENGTH_LONG).show();
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

	@Override
	public void onResume() {
		super.onResume();
		Toast.makeText(getBaseContext(), "onResume Accelerometer Started", Toast.LENGTH_LONG).show();

		//Check device supported Accelerometer senssor or not
		if (AccelerometerManager.isSupported(this)) {

			//Start Accelerometer Listening
			AccelerometerManager.startListening(this);
		}
	}

	@Override
	public void onStop() {
		super.onStop();

		//Check device supported Accelerometer senssor or not
		if (AccelerometerManager.isListening()) {

			//Start Accelerometer Listening
			AccelerometerManager.stopListening();

			Toast.makeText(getBaseContext(), "onStop Accelerometer Stoped", Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("Sensor", "Service  distroy");

		//Check device supported Accelerometer senssor or not
		if (AccelerometerManager.isListening()) {

			//Start Accelerometer Listening
			AccelerometerManager.stopListening();

			Toast.makeText(getBaseContext(), "onDestroy Accelerometer Stoped", Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public void onBackPressed() {
		Toast.makeText(getBaseContext(), "backbutton pressed", Toast.LENGTH_LONG).show();
		
		if (!alarmActive)
			super.onBackPressed();
	}

}
