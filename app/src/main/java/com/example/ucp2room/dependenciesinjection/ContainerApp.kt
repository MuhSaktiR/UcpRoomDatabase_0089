package com.example.ucp2room.dependenciesinjection

import android.content.Context
import com.example.ucp2room.data.database.TokoDatabase
import com.example.ucp2room.repository.LocalRepositoryBrg
import com.example.ucp2room.repository.LocalRepositorySpl
import com.example.ucp2room.repository.RepositoryBrg
import com.example.ucp2room.repository.RepositorySpl

interface InterfaceContainerApp {
    val repositoryBrg: RepositoryBrg
    val repositorySpl: RepositorySpl
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryBrg: RepositoryBrg by lazy {
        LocalRepositoryBrg(TokoDatabase.getDatabase(context).barangDao())
    }

    override val repositorySpl: RepositorySpl by lazy {
        LocalRepositorySpl(TokoDatabase.getDatabase(context).supplierDao())
    }
}