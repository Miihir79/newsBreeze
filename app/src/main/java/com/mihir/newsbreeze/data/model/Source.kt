package com.mihir.newsbreeze.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Source(
    val id: String?,
    val name: String?
):Parcelable