package com.shivamjsr18.workouttracker.features.homeSection.model

import com.google.firebase.firestore.DocumentId

data class ExerciseSession(
    @DocumentId val exerciseSessionId : String = "",
    val exerciseId : String = "",
    val muscleGroup : String = "",
    val date : String = "",
    val set1: Double = 0.0,
    val set2: Double = 0.0,
    val set3: Double = 0.0,
    val remarks: String?=null
)
