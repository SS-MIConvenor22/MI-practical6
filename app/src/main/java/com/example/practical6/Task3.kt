package com.example.practical6

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Task3 : AppCompatActivity(), LocationListener {

    private lateinit var l_mgr: LocationManager
    private var coordinate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task3)
        l_mgr = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        // do we have permission to get location data?
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            startSensor()
        }
        else{

            val textView = findViewById<TextView>(R.id.dataView)
            textView.text = "Asking for permission..."
            ActivityCompat.requestPermissions(this, arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION),1)
        }
    }

    fun startSensor(){
        try{
            l_mgr.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0,
                0f,
                this
            )
        }
        catch (e: SecurityException){
            val textView = findViewById<TextView>(R.id.dataView)
            textView.text = e.localizedMessage
        }
    }
    override fun onLocationChanged(p0: Location) {
        val textView = findViewById<TextView>(R.id.dataView)
        textView.text = "POSITION:\n" +
                "LAT: " + p0.latitude + "\n" +
                "LNG: " + p0.longitude + "\n" +
                "ACC: " + p0.accuracy + "m\n"

        coordinate = "geo:" + p0.latitude.toString() + "," + p0.longitude.toString()
    }

    fun openMap(view: View) {
        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(coordinate))
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
            startSensor()
        }
        else{
            findViewById<TextView>(R.id.dataView).text = "Sorry, location permissions required..."
        }
    }
}