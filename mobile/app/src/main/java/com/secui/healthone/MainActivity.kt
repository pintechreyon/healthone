package com.secui.healthone

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.secui.healthone.compose.DataCollectFirstPage
import com.secui.healthone.compose.DataCollectSecondPage
import com.secui.healthone.compose.LoginPage
import com.secui.healthone.compose.OverViewPage
import com.secui.healthone.ui.common.TopBar
import com.secui.healthone.util.PageRoutes

class MainActivity : ComponentActivity() {

    val APP_TAG = "SimpleHealth"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sharedPreferences = getSharedPreferences("healthone", Context.MODE_PRIVATE)
            val jwtToken = sharedPreferences.getString("jwt_token", null)

            val navController = rememberNavController()
            val context = LocalContext.current;

            NavHost(navController, startDestination = if (jwtToken != null) PageRoutes.Login.route else PageRoutes.OverView.route) {
                // 원래 코드는 jwtToken == null

                composable(PageRoutes.Login.route){
                    LoginPage(navController)
                }
                composable(PageRoutes.OverView.route) {
                    Column {
                        TopBar()
                        OverViewPage(navController)
                    }
                }
                composable(PageRoutes.DataCollectFirst.route){
                    DataCollectFirstPage(navController)
                }
                composable(PageRoutes.DataCollectSecond.route){
                    DataCollectSecondPage(navController)
                }
            }
        }
    }
}