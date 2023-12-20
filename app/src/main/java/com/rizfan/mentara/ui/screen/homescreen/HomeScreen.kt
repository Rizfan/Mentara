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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rizfan.mentara.R
import com.rizfan.mentara.data.model.UserModel
import com.rizfan.mentara.ui.common.UiState
import com.rizfan.mentara.ui.components.BalanceCard
import com.rizfan.mentara.ui.theme.MentaraTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onTopUpButtonClicked: () -> Unit,
    navigateToWelcome: () -> Unit = {}
) {
    viewModel.uiState.collectAsState(UiState.Loading).value.let {
        when(it){
            is UiState.Loading ->{
                viewModel.getUser()
            }
            is UiState.Success ->{
                HomeContent(
                    modifier = modifier,
                    user = it.data,
                    onTopUpButtonClicked = onTopUpButtonClicked,
                    onLogoutClick = {
                        viewModel.logout()
                        navigateToWelcome()
                    }
                )
            }
            is UiState.Error ->{
                navigateToWelcome()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    user : UserModel,
    onTopUpButtonClicked: () -> Unit,
    onLogoutClick: () -> Unit = {}
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.greatings, user.name),
            style = TextStyle(
                fontSize = 26.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF000000),
            ),
            modifier = modifier.padding(top = 16.dp)
        )
        Text(
            text = stringResource(R.string.how_are_you),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF848484),
            ),
            modifier = modifier.padding(bottom = 16.dp)
        )

        BalanceCard(
            balance = user.balance.toString(),
            onTopUpClick = onTopUpButtonClicked,
        )

        Text(
            text = stringResource(R.string.check_your_self_here),
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
                    contentDescription = stringResource(R.string.mental_health)
                )
                Column(modifier = modifier) {
                    Text(
                        text = stringResource(R.string.stress_level),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                        ),
                        modifier = modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = stringResource(R.string.check_your_stress_level_here),
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
        HomeScreen(
            onTopUpButtonClicked = {}
        )
    }
}