package com.saurabh.vaish.womensafety

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lat:Double=0.0
    var longi:Double=0.0
    // var locationNotObtained=true
    lateinit var locationManager: LocationManager
    lateinit var listener: LocationListener

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar as Toolbar?)

        locationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager

        listener=object :LocationListener{
            override fun onLocationChanged(location: Location?) {
                lat=location!!.latitude
                longi=location!!.longitude

                Log.d("MyLocation",lat.toString()+" "+longi.toString())
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String?) {
            }

            override fun onProviderDisabled(provider: String?) {
            }

        }


        ActivityCompat.requestPermissions(this@MainActivity,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION),
            102)

        Picasso.get().load(R.drawable.alert).into(alertIcon)


       alertIcon.setOnClickListener {
           locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,15000,100F,listener)
           val intent=Intent(this@MainActivity,OnAlert::class.java)
           val st=lat.toString()+","+longi.toString()
           Log.d("MyLoc", st)
           intent.putExtra("LatLong",st);
           startActivity(intent)
       }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_items,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item!!.itemId){
            R.id.addContacts->{
                val intent= Intent(this@MainActivity,AddContacts::class.java)
                startActivity(intent)
            }
            R.id.showContacts->{
                val intent=Intent(this@MainActivity,ShowContacts::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
