package com.shivamjsr18.workouttracker.services.impl

import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.shivamjsr18.workouttracker.model.User
import com.shivamjsr18.workouttracker.services.AccResult
import com.shivamjsr18.workouttracker.services.AccountService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class AccountServiceImpl: AccountService {
    private val firebaseAuth = Firebase.auth

    override val currentUserId: String
        get() = firebaseAuth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = firebaseAuth.currentUser!=null

    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener {auth->
                this.trySend(auth.currentUser?.let{currentUser->
                    User(
                        uid = currentUser.uid,
                        name = currentUser.displayName?:"",
                        email = currentUser.email?:"",
                        isAnonymous = currentUser.isAnonymous
                    )
                }?:User())
            }
            firebaseAuth.addAuthStateListener(listener)
            awaitClose{ firebaseAuth.removeAuthStateListener(listener) }
        }

    override suspend fun createEmailPassAccount(email: String, password: String, name: String) : AccResult {
        var result : AccResult = AccResult.Success
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{task->
                if(task.isSuccessful){
                    val profileUpdates = userProfileChangeRequest { displayName = name }
                    firebaseAuth.currentUser!!.updateProfile(profileUpdates)
                        .addOnCompleteListener{task2->
                            if(task2.isSuccessful) result=AccResult.Success
                            else result = AccResult.Failure(task2.exception?.message?:"")
                        }
                }
                else result = AccResult.Failure(task.exception?.message?:"")
            }.await()
        return result
    }

    override suspend fun authenticate(email: String, password: String) : AccResult {
        var result : AccResult = AccResult.Success
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{task->
                if(task.isSuccessful) result=AccResult.Success
                else result = AccResult.Failure(task.exception?.message?:"")
            }.await()
        return result
    }

    override suspend fun sendRecoveryEmail(email: String) : AccResult {
        var result : AccResult = AccResult.Success
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener{task->
                if(task.isSuccessful) result=AccResult.Success
                else result = AccResult.Failure(task.exception?.message?:"")
            }.await()
        return result
    }

    override suspend fun createAnonymousAccount() : AccResult {
        var result : AccResult = AccResult.Success
        firebaseAuth.signInAnonymously()
            .addOnCompleteListener{task->
                if(task.isSuccessful) result=AccResult.Success
                else result = AccResult.Failure(task.exception?.message?:"")
            }.await()
        return result
    }

    override suspend fun linkAccount(email: String, password: String) : AccResult {
        var result : AccResult = AccResult.Success
        if(firebaseAuth.currentUser!!.isAnonymous){
            val credentials = EmailAuthProvider.getCredential(email,password)
            firebaseAuth.currentUser!!.linkWithCredential(credentials)
                .addOnCompleteListener{task->
                    if(task.isSuccessful) result=AccResult.Success
                    else result = AccResult.Failure(task.exception?.message?:"")
                }.await()
        }
        else result=AccResult.Failure("Account is not anonymous")
        return result
    }

    override suspend fun deleteAccount(password:String) : AccResult {
        var result : AccResult = AccResult.Success
        val email =firebaseAuth.currentUser!!.email!!
        val credential = EmailAuthProvider.getCredential(email,password)
        firebaseAuth.currentUser!!.reauthenticate(credential)
            .addOnCompleteListener{task->
                if(task.isSuccessful){
                    firebaseAuth.currentUser!!.delete()
                        .addOnCompleteListener{task2->
                            if(task2.isSuccessful) result=AccResult.Success
                            else result = AccResult.Failure(task2.exception?.message?:"")
                        }
                }
                else result = AccResult.Failure(task.exception?.message?:"")
            }.await()
        return result
    }

    override suspend fun signOut() : AccResult {
        var result : AccResult = AccResult.Success
        if(hasUser){
            if(firebaseAuth.currentUser!!.isAnonymous){
                firebaseAuth.currentUser!!.delete()
                    .addOnCompleteListener{task->
                        if(task.isSuccessful) result=AccResult.Success
                        else result = AccResult.Failure(task.exception?.message?:"")
                    }
            }
            firebaseAuth.signOut()
        }
        return result
    }
}