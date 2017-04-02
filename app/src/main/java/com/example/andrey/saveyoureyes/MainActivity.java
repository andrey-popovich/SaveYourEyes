package com.example.andrey.saveyoureyes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView textLight;
    SensorManager mSensorManager;
    Sensor mLightSensor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textLight = (TextView) findViewById(R.id.txtLight);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mLightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mLightSensor != null) {
            mSensorManager.registerListener(this, mLightSensor, SensorManager.SENSOR_DELAY_FASTEST);
        } else {
            textLight.setText("Light sensor is NOT available");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            int lux = (int) event.values[0];
            switch (lux){
                case 1:
                    textLight.setText("LOW LIGHT LEVEL");
                    break;
                case 100:
                    textLight.setText("MEDIUM LIGHT LEVEL");
                    break;
                case 1000:
                    textLight.setText("HIGH LIGHT LEVEL");
                    break;
                default:
                    textLight.setText("Light sensor is NOT available");
                    break;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
