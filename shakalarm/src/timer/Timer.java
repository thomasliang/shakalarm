package timer;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.os.Vibrator;

public class Timer extends CountDownTimer{
	
	public static Ringtone r;
	public static Vibrator v;
	private static int HELLO_ID = TimerActivity.HELLO_ID;
	private Context mContext;
	private PowerManager pm;
	
	 
	public Timer(long millisInFuture, long countDownInterval, Context mContext) {
			super(millisInFuture, countDownInterval);
			this.mContext = mContext;
	}
	
	@Override
	public void onFinish() {
		
		Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		v = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
		r = RingtoneManager.getRingtone(mContext, notification);
		pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
		PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "Countdown Timer");
		final long[] pattern = {1,300,75,300,75,300,75,300,3000,300,75,300,75,300,75,300};
		
		
		Intent TimerFinishedDialog = new Intent(mContext,TimerFinishedDialog.class);
		TimerFinishedDialog.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		TimerFinishedDialog.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		TimerCountdownActivity.countdown_text.setText("Finished.");
		TimerCountdownActivity.finishButton.setText("Ok");
		TimerCountdownActivity.timerFinished = true;
		
		wl.acquire();
		
		mContext.getApplicationContext().startActivity(TimerFinishedDialog);
		TimerActivity.mNotificationManager.cancel(HELLO_ID);
		
		v.vibrate(pattern, -1);
		r.play();
		
		wl.release();
	}
	
	@Override
	public void onTick(long millisUntilFinished) {
		

		long remainingMs = millisUntilFinished;
		
		// Display meaningful time remaining instead of milliseconds.
		Integer h = new Integer((int) remainingMs/3600000);
		long remaining = remainingMs - (h*3600000);
		Integer m = new Integer((int) remaining/60000);
		remaining = remaining - (m*60000);
		Integer s = new Integer((int) (remaining /1000));
		
		String remainingH;
		String remainingM;
		String remainingS;
		
		if(h.equals(new Integer(0)))
			 remainingH = "00";
		else
			if(h<10)
				remainingH = "0"+h.toString();
			else
			remainingH = h.toString();
		
		if(m.equals(new Integer(0)))
			 remainingM = "00";
		else{
			if(m < 10)
				remainingM = "0"+m.toString();
			else
				remainingM = m.toString();
		}
		
		if(s<10)
			remainingS = "0"+s.toString();
		else
			remainingS = s.toString();
		
			
		if(remainingH.equals("00"))
			TimerCountdownActivity.countdown_text.setText(remainingM+":"+remainingS);
		else
			TimerCountdownActivity.countdown_text.setText(remainingH+":"+remainingM+":"+remainingS);
	}
	
}

