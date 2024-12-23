package com.example.ucp2room.repository

import com.example.ucp2room.data.dao.SupplierDao
import com.example.ucp2room.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

class LocalRepositorySpl (
    private val supplierDao: SupplierDao
) : RepositorySpl {
    override suspend fun insertSpl(supplier: Supplier) {
        supplierDao.insertSupplier(supplier)
    }

    override fun getAllSpl(): Flow<List<Supplier>> {
        return supplierDao.getAllSupplier()
    }
}