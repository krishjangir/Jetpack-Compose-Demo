package com.krishworld.jetpack_compose_demo.routes

import androidx.compose.runtime.Composable
import androidx.media3.common.util.UnstableApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.krishworld.jetpack_compose_demo.ui.screen.dashboard.Dashboard
import com.krishworld.jetpack_compose_demo.ui.screen.forgotpassword.ForgotPassword
import com.krishworld.jetpack_compose_demo.ui.screen.login.LoginPage
import com.krishworld.jetpack_compose_demo.ui.screen.signup.SignUp

@UnstableApi
@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginPage(navController = navController)
        }

        composable(Routes.SignUp.route) {
            SignUp(navController = navController)
        }

        composable(Routes.ForgotPassword.route) {
            ForgotPassword(navController = navController)
        }

        composable(Routes.Dashboard.route) {
            Dashboard(navController = navController)
        }
    }
}