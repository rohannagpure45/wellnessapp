package com.example.mobileapplicationdevelopment2025.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import com.example.mobileapplicationdevelopment2025.data.WellnessDatabase
import com.example.mobileapplicationdevelopment2025.network.WellnessApi
import com.example.mobileapplicationdevelopment2025.repository.WellnessRepository

@HiltWorker
class RefreshDataWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val repository: WellnessRepository
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        return try {
            repository.refreshItems()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}