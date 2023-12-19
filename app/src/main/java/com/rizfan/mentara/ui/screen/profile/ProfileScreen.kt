package com.rizfan.mentara.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rizfan.mentara.R
import com.rizfan.mentara.data.model.UserModel
import com.rizfan.mentara.ui.common.UiState
import com.rizfan.mentara.ui.components.PersonalInformationCard
import com.rizfan.mentara.ui.theme.MentaraTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
        when(it){
            is UiState.Loading ->{
                viewModel.getUser()
            }
            is UiState.Success ->{
                ProfileContent(
                    modifier = modifier,
                    user = it.data
                )
            }
            is UiState.Error ->{}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    user : UserModel
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = stringResource(R.string.menu_profile),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                )
            })
            Spacer(modifier = modifier.height(32.dp))
            Image(
                painter = painterResource(id = R.drawable.profil),
                contentDescription = "image description",
                modifier = modifier
                    .width(190.dp)
                    .height(190.dp)
            )
            Spacer(modifier = modifier.height(32.dp))
            PersonalInformationCard(
                user = user,
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_5, showSystemUi = true)
fun ProfileScreenPreview() {
    MentaraTheme {
        ProfileScreen()
    }
}