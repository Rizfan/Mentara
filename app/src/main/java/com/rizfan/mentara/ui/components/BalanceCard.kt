package com.rizfan.mentara.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rizfan.mentara.ui.theme.MentaraTheme

@Composable
fun BalanceCard(
    balance: String,
    onTopUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color(0xFFFB2528),
                shape = RoundedCornerShape(size = 10.dp)
            )
            .width(359.dp)
    ){
        Row (
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),

        ) {
            Column(
                modifier = modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            ) {
                Text(
                    text = "Mentara Balance",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xCC000000),
                    )
                )
                Row (
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Outlined.AddCircle,
                        contentDescription = "Shopping Cart",
                        tint = Color(0xFFFB2528),
                        modifier = modifier
                            .width(20.dp)
                            .height(20.dp)
                    )
                    Text(
                        text = balance,
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                        )
                    )
                }
            }
            Button(
                onClick = onTopUpClick,
                modifier = Modifier
                    .width(96.dp)
                    .height(37.dp)
                    .background(color = Color(0xFFFB2528), shape = RoundedCornerShape(size = 5.dp))
                    .wrapContentWidth(Alignment.End)
            ) {
                Text(
                    text = "Top Up",
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BalanceCardPreview() {
    MentaraTheme {
        BalanceCard(
            balance = "4",
            onTopUpClick = {}
        )
    }
}