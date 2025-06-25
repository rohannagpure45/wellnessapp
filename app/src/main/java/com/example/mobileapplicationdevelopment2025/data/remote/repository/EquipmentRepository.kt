package com.example.mobileapplicationdevelopment2025.data.remote.repository

import com.example.mobileapplicationdevelopment2025.data.local.EquipmentDao
import com.example.mobileapplicationdevelopment2025.data.local.EquipmentEntity
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
    val equipments: Flow<List<EquipmentDto>> = dao.getAll().map { entities ->
        entities.map { it.toDto() }
    }

    fun getAll(): Flow<List<EquipmentDto>> = equipments

    suspend fun refresh() {
        val remoteList = api.fetchEquipment()
        dao.replaceAll(remoteList.map { it.toEntity() })
    }

}

private fun EquipmentEntity.toDto(): EquipmentDto {
    return EquipmentDto(id, name, imageUrl, category, description)
}
