package com.example.ucp2room.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2room.ui.viewmodel.PenyediaViewModel
import com.example.ucp2room.ui.viewmodel.supplier.HomeSplViewModel

object ListSpl {
    @Composable
    fun NamaSupplier(
        splList: HomeSplViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ) : List<String> {
        val getNamaSpl by splList.homeUIState.collectAsState()
        val namaSpl = getNamaSpl.listSpl.map { it.nama }
        return namaSpl
    }
}