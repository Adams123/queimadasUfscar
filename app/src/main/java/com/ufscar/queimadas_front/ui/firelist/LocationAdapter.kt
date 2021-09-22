package com.ufscar.queimadas_front.ui.firelist

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.ufscar.queimadas_front.R
import com.ufscar.queimadas_front.data.network.response.LocationCompleteResponse
import java.time.format.DateTimeFormatter


class LocationAdapter(context: Context, resource: Int) :
    ArrayAdapter<LocationCompleteResponse>(context, resource) {

    val data = ArrayList<LocationCompleteResponse>()

    override fun getCount(): Int {
        return data.size
    }

    override fun add(`object`: LocationCompleteResponse?) {
        super.add(`object`)
        `object`?.let {
            data.add(it)
        }
    }

    override fun getItem(position: Int): LocationCompleteResponse {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val location: LocationCompleteResponse = getItem(position)
        val localView : View = convertView ?: LayoutInflater.from(context).inflate(R.layout.row_fire_list, parent, false)
        val locationName = localView.findViewById(R.id.locationName) as TextView
        val latitude = localView.findViewById(R.id.latitude) as TextView
        val longitude = localView.findViewById(R.id.longitude) as TextView
        val username = localView.findViewById(R.id.username) as TextView
        val creationDate = localView.findViewById(R.id.creationDate) as TextView

        locationName.text = location.locationName
        latitude.text = location.latitude.toString()
        longitude.text = location.longitude.toString()
        username.text = location.username
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            creationDate.text = DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss").parse(location.detectionDate).toString()
        } else {
            creationDate.text = location.detectionDate
        }
        return localView
    }
}