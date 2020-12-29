package com.ufscar.queimadas.ui.mapshome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ufscar.queimadas.R
import com.ufscar.queimadas.utils.toast

class MapsHomeActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val mapsViewModel: MapsViewModel = MapsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps_home)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val sbo = LatLng(-22.7466861,-47.4232819)
        mMap.addMarker(MarkerOptions().position(sbo).title("SBO"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sbo, 16f))

        mMap.setOnMapClickListener(mapsViewModel)
        mMap.setOnMapLongClickListener(mapsViewModel)
    }
}