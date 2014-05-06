package screamalarm.test;

import com.robotium.solo.Solo;

import screamalarm.ScreamAlarmActivity;
import accelerometer.ShakalarmActivity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.KeyEvent;

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
		solo = new Solo(getInstrumentation(), getActivity());

		Intent intent = new Intent(this.getInstrumentation().getTargetContext().getApplicationContext(), ScreamAlarmActivity.class);
		setActivityIntent(intent);
		mActivity = (ScreamAlarmActivity) getActivity();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@SmallTest 
	public void testBackButtonPressed() {
		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
		assertFalse(this.mActivity.isFinishing());
	}
}
