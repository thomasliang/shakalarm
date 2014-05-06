/*
 * If there is NoClassDefFoundError, 
 * go to java Build Path -> Order and Export and check robotium's lib
 */

package shakalarm.alarm.test;

import java.util.Calendar;
import java.util.List;

import alarm.Alarm;
import alarm.database.Database;
import alarm.preference.AlarmPreferencesActivity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.robotium.solo.Solo;

public class AlarmPreferenceAcitivityTest extends ActivityInstrumentationTestCase2<AlarmPreferencesActivity> {

	private AlarmPreferencesActivity mActivity;
	ImageButton cancelButton;
	ImageButton okButton;
	private ListView listView;

	private Solo solo;

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

		solo = new Solo(getInstrumentation(), getActivity());

		cancelButton = (ImageButton) mActivity.findViewById(shakalarm.alarm.R.id.textView_back);
		okButton = (ImageButton) mActivity.findViewById(shakalarm.alarm.R.id.textView_OK);
		listView = (ListView) mActivity.findViewById(android.R.id.list);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		List<Alarm> alarms = Database.getAll();
		for (Alarm a : alarms) {
			Database.deleteEntry(a);
		}
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
		assertTrue(alarms.size() == 1);
	}

	@SmallTest
	public void testAlarmSetTime() throws InterruptedException {
		View timeView = listView.getChildAt(2);
		TouchUtils.clickView(this, timeView);
		Calendar calendar = Calendar.getInstance();
		solo.setTimePicker(0, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE) + 1);
		solo.clickOnView(solo.getView(android.R.id.button1)); //click on the positive button
		getInstrumentation().waitForIdleSync();
		TouchUtils.clickView(this, okButton);		
		fail("test ringing after 1 min");
	}
}
