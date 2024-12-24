package com.example.ucp2room.ui.viewmodel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2room.data.entity.Barang
import com.example.ucp2room.repository.RepositoryBrg
import kotlinx.coroutines.launch

class InsertBrgViewModel (private val repositoryBrg: RepositoryBrg
) : ViewModel()
{
    var uiState by mutableStateOf(BrgUIState())

    //Memperbarui state berdasarkan input pengguna
    fun updateState(barangEvent: BarangEvent) {
        uiState = uiState.copy(
            barangEvent = barangEvent,

            )
    }

    //Validasi data input pengguna
    private fun validateFields(): Boolean{
        val event = uiState.barangEvent
        val errorState =  FormErrorState(
            nama = if (event.nama.isNotEmpty()) null else "Nama Barang tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi tidak boleh kosong",
            harga = when {
                event.harga.isEmpty() -> "Harga tidak boleh kosong"
                event.harga.toDoubleOrNull() == null -> "Harga harus berupa angka"
                event.harga.toDouble() < 0 -> "Harga tidak boleh negatif"
                else -> null
            },
            stok = when {
                event.stok.isEmpty() -> "Stok tidak boleh kosong"
                event.stok.toIntOrNull() == null -> "Stok harus berupa angka"
                event.stok.toInt() < 0 -> "Stok tidak boleh negatif"
                else -> null
            },
            namaS = if (event.namaS.isNotEmpty()) null else "Nama Supplier tidak boleh kosong",
        )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    //Menyimpan data ke repository
    fun saveData(){
        val currentEvent = uiState.barangEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryBrg.insertBrg(currentEvent.toBarangEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil disimpan",
                        barangEvent = BarangEvent(), // Reset input form
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

data class BrgUIState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

data class FormErrorState(
    val nama: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val namaS: String? = null
) {
    fun isValid(): Boolean {
        return nama == null && deskripsi == null &&
                harga == null && stok == null && namaS == null
    }
}

//Menyimpan input form ke dalam entity
fun BarangEvent.toBarangEntity(): Barang = Barang (
    id = id ?: 0,
    nama = nama,
    deskripsi = deskripsi,
    harga = harga.toDoubleOrNull() ?: 0.0,
    stok = stok.toIntOrNull() ?: 0,
    namaS = namaS
)

// data class variabel yang menyimpan data input form
data class BarangEvent(
    val id: Int? = null,
    val nama: String = "",
    val deskripsi: String = "",
    val harga: String = "",
    val stok: String = "",
    val namaS: String = ""
)