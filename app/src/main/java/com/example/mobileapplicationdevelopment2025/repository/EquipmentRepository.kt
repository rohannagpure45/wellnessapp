package com.example.mobileapplicationdevelopment2025.data.remote.repository

import com.example.mobileapplicationdevelopment2025.data.local.EquipmentDao
import com.example.mobileapplicationdevelopment2025.data.remote.EquipmentApi
import com.example.mobileapplicationdevelopment2025.data.remote.model.EquipmentDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EquipmentRepository @Inject constructor(
    private val api: EquipmentApi,
    private val dao: EquipmentDao
) {

    /** Network → DB (one-shot). Call from WorkManager or ViewModel. */
    suspend fun refresh() {
        val networkList = api.fetchEquipment()
        dao.upsert(networkList.map(EquipmentDto::toEntity))
    }

    /** Stream of cached items for Compose. */
    fun getAll(): Flow<List<EquipmentDto>> =
        dao.observeAll().map { list -> list.map { it.toDto() } }

    /** Detail (offline-first). */
    suspend fun getDetail(id: Int): EquipmentDto =
        dao.get(id)?.toDto() ?: api.fetchEquipmentDetail(id).also {
            dao.upsert(listOf(it.toEntity()))
        }
}
