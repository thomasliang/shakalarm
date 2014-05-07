package screamalarm.test;

import java.util.Calendar;

import screamalarm.ScreamAlarmActivity;
import alarm.Alarm;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.KeyEvent;

import com.robotium.solo.Solo;

public class ScreamAlarmActivityTest extends ActivityInstrumentationTestCase2<ScreamAlarmActivity> {

	private ScreamAlarmActivity mActivity;
	private Solo solo;

	public ScreamAlarmActivityTest() {
		super(ScreamAlarmActivity.class);
	}

	//	@SmallTest
	//	// SmallTest: this test doesn't interact with any file system or network. 
	//	public void testView() { // checks if the activity is created 
	//		assertNotNull(getActivity());
	//	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		//Inject new intent
		Intent shakalarmActivityIntent = new Intent(this.getInstrumentation().getTargetContext().getApplicationContext(), ScreamAlarmActivity.class);
		Alarm alarm = new Alarm();
		Calendar alarmTime = Calendar.getInstance();
		alarmTime.setTimeInMillis(alarmTime.getTimeInMillis() + 5000);
		shakalarmActivityIntent.putExtra("alarm", alarm);
		shakalarmActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		setActivityIntent(shakalarmActivityIntent);

		mActivity = (ScreamAlarmActivity) getActivity();

		solo = new Solo(getInstrumentation(), getActivity());
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@SmallTest
	public void testBackButtonPressed() throws InterruptedException {
		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
		assertFalse(this.mActivity.isFinishing());
	}	
}
