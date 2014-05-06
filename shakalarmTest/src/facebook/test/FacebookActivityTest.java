package facebook.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.KeyEvent;
import android.widget.ImageButton;

import com.facebook.widget.LoginButton;
import com.robotium.solo.Solo;

import facebook.FacebookSampleActivity;

public class FacebookActivityTest extends ActivityInstrumentationTestCase2<FacebookSampleActivity> {

	private FacebookSampleActivity mActivity;
	private LoginButton loginButton;
	private Solo solo;
	private ImageButton newButton;
	private ImageButton stopwatchButton;
	private ImageButton timerButton;
	private ImageButton facebookButton;

	public FacebookActivityTest() {
		super(FacebookSampleActivity.class);
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

		mActivity = (FacebookSampleActivity) getActivity();

		newButton = (ImageButton) mActivity.findViewById(shakalarm.alarm.R.id.Alarm_tab);
		stopwatchButton = (ImageButton) mActivity.findViewById(shakalarm.alarm.R.id.Timer_tab);
		timerButton = (ImageButton) mActivity.findViewById(shakalarm.alarm.R.id.Counter_tab);
		facebookButton = (ImageButton) mActivity.findViewById(shakalarm.alarm.R.id.Setting_tab);

		loginButton = (LoginButton) mActivity.findViewById(shakalarm.alarm.R.id.login_button);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@SmallTest
	public void testClickLoginButton() {
		TouchUtils.clickView(this, loginButton);
		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
	}

	//	@SmallTest
	//	public void testSwitchActivity() {
	//		solo.clickOnView(facebookButton);
	//		solo.clickOnView(stopwatchButton);
	//		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
	//		solo.clickOnView(newButton);
	//		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
	//		solo.clickOnView(timerButton);
	//		getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
	//	}

}
