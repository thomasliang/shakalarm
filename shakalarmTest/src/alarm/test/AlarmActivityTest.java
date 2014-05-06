package alarm.test;

import alarm.AlarmActivity;
import alarm.preference.AlarmPreferencesActivity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.KeyEvent;
import android.widget.ImageButton;

import com.robotium.solo.Solo;

public class AlarmActivityTest extends ActivityInstrumentationTestCase2<AlarmActivity> {

	private AlarmActivity mActivity;

	private ImageButton newButton;

	Solo solo;

	private ImageButton stopwatchButton;

	private ImageButton timerButton;

	private ImageButton facebookButton;

	public AlarmActivityTest() {
		super(AlarmActivity.class);
	}

	@SmallTest
	// SmallTest: this test doesn't interact with any file system or network. 
	public void testView() { // checks if the activity is created 
		assertNotNull(getActivity());
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());

		mActivity = (AlarmActivity) getActivity();

		newButton = (ImageButton) mActivity.findViewById(shakalarm.alarm.R.id.Alarm_tab);
		stopwatchButton = (ImageButton) mActivity.findViewById(shakalarm.alarm.R.id.Timer_tab);
		timerButton = (ImageButton) mActivity.findViewById(shakalarm.alarm.R.id.Counter_tab);
		facebookButton = (ImageButton) mActivity.findViewById(shakalarm.alarm.R.id.Setting_tab);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//	@SmallTest
	//	// test whether the AlarmPreferece activity will be started after the add button on the top right corner is clicked
	//	public void testTouchAdd() {
	//		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(AlarmPreferencesActivity.class.getName(), null, false);
	//		AlarmPreferencesActivity nextActivity = (AlarmPreferencesActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 500);
	//
	//		TouchUtils.clickView(this, newButton);
	//		assertNotNull(nextActivity);
	//		nextActivity.finish();
	//	}

	@SmallTest
	public void testAddDeleteAlarm() {
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(AlarmPreferencesActivity.class.getName(), null, false);

		TouchUtils.clickView(this, newButton);
		AlarmPreferencesActivity nextActivity = (AlarmPreferencesActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 500);
		assertNotNull(nextActivity);

		ImageButton okButton = (ImageButton) nextActivity.findViewById(shakalarm.alarm.R.id.textView_OK);
		TouchUtils.clickView(this, okButton);
		//assertTrue(alarms.size() == 1);

		//getInstrumentation().waitForIdleSync();

		activityMonitor = getInstrumentation().addMonitor(AlarmPreferencesActivity.class.getName(), null, false);
		solo.clickInList(0);
		solo.clickOnImageButton(1); //click delete
		solo.clickOnButton("Ok");
	}

	@SmallTest
	public void testSwitchActivity() {
		solo.clickOnView(stopwatchButton);
		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
		solo.clickOnView(newButton);
		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
		solo.clickOnView(timerButton);
		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
		solo.clickOnView(facebookButton);
		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
	}

}
