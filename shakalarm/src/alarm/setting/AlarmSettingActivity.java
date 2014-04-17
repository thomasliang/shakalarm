package alarm.setting;


import shakalarm.alarm.R;
import stopwatch.StopWatchActivity;
import timer.TimerActivity;
import alarm.AlarmActivity;
import alarm.preference.AlarmPreferencesActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;

public class AlarmSettingActivity extends Activity {
	ImageButton newButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(shakalarm.alarm.R.layout.setting_activity);

      //The plus button on the top right corner (adding new alarm)
      		newButton = (ImageButton) findViewById(shakalarm.alarm.R.id.button_new);
      		newButton.setOnTouchListener(new OnTouchListener() {
      			@Override
      			public boolean onTouch(View v, MotionEvent event) {
      				switch (event.getAction()) {
      				case MotionEvent.ACTION_DOWN:
      					newButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
      					break;
      				case MotionEvent.ACTION_UP:
      					newButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
      					v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
      					Intent newAlarmIntent = new Intent(AlarmSettingActivity.this, FaceBookActivity.class);
      					startActivity(newAlarmIntent);
      				case MotionEvent.ACTION_MOVE:
      				case MotionEvent.ACTION_CANCEL:
      					newButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
      					break;
      				}
      				return true;
      			}
      		});
      		
        
        final View alarmButton = (ImageButton) findViewById(shakalarm.alarm.R.id.Alarm_tab);
      	alarmButton.setOnTouchListener(new OnTouchListener() {
    		@Override
    		public boolean onTouch(View v, MotionEvent event) {

    			switch (event.getAction()) {
    			case MotionEvent.ACTION_DOWN:
    				alarmButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
    				break;
    			case MotionEvent.ACTION_UP:
    				alarmButton.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
    				v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    				Intent newAlarmIntent = new Intent(AlarmSettingActivity.this, AlarmActivity.class);
    				startActivity(newAlarmIntent);

    			case MotionEvent.ACTION_MOVE:
    			case MotionEvent.ACTION_CANCEL:
    				alarmButton.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
    				break;
    			}
    			return true;
    		}
    	});
    	
          
    		//The timer button on the bottom (2nd from the left)
    		final View stopWatchButton = (ImageButton) findViewById(shakalarm.alarm.R.id.Timer_tab);
    		stopWatchButton.setOnTouchListener(new OnTouchListener() {
    			@Override
    			public boolean onTouch(View v, MotionEvent event) {

    				switch (event.getAction()) {
    				case MotionEvent.ACTION_DOWN:
    					stopWatchButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
    					break;
    				case MotionEvent.ACTION_UP:
    					stopWatchButton.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
    					v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    					Intent newAlarmIntent = new Intent(AlarmSettingActivity.this, StopWatchActivity.class);
    					startActivity(newAlarmIntent);

    				case MotionEvent.ACTION_MOVE:
    				case MotionEvent.ACTION_CANCEL:
    					stopWatchButton.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
    					break;
    				}
    				return true;
    			}
    		});

    		//The count down timer button (3rd from the left)
    		final View timerButton = (ImageButton) findViewById(shakalarm.alarm.R.id.Counter_tab);
    		timerButton.setOnTouchListener(new OnTouchListener() {
    			@Override
    			public boolean onTouch(View v, MotionEvent event) {

    				switch (event.getAction()) {
    				case MotionEvent.ACTION_DOWN:
    					timerButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
    					break;
    				case MotionEvent.ACTION_UP:
    					timerButton.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
    					Intent newAlarmIntent = new Intent(AlarmSettingActivity.this, TimerActivity.class);
    					startActivity(newAlarmIntent);

    				case MotionEvent.ACTION_MOVE:
    				case MotionEvent.ACTION_CANCEL:
    					timerButton.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
    					break;
    				}
    				return true;
    			}
    			});
    		
    		final View settingButton = (ImageButton) findViewById(shakalarm.alarm.R.id.Setting_tab);
    		settingButton.setOnTouchListener(new OnTouchListener() {
    			@Override
    			public boolean onTouch(View v, MotionEvent event) {

    				switch (event.getAction()) {
    				case MotionEvent.ACTION_DOWN:
    					settingButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
    					break;
    				case MotionEvent.ACTION_UP:
    					settingButton.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
    					v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
    					Intent newAlarmIntent = new Intent(AlarmSettingActivity.this, AlarmSettingActivity.class);
    					startActivity(newAlarmIntent);

    				case MotionEvent.ACTION_MOVE:
    				case MotionEvent.ACTION_CANCEL:
    					settingButton.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
    					break;
    				}
    				return true;
    			}
    		});
    }
}

    