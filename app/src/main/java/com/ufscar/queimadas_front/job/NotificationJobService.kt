package com.ufscar.queimadas_front.job

import android.app.job.JobParameters
import android.app.job.JobService
import com.ufscar.queimadas_front.data.repository.MapsRepository
import javax.inject.Inject

class NotificationJobService @Inject constructor(private val repository: MapsRepository) : JobService() {


    override fun onStartJob(params: JobParameters?): Boolean {

        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        TODO("Not yet implemented")
    }

}