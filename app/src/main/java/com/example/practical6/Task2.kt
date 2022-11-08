package com.example.practical6

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Task2 : AppCompatActivity(), SensorEventListener {

    private lateinit var s_mgr: SensorManager;
    private lateinit var s: Sensor;
    private lateinit var mediaPlayer: MediaPlayer;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task2)
        s_mgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        s = s_mgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        s_mgr.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL)
        mediaPlayer = MediaPlayer.create(applicationContext,R.raw.fireworks)

    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0!!.values[0] in (SensorManager.GRAVITY_MOON - 0.1) .. (SensorManager.GRAVITY_MOON + 0.1)){
            mediaPlayer.start() // no need to call prepare(); create() does that for you
        }
        else{
            if (mediaPlayer.isPlaying){
                mediaPlayer.pause()
                mediaPlayer.seekTo(0)
            }
        }

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}