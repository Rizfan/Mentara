package com.rizfan.mentara.ui.screen.questionnaire

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rizfan.mentara.ui.components.QuestionCard
import com.rizfan.mentara.ui.screen.history.ScrollToTopButton
import com.rizfan.mentara.ui.theme.MentaraTheme
import kotlinx.coroutines.launch

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

    QuestionnaireContent(
        modifier = modifier,
        onOptionSelected = onOptionSelected,
        selectedOption = selectedOption,
        radioOptions = radioOptions
    )

}

@Composable
fun QuestionnaireContent(
    modifier: Modifier = Modifier,
    onOptionSelected: (String) -> Unit,
    selectedOption: String,
    radioOptions: List<String>
) {
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val showButton: Boolean by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }
    Box(modifier = modifier){
        LazyColumn(
            modifier = modifier.padding(horizontal = 8.dp),
            state = listState, contentPadding = PaddingValues(bottom = 60.dp)
        ){
            items(10){
                QuestionCard(
                    onOptionSelected = onOptionSelected,
                    selectedOption = selectedOption,
                    radioOptions = radioOptions
                )
            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 15.dp)
                .align(Alignment.BottomCenter)
        ) {
            ScrollToTopButton(onClick = {
                scope.launch {
                    listState.scrollToItem(index = 0)
                }
            })
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