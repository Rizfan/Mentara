package com.rizfan.mentara.ui.screen.history

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rizfan.mentara.R
import com.rizfan.mentara.data.response.ListResultItem
import com.rizfan.mentara.ui.common.UiState
import com.rizfan.mentara.ui.components.HistoryCard
import kotlinx.coroutines.launch

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel(),
) {

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
        when (it) {
            is UiState.Loading -> {
                viewModel.getHistory()
            }
            is UiState.Success -> {
                HistoryContent(
                    modifier = modifier,
                    history = it.data.listResult
                )
            }
            is UiState.Error -> {
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HistoryContent(
    modifier: Modifier = Modifier,
    history: List<ListResultItem?>?,
) {
    Box(modifier = modifier){
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn(
            state = listState, contentPadding = PaddingValues(bottom = 60.dp)
        ){
            stickyHeader {
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = stringResource(R.string.menu_history),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                    )
                })
            }
            if (history.isNullOrEmpty()){
                item {
                    Text(
                        text = "Data Tidak Ada!",
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            }else{
                items(history) { data ->
                    HistoryCard(
                        historyDate = data?.resultDate.toString(),
                        historyResult = data?.resultQuestionnaire.toString(),
                    )
                }
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
fun ScrollToTopButton(
    onClick: () -> Unit, modifier: Modifier = Modifier
) {
    FilledIconButton(
        onClick = onClick, modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = stringResource(R.string.scroll_to_top),
        )
    }
}
