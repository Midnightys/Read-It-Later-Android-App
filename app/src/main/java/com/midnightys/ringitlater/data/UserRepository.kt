package com.midnightys.ringitlater.data

import com.google.firebase.firestore.FirebaseFirestore


/**
 * Created by Kort on 2020/3/8.
 */
interface UserRepositoryProvider {
    val firestore: FirebaseFirestore
    fun createNewUserDocument(userId: String)
}

typealias userId = String

class UserRepository(override val firestore: FirebaseFirestore) : UserRepositoryProvider {

    override fun createNewUserDocument(userId: userId) {
        firestore
            .collection(USER_COLLECTION)
            .document(userId)
    }

    companion object {
        const val USER_COLLECTION = "users"
    }
}