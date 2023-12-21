package com.rizfan.mentara.ui.screen.questionnaire

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rizfan.mentara.ui.theme.MentaraTheme

@Composable
fun QuestionnaireScreen(
    modifier: Modifier = Modifier
) {
    val radioOptions = listOf("Sangat setuju", "Setuju", "Netral", "Tidak setuju", "Sangat tidak setuju")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    val point = when(selectedOption){
        "Sangat setuju" -> 5
        "Setuju" -> 4
        "Netral" -> 3
        "Tidak setuju" -> 2
        else -> 1
    }

    Box(modifier = modifier){
        Column(Modifier.selectableGroup()) {
            Text(
                text = "Saya merasa bahagia dan puas dengan hidup saya.",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 27.2.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    letterSpacing = 0.5.sp,
                ),
                modifier = Modifier
                    .padding(top = 32.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            )
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null // null recommended for accessibility with screenreaders
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
fun QuestionnaireScreenPreview() {
    MentaraTheme {
        QuestionnaireScreen()
    }
}