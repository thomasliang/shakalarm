package shakalarm.alarm.test;

import alarm.AlarmActivity;
import alarm.AlarmListAdapter;
import alarm.database.Database;
import alarm.preference.AlarmPreferencesActivity;
import android.app.Instrumentation.ActivityMonitor;
import android.database.Cursor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.ImageButton;
import android.widget.ListView;

import com.robotium.solo.Solo;

public class AlarmActivityTest extends ActivityInstrumentationTestCase2<AlarmActivity> {

	private AlarmActivity mActivity;

	ImageButton newButton;
	ListView alarmListView;
	AlarmListAdapter alarmListAdapter;
	ImageButton alarmButton;

	Solo solo;
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
		
		mActivity = (AlarmActivity) getActivity();		
		newButton = (ImageButton) mActivity.findViewById(shakalarm.alarm.R.id.Alarm_tab);


		solo = new Solo(getInstrumentation(), getActivity());
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
}
