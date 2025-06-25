package com.example.mobileapplicationdevelopment2025.data.remote.repository

import com.example.mobileapplicationdevelopment2025.data.local.EquipmentDao
import com.example.mobileapplicationdevelopment2025.data.remote.EquipmentApi
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EquipmentRepository @Inject constructor(
    private val api: EquipmentApi,
    private val dao: EquipmentDao
) {
    fun equipments(): Flow<List<EquipmentDto>> = dao.observeAll().map { it.map(::toDto) }

    suspend fun refresh() {
        val remote = api.fetchEquipment()
        dao.upsertAll(remote.map(::toEntity))
    }
}
