package timer;


import shakalarm.alarm.R;
import stopwatch.StopWatchActivity;
import alarm.AlarmActivity;
import alarm.setting.AlarmSettingActivity;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class TimerActivity extends Activity {
	
	private int hours;
	private int minutes;
	private int seconds;
	
	private EditText hoursBox;
	private EditText minutesBox;
	private EditText secondsBox;
	
	public static NotificationManager mNotificationManager;
	public static int HELLO_ID = 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.timer_activity);
        
      hoursBox = (EditText) findViewById(R.id.hoursBox);
      minutesBox = (EditText) findViewById(R.id.minutesBox);
	  secondsBox = (EditText) findViewById(R.id.secondsBox);
      Button startButton = (Button) findViewById(R.id.startButton);
      
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
				Intent newAlarmIntent = new Intent(TimerActivity.this, AlarmActivity.class);
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
					Intent newAlarmIntent = new Intent(TimerActivity.this, StopWatchActivity.class);
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
					Intent newAlarmIntent = new Intent(TimerActivity.this, TimerActivity.class);
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
					Intent newAlarmIntent = new Intent(TimerActivity.this, AlarmSettingActivity.class);
					startActivity(newAlarmIntent);

				case MotionEvent.ACTION_MOVE:
				case MotionEvent.ACTION_CANCEL:
					settingButton.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
					break;
				}
				return true;
			}
		});
       
      startButton.setOnClickListener(new View.OnClickListener() {
    
    	  public void onClick(View v) {
    		  if(!(hoursBox.getText().toString().equals("") 
    				  && minutesBox.getText().toString().equals("")
    				  && secondsBox.getText().toString().equals(""))) {
    			    
    			  Intent startCountdown = new Intent(TimerActivity.this, TimerCountdownActivity.class);
		   
    			  if(hoursBox.getText().toString().equals(""))
    				  hoursBox.setText("0");
		    
    			  if(minutesBox.getText().toString().equals(""))
    				  minutesBox.setText("0");
		    
    			  if(secondsBox.getText().toString().equals(""))
    				  secondsBox.setText("0");
				    		
    			  hours = Integer.parseInt(hoursBox.getText().toString());
    			  minutes = Integer.parseInt(minutesBox.getText().toString());	
    			  seconds = Integer.parseInt(secondsBox.getText().toString());
		    
    			  startCountdown.putExtra("hours", hours);
    			  startCountdown.putExtra("minutes", minutes);
    			  startCountdown.putExtra("seconds", seconds);
	   	
    			  String ns = Context.NOTIFICATION_SERVICE;
    			  mNotificationManager = (NotificationManager) getSystemService(ns);
    			  CharSequence tickerText = "Timer active";
    			  int icon = R.drawable.logo;
        
    			  long when = System.currentTimeMillis();

    			  Notification notification = new Notification(icon, tickerText, when);
        
    			  CharSequence contentTitle = "Countdown Timer";
    			  CharSequence contentText = "Timer active";
    			  Intent notificationIntent = new Intent(TimerActivity.this, TimerCountdownActivity.class);
    			  PendingIntent pIntent = PendingIntent.getActivity(TimerActivity.this, 0, notificationIntent, HELLO_ID);
    			  notification.setLatestEventInfo(TimerActivity.this, contentTitle, contentText, pIntent);
    			  notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        
    			  mNotificationManager.notify(HELLO_ID, notification);
        
    			  startActivity(startCountdown);
    		  }
    	  }
    	 });
      
    }
    
    protected void onResume(){
  
    	super.onResume();
    	
    	hoursBox.setText("");
    	minutesBox.setText("");
    	secondsBox.setText("");
    }
}
