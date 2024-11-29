package com.shivamjsr18.workouttracker.features.homeSection.ui.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.shivamjsr18.workouttracker.features.homeSection.ui.home.components.MuscleGroupCard
import com.shivamjsr18.workouttracker.features.homeSection.model.MuscleGroup
import com.shivamjsr18.workouttracker.ui.theme.WorkoutTrackerTheme

@Composable
fun HomeScreen(
//    viewModel: HomeViewModel
){
    HomeScreenContent(
        accountName = "Shivam",
        onAccountClicked = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    accountName: String,
    onAccountClicked: ()->Unit,
){
    val muscleGroups = MuscleGroup.entries
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onAccountClicked,
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Account",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                },
                title = {
                    Text(
                        text = "Hi, $accountName",
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            )
        }
    ){paddingValues->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(paddingValues)
        ) {
            item(span = { GridItemSpan(2) }){
                Row(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "What are we doing today ?",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(16.dp)
                            .fillMaxWidth()
                    )
                }
            }
            muscleGroups.forEach{ muscleGroup->
                item {
                    MuscleGroupCard(
                        muscleGroup = muscleGroup,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun HomeScreenContentPreview(){
    WorkoutTrackerTheme {
        Surface {
            HomeScreenContent(
                "Shivam",
                {}
            )
        }
    }
}