package stopwatch.test;

import shakalarm.alarm.R;
import stopwatch.StopWatchActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.KeyEvent;
import android.widget.ImageButton;

import com.robotium.solo.Solo;

public class StopWatchActivityTest extends ActivityInstrumentationTestCase2<StopWatchActivity> {

	private StopWatchActivity mActivity;
	private Solo solo;
	private ImageButton newButton;
	private ImageButton stopwatchButton;
	private ImageButton timerButton;
	private ImageButton facebookButton;
	
	public StopWatchActivityTest() {
		super(StopWatchActivity.class);
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
		mActivity = (StopWatchActivity) getActivity();

		newButton = (ImageButton) mActivity.findViewById(shakalarm.alarm.R.id.Alarm_tab);
		stopwatchButton = (ImageButton) mActivity.findViewById(shakalarm.alarm.R.id.Timer_tab);
		timerButton = (ImageButton) mActivity.findViewById(shakalarm.alarm.R.id.Counter_tab);
		facebookButton = (ImageButton) mActivity.findViewById(shakalarm.alarm.R.id.Setting_tab);		
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@SmallTest
	public void testStopWatch() throws InterruptedException {
		solo.clickOnButton("Start");
		solo.clickOnButton("Reset");
		solo.clickOnButton("Stop");
	}

	@SmallTest
	public void testSwitchActivity() {
		solo.clickOnView(stopwatchButton);
		solo.clickOnView(newButton);
		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
		solo.clickOnView(timerButton);
		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
		solo.clickOnView(facebookButton);
		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
	}
}
