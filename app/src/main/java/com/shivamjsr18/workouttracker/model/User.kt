package com.shivamjsr18.workouttracker.model

data class User(
    val uid: String = "",
    val name:String = "",
    val email: String = "",
    val isAnonymous: Boolean = true,
)