package com.example.practical6

import android.R.attr.x
import android.R.attr.y
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class Task4 : AppCompatActivity(), SensorEventListener{

    private lateinit var s_mgr: SensorManager;
    private lateinit var s: Sensor;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task4)
        s_mgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        s = s_mgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        s_mgr.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onSensorChanged(p0: SensorEvent?) {
        val accelerometerMaxRange = 10f // This is NOT right, but it's a good value to work with

        var newAngle = 0f
        if (p0!!.values[2] > 9) {
            // Phone is horizontally flat, can't point towards gravity, really. Do whatever you think is right
        } else {
            newAngle = (p0.values[0] * 90 / accelerometerMaxRange)
            if (p0.values[1] < 0) {
                newAngle = 180 - newAngle
            }
        }
        findViewById<ImageView>(R.id.arrow).rotation = (newAngle)
        Log.d("Hello",newAngle.toString())

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}