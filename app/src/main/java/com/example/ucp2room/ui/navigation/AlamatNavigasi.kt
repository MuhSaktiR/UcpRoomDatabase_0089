package com.example.ucp2room.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHomeBrg : AlamatNavigasi {
    override val route = "home-brg"
}

object DestinasiInsertBrg : AlamatNavigasi {
    override val route: String = "insert-brg"
}

