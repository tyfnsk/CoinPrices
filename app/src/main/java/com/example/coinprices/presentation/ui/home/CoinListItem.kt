package com.example.coinprices.presentation.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.coinprices.domain.model.Coin
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CoinListItem(
    coin: Coin,
    modifier: Modifier = Modifier,
    onItemClick: (Coin) -> Unit
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onItemClick(coin) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = coin.image,
                contentDescription = coin.name,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${coin.name} (${coin.symbol.uppercase()})",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Row {
                    Text(text = "Price: ")
                    Text(text = NumberFormat
                        .getCurrencyInstance(Locale.US)
                        .format(coin.currentPrice))
                }

            }


            val changeColor = if (coin.priceChangePercentage >= 0) Color(0xFF4CAF50) else Color(0xFFF44336)
            val changeText = String.format("%.2f%%", coin.priceChangePercentage)

            Text(
                text = changeText,
                fontWeight = FontWeight.Bold,
                color = changeColor,
                fontSize = 16.sp
            )
        }
    }
}