package accelerometer;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import za.co.neilson.alarm.R;
public class MainAccelerometer extends Activity implements AccelerometerListener{

	private TextView acc_x;
	private TextView acc_y;
	private TextView acc_z;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_alert);
		acc_x = (TextView) findViewById(R.id.acceleration_x);
		acc_y = (TextView) findViewById(R.id.acceleration_y);
		acc_z = (TextView) findViewById(R.id.acceleration_z);
		
		// Check onResume Method to start accelerometer listener
	}
   
	public void onAccelerationChanged(float x, float y, float z) {
		// TODO Auto-generated method stub
		acc_x.setText("Acceleration x: " + x + " m/s^2");
		acc_y.setText("Acceleration y: " + y + " m/s^2");
		acc_z.setText("Acceleration z: " + z + " m/s^2");
		
		
	}

	public void onShake(float force) {
		
		// Called when Motion Detected
		Toast.makeText(getBaseContext(), "Motion detected", 
				Toast.LENGTH_LONG).show();
		
	}

	@Override
    public void onResume() {
            super.onResume();
            Toast.makeText(getBaseContext(), "onResume Accelerometer Started", 
            		Toast.LENGTH_LONG).show();
            
            //Check device supported Accelerometer senssor or not
            if (AccelerometerManager.isSupported(this)) {
            	
            	//Start Accelerometer Listening
    			AccelerometerManager.startListening(this);
            }
    }
	
	@Override
    public void onStop() {
            super.onStop();
            
            //Check device supported Accelerometer senssor or not
            if (AccelerometerManager.isListening()) {
            	
            	//Start Accelerometer Listening
    			AccelerometerManager.stopListening();
    			
    			Toast.makeText(getBaseContext(), "onStop Accelerometer Stoped", 
    					Toast.LENGTH_LONG).show();
            }
           
    }
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("Sensor", "Service  distroy");
		
		//Check device supported Accelerometer senssor or not
		if (AccelerometerManager.isListening()) {
			
			//Start Accelerometer Listening
			AccelerometerManager.stopListening();
			
			Toast.makeText(getBaseContext(), "onDestroy Accelerometer Stoped", 
					Toast.LENGTH_LONG).show();
        }
			
	}

}
