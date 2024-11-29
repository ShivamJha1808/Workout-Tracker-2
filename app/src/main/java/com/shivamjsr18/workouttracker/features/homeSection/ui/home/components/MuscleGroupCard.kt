package com.shivamjsr18.workouttracker.features.homeSection.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shivamjsr18.workouttracker.features.homeSection.model.MuscleGroup
import com.shivamjsr18.workouttracker.ui.theme.WorkoutTrackerTheme

@Composable
fun MuscleGroupCard(muscleGroup: MuscleGroup, modifier: Modifier = Modifier){
    Card(
        modifier=modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            contentColor = MaterialTheme.colorScheme.onSurface,
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = muscleGroup.name,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MuscleGroupCardPreview(){
    WorkoutTrackerTheme {
        Column(
            Modifier.fillMaxSize()
        ) {
            Spacer(Modifier.height(54.dp))
            MuscleGroupCard(MuscleGroup.Forearms)
        }
    }
}