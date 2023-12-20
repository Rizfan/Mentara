package com.rizfan.mentara.ui.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rizfan.mentara.R
import com.rizfan.mentara.ui.components.MainButton
import com.rizfan.mentara.ui.theme.MentaraTheme

@Composable
fun WelcomePage(
    modifier: Modifier = Modifier,
    navigateToLogin : () -> Unit
) {
    Box(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = R.drawable.draw_welcome),
                contentDescription = stringResource(R.string.welcome),
                modifier = modifier
                    .width(192.dp)
                    .height(333.dp)
                    .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
            )
            Text(
                text = stringResource(R.string.welcome),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                ),
                modifier = modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
            )
            Text(
                text = stringResource(R.string.welcoming_text),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                ),
                modifier = modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .width(260.dp)
            )
        }
        Column (
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            MainButton(
                text = stringResource(R.string.start),
                onClick = {navigateToLogin()},
                modifier = modifier
                    .padding(top = 16.dp, bottom = 16.dp)
            )
        }
    }

}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun WelcomePagePreview() {
    MentaraTheme{
        WelcomePage(
            navigateToLogin = {}
        )
    }
}
