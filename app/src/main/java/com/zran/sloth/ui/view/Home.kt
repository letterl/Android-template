package com.zran.sloth.ui.view

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class Home {

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun ExperimentalAnimationNav() {

        val navController = rememberAnimatedNavController()
        AnimatedNavHost(navController, startDestination = "Blue") {
            composable(
                route = "Red",
                enterTransition = {
                    slideIntoContainer(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessMediumLow
                        ),towards = AnimatedContentScope.SlideDirection.Left
                    )
                },
                exitTransition = {
                    slideOutHorizontally()
                },
                content = {
                    RedScreen(navController)
                }
            )
            composable(
                route = "Blue",
                enterTransition = {
                    slideIntoContainer(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessMediumLow
                        ),towards = AnimatedContentScope.SlideDirection.Left
                    )
                },
                exitTransition = {
                    when (initialState.destination.route) {
                        "Blue" -> slideOutHorizontally()
                        "Green" -> slideOutHorizontally()
                        "Red" -> slideOutHorizontally()
                        else -> null
                    }
                },
                content = {
                    BlueScreen(navController)
                }
            )
        }
    }


    @ExperimentalAnimationApi
    @Composable
    fun AnimatedVisibilityScope.BlueScreen(navController: NavHostController) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Blue)
        ) {
            Spacer(Modifier.height(Dp(25f)))
            NavigateButton(
                "Navigate Horizontal",
                Modifier
                    .wrapContentWidth()
                    .then(Modifier.align(Alignment.CenterHorizontally))
            ) { navController.navigate("Red") }
            Spacer(Modifier.height(Dp(25f)))
            NavigateButton(
                "Navigate Expand",
                Modifier
                    .wrapContentWidth()
                    .then(Modifier.align(Alignment.CenterHorizontally))
            ) { navController.navigate("Inner") }
            Text(
                "Blue",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .animateEnterExit(
                        enter = fadeIn(animationSpec = tween(250, delayMillis = 450)),
                        exit = ExitTransition.None
                    ),
                color = Color.White, fontSize = 80.sp, textAlign = TextAlign.Center
            )
            NavigateBackButton(navController)
        }
    }

    @ExperimentalAnimationApi
    @Composable
    fun AnimatedVisibilityScope.RedScreen(navController: NavHostController) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Red)
        ) {
            Spacer(Modifier.height(Dp(25f)))
            NavigateButton(
                "Navigate Horizontal",
                Modifier
                    .wrapContentWidth()
                    .then(Modifier.align(Alignment.CenterHorizontally))
            ) { navController.navigate("Blue") }
            Spacer(Modifier.height(Dp(25f)))
            NavigateButton(
                "Navigate Vertical",
                Modifier
                    .wrapContentWidth()
                    .then(Modifier.align(Alignment.CenterHorizontally))
            ) { navController.navigate("Green") }
            Text(
                "Red",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .animateEnterExit(
                        enter = fadeIn(animationSpec = tween(250, delayMillis = 450)),
                        exit = ExitTransition.None
                    ),
                color = Color.White, fontSize = 80.sp, textAlign = TextAlign.Center
            )
            NavigateBackButton(navController)
        }
    }

    @Composable
    fun NavigateButton(
        text: String,
        modifier: Modifier = Modifier,
        listener: () -> Unit = { }
    ) {
        Button(
            onClick = listener,
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
            modifier = modifier
        ) {
            Text(text = text)
        }
    }


    @Composable
    fun NavigateBackButton(navController: NavController) {
        // Use LocalLifecycleOwner.current as a proxy for the NavBackStackEntry
        // associated with this Composable
        if (navController.currentBackStackEntry == LocalLifecycleOwner.current &&
            navController.previousBackStackEntry != null
        ) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Go to Previous screen")
            }
        }
    }








}