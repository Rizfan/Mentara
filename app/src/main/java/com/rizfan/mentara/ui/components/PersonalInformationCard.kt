package com.rizfan.mentara.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rizfan.mentara.R
import com.rizfan.mentara.data.model.UserModel
import com.rizfan.mentara.ui.theme.md_theme_light_primaryContainer

@Composable
fun PersonalInformationCard(
    user: UserModel,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier){
        ElevatedCard(
            modifier = Modifier
                .width(297.dp)
                .background(
                    color = md_theme_light_primaryContainer,
                    shape = RoundedCornerShape(size = 10.dp)
                )
        ) {
            Column{
                Text(
                    text = stringResource(R.string.personal_infomation), style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFB2528),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.3.sp,
                    ),
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
                Row(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                ) {
                    Text(
                        text = "Name",
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.Start)
                            .padding(bottom = 16.dp)
                    )
                    Text(
                        text = user.name,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.End)
                            .padding(bottom = 16.dp),
                        textAlign = TextAlign.End
                    )
                }
                Row(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                ) {
                    Text(
                        text = "Email",
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.Start)
                            .padding(bottom = 16.dp)
                    )
                    Text(
                        text = user.email,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.End)
                            .padding(bottom = 16.dp),
                        textAlign = TextAlign.End
                    )
                }
                Row(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                ) {
                    Text(
                        text = "Phone",
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.Start)
                            .padding(bottom = 16.dp)
                    )
                    Text(
                        text = user.noTelp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.End)
                            .padding(bottom = 16.dp),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, device = "id:pixel_5")
fun PersonalInformationCardPreview() {
    PersonalInformationCard(
        user = UserModel(
            "Rizfan",
            "Rizfan",
            "927329",
            3,
            email = "ueibraas"
        )
    )
}