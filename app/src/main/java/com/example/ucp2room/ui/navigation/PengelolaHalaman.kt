package com.example.ucp2room.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2room.ui.view.HalamanUtama
import com.example.ucp2room.ui.view.barang.DetailBrgView
import com.example.ucp2room.ui.view.barang.InsertBrgView
import com.example.ucp2room.ui.view.barang.InsertSplView
import com.example.ucp2room.ui.view.barang.ListBrgView
import com.example.ucp2room.ui.view.barang.UpdateBrgView
import com.example.ucp2room.ui.view.supplier.ListSplView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHalamanUtama.route
    ) {
        composable(
            route = DestinasiHalamanUtama.route
        ) {
            HalamanUtama(
                onListBrgClick = {
                    navController.navigate(DestinasiHomeBrg.route)
                },

                onListSplClick = {
                    navController.navigate(DestinasiHomeSpl.route)
                },

                onInsertBrgClick = {
                    navController.navigate(DestinasiInsertBrg.route)
                },

                onInsertSplClick = {
                    navController.navigate(DestinasiInsertSpl.route)
                }
            )
        }

        composable(
            route = DestinasiHomeBrg.route
        ) {
            ListBrgView(
                onDetailClick = { id ->
                    navController.navigate("${DestinasiDetail.route}/$id")
                },
                onBack = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiHomeSpl.route
        ) {
            ListSplView(
                modifier = modifier,
                onBack = {
                    navController.popBackStack()
                },
            )
        }

        composable(
            route = DestinasiInsertBrg.route
        ) {
            InsertBrgView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsertSpl.route
        ) {
            InsertSplView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable(
            DestinasiDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.ID) {
                    type = NavType.IntType
                }
            )
        ) {
            val id = it.arguments?.getInt(DestinasiDetail.ID)

            id?.let { id ->
                DetailBrgView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdate.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            DestinasiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.ID) {
                    type = NavType.IntType
                }
            )
        ) {
            UpdateBrgView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }
    }
}
