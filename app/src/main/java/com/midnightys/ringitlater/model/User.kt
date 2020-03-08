package com.midnightys.ringitlater.model

import android.net.Uri
import android.os.Parcelable
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.parcel.Parcelize

/**
 * Created by Kort on 2020/3/8.
 */
@Parcelize
data class User(
    val id: String = "",
    val email: String? = null,
    val photoUrl: String? = null
) : Parcelable