package com.example.ucp2room.ui.viewmodel.supplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2room.data.entity.Supplier
import com.example.ucp2room.repository.RepositorySpl
import kotlinx.coroutines.launch

class InsertSplViewModel (private val repositorySpl: RepositorySpl
) : ViewModel()
{
    var uiState by mutableStateOf(SplUIState())

    //Memperbarui state berdasarkan input pengguna
    fun updateState(supplierEvent: SupplierEvent) {
        uiState = uiState.copy(
            supplierEvent = supplierEvent,

            )
    }

    //Validasi data input pengguna
    private fun validateFields(): Boolean{
        val event = uiState.supplierEvent
        val errorState =  FormErrorState(
            nama = if (event.nama.isNotEmpty()) null else "Nama Supplier tidak boleh kosong",
            kontak = if (event.kontak.isNotEmpty()) null else "Nomor Kontak tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong"
        )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    //Menyimpan data ke repository
    fun saveData(){
        val currentEvent = uiState.supplierEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositorySpl.insertSpl(currentEvent.toSupplierEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil disimpan",
                        supplierEvent = SupplierEvent(), // Reset input form
                        isEntryValid = FormErrorState(), // Reset error state
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda."
            )
        }
    }

    // Reset pesan Snackbar setelah ditampilkan
    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessage = null)
    }
}

data class SplUIState(
    val supplierEvent: SupplierEvent = SupplierEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

data class FormErrorState(
    val nama: String? = null,
    val kontak: String? = null,
    val alamat: String? = null
) {
    fun isValid(): Boolean {
        return nama == null && kontak == null && alamat == null
    }
}

//Menyimpan input form ke dalam entity
fun SupplierEvent.toSupplierEntity(): Supplier = Supplier (
    nama = nama,
    kontak = kontak,
    alamat = alamat,

)

// data class variabel yang menyimpan data input form
data class SupplierEvent(
    val nama: String = "",
    val kontak: String = "",
    val alamat: String = "",
)