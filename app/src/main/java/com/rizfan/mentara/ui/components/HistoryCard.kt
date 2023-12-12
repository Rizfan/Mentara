package com.rizfan.mentara.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HistoryCard(
    historyDate : String,
    historyResult : String,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .fillMaxWidth()
    ){
        Column {
            Text(
                text = historyDate,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
            )
            Row{
                Text(
                    text = "Hasil test tingkat Stress : ",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Light
                    ),
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                )
                Text(
                    text = historyResult,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(end = 16.dp, bottom = 16.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HistoryCardPreview() {
    HistoryCard(
        historyDate = "21 November 2023",
        historyResult = "Rendah"
    )
}