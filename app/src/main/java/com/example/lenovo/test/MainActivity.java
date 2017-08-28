package com.example.lenovo.test;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView accel, lights, press, temp ;


    private SensorManager sensorManager1;
    private Sensor accelerometer;

    private SensorManager sensorManager2;
    private Sensor light;

    private SensorManager sensorManager3;
    private Sensor temperature;

    private SensorManager sensorManager4;
    private Sensor pressure;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button graph;

        accel = (TextView) findViewById(R.id.acc);
        lights = (TextView) findViewById(R.id.lig);
        press = (TextView) findViewById(R.id.pre);
        temp = (TextView) findViewById(R.id.tem);

        sensorManager4 = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        pressure = sensorManager4.getDefaultSensor(Sensor.TYPE_PRESSURE);

        sensorManager3 = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        temperature = sensorManager3.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        sensorManager2 = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        light = sensorManager2.getDefaultSensor(Sensor.TYPE_LIGHT);

        sensorManager1 = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager4.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        graph=(Button)findViewById(R.id.graphs);
        graph.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),SendPhotos.class);
                startActivity(i);
            }
        });

    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        double maga=0,magp=0,magt=0,magl=0;
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
             maga =Math.sqrt(event.values[0] * event.values[0] + event.values[1] * event.values[1]
                    + event.values[2] * event.values[2]);
        }
        else if(event.sensor.getType()==Sensor.TYPE_LIGHT) {
             magl=event.values[0] ;
        }
        else if(event.sensor.getType()==Sensor.TYPE_PRESSURE) {
             magp =event.values[0] ;
        }
        else if(event.sensor.getType()==Sensor.TYPE_AMBIENT_TEMPERATURE) {
             magt =event.values[0];
        }
        else {
            maga=0;
            magl=0;
            magp=0;
            magt=0;
        }

        maga = Double.parseDouble(String.format(Locale.getDefault(),"%.4f",maga));
        magp = Double.parseDouble(String.format(Locale.getDefault(),"%.4f",magp));
        magt = Double.parseDouble(String.format(Locale.getDefault(),"%.4f",magt));
        magl = Double.parseDouble(String.format(Locale.getDefault(),"%.4f",magl));


        accel.setText(getString(R.string.magaccel ,maga));
        lights.setText(getString(R.string.maglight ,magl));
        temp.setText(getString(R.string.magtemp ,magt));
        press.setText(getString(R.string.magpress ,magp));

    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        sensorManager4.registerListener(this, pressure, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager3.registerListener(this, temperature, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager2.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager1.registerListener(this,accelerometer , SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        sensorManager1.unregisterListener(this);
        sensorManager2.unregisterListener(this);
        sensorManager3.unregisterListener(this);
        sensorManager4.unregisterListener(this);
    }

    protected void graph(View view){
        Intent intent = new Intent(FromActivity.this, second.class);
        startActivity(intent);
    }
}
