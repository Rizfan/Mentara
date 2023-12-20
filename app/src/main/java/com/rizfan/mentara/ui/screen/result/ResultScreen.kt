package com.rizfan.mentara.ui.screen.result

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rizfan.mentara.R
import com.rizfan.mentara.ui.common.UiState
import com.rizfan.mentara.ui.components.ResultCard

@Composable
fun ResultScreen(
    resultId : Int,
    modifier : Modifier = Modifier,
    navigateBack : () -> Unit,
    viewModel: ResultViewModel = hiltViewModel()
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
        when(it){
            is UiState.Loading ->{
                viewModel.getDetailResult(resultId)
            }
            is UiState.Success ->{
                ResultContent(
                    modifier = modifier,
                    result = it.data.result?.resultQuestionnaire.toString(),
                    onBackClick = navigateBack
                )
            }
            is UiState.Error ->{}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultContent(
    result : String,
    onBackClick: () -> Unit,
    modifier : Modifier = Modifier
){
    Box(modifier = modifier){

        Column (
            modifier = modifier
                .fillMaxWidth()
        ){
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.menu_result),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight(700),
                        ),
                    )
                },
                colors = TopAppBarColors(
                    containerColor = Color(0xFFFB2528),
                    titleContentColor = Color(0xFFFFFFFF),
                    navigationIconContentColor = Color(0xFFFFFFFF),
                    scrolledContainerColor = Color(0xFFFB2528),
                    actionIconContentColor = Color(0xFFFFFFFF),
                ),
                navigationIcon = {
                    Icon(
                        Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .clickable { onBackClick() }
                    )
                }
            )
            Spacer(modifier = modifier.padding(8.dp))
            ResultCard(
                result = result
            )
        }
    }
}