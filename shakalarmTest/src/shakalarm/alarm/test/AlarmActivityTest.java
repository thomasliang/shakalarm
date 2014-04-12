package shakalarm.alarm.test;

import java.lang.reflect.Method;

import alarm.AlarmActivity;
import alarm.AlarmListAdapter;
import alarm.preference.AlarmPreferencesActivity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.ImageButton;
import android.widget.ListView;

public class AlarmActivityTest extends ActivityInstrumentationTestCase2<AlarmActivity> {

	private AlarmActivity mActivity;

	ImageButton newButton;
	ListView alarmListView;
	AlarmListAdapter alarmListAdapter;
	ImageButton alarmButton;

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
		newButton = (ImageButton) mActivity.findViewById(shakalarm.alarm.R.id.button_new);
	}


	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@SmallTest
	// test whether the AlarmPreferece activity will be started after the add button on the top right corner is clicked
	public void testTouchAdd() {
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(AlarmPreferencesActivity.class.getName(), null, false);

		TouchUtils.clickView(this, newButton);
		AlarmPreferencesActivity nextActivity = (AlarmPreferencesActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 500);
		assertNotNull(nextActivity);
		nextActivity.finish();
	}
}
