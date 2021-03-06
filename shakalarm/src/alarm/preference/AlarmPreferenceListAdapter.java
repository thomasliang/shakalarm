package alarm.preference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import alarm.Alarm;
import alarm.preference.AlarmPreference.Type;
import android.content.Context;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class AlarmPreferenceListAdapter extends BaseAdapter {

	private Context context;
	private Alarm alarm;
	private List<AlarmPreference> preferences = new ArrayList<AlarmPreference>();
	private final String[] repeatDays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
	private final String[] alarmDifficulties = { "Easy", "Medium", "Hard" };
	private final String[] alarmModes = { "Shakalarm", "Blowalarm", "ScreamAlarm" };

	private String[] alarmTones;
	private String[] alarmTonePaths;

	public AlarmPreferenceListAdapter(Context context, Alarm alarm) {
		setContext(context);

		initRingtoneList();
		setAlarm(alarm);
	}

	private void initRingtoneList() {
		Log.d("AlarmPreferenceListAdapter", "Loading Ringtones...");

		SongsManager songsMgr = new SongsManager();
		ArrayList<HashMap<String, String>> songList = songsMgr.getPlayList();

		alarmTones = new String[songList.size()];
		alarmTonePaths = new String[songList.size()];

		for (int i = 0; i < songList.size(); i++) {
			alarmTones[i] = songList.get(i).get("songTitle");
			alarmTonePaths[i] = songList.get(i).get("songPath");
		}

		Log.d("AlarmPreferenceListAdapter", "Finished Loading " + alarmTones.length + " Ringtones.");
	}

	@Override
	public int getCount() {
		return preferences.size();
	}

	@Override
	public Object getItem(int position) {
		return preferences.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AlarmPreference alarmPreference = (AlarmPreference) getItem(position);
		LayoutInflater layoutInflater = LayoutInflater.from(getContext());
		switch (alarmPreference.getType()) {
		case BOOLEAN:
			if (null == convertView || convertView.getId() != android.R.layout.simple_list_item_checked)
				convertView = layoutInflater.inflate(android.R.layout.simple_list_item_checked, null);

			CheckedTextView checkedTextView = (CheckedTextView) convertView.findViewById(android.R.id.text1);
			checkedTextView.setText(alarmPreference.getTitle());
			checkedTextView.setChecked((Boolean) alarmPreference.getValue());
			break;
		case INTEGER:
		case STRING:
		case LIST:
		case MULTIPLE_LIST:
		case TIME:
		default:
			if (null == convertView || convertView.getId() != android.R.layout.simple_list_item_2)
				convertView = layoutInflater.inflate(android.R.layout.simple_list_item_2, null);

			TextView text1 = (TextView) convertView.findViewById(android.R.id.text1);
			text1.setTextSize(18);
			text1.setText(alarmPreference.getTitle());

			TextView text2 = (TextView) convertView.findViewById(android.R.id.text2);
			text2.setText(alarmPreference.getSummary());
			break;
		}

		return convertView;
	}

	public Alarm getAlarm() {
		for (AlarmPreference preference : preferences) {
			switch (preference.getKey()) {
			case ALARM_ACTIVE:
				alarm.setAlarmActive((Boolean) preference.getValue());
				break;
			case ALARM_NAME:
				alarm.setAlarmName((String) preference.getValue());
				break;
			case ALARM_TIME:
				alarm.setAlarmTime((String) preference.getValue());
				break;
			case ALARM_DIFFICULTY:
				alarm.setDifficulty(Alarm.Difficulty.valueOf((String) preference.getValue()));
				break;
			case ALARM_TONE:
				alarm.setAlarmTonePath((String) preference.getValue());
				break;
			case ALARM_VIBRATE:
				alarm.setVibrate((Boolean) preference.getValue());
				break;
			case ALARM_REPEAT:
				alarm.setDays((Alarm.Day[]) preference.getValue());
				break;
			case ALARM_MODE:
				alarm.setMode(Alarm.AlarmMode.valueOf((String) preference.getValue()));
			}
		}

		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
		preferences.clear();
		preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_ACTIVE, "Active", null, null, alarm.getAlarmActive(), Type.BOOLEAN));
		preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_NAME, "Label", alarm.getAlarmName(), null, alarm.getAlarmName(), Type.STRING));
		preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_TIME, "Set time", alarm.getAlarmTimeString(), null, alarm.getAlarmTime(), Type.TIME));
		preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_REPEAT, "Repeat", alarm.getRepeatDaysString(), repeatDays, alarm.getDays(),
				Type.MULTIPLE_LIST));
		preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_DIFFICULTY, "Difficulty", alarm.getDifficulty().toString(), alarmDifficulties, alarm
				.getDifficulty(), Type.LIST));

		Uri alarmToneUri = Uri.parse(alarm.getAlarmTonePath());
		Ringtone alarmTone = RingtoneManager.getRingtone(getContext(), alarmToneUri);

		if (alarmTone instanceof Ringtone && !alarm.getAlarmTonePath().equalsIgnoreCase("")) {
			preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_TONE, "Ringtone", alarmTone.getTitle(getContext()), alarmTones, alarm
					.getAlarmTonePath(), Type.LIST));
		} else {
			preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_TONE, "Ringtone", getAlarmTones()[0], alarmTones, null, Type.LIST));
		}

		preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_VIBRATE, "Vibrate", null, null, alarm.getVibrate(), Type.BOOLEAN));
		preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_MODE, "Alarm mode", alarm.getMode().toString(), alarmModes, alarm.getMode(), Type.LIST));
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public String[] getRepeatDays() {
		return repeatDays;
	}

	public String[] getAlarmDifficulties() {
		return alarmDifficulties;
	}

	public String[] getAlarmTones() {
		return alarmTones;
	}

	public String[] getAlarmTonePaths() {
		return alarmTonePaths;
	}

}
