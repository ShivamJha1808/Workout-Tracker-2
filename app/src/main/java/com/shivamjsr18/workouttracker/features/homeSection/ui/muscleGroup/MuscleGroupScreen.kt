package com.shivamjsr18.workouttracker.features.homeSection.ui.muscleGroup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shivamjsr18.workouttracker.features.homeSection.model.Exercise
import com.shivamjsr18.workouttracker.features.homeSection.model.MuscleGroup
import com.shivamjsr18.workouttracker.features.homeSection.ui.muscleGroup.components.ExerciseCard
import com.shivamjsr18.workouttracker.ui.theme.WorkoutTrackerTheme

@Composable
fun MuscleGroupScreen(
    viewModel: MuscleGroupViewModel,
    muscleGroup: MuscleGroup,
    onExerciseClick: (muscleGroup:MuscleGroup,exercise:Exercise)->Unit,
    onBackClick: ()->Unit,
    onHistoryClick: (muscleGroup:MuscleGroup)->Unit,
){
    val exerciseList: List<Exercise> = listOf(Exercise("dummy"))
    MuscleGroupScreenContents(
        muscleGroup = muscleGroup,
        exerciseList = exerciseList,
        onExerciseClick = onExerciseClick,
        onBackClick = onBackClick,
        onHistoryClick = onHistoryClick,
        {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MuscleGroupScreenContents(
    muscleGroup: MuscleGroup,
    exerciseList: List<Exercise>,
    onExerciseClick: (muscleGroup:MuscleGroup,exercise:Exercise)->Unit,
    onBackClick: ()->Unit,
    onHistoryClick: (muscleGroup:MuscleGroup)->Unit,
    onAddExercise: ()->Unit,
){
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate Back"
                        )
                    }
                },
                title = {
                    Text(
                        text = muscleGroup.name,
                        style = MaterialTheme.typography.headlineLarge
                    )
                },
                actions = {
                    IconButton(
                        onClick = {onHistoryClick(muscleGroup)}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Go to History"
                        )
                    }
                }
            )
        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = onAddExercise
//            ) {
////                Row(
////                    modifier = Modifier.padding(horizontal = 16.dp),
////                    verticalAlignment = Alignment.CenterVertically
////                ) {
////                    Text(
////                        text = "Add",
////                        fontSize = 24.sp,
////                        fontWeight = FontWeight.Bold
////                    )
////                    Icon(
////                        imageVector = Icons.Default.Add,
////                        contentDescription = "Add exercise"
////                    )
////                }
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Add exercise"
//                )
//            }
//        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(exerciseList){exercise ->
                ExerciseCard(
                    exercise= exercise,
                    lastDay = "2024/11/23",
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable {
                            onExerciseClick(muscleGroup,exercise)
                        }
                )
            }
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Button(
                        onClick = onAddExercise,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "Add")
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add exercise"
                        )
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun MuscleGroupScreenContentsPreview(){
    WorkoutTrackerTheme {
        MuscleGroupScreenContents(
            MuscleGroup.Biceps,
            listOf(Exercise("Curls"),Exercise("Hammer")),
            { _:MuscleGroup, _:Exercise->},
            {},
            {},
            {}
        )
    }
}