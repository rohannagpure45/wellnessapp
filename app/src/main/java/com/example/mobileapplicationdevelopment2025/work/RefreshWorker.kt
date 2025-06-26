package com.example.mobileapplicationdevelopment2025.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.mobileapplicationdevelopment2025.data.remote.repository.EquipmentRepository
import com.example.mobileapplicationdevelopment2025.data.remote.repository.FoodRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class RefreshWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val foodRepo: FoodRepository,
    private val eqRepo: EquipmentRepository
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result =
        try {
            foodRepo.search("apple")
            eqRepo.refresh()
            Result.success()
        } catch (_: Exception) {
            Result.retry()
        }
}
