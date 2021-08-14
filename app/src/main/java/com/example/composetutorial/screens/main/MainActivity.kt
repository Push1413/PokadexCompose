package com.example.composetutorial.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.data.Post
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import com.example.composetutorial.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    private val mainViewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                mainMethod()
            }
        }
    }

    @Composable
    private fun mainMethod() {
        GETData(mainViewModel = mainViewModel)
    }

    @Composable
    fun EachRow(post: Post){
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            elevation = 2.dp,
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(text = post.body,modifier = Modifier.padding(10.dp),fontStyle = FontStyle.Italic)
        }
    }

    @Composable
    fun GETData(mainViewModel: MainViewModel){
        when(val result = mainViewModel.response.value){
            is ApiState.Success->{
                LazyColumn{
                    items(result.data){ response->
                        EachRow( response)
                    }
                }
            }
            is ApiState.Failure->{
                Text(text = "${result.msg}")
            }
            ApiState.Loading->{
                CircularProgressIndicator()
            }
            ApiState.Empty->{

            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ComposeTutorialTheme {
            mainMethod()
        }
    }
}