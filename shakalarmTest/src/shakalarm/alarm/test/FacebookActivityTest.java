package shakalarm.alarm.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;

import com.facebook.widget.LoginButton;

import facebook.FacebookSampleActivity;

public class FacebookActivityTest extends ActivityInstrumentationTestCase2<FacebookSampleActivity> {

	private FacebookSampleActivity mActivity;
	private LoginButton loginButton;
	
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
		
		mActivity = (FacebookSampleActivity) getActivity();		
		loginButton = (LoginButton) mActivity.findViewById(shakalarm.alarm.R.id.login_button);
	}


	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@SmallTest
	public void testClickLoginButton() {
		TouchUtils.clickView(this, loginButton);
	}
	
}
