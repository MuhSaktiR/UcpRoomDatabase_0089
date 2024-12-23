package com.example.ucp2room.ui.viewmodel

import com.example.ucp2room.ui.viewmodel.barang.DetailBrgViewModel
import com.example.ucp2room.ui.viewmodel.barang.HomeBrgViewModel
import com.example.ucp2room.ui.viewmodel.barang.InsertBrgViewModel
import com.example.ucp2room.ui.viewmodel.barang.UpdateBrgViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2room.TokoApp
import com.example.ucp2room.ui.viewmodel.supplier.HomeSplViewModel
import com.example.ucp2room.ui.viewmodel.supplier.InsertSplViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeBrgViewModel(
                tokoApp().containerApp.repositoryBrg
            )
        }

        initializer {
            InsertBrgViewModel (
                tokoApp().containerApp.repositoryBrg
            )
        }

        initializer {
            DetailBrgViewModel (
                createSavedStateHandle(),
                tokoApp().containerApp.repositoryBrg
            )
        }

        initializer {
            UpdateBrgViewModel (
                createSavedStateHandle(),
                tokoApp().containerApp.repositoryBrg
            )
        }

        initializer {
            HomeSplViewModel (
                tokoApp().containerApp.repositorySpl
            )
        }

        initializer {
            InsertSplViewModel (
                tokoApp().containerApp.repositorySpl
            )
        }

    }
}

fun CreationExtras.tokoApp() : TokoApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TokoApp)