package com.example.coinprices.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coinprices.domain.model.Coin
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun CoinListScreen(
    viewModel: CoinListViewModel = hiltViewModel(),
    onItemClick: (Coin) -> Unit
) {
    val state by viewModel.state.collectAsState()

    val swipeState = rememberSwipeRefreshState(isRefreshing = state.isLoading)
    LaunchedEffect(state.isLoading) {
        swipeState.isRefreshing = state.isLoading
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
        SwipeRefresh(
            state = swipeState,
            onRefresh = {
                android.util.Log.d("CoinListScreen", "Swipe to refresh triggered") // to see Log
                viewModel.refresh()
            }            // pull-to-refresh
        ) {
            when {
                state.error.isNotBlank() -> {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }

                state.coins.isNotEmpty() -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(state.coins) { coin ->
                            CoinListItem(
                                coin = coin,
                                onItemClick = onItemClick
                            )
                        }
                    }
                }
            }
        }

        /* İlk açılışta tam-ekran spinner */
        if (state.isLoading && state.coins.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}