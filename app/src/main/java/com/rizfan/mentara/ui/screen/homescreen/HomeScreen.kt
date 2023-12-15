package com.rizfan.mentara.ui.screen.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rizfan.mentara.ui.components.BalanceCard
import com.rizfan.mentara.ui.theme.MentaraTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,

) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Text(
            text = "Halo, Rizfan!",
            style = TextStyle(
                fontSize = 26.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF000000),
            ),
            modifier = modifier.padding(top = 16.dp)
        )
        Text(
            text = "Bagaimana kabar kamu?",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF848484),
            ),
            modifier = modifier.padding(bottom = 16.dp)
        )

        BalanceCard(
            balance = "5",
            onTopUpClick = { /*TODO*/ },
        )

        Text(
            text = "Periksa dirimu disini!",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF000000),
                ),
            modifier = modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        Button(
            onClick = {},
            shape = RoundedCornerShape(10.dp),
            modifier = modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6386FF),
            ),
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start

            ) {
                Icon(
                    Icons.Filled.HealthAndSafety,
                    contentDescription ="Kesehatan Mental"
                )
                Column(modifier = modifier) {
                    Text(
                        text = "Kesehatan Mental",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                        ),
                        modifier = modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = "Cek sekarang!",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF0F172A),
                        ),
                        modifier = modifier.padding(start = 8.dp)
                    )

                }
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    device = Devices.PIXEL_4,
    showSystemUi = true
)
fun HomeScreenPreview() {
    MentaraTheme {
        HomeScreen()
    }
}