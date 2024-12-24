package com.example.ucp2room.ui.view.barang

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2room.data.ListSpl
import com.example.ucp2room.ui.customwidget.Dropdown
import com.example.ucp2room.ui.customwidget.TopAppBar
import com.example.ucp2room.ui.viewmodel.barang.FormErrorState
import com.example.ucp2room.ui.viewmodel.barang.BarangEvent
import com.example.ucp2room.ui.viewmodel.barang.InsertBrgViewModel
import com.example.ucp2room.ui.viewmodel.barang.BrgUIState
import com.example.ucp2room.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun InsertBrgView(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    viewModel: InsertBrgViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState // Ambil UI State dari viewmodel
    val snackbarHostState =  remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Observasi perubahan snackBarMessage
    LaunchedEffect(uiState.snackBarMessage)  {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)},
        topBar = {
            TopAppBar(
            onBack = onBack,
            showBackButton = true,
            judul = "Tambah Barang",
            modifier = modifier
            )
        }
    ) { padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ){
            InsertBodyBrg(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    //Update state di viewmodel
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    viewModel.saveData()
                    if (uiState.snackBarMessage == "Data Berhasil disimpan")
                    {
                        onNavigate()
                    }
                }
            )

        }

    }
}

@Composable
fun InsertBodyBrg(
    modifier: Modifier = Modifier,
    onValueChange: (BarangEvent) -> Unit,
    uiState: BrgUIState,
    onClick: () -> Unit
) {
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormBarang(
            barangEvent = uiState.barangEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth(),
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = buttonColors(
                containerColor = Color(0xFF2b95bb)
            )
        ) {
            Text("Simpan")
        }
    }
}

@Composable
fun FormBarang(
    barangEvent: BarangEvent = BarangEvent(),
    onValueChange: (BarangEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
    ){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.nama,
            onValueChange = {
                onValueChange(barangEvent.copy(nama = it))
            },
            label = { Text("Nama")},
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nama Barang")}
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.deskripsi,
            onValueChange = {
                onValueChange(barangEvent.copy(deskripsi = it))
            },
            label = { Text("Deskripsi")},
            isError = errorState.deskripsi != null,
            placeholder = { Text("Masukkan Deskripsi Barang")}
        )
        Text(
            text = errorState.deskripsi ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.harga,
            onValueChange = {
                onValueChange(barangEvent.copy(harga = it))
            },
            label = { Text("Harga") },
            isError = errorState.harga != null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = { Text("Masukkan Harga Barang") }
        )
        Text(
            text = errorState.harga ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.stok,
            onValueChange = {
                onValueChange(barangEvent.copy(stok = it))
            },
            label = { Text("Stok") },
            isError = errorState.stok != null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = { Text("Masukkan Stok Barang") }
        )
        Text(
            text = errorState.stok ?: "",
            color = Color.Red
        )

        Dropdown(
            modifier = Modifier,
            selectedValue = barangEvent.namaS,
            label = "Nama Supplier",
            onValueChangedEvent = { pilihSupplier ->
                onValueChange(barangEvent.copy(namaS = pilihSupplier))
            },
            options = ListSpl.namaSupplier(),
        )
        Text(
            text = errorState.stok ?: "",
            color = Color.Red
        )
        Spacer(Modifier.padding(20.dp))
    }
}