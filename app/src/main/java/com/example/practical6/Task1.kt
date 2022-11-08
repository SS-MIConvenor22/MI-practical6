package com.example.practical6

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView

class Task1 : AppCompatActivity(), SensorEventListener{
    private lateinit var s_mgr: SensorManager;
    private lateinit var s: Sensor;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task1)
        findViewById<ImageView>(R.id.fullmoon_image).visibility = View.INVISIBLE

        s_mgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        s = s_mgr.getDefaultSensor(Sensor.TYPE_LIGHT)
        s_mgr.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0!!.values[0] in (SensorManager.LIGHT_FULLMOON - 10) .. (SensorManager.LIGHT_FULLMOON + 10)){

            findViewById<ImageView>(R.id.fullmoon_image).visibility = View.VISIBLE
        }
        else {
            findViewById<ImageView>(R.id.fullmoon_image).visibility = View.INVISIBLE
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}