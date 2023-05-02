package com.secui.healthone.ui.common

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.secui.healthone.R
import com.secui.healthone.compose.MealPlanPage
import com.secui.healthone.compose.OverViewPage
import com.secui.healthone.compose.*
import com.secui.healthone.compose.Challenge.PopularDetailPage
import com.secui.healthone.compose.MealPlan.ExerciseInputPage
import com.secui.healthone.compose.MealPlan.MealInputPage
import com.secui.healthone.compose.sleep.SleepPage
import com.secui.healthone.util.PageRoutes


@Composable
fun DrawerButton(text: String, icon: Int? = null, onClick: () -> Unit) {
    val textColor = AppColors.black
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 16.dp)) {
            if (icon != null) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text,
                fontSize = 16.sp,
                color = textColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TopBar() {
    val navController = rememberNavController()
    val menuOpen = remember { mutableStateOf(false) } // 메뉴 상태를 기억하는 변수
    val menuOffset = animateDpAsState(if (menuOpen.value) 0.dp else 200.dp) // 애니메이션 상태

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column {
                TopAppBar(
                    title = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_topbar_logo),
                            contentDescription = "logo",
                            modifier = Modifier.size(60.dp)
                        )
                    },
                    backgroundColor = AppColors.white,
                    actions = {
                        IconButton(onClick = {
                            menuOpen.value = !menuOpen.value
                        }) { // 상태를 업데이트하여 메뉴를 열고 닫습니다.
                            Icon(
                                painter = painterResource(id = R.drawable.ic_topbar_toggle),
                                contentDescription = "Menu",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                )
            }
            Column {
                NavHost(navController, startDestination = PageRoutes.OverView.route) {
                    composable(PageRoutes.OverView.route) {
                        OverViewPage(navController = navController)
                    }
                    composable(PageRoutes.MealPlan.route) {
                        MealPlanPage(navController = navController) // 식단 화면
                    }
                    composable(PageRoutes.HeartRate.route) {
                        HeartRatePage(navController = navController) // 심박수 측정
                    }
                    composable(PageRoutes.Challenge.route) {
                        ChallengePage(navController = navController) // 챌린지
                    }
                    composable(PageRoutes.Alert.route) {
                        AlertPage(navController = navController) /// 알림
                    }
                    composable(PageRoutes.HeartMeasure.route) {
                        HeartMeasurePage(navController)
                    }
                    composable(PageRoutes.MealInput.route) {
                        MealInputPage(navController)
                    }
                    composable(PageRoutes.ExerciseInput.route) {
                        ExerciseInputPage(navController)
                    }
                    composable(PageRoutes.Sleep.route) {
                        SleepPage(navController = navController)
                    }
                    composable(PageRoutes.My.route) {
                        MyPage(navController = navController)
                    }
                    composable(PageRoutes.Walking.route) {
                        WalkingPage(navController)
                    }
                    composable(PageRoutes.WalkingDetail.route) {
                        WalkingDetailPage(navController)
                    }
                    composable(PageRoutes.PopularDetail.route) {
                        PopularDetailPage(navController)
                    }
                    composable(PageRoutes.Setting.route) {
                        SettingPage(navController = navController)
                    }
                }
            }
        }
            Column(
                modifier = Modifier
                    .offset(x = menuOffset.value) // 애니메이션 값을 적용
                    .width(200.dp)
                    .fillMaxHeight()
                    .background(colorResource(id = R.color.white))
                    .align(Alignment.TopEnd)
            ) {
                DrawerButton(
                    text = "내 페이지",
                    onClick = {
                        navController.navigate(PageRoutes.My.route)
                    }
                )
                Divider(color = AppColors.black, thickness = 1.dp)
                DrawerButton(
                    text = "메인",
                    onClick = {
                        navController.navigate(PageRoutes.OverView.route)
                    }
                )
                Divider(color = AppColors.black, thickness = 1.dp)
                DrawerButton(
                    text = "심박수",
                    icon = R.drawable.ic_heart,
                    onClick = {
                        navController.navigate(PageRoutes.HeartRate.route)
                    }
                )
                Divider(color = AppColors.black, thickness = 1.dp)
                DrawerButton(
                    text = "식단",
                    icon = R.drawable.ic_food,
                    onClick = {
                        navController.navigate(PageRoutes.MealPlan.route)
                    }
                )
                Divider(color = AppColors.black, thickness = 1.dp)
                DrawerButton(
                    text = "수면",
                    icon = R.drawable.ic_sleep,
                    onClick = {
                        navController.navigate(PageRoutes.Sleep.route)
                    }
                )
                Divider(color = AppColors.black, thickness = 1.dp)
                DrawerButton(
                    text = "걸음수",
                    icon = R.drawable.ic_walking,
                    onClick = {
                        navController.navigate(PageRoutes.Walking.route)
                    }
                )
                Divider(color = AppColors.black, thickness = 1.dp)
                DrawerButton(
                    text = "심박 수 측정",
                    onClick = {
                        navController.navigate(PageRoutes.HeartRate.route)
                    }
                )
                Divider(color = AppColors.black, thickness = 1.dp)
                DrawerButton(
                    text = "챌린지 페이지",
                    onClick = {
                        navController.navigate(PageRoutes.Challenge.route)
                    }
                )
                DrawerButton(
                    text = "알림 페이지",
                    onClick = {
                        navController.navigate(PageRoutes.Alert.route)
                    }
                )
                Divider(color = AppColors.black, thickness = 1.dp)
                DrawerButton(
                    text = "설정",
                    onClick = {
                        navController.navigate(PageRoutes.Setting.route)
                    }
                )
                Divider(color = AppColors.black, thickness = 1.dp)
            }

        }
    }