package com.shivamjsr18.workouttracker.services

import com.shivamjsr18.workouttracker.model.User
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: String
    val hasUser: Boolean

    val currentUser: Flow<User>

    suspend fun authenticate(email: String, password: String) : AccResult
    suspend fun createEmailPassAccount(email: String, password: String,name: String) : AccResult
    suspend fun sendRecoveryEmail(email: String) : AccResult
    suspend fun createAnonymousAccount() : AccResult
    suspend fun linkAccount(email: String, password: String) : AccResult
    suspend fun deleteAccount(password:String) : AccResult
    suspend fun signOut() : AccResult
}


sealed class AccResult{
    data object Success : AccResult()
    data class Failure(val message:String) : AccResult()
}