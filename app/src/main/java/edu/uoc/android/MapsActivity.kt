package edu.uoc.android

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import edu.uoc.android.rest.RetrofitFactory
import edu.uoc.android.rest.models.Element
import edu.uoc.android.rest.models.Museums
import kotlinx.coroutines.*
import timber.log.Timber

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val ui = CoroutineScope(Dispatchers.Main + Job())
    private val io = CoroutineScope(Dispatchers.IO + Job())
    private var MARKER_SIZE: Int = 0
    private lateinit var markerBitmap: Bitmap
    private val LOCATION_PERMISSIONS_REQUEST_CODE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        MARKER_SIZE = resources.getDimension(R.dimen.maps_marker_size).toInt()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isMyLocationButtonEnabled = true
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (!hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) && !hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    LOCATION_PERMISSIONS_REQUEST_CODE
                )
            } else {
                loadUserLocation()
            }
        } else {
            loadUserLocation()
        }

        prepareMarker()
        loadMuseums()
    }

    private fun hasPermission(permission: String): Boolean {
        return (ActivityCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun prepareMarker() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_library)
        markerBitmap =
            Bitmap.createScaledBitmap(bitmap, MARKER_SIZE, MARKER_SIZE, false)
    }

    private fun loadMuseums() = ui.launch {
        val retrofit = RetrofitFactory.museumAPI
        try {
            val museums = withContext(io.coroutineContext) { retrofit.museums("", "") }
            if (museums?.elements != null) {
                renderMuseums(museums)
            }
        } catch (e: Exception) {
            Timber.e(e)
            notifyError()
        }
    }

    private fun renderMuseums(museums: Museums) {
        museums.elements!!.forEach {
            renderMuseumElement(it)
        }
    }

    private fun renderMuseumElement(it: Element) {
        if (it.localitzacio == null) return
        try {
            val latLng = createLatLngFromString(it.localitzacio!!)
            val marker = MarkerOptions()
                .position(latLng)
                .title(it.adrecaNom)
                .icon(BitmapDescriptorFactory.fromBitmap(markerBitmap))
            mMap.addMarker(marker)
        } catch (e: IllegalArgumentException) {
            Timber.e("Coordinates for museum ${it.adrecaNom} are not valid. Will be ignored.")
        } catch (e: UninitializedPropertyAccessException) {
            Timber.e("Marker bitmap has not yet been prepared")
        }

    }

    private fun notifyError() {
        Toast.makeText(this, "Something unexpected happened. Please try again.", Toast.LENGTH_SHORT)
            .show()
    }

    private fun createLatLngFromString(coords: String): LatLng {
        val split = coords.split(",")
        if (split.size == 2) {
            try {
                val lat = split[0].trim().toDouble()
                val long = split[1].trim().toDouble()
                return LatLng(lat, long)
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException("Some of the coordinate parts are not doubles.", e)
            }
        } else {
            throw IllegalArgumentException("Coordinates do not follow the expected format.")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                loadUserLocation()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @SuppressLint("MissingPermission")
    private fun loadUserLocation() {
        mMap.isMyLocationEnabled = true
        val locationProvider = LocationServices.getFusedLocationProviderClient(this)
        locationProvider.lastLocation.addOnSuccessListener {
            if (it != null) {
                mMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(it.latitude, it.longitude),
                        12f
                    )
                )
            }
        }
    }
}