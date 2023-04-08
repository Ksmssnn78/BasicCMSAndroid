package com.example.cmsapp.Utils.Extensions

import com.example.cmsapp.networks.JsonAdapter.DateJsonAdapter
import com.squareup.moshi.Moshi
import timber.log.Timber
import java.net.URLEncoder
import java.util.*

object MoshiUtil {
    fun getMoshi(): Moshi {
        return Moshi.Builder()
            .add(Date::class.java, DateJsonAdapter())
            .build()
    }
}

inline fun <reified T> String?.getObjFromJson(): T? {
    if (this == null) return null

    Timber.e("getObjFromJson: $this")

    val jsonAdapter = MoshiUtil.getMoshi().adapter(T::class.java).lenient()

    return jsonAdapter.fromJson(this)
}

inline fun <reified T> T?.getJsonFromObj(): String? {
    if (this == null) return null

    Timber.e("getJsonFromObj: $this")

    val jsonAdapter = MoshiUtil.getMoshi().adapter(T::class.java).lenient()

    return jsonAdapter.toJson(this).urlEncode()
}

fun String.urlEncode(): String = URLEncoder.encode(this, "utf-8")
