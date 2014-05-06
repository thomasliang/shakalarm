package timer.test;

import com.robotium.solo.Solo;

import timer.TimerActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.KeyEvent;
import android.widget.ImageButton;

public class TimerActivityTest extends ActivityInstrumentationTestCase2<TimerActivity> {

	private TimerActivity mActivity;
	private Solo solo;
	private ImageButton newButton;
	private ImageButton stopwatchButton;
	private ImageButton timerButton;
	private ImageButton facebookButton;

	public TimerActivityTest() {
		super(TimerActivity.class);
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

		mActivity = (TimerActivity) getActivity();
		
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
	public void testStartTimer() throws InterruptedException {
		solo.enterText(2, "1");
		solo.clickOnButton("Start");
		solo.waitForDialogToOpen();
		solo.clickOnButton("Dismiss");
	}
	
	@SmallTest
	public void testCancelTimer() throws InterruptedException {
		solo.enterText(2, "59");
		solo.clickOnButton("Start");
		solo.clickOnButton("Cancel");
		solo.waitForDialogToOpen();
		solo.clickOnButton("No");
		solo.clickOnButton("Cancel");
		solo.waitForDialogToOpen();
		solo.clickOnButton("Yes");
	}
	
	
	@SmallTest
	public void testSwitchActivity() {
		solo.clickOnView(timerButton);
		solo.clickOnView(stopwatchButton);
		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
		solo.clickOnView(newButton);
		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
		solo.clickOnView(facebookButton);
		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
	}

}
