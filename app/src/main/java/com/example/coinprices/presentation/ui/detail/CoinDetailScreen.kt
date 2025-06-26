package com.example.coinprices.presentation.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.rememberAsyncImagePainter
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CoinDetailScreen(
    coinId: String,
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    // Get state from ViewModel
    val state by viewModel.state.collectAsState()

    // Bring the CoinDetail into scope
    LaunchedEffect(key1 = coinId) {
        viewModel.loadCoinDetail(coinId)
    }

    Box(modifier = Modifier.fillMaxSize()
        .padding(WindowInsets.systemBars.asPaddingValues())) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.error.isNotBlank() -> {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.coin != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    state.coin!!.image.let { imageUrl ->
                        Image(
                            painter = rememberAsyncImagePainter(model = imageUrl),
                            contentDescription = state.coin!!.name,
                            modifier = Modifier.size(100.dp).align(Alignment.Start)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    Text(
                        text = "${state.coin!!.name} (${state.coin!!.symbol.uppercase()})",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = NumberFormat
                        .getCurrencyInstance(Locale.US)
                        .format(state.coin!!.currentPrice))
                    Text(text = "Rank: ${state.coin!!.marketCapRank}")

                    val changeColor = if (state.coin!!.priceChangePercentage >= 0) Color(0xFF4CAF50) else Color(0xFFF44336)
                    val changeText = String.format("%.2f%%", state.coin!!.priceChangePercentage)

                    Row {
                        Text(text = "Price Change: ")
                        Text(
                            text = changeText,
                            fontWeight = FontWeight.Bold,
                            color = changeColor,
                            fontSize = 16.sp
                        )
                    }


                }
            }
        }
    }
}