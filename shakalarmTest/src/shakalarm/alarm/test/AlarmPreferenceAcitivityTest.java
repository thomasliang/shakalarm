package shakalarm.alarm.test;

import java.util.List;

import accelerometer.ShakalarmActivity;
import alarm.Alarm;
import alarm.AlarmActivity;
import alarm.database.Database;
import alarm.preference.AlarmPreferencesActivity;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.ImageButton;
import android.widget.TextView;

public class AlarmPreferenceAcitivityTest extends ActivityInstrumentationTestCase2<AlarmPreferencesActivity> {

	private AlarmPreferencesActivity mActivity;
	TextView cancelButton;
	TextView okButton;

	public AlarmPreferenceAcitivityTest() {
		super(AlarmPreferencesActivity.class);
	}

	@SmallTest
	// SmallTest: this test doesn't interact with any file system or network. 
	public void testView() { // checks if the activity is created 
		assertNotNull(getActivity());
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent AlarmPreferencesActivityIntent = new Intent(this.getInstrumentation().getTargetContext().getApplicationContext(), AlarmPreferencesActivity.class);
		setActivityIntent(AlarmPreferencesActivityIntent);

		mActivity = (AlarmPreferencesActivity) getActivity();

		cancelButton = (TextView) mActivity.findViewById(shakalarm.alarm.R.id.textView_cancel);
		okButton = (TextView) mActivity.findViewById(shakalarm.alarm.R.id.textView_OK);

	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@SmallTest
	public void testTouchCancel() {
		TouchUtils.clickView(this, cancelButton);
		assertTrue(mActivity.isFinishing());
	}

	@SmallTest
	public void testTouchOK() {
		TouchUtils.clickView(this, okButton);
		assertTrue(mActivity.isFinishing());
	}

	@SmallTest
	public void testAddAlarm() {
		TouchUtils.clickView(this, okButton);
		List<Alarm> alarms = Database.getAll();
		assertTrue(alarms.size() >= 1);
	}
}
