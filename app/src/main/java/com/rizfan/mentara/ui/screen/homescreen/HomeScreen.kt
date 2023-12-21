package com.rizfan.mentara.ui.screen.homescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.MonitorHeart
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
    navigateToWelcome: @Composable (() -> Unit),
    navigateToChatbot: () -> Unit = {}
) {
    viewModel.uiState.collectAsState(UiState.Loading).value.let {
        when(it){
            is UiState.Success ->{
                if (it.data.isLogin){
                    HomeContent(
                        modifier = modifier,
                        user = it.data,
                        onTopUpButtonClicked = onTopUpButtonClicked,
                        onLogoutClick = {
                            viewModel.logout()
                        },
                        navigateToChatbot = navigateToChatbot
                    )
                }else{
                    navigateToWelcome()
                }
            }
            is UiState.Loading ->{
                viewModel.getUser()
            }
            is UiState.Error ->{
                navigateToWelcome()
            }
        }
    }
}


@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    user : UserModel,
    onTopUpButtonClicked: () -> Unit,
    onLogoutClick: () -> Unit = {},
    navigateToChatbot: () -> Unit = {}
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
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
            }
            IconButton(onClick = onLogoutClick) {
                Icon(
                    Icons.AutoMirrored.Filled.Logout,
                    contentDescription = stringResource(R.string.menu_logout)
                )
            }
        }

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


//        Stress Level
        ElevatedCard(
            modifier = modifier
                .fillMaxWidth()
                .height(85.dp)
                .padding(bottom = 16.dp),
            colors = CardColors(
                containerColor =  Color(0xFF6386FF),
                contentColor = Color(0xFFFFFFFF),
                disabledContainerColor =  Color(0xFF6386FF),
                disabledContentColor = Color(0xFFFFFFFF),
            ),
        ) {
            Row(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(horizontal=16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    Icons.Outlined.MonitorHeart,
                    contentDescription = stringResource(R.string.stress_level),
                    modifier = modifier.size(40.dp)
                )
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
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

//        MentalBot
        ElevatedCard(
            modifier = modifier
                .fillMaxWidth()
                .height(85.dp)
                .padding(bottom = 16.dp)
                .clickable(
                    onClick = navigateToChatbot
                ),
            colors = CardColors(
                containerColor =  Color(0xFFFAC05E),
                contentColor = Color(0xFFFFFFFF),
                disabledContainerColor =  Color(0xFFFAC05E),
                disabledContentColor = Color(0xFFFFFFFF),
            ),
        ) {
            Row(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(horizontal=16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    Icons.Outlined.Forum,
                    contentDescription = stringResource(R.string.mentalbot),
                    modifier = modifier.size(40.dp)
                )
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.mentalbot),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                        ),
                        modifier = modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = stringResource(R.string.mentalbot_description),
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
        HomeContent(
            user = UserModel(
                name = "Rizfan",
                balance = 100000,
                isLogin = true
            ),
            onTopUpButtonClicked = {}
        )
    }
}