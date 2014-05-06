package shakalarm.alarm.test;

import stopwatch.StopWatchActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

public class StopWatchActivityTest extends ActivityInstrumentationTestCase2<StopWatchActivity> {

	private StopWatchActivity mActivity;

	public StopWatchActivityTest() {
		super(StopWatchActivity.class);
	}

	@SmallTest
	// SmallTest: this test doesn't interact with any file system or network. 
	public void testView() { // checks if the activity is created 
		assertNotNull(getActivity());
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		mActivity = (StopWatchActivity) getActivity();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
