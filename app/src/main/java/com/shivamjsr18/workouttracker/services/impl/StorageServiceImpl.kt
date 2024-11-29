package com.shivamjsr18.workouttracker.services.impl

import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.firestore
import com.shivamjsr18.workouttracker.features.homeSection.model.Exercise
import com.shivamjsr18.workouttracker.features.homeSection.model.ExerciseSession
import com.shivamjsr18.workouttracker.features.homeSection.model.MuscleGroup
import com.shivamjsr18.workouttracker.services.AccountService
import com.shivamjsr18.workouttracker.services.StorageResult
import com.shivamjsr18.workouttracker.services.StorageService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class StorageServiceImpl(private val accountService: AccountService): StorageService {
    private val firestore = Firebase.firestore


    override suspend fun getExerciseList(muscleGroup: MuscleGroup): Flow<List<Exercise>> {
        return firestore.collection(USER_COLLECTION)
            .document(accountService.currentUserId)
            .collection(MUSCLE_GROUP_COLLECTION)
            .document(muscleGroup.name)
            .collection(EXERCISE_COLLECTION)
            .orderBy(NAME_FIELD, Query.Direction.DESCENDING)
            .dataObjects()
    }

    override suspend fun saveExercise(exercise: Exercise): StorageResult {
        var result: StorageResult=StorageResult.Success
        firestore.collection(USER_COLLECTION)
            .document(accountService.currentUserId)
            .collection(MUSCLE_GROUP_COLLECTION)
            .document(exercise.muscleGroup)
            .collection(EXERCISE_COLLECTION)
            .add(exercise)
            .addOnCompleteListener{task->
                if(!task.isSuccessful) result = StorageResult.Failure(task.exception?.message?:"")
            }
            .await()
        return result
    }

    override suspend fun updateExercise(exercise: Exercise): StorageResult {
        var result: StorageResult=StorageResult.Success
        firestore.collection(USER_COLLECTION)
            .document(accountService.currentUserId)
            .collection(MUSCLE_GROUP_COLLECTION)
            .document(exercise.muscleGroup)
            .collection(EXERCISE_COLLECTION)
            .document(exercise.exerciseId)
            .set(exercise)
            .addOnCompleteListener{task->
                if(!task.isSuccessful) result = StorageResult.Failure(task.exception?.message?:"")
            }
            .await()
        return result
    }

    override suspend fun deleteExercise(exercise: Exercise): StorageResult {
        var result: StorageResult=StorageResult.Success
        firestore.collection(USER_COLLECTION)
            .document(accountService.currentUserId)
            .collection(MUSCLE_GROUP_COLLECTION)
            .document(exercise.muscleGroup)
            .collection(EXERCISE_COLLECTION)
            .document(exercise.exerciseId)
            .delete()
            .addOnCompleteListener{task->
                if(!task.isSuccessful) result = StorageResult.Failure(task.exception?.message?:"")
            }
            .await()
        return result
//        TODO("Do proper delete of sub-collections")
    }



    override suspend fun getExerciseSessionList(exercise: Exercise): Flow<List<ExerciseSession>> {
        return firestore.collection(USER_COLLECTION)
            .document(accountService.currentUserId)
            .collection(MUSCLE_GROUP_COLLECTION)
            .document(exercise.muscleGroup)
            .collection(EXERCISE_COLLECTION)
            .document(exercise.exerciseId)
            .collection(EXERCISE_SESSION_COLLECTION)
            .orderBy(NAME_FIELD, Query.Direction.DESCENDING)
            .dataObjects()
    }

    override suspend fun saveExerciseSession(exerciseSession: ExerciseSession): StorageResult {
        var result:StorageResult = StorageResult.Success
        firestore.collection(USER_COLLECTION)
            .document(accountService.currentUserId)
            .collection(MUSCLE_GROUP_COLLECTION)
            .document(exerciseSession.muscleGroup)
            .collection(EXERCISE_COLLECTION)
            .document(exerciseSession.exerciseId)
            .collection(EXERCISE_SESSION_COLLECTION)
            .add(exerciseSession)
            .addOnCompleteListener{task->
                if(!task.isSuccessful) result = StorageResult.Failure(task.exception?.message?:"")
            }
            .await()
        return result
    }

    override suspend fun updateExerciseSession(exerciseSession: ExerciseSession): StorageResult {
        var result:StorageResult = StorageResult.Success
        firestore.collection(USER_COLLECTION)
            .document(accountService.currentUserId)
            .collection(MUSCLE_GROUP_COLLECTION)
            .document(exerciseSession.muscleGroup)
            .collection(EXERCISE_COLLECTION)
            .document(exerciseSession.exerciseId)
            .collection(EXERCISE_SESSION_COLLECTION)
            .document(exerciseSession.exerciseSessionId)
            .set(exerciseSession)
            .addOnCompleteListener{task->
                if(!task.isSuccessful) result = StorageResult.Failure(task.exception?.message?:"")
            }
            .await()
        return result
    }

    override suspend fun deleteExercise(exerciseSession: ExerciseSession): StorageResult {
        var result:StorageResult = StorageResult.Success
        firestore.collection(USER_COLLECTION)
            .document(accountService.currentUserId)
            .collection(MUSCLE_GROUP_COLLECTION)
            .document(exerciseSession.muscleGroup)
            .collection(EXERCISE_COLLECTION)
            .document(exerciseSession.exerciseId)
            .collection(EXERCISE_SESSION_COLLECTION)
            .document(exerciseSession.exerciseSessionId)
            .delete()
            .addOnCompleteListener{task->
                if(!task.isSuccessful) result = StorageResult.Failure(task.exception?.message?:"")
            }
            .await()
        return result
    }


    companion object{
        private const val USER_COLLECTION = "UserCollection"
        private const val MUSCLE_GROUP_COLLECTION = "MuscleGroupCollection"
        private const val EXERCISE_COLLECTION = "ExerciseCollection"
        private const val EXERCISE_SESSION_COLLECTION = "ExerciseSessionCollection"
        private const val NAME_FIELD = "name"
    }


}