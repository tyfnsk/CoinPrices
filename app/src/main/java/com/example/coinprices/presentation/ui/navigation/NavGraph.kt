package com.example.coinprices.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.coinprices.presentation.ui.detail.CoinDetailScreen
import com.example.coinprices.presentation.ui.home.CoinListScreen
import com.example.coinprices.presentation.ui.home.CoinListViewModel

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "coin_list"
    ) {
        // Liste ekranı
        composable("coin_list") {
            CoinListScreen(
                viewModel = hiltViewModel<CoinListViewModel>(),
                onItemClick = { coin ->
                    navController.navigate("coin_detail/${coin.id}")
                }
            )
        }

        // Detay ekranı
        composable("coin_detail/{coinId}") { backStackEntry ->
            val coinId = backStackEntry.arguments?.getString("coinId") ?: return@composable
            CoinDetailScreen(coinId = coinId)
        }
    }
}