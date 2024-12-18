package com.example.ucp2room.repository

import com.example.ucp2room.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

interface RepositorySpl {
    suspend fun insertSpl(supplier: Supplier)

    fun getAllSpl(): Flow<List<Supplier>>

    fun getSpl(id: String) : Flow<Supplier>

}