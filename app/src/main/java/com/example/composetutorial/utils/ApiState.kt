package com.example.composetutorial.utils

import com.example.composetutorial.data.Post

sealed class ApiState{

    class Success(val data:List<Post> ) : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    object Loading : ApiState()
    object Empty : ApiState()
}
