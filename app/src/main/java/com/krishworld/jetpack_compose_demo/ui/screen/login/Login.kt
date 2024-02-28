package com.krishworld.jetpack_compose_demo.ui.screen.login


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.krishworld.jetpack_compose_demo.R
import com.krishworld.jetpack_compose_demo.routes.Routes
import com.krishworld.jetpack_compose_demo.ui.theme.Purple40
import com.krishworld.jetpack_compose_demo.utils.dimensionTextResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString(stringResource(R.string.sign_up_here)),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(dimensionResource(R.dimen.m_surrounding_spacing)),
            onClick = { navController.navigate(Routes.SignUp.route) },
            style = TextStyle(
                fontSize = dimensionTextResource(R.dimen.text_size_16),
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple40
            )
        )
    }
    Column(
        modifier = Modifier.padding(dimensionResource(R.dimen.m_surrounding_spacing)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

        Text(
            text = stringResource(R.string.login),
            style = TextStyle(
                fontSize = dimensionTextResource(R.dimen.text_size_36),
                fontFamily = FontFamily.Serif
            )
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.m_vertical_spacing)))
        TextField(
            label = { Text(text = stringResource(R.string.username)) },
            value = username.value,
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = { username.value = it })

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.m_vertical_spacing)))
        TextField(
            label = { Text(text = stringResource(R.string.password)) },
            value = password.value,
            modifier = Modifier
                .fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.m_vertical_spacing)))
        Box(
            modifier = Modifier.padding(
                dimensionResource(R.dimen.m_horizontal_spacing),
                0.dp,
                dimensionResource(R.dimen.m_horizontal_spacing),
                0.dp
            )
        ) {
            Button(
                onClick = {
                    navController.navigate(Routes.Dashboard.route) {
                        popUpTo(Routes.Dashboard.route)
                    }
                },
                shape = RoundedCornerShape(dimensionResource(R.dimen.xxxl_surrounding_spacing)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.xxxl_vertical_spacing))
            ) {
                Text(text = stringResource(R.string.login))
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.m_vertical_spacing)))
        ClickableText(
            text = AnnotatedString(stringResource(R.string.forgot_password)),
            onClick = { navController.navigate(Routes.ForgotPassword.route) },
            style = TextStyle(
                fontSize = dimensionTextResource(R.dimen.text_size_16),
                fontFamily = FontFamily.Default
            )
        )
    }
}