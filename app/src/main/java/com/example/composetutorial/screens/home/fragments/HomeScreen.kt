package com.example.composetutorial.screens.home.fragments

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.composetutorial.data.Post
import com.example.composetutorial.screens.home.MainViewModel
import com.example.composetutorial.utils.ApiState

@Composable
fun fetchPosts(viewModel: MainViewModel) {
    when (val result = viewModel.response.value) {
        is ApiState.Success -> {
            LazyColumn {
                items(result.data) { response ->
                    EachRow(response)
                }
            }
        }
        is ApiState.Failure -> {
            Text(text = "${result.msg}")
        }
        ApiState.Loading -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()

            ) {
                CircularProgressIndicator()
            }
        }
        ApiState.Empty -> {

        }
    }
}

@Composable
fun EachRow(post: Post) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(text = post.body, modifier = Modifier.padding(10.dp), fontStyle = FontStyle.Italic)
    }
}