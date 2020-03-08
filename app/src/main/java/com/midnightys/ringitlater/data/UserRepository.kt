package com.midnightys.ringitlater.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.midnightys.ringitlater.model.User
import com.midnightys.status.*
import com.midnightys.toolboxapp.addOnTaskStatusChangeFlow
import com.midnightys.toolboxapp.toSingle
import kotlinx.coroutines.flow.*
import timber.log.Timber


/**
 * Created by Kort on 2020/3/8.
 */
typealias FirebaseUser = com.google.firebase.auth.FirebaseUser

interface UserRepositoryProvider {
    fun getFirebaseUserOnce(): Flow<Status<FirebaseUser>>
    fun getUserOnce(): Flow<Status<User>>
    fun createNewUserDocument(): Flow<Status<Unit>>
}

class UserRepository(private val auth: FirebaseAuth, private val firestore: FirebaseFirestore) :
    UserRepositoryProvider {

    override fun getFirebaseUserOnce(): Flow<Status<FirebaseUser>> = statusFlow {
        val firebaseUser = auth.currentUser
        if (firebaseUser != null) emit(Success(firebaseUser))
        else emit(Failure(UserIsNullException()))
    }

    override fun getUserOnce(): Flow<Status<User>> = getFirebaseUserOnce().map {
        it.flatMap {
            firestore
                .collection(USER_COLLECTION)
                .document(it.uid)
                .toSingle<User>()
                .statusSingle()
        }
    }

    override fun createNewUserDocument(): Flow<Status<Unit>> {
        Timber.d("createNewUserDocument, user is: ${auth.currentUser}")
        return auth.currentUser?.let { user ->
            firestore
                .collection(USER_COLLECTION)
                .document(user.uid)
                .set(User(user.uid, user.email, user.photoUrl?.toString()))
                .addOnTaskStatusChangeFlow()
        } ?: statusFlow { Failure(UserIsNullException()) }
    }

    companion object {
        const val USER_COLLECTION = "users"
    }
}

data class UserIsNullException(override val message: String? = "auth.currentUser is null") :
    Exception()