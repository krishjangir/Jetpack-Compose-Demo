package com.krishworld.jetpack_compose_demo.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.krishworld.jetpack_compose_demo.R
import com.krishworld.jetpack_compose_demo.ui.theme.Purple40
import com.krishworld.jetpack_compose_demo.ui.theme.Purple80
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerView(drawerState: DrawerState, scope: CoroutineScope) {
    val settings = listOf("Edit Profile", "Logout")
    val support = listOf("Privacy & Policy", "About Us")
    LazyColumn(
        modifier = Modifier
            .width(350.dp)
            .fillMaxHeight()
            .background(color = Color.White)
    ) {
        item {
            AddHeader(
                drawerState = drawerState,
                scope = scope,
                title = "Krishan Jangir",
                desc = "Software Engineer"
            )
        }
        item {
            AddDrawerHeader(title = "Settings")
        }
        items(settings.size) { index ->
            AddDrawerContentView(
                title = settings[index]
            )
        }
        item {
            AddDrawerHeader(title = "Help & Support")
        }
        items(support.size) { index ->
            AddDrawerContentView(
                title = support[index]
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHeader(
    drawerState: DrawerState,
    scope: CoroutineScope,
    title: String,
    desc: String,
    titleColor: Color = Color.White,
) {
    Box(
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Purple80),
            border = BorderStroke(1.dp, color = Color.Gray),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painterResource(R.drawable.ic_notification),
                    modifier = Modifier
                        .padding(14.dp)
                        .size(100.dp)
                        .clip(CircleShape) // clip to the circle shape
                        .border(5.dp, Color.White, CircleShape)
                        .padding(14.dp),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.Red),
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = title,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = titleColor
                        ),
                        modifier = Modifier.padding(horizontal = 14.dp)
                    )
                    Text(
                        text = desc,
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
        IconButton(
            onClick = {
                scope.launch {
                    drawerState.close()
                }
            },
            modifier = Modifier
                .clip(CircleShape)
                .background(Purple80)
                .align(Alignment.TopEnd)
                .size(32.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = "",
                // tint = Color.White
            )
        }
    }
}

@Composable
fun AddDrawerContentView(title: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {}
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        if (title.isNotEmpty()) {
            Text(text = title, modifier = Modifier.weight(1f), fontSize = 14.sp)
        }

    }
}

@Composable
fun AddDrawerHeader(
    title: String,
    titleColor: Color = Purple40,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        border = BorderStroke(1.dp, color = Color.Gray),
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = titleColor
            ),
            modifier = Modifier.padding(14.dp)
        )

    }
}