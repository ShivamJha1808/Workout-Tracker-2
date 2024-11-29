package com.shivamjsr18.workouttracker.features.homeSection.ui.exercise

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shivamjsr18.workouttracker.features.homeSection.model.Exercise
import com.shivamjsr18.workouttracker.features.homeSection.model.ExerciseSession
import com.shivamjsr18.workouttracker.features.homeSection.model.MuscleGroup

@Composable
fun ExerciseScreen(
    viewModel: ExerciseViewModel,
    muscleGroup: MuscleGroup,
    exercise: Exercise
){
    val currExerciseSession = ExerciseSession()
}

@Composable
fun ExerciseScreenContents(
    muscleGroup: MuscleGroup,
    exercise: Exercise,
    exerciseSession: ExerciseSession
){
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

        }

    }
}