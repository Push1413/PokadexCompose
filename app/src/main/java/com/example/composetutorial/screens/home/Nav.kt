package com.example.composetutorial.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composetutorial.model.BottomNavItem
import com.example.composetutorial.screens.home.fragments.ChatFragment
import com.example.composetutorial.screens.home.fragments.fetchPosts
import com.example.composetutorial.screens.home.fragments.settingsFragment
import com.example.composetutorial.utils.Constants

sealed class Screens(val route: String) {

    object MainScreen : Screens(Constants.HOME_ROUTE)
    object SettingsScreen : Screens(Constants.SETTINGS_ROUTE)
    object ChatScreen : Screens(Constants.CHAT_ROUTE)

}

@Composable
fun Navigation(navController: NavHostController, mainViewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(Screens.MainScreen.route) {
            fetchPosts(mainViewModel)
        }
        composable(Screens.ChatScreen.route) {
            ChatFragment()
        }
        composable(Screens.SettingsScreen.route) {
            settingsFragment()
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BottomNav(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,
                onClick = { onItemClick(item) },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (item.badgeCount > 0) {
                            BadgeBox(
                                badgeContent = {
                                    Text(text = item.badgeCount.toString())
                                }
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )
                            }
                        } else {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name
                            )
                        }
                        if (selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }

                    }

                }
            )

        }

    }

}