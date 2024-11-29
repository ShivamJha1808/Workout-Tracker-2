package com.shivamjsr18.workouttracker.features.homeSection.model

import com.google.firebase.firestore.DocumentId

data class Exercise(
    @DocumentId val exerciseId:String="",
    val muscleGroup:String="",
    val name: String =""
)
