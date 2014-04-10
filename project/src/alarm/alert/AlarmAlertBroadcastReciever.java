package alarm.alert;

import accelerometer.ShakalarmActivity;
import alarm.Alarm;
import alarm.service.AlarmServiceBroadcastReciever;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AlarmAlertBroadcastReciever extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent AlarmServiceIntent = new Intent(
				context,
				AlarmServiceBroadcastReciever.class);
		context.sendBroadcast(AlarmServiceIntent, null);
		
		StaticWakeLock.lockOn(context);
		Bundle bundle = intent.getExtras();
		final Alarm alarm = (Alarm) bundle.getSerializable("alarm");

		Intent AlarmAlertActivityIntent;

		AlarmAlertActivityIntent = new Intent(context, ShakalarmActivity.class); //changed from AlarmAlertActivity to ShakalarmActivity

		AlarmAlertActivityIntent.putExtra("alarm", alarm);

		AlarmAlertActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		context.startActivity(AlarmAlertActivityIntent);
	}

}
