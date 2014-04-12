package shakalarm.alarm.test;

import java.util.Calendar;

import accelerometer.ShakalarmActivity;
import alarm.Alarm;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.ImageView;
import android.widget.TextView;

public class ShakalarmActivityTest extends ActivityInstrumentationTestCase2<ShakalarmActivity> {
	private ShakalarmActivity shakalarmActivity;
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

	public ShakalarmActivityTest() {
		super(ShakalarmActivity.class);
	}
	
	
	@Override
	/**
	 * Inject an intent (alarm info) and start the Activity for each test
	 * @author Wing
	 */
	protected void setUp() throws Exception {
		super.setUp();
		//Inject new intent
		Intent shakalarmActivityIntent = new Intent(this.getInstrumentation().getTargetContext().getApplicationContext(), ShakalarmActivity.class);
		Alarm alarm = new Alarm();
		Calendar alarmTime = Calendar.getInstance();
		alarmTime.setTimeInMillis(alarmTime.getTimeInMillis() + 5000);
		shakalarmActivityIntent.putExtra("alarm", alarm);
		shakalarmActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		setActivityIntent(shakalarmActivityIntent);

		//startActivity
		shakalarmActivity = (ShakalarmActivity) getActivity();
	}
	
	@Override
	protected void tearDown() throws Exception {
		
		super.tearDown();
	}
	
//	@SmallTest
//	// SmallTest: this test doesn't interact with any file system or network. 
//	public void testView() { // checks if the activity is created 
//		assertNotNull(getActivity());
//	}

	@UiThreadTest
	public void testShakeTillStop() throws InterruptedException {
		Thread.sleep(1000);
		int initial_shake_countDown = shakalarmActivity.getShakeCountDown();
		for (int i = 0; i < initial_shake_countDown; ++i) {
			shakalarmActivity.onShake(10f);
		}
		assertTrue(this.shakalarmActivity.isFinishing());
	}
}
