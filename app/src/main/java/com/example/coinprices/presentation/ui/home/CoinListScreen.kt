package com.example.coinprices.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coinprices.domain.model.Coin

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoinListScreen(
    viewModel: CoinListViewModel = hiltViewModel(),
    onItemClick: (Coin) -> Unit
) {
    val uiState by viewModel.state.collectAsState()

    val pullState = rememberPullRefreshState(
        refreshing = uiState.isLoading,
        onRefresh  = viewModel::refresh
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
            .pullRefresh(pullState)
    ) {

        when {
            uiState.error.isNotBlank() -> {
                Text(
                    text       = uiState.error,
                    color      = MaterialTheme.colorScheme.error,
                    modifier   = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                        .padding(16.dp),
                    textAlign  = TextAlign.Center
                )
            }

            uiState.coins.isNotEmpty() -> {
                LazyColumn(
                    modifier       = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(uiState.coins) { coin ->
                        CoinListItem(
                            coin        = coin,
                            onItemClick = onItemClick
                        )
                    }
                }
            }
        }

        PullRefreshIndicator(
            refreshing = uiState.isLoading,
            state      = pullState,
            modifier   = Modifier.align(Alignment.TopCenter)
        )

        if (uiState.isLoading && uiState.coins.isEmpty()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}