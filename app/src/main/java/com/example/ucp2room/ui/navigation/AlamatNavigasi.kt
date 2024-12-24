package com.example.ucp2room.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHomeBrg : AlamatNavigasi {
    override val route = "home-brg"
}

object DestinasiHomeSpl : AlamatNavigasi {
    override val route = "home-spl"
}

object DestinasiDetail : AlamatNavigasi {
    override val route = "detail"
    const val ID = "id-detail"
    val routesWithArg = "$route/{$ID}"
}

object DestinasiUpdate : AlamatNavigasi {
    override val route = "update"
    const val ID = "id-update"
    val routesWithArg = "$route/{$ID}"
}

object DestinasiHalamanUtama : AlamatNavigasi {
    override val route = "awalan"
}

object DestinasiInsertBrg : AlamatNavigasi {
    override val route: String = "insert-brg"
}

object DestinasiInsertSpl : AlamatNavigasi {
    override val route: String = "insert-spl"
}