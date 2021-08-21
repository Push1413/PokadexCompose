package com.example.composetutorial.screens.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.example.composetutorial.R
import com.example.composetutorial.screens.login.LoginActivity
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                splash()
            }
        }
    }

    @Composable
    private fun splash() {
        val scale = remember {
            androidx.compose.animation.core.Animatable(1f)
        }
        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 1.9f,
                animationSpec = tween(
                    durationMillis = 500,
                    easing = {
                        OvershootInterpolator(3f).getInterpolation(it)
                    }
                )
            )
            delay(1000L)
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.scale(scale.value)
            )
        }
    }
}