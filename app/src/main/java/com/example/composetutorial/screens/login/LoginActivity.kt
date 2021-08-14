package com.example.composetutorial.screens.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetutorial.screens.main.MainActivity
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                mainMethod()
            }
        }
    }


    @Composable
    fun mainMethod(){
//        ShowSwitch()
        LoginScreen()
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ComposeTutorialTheme {
            mainMethod()
        }
    }

    @Composable
    fun ShowSwitch() {

        val isChecked = remember {
            mutableStateOf(true)
        }

        Switch(
            checked = isChecked.value,
            onCheckedChange = {
                isChecked.value = it
            },
            modifier = Modifier.run {
                size(20.dp)
                padding(100.dp)
            }
        )

    }

    @Composable
    fun LoginScreen(){
        val username = remember {
            mutableStateOf("")
        }
        val password = remember {
            mutableStateOf("")
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                text = "Hello Again!", fontSize = 25.sp, color = Color.Blue,
                fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold
            )
            Text(
                text = "Welcome", fontSize = 25.sp, color = Color.Blue,
                fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold
            )
            Text(
                text = "Back", fontSize = 25.sp, color = Color.Blue,
                fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold
            )
            OutlinedTextField(
                value = username.value,
                onValueChange ={
                    username.value = it
                },
                label = {
                    Text(text = "Username")
                },
                leadingIcon ={
                    Icon(Icons.Default.Person, contentDescription ="person" )
                },
                modifier =Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "enter username")
                }
            )

            OutlinedTextField(
                value = password.value,
                onValueChange ={
                    password.value = it
                },
                label = {
                    Text(text = "Password")
                },
                leadingIcon ={
                    Icon(Icons.Default.Info, contentDescription ="info" )
                },
                modifier =Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "enter password")
                }
            )

            OutlinedButton(
                onClick = { navigateToMain()},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Text(text = "Login")
            }
            
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}