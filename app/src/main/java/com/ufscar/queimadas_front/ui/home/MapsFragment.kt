package com.ufscar.queimadas_front.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.Interpolator
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.ufscar.queimadas_front.R
import com.ufscar.queimadas_front.data.UserPreferences
import com.ufscar.queimadas_front.data.network.Resource
import com.ufscar.queimadas_front.databinding.FragmentMapsBinding
import com.ufscar.queimadas_front.utils.enable
import com.ufscar.queimadas_front.utils.getLogger
import com.ufscar.queimadas_front.utils.handleApiError
import com.ufscar.queimadas_front.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MapsFragment : Fragment(R.layout.fragment_maps), OnMapReadyCallback, GoogleMap.OnMarkerClickListener  {

    private val log = getLogger(this::class.java)

    @Inject
    lateinit var userPreferences: UserPreferences

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var locationManager: LocationManager
    private lateinit var lastLocation: Location

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val DEFAULT_ZOOM = 15
    }

    private val viewModel by viewModels<MapsViewModel>()

    private lateinit var mMap: GoogleMap
    private lateinit var binding: FragmentMapsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMapsBinding.bind(view)


        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        locationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)?.getMapAsync(this)

        binding.logoutBtn.setOnClickListener {
            performLogout()
        }

        binding.sendBtn.setOnClickListener {
            fetchCurrentLocationAndSendToBE()
        }.also {
            fetchNewLocations()
        }

        binding.fetchNotifications.setOnClickListener {
            fetchNewLocations()
        }

        binding.openFireList.setOnClickListener {
            findNavController().navigate(R.id.action_mapFragment_to_fire_list)
        }
    }

    private fun fetchNewLocations(){
        viewModel.fetchPendings()
    }

    private fun observeMappings(){
        viewModel.pendingsResponse.observe(this, {
            when (it) {
                is Resource.Success -> {
                    binding.fetchNotifications.enable(true)
                    for (locationResponse in it.value) {
                        val latLng = LatLng(locationResponse.latitude.toDouble(), locationResponse.longitude.toDouble())
                        mMap.addMarker(MarkerOptions().position(latLng).visible(true))
                    }
                }
                is Resource.Failure -> {
                    binding.fetchNotifications.enable(true)
                    handleApiError(it){ fetchNewLocations() }
                }
                is Resource.Loading -> {
                    binding.fetchNotifications.enable(false)
                }
                else -> toast("Erro inesperado!")
            }
        })
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)

        setUpMap()

        fetchNewLocations()

        observeMappings()
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                mMap.isMyLocationEnabled = true
            } else {
                toast("Location is disabled")
            }
        }
    }

    private fun performLogout() {
        lifecycleScope.launch {
            userPreferences.clear()
        }
        findNavController().navigate("queimadas://home?id=homeDeepLink".toUri())
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    private fun setUpMap() {
        if (!isFineLocationOn()) {
            if (!isCoarseLocationOn()) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
                return
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
                return
            }
        } else if (!isCoarseLocationOn()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        mMap.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(requireActivity()) { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }


        mMap.setOnMyLocationButtonClickListener {
            getDeviceLocation()
            true
        }
    }

    private fun sendToBackend(latlng: LatLng) {
        lifecycleScope.launch{
            viewModel.create(latlng.latitude, latlng.longitude)
        }
    }

    private fun fetchCurrentLocationAndSendToBE() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener(requireActivity()) {
            if(it!=null){
                sendToBackend(LatLng(it.latitude, it.longitude))
            }
        }
    }

    private fun getDeviceLocation() {
        try {
            val locationResult = fusedLocationClient.lastLocation
            locationResult.addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    lastLocation = task.result
                    if (lastLocation != null) {
                        mMap.moveCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                LatLng(
                                    lastLocation.latitude,
                                    lastLocation.longitude
                                ), DEFAULT_ZOOM.toFloat()
                            )
                        )
                    }
                } else {
                    val defaultLocation = LatLng(-33.8523341, 151.2106085)
                    mMap.moveCamera(
                        CameraUpdateFactory
                            .newLatLngZoom(defaultLocation, DEFAULT_ZOOM.toFloat())
                    )
                    mMap.uiSettings.isMyLocationButtonEnabled = false
                }
            }

        } catch (e: SecurityException) {
            log.error("Exception: %s", e.message, e)
        }
    }


    private fun isFineLocationOn(): Boolean {
        return ActivityCompat.checkSelfPermission(requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun isCoarseLocationOn(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        val handler = Handler()
        val start = SystemClock.uptimeMillis()
        val duration: Long = 1500

        val interpolator: Interpolator = BounceInterpolator()

        handler.post(object : Runnable {
            override fun run() {
                val elapsed = SystemClock.uptimeMillis() - start
                val t =
                    (1 - interpolator.getInterpolation(elapsed.toFloat() / duration))
                        .coerceAtLeast(0F)
                marker?.setAnchor(0.5f, 1.0f + 2 * t)
                if (t > 0.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16)
                }
            }
        })

        return false
    }
}