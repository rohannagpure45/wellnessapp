package com.example.mobileapplicationdevelopment2025.data.remote.repository

import com.example.mobileapplicationdevelopment2025.data.local.EquipmentDao
import com.example.mobileapplicationdevelopment2025.data.local.EquipmentEntity
import com.example.mobileapplicationdevelopment2025.data.remote.EquipmentApi        // <- FIX
import com.example.mobileapplicationdevelopment2025.data.remote.model.EquipmentDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EquipmentRepository @Inject constructor(
    private val api: EquipmentApi,
    private val dao: EquipmentDao
) {

    val equipments: StateFlow<List<EquipmentDto>> =
        dao.getAll()
            .map { list -> list.map(EquipmentEntity::toDto) }
            .stateIn(
                scope = CoroutineScope(Dispatchers.IO),
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    suspend fun getAll(): List<EquipmentDto> =
        api.fetchEquipment()
            .also { dao.upsert(it.map(EquipmentDto::toEntity)) }

    suspend fun getDetail(id: Int): EquipmentDto =
        dao.get(id)?.toDto() ?: api.fetchEquipmentDetail(id)

    suspend fun refresh() {
        val items = api.fetchEquipment()
        dao.upsert( items.map(EquipmentDto::toEntity) )
    }
}


private fun EquipmentEntity.toDto() =
    EquipmentDto(id, name, imageUrl, description)

private fun EquipmentDto.toEntity() =
    EquipmentEntity(id, name, imageUrl, description)
