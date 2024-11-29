package com.shivamjsr18.workouttracker.services

import com.shivamjsr18.workouttracker.features.homeSection.model.Exercise
import com.shivamjsr18.workouttracker.features.homeSection.model.ExerciseSession
import com.shivamjsr18.workouttracker.features.homeSection.model.MuscleGroup
import kotlinx.coroutines.flow.Flow

interface StorageService {
//
    suspend fun getExerciseList(muscleGroup: MuscleGroup) : Flow<List<Exercise>>
    suspend fun saveExercise(exercise: Exercise): StorageResult
    suspend fun updateExercise(exercise: Exercise): StorageResult
    suspend fun deleteExercise(exercise: Exercise): StorageResult

    suspend fun getExerciseSessionList(exercise: Exercise) : Flow<List<ExerciseSession>>
    suspend fun saveExerciseSession(exerciseSession: ExerciseSession): StorageResult
    suspend fun updateExerciseSession(exerciseSession: ExerciseSession): StorageResult
    suspend fun deleteExercise(exerciseSession: ExerciseSession): StorageResult

//    suspend fun getIndividualSetsOfExercise(exercise: Exercise): Flow<List<IndividualSet>>
//    suspend fun getLastIndividualSetOfExercise(exercise: Exercise): IndividualSet?
//    suspend fun saveIndividualSetOfExercise(exercise: Exercise,individualSet: IndividualSet) : String
//    suspend fun updateIndividualSetOfExercise(exercise: Exercise,individualSet: IndividualSet)
//    suspend fun deleteIndividualSetOfExercise(exercise: Exercise,individualSet: IndividualSet)
}

sealed class StorageResult{
    data object Success : StorageResult()
    data class Failure(val message : String) : StorageResult()
}