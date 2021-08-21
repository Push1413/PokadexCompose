package com.example.composetutorial.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import com.example.composetutorial.model.BottomNavItem

object DataProvider {

    fun getBottomList():List<BottomNavItem>{
        return listOf(
            BottomNavItem(
                name = Constants.HOME_TITLE,
                route = Constants.HOME_ROUTE,
                icon = Icons.Default.Home,
                badgeCount = 0
            ),
            BottomNavItem(
                name = Constants.CHAT_TITLE,
                route = Constants.CHAT_ROUTE,
                icon = Icons.Default.Notifications,
                badgeCount = 2
            ),
            BottomNavItem(
                name = Constants.SETTINGS_TITLE,
                route = Constants.SETTINGS_ROUTE,
                icon = Icons.Default.Settings,
                badgeCount = 0
            )
        )
    }
}