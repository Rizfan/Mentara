package com.rizfan.mentara.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rizfan.mentara.ui.theme.MentaraTheme

@Composable
fun ResultCard(
    modifier: Modifier = Modifier,
    result : String
) {
    Box(
        modifier = modifier.padding(16.dp)
    ){
        ElevatedCard(
            modifier = modifier
                .shadow(5.dp),
            shape = RoundedCornerShape(5.dp),
            colors = CardColors(
                containerColor = Color(0xFFFFFFFF),
                contentColor = Color(0xFF000000),
                disabledContainerColor = Color(0xFFFFFFFF),
                disabledContentColor = Color(0xFF000000),
            )
        ) {
            Text(
                text = "Test Results :",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 27.2.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    letterSpacing = 0.5.sp,
                ),
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            )
            Text(
                text = buildAnnotatedString{
                    append("Based on the test you did, your stress level is ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                        append("$result!")
                    }
                },
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 27.2.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Justify,
                    letterSpacing = 0.5.sp,
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ResultCardPreview() {
    MentaraTheme {
        ResultCard(result = "Tinggi")
    }
}