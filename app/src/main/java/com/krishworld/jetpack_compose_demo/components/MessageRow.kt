package com.krishworld.jetpack_compose_demo.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.krishworld.jetpack_compose_demo.ui.theme.Purple80

@Composable
fun MessageRow(
    message: String,
    titleColor: Color = Color.White,
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Purple80),
        border = BorderStroke(1.dp, color = Color.Gray),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            NetworkImage(
                url = "https://3.bp.blogspot.com/-VVp3WvJvl84/X0Vu6EjYqDI/AAAAAAAAPjU/ZOMKiUlgfg8ok8DY8Hc-ocOvGdB0z86AgCLcBGAsYHQ/s1600/jetpack%2Bcompose%2Bicon_RGB.png"
            )
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = message,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = titleColor
                    ),
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
                Text(
                    text = message,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = titleColor
                    ),
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
            }

        }
    }
}

