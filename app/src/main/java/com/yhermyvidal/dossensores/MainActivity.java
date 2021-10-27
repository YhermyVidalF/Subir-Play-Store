package com.yhermyvidal.dossensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView txt_sensores;
    private TextView txt_giro;
    private TextView txt_tem;

    private SensorManager sensorManager;
    private List<Sensor> sensores;

    private Sensor sensorG, sensorTA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_sensores = (TextView) findViewById(R.id.txt_sensores);
        txt_giro = (TextView) findViewById(R.id.txt_giro);
        txt_tem = (TextView) findViewById(R.id.txt_tem);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

        int i = 1;
        for (Iterator<Sensor> it = sensores.iterator(); it.hasNext(); i++) {
            Sensor sensor = it.next();
            txt_sensores.append(String.format("%d: %s, %d, %s\n", i, sensor.getName(), sensor.getType(), sensor.getVendor()));
        }

        sensorG = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(this, sensorG, SensorManager.SENSOR_DELAY_NORMAL);

        sensorTA = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensorTA, SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()){
            case Sensor.TYPE_GYROSCOPE:
                txt_giro.setText(String.format(" %.0f", sensorEvent.values[0]));
                break;
            case Sensor.TYPE_ACCELEROMETER:
                txt_tem.setText(String.format(" %.0f", sensorEvent.values[0]));
                break;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
