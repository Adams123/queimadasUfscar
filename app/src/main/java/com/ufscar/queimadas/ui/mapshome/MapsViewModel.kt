package com.ufscar.queimadas.ui.mapshome

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.ufscar.queimadas.rest.repositories.MapsRepository
import com.ufscar.queimadas.utils.getLogger

class MapsViewModel : ViewModel(), GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    val logger = getLogger(MapsViewModel::class.java)

    override fun onMapClick(point: LatLng?) {
        MapsRepository().addPoint(point!!)
        logger.info("Cliqued $point")
    }

    override fun onMapLongClick(point: LatLng?) {
        logger.info("Long cliqued $point")
    }
}