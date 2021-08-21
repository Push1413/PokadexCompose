package com.example.composetutorial.screens.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetutorial.R
import com.example.composetutorial.screens.home.MainActivity
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
    fun mainMethod() {
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
    fun LoginScreen() {
        //rememberSaveable to survive configuration change
        val username = rememberSaveable {
            mutableStateOf("")
        }
        val password = rememberSaveable {
            mutableStateOf("")
        }

        var isError = rememberSaveable { mutableStateOf(false) }
        var errorMsg = rememberSaveable { mutableStateOf("") }

        fun validateInputs() {
            if (password.value.isEmpty()) {
                isError.value = true
                errorMsg.value = "Password is Empty"
            }
            if (password.value.isNotEmpty()) {
                isError.value = false
                navigateToMain()
            }
        }

        @Composable
        fun setTextColor(): Color {
            return if (colors.isLight) {
                Color(0xFF000000)
            } else Color(0xFFFFFFFF)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            if (username.value.isEmpty()) {
                Text(
                    text = "Hello Again!", fontSize = 25.sp, color = Color.Blue,
                    fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold
                )
            } else {
                Text(
                    text = "Hello ${username.value}!", fontSize = 25.sp, color = colors.primary,
                    fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "Welcome", fontSize = 25.sp, color = colors.primary,
                fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold
            )
            Text(
                text = "Back", fontSize = 25.sp, color = colors.primary,
                fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold
            )
            OutlinedTextField(
                value = username.value,
                onValueChange = {
                    username.value = it
                },
                label = {
                    Text(text = stringResource(R.string.user_name))
                },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = "person")
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = stringResource(R.string.user_placeholder))
                },
                singleLine = true,
                textStyle = TextStyle(color = setTextColor()),
            )

            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                label = {
                    Text(text = stringResource(R.string.pass_label))
                },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "lock")
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = stringResource(R.string.pass_placeholder))
                },
                trailingIcon = {
                    if (isError.value) {
                        Icon(Icons.Default.Error, contentDescription = "error")
                    }
                },
                isError = isError.value,
                singleLine = true,
                textStyle = TextStyle(color = setTextColor()),
                visualTransformation = PasswordVisualTransformation(mask = '\u2022')
            )

            if (isError.value) {
                Text(
                    text = errorMsg.value,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            OutlinedButton(
                onClick = { validateInputs() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = setTextColor()
                )
            ) {
                Text(
                    text = stringResource(R.string.login),
                    modifier = Modifier
                        .padding(10.dp)
                )
            }

        }
    }


    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}