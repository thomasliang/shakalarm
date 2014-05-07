package alarm.test;

import java.util.Calendar;
import java.util.List;

import alarm.Alarm;
import alarm.AlarmActivity;
import alarm.database.Database;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.KeyEvent;
import android.widget.ImageButton;

import com.robotium.solo.Solo;

public class AlarmActivityTest extends ActivityInstrumentationTestCase2<AlarmActivity> {

	private AlarmActivity mActivity;

	Solo solo;

	public AlarmActivityTest() {
		super(AlarmActivity.class);
	}

	//	@SmallTest
	//	// SmallTest: this test doesn't interact with any file system or network. 
	//	public void testView() { // checks if the activity is created 
	//		assertNotNull(getActivity());
	//	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		getInstrumentation().waitForIdleSync();
		solo = new Solo(getInstrumentation(), getActivity());

		mActivity = (AlarmActivity) getActivity();
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
		solo.clickOnImageButton(0);
		solo.clickOnImageButton(2);
		//assertTrue(alarms.size() == 1);

		//getInstrumentation().waitForIdleSync();

		solo.clickInList(0);
		solo.clickOnImageButton(1); //click delete
		solo.clickOnButton("Ok");
	}

//	@SmallTest
//	public void testUpdateAlarm() {
//		solo.clickOnImageButton(0);
//		solo.clickOnImageButton(2);
//		//assertTrue(alarms.size() == 1);
//
//		//getInstrumentation().waitForIdleSync();
//		solo.clickInList(0);
//		solo.clickInList(3);
//		Calendar calendar = Calendar.getInstance();
//		solo.setTimePicker(0, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE) - 1);
//		solo.clickOnView(solo.getView(android.R.id.button1)); //click on the positive button
//
//		solo.clickOnImageButton(2);
//	}

	//	@SmallTest
	//	public void testSwitchActivity() {
	//		solo.clickOnView(stopwatchButton);
	//		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
	//		solo.clickOnView(newButton);
	//		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
	//		solo.clickOnView(timerButton);
	//		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
	//		solo.clickOnView(facebookButton);
	//		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
	//	}

}
