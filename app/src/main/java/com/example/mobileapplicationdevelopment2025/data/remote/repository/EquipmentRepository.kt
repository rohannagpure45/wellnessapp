package com.example.mobileapplicationdevelopment2025.data.remote.repository

import com.example.mobileapplicationdevelopment2025.data.local.EquipmentDao
import com.example.mobileapplicationdevelopment2025.data.remote.EquipmentApi
import com.example.mobileapplicationdevelopment2025.data.remote.model.EquipmentDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EquipmentRepository @Inject constructor(
    private val api: EquipmentApi,
    private val dao: EquipmentDao
) {
    suspend fun getAll(): List<EquipmentDto> =
        api.fetchEquipment().also { dao.upsert(it.map(EquipmentDto::toEntity)) }

    suspend fun getDetail(id: Int): EquipmentDto =
        dao.get(id)?.toDto() ?: api.fetchEquipmentDetail(id)
}