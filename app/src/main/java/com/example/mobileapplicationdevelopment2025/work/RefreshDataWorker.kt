package com.example.mobileapplicationdevelopment2025.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.mobileapplicationdevelopment2025.data.WellnessDatabase
import com.example.mobileapplicationdevelopment2025.network.WellnessApi
import com.example.mobileapplicationdevelopment2025.repository.WellnessRepository

class RefreshDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        val dao = WellnessDatabase.getInstance(applicationContext).wellnessDao()
        val api = WellnessApi.create()
        val repository = WellnessRepository(api, dao)
        return try {
            repository.refreshItems()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
