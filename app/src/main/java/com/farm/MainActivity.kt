package com.farm

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.farm.field.AddFieldActivity
import com.farm.field.FieldActivity
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() , LocationListener {

    var latitude : Double = 0.0
    var longitude : Double = 0.0
    var lonStart : Double = 0.0
    var lonStop : Double = 0.0
    var latStart : Double = 0.0
    var latStop : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.application_toolbar))
    }

    override fun onStart() {
        super.onStart()
        findViewById<Button>(R.id.start_stop_button).setOnClickListener(start())
        fillLocation(applicationContext)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.add_field){
            startActivity(Intent(this@MainActivity,AddFieldActivity::class.java))
        }
        if(item.itemId == R.id.view_fields){
            startActivity(Intent(this@MainActivity,FieldActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    fun start() : View.OnClickListener{
        return View.OnClickListener {
            if(lonStart == 0.0) {
                lonStart = longitude
                latStart = latitude
                findViewById<Button>(R.id.start_stop_button).text = "STOP"
                findViewById<TextView>(R.id.distance).text = "0.0"
            }else{
                lonStop = longitude
                latStop = latitude
                findViewById<TextView>(R.id.distance).text = calculateDistance().toString()
                findViewById<Button>(R.id.start_stop_button).text = "START"
                lonStart = 0.0
                latStart = 0.0
                lonStop = 0.0
                latStop = 0.0
            }
        }
    }

    fun calculateDistance() : Double{
        val lonDelta = (lonStop - lonStart).absoluteValue *111
        val latDelta = (latStop - latStart).absoluteValue *111
        return (sqrt(lonDelta.pow(2) + latDelta.pow(2)))*1000
    }

    fun fillLocation(context: Context) {

        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager;

        ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
        1
        )

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            100,
            1F,
            this
        )
    }

    override fun onLocationChanged(location: Location) {
        latitude = location.latitude
        longitude = location.longitude
        findViewById<TextView>(R.id.lat).text = latitude.toString()
        findViewById<TextView>(R.id.lon).text = longitude.toString()
        findViewById<TextView>(R.id.speed).text = location.speed.toString()
        findViewById<TextView>(R.id.accuracy).text = location.accuracy.toString()
    }

}