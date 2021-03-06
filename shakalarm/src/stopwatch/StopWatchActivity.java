package stopwatch;
import facebook.FacebookSampleActivity;
import shakalarm.alarm.R;
import timer.TimerActivity;
import alarm.AlarmActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;

public class StopWatchActivity extends Activity {
    Chronometer mChronometer;
    long time = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     
        setContentView(shakalarm.alarm.R.layout.stopwatch_activity);

        Button button;

        mChronometer = (Chronometer) findViewById(R.id.chronometer);
        mChronometer.setTextSize(70);
       
        
        // Watch for button clicks.
        button = (Button) findViewById(R.id.start);
        button.setOnClickListener(mStartListener);
       

        button = (Button) findViewById(R.id.stop);
        button.setOnClickListener(mStopListener);

        button = (Button) findViewById(R.id.reset);
        button.setOnClickListener(mResetListener);
        
       
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
					Intent newAlarmIntent = new Intent(StopWatchActivity.this, StopWatchActivity.class);
					newAlarmIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
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
					Intent newAlarmIntent = new Intent(StopWatchActivity.this, TimerActivity.class);
					newAlarmIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					startActivity(newAlarmIntent);

				case MotionEvent.ACTION_MOVE:
				case MotionEvent.ACTION_CANCEL:
					timerButton.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
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
					Intent newAlarmIntent = new Intent(StopWatchActivity.this, AlarmActivity.class);
					newAlarmIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					startActivity(newAlarmIntent);

				case MotionEvent.ACTION_MOVE:
				case MotionEvent.ACTION_CANCEL:
					alarmButton.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
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
					Intent newAlarmIntent = new Intent(StopWatchActivity.this, FacebookSampleActivity.class);
					newAlarmIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
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

    View.OnClickListener mStartListener = new OnClickListener() {
        public void onClick(View v) {
        
        	mChronometer.setBase(SystemClock.elapsedRealtime()+time);
            mChronometer.start();
        }
    };

    View.OnClickListener mStopListener = new OnClickListener() {
        public void onClick(View v) {
        	time = mChronometer.getBase()-SystemClock.elapsedRealtime();
            mChronometer.stop();
        }
    };

    View.OnClickListener mResetListener = new OnClickListener() {
        public void onClick(View v) {
        	time = 0;
            mChronometer.setBase(SystemClock.elapsedRealtime());
        }
    };
}       