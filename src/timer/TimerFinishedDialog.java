package timer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class TimerFinishedDialog extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog alert = showTimerFinishedDialog();
		
        alert.show();
	}
        
	private AlertDialog showTimerFinishedDialog() {
		
		final AlertDialog alertDialog = new AlertDialog.Builder(this)
			.setTitle("Countdown Timer")
			.setMessage("Timer finished.")
		
			.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() { 
				public void onClick(DialogInterface dialog, int whichButton) { 
					TimerCountdownActivity.finishButton.performClick();
					finish();
				}              
			})
			.setNegativeButton("New timer", new DialogInterface.OnClickListener() { 
				public void onClick(DialogInterface dialog, int whichButton) { 
					TimerCountdownActivity.finishButton.performClick();
					Intent newTimer = new Intent(TimerFinishedDialog.this, TimerActivity.class);
					startActivity(newTimer);
					finish();
				}              
			})
		.create();
		
	return alertDialog;
	}
}
