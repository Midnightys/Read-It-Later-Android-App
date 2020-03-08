package com.midnightys.ringitlater.model

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.android.parcel.Parcelize

/**
 * Created by Kort on 2020/3/8.
 */
@Parcelize
data class Article(
    private val url: String = "",
    private val createdTimestamp: Timestamp = Timestamp.now()
) : Parcelable