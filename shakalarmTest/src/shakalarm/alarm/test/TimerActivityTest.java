package shakalarm.alarm.test;

import timer.TimerActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

public class TimerActivityTest extends ActivityInstrumentationTestCase2<TimerActivity> {

	private TimerActivity mActivity;

	public TimerActivityTest() {
		super(TimerActivity.class);
	}

	@SmallTest
	// SmallTest: this test doesn't interact with any file system or network. 
	public void testView() { // checks if the activity is created 
		assertNotNull(getActivity());
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		mActivity = (TimerActivity) getActivity();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
