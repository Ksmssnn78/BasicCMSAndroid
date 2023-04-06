package com.example.cmsapp.Utils.Extensions

import com.example.cmsapp.networks.JsonAdapter.DateJsonAdapter
import com.squareup.moshi.Moshi
import java.util.*

object MoshiUtil {
    fun getMoshi(): Moshi {
        return Moshi.Builder()
            .add(Date::class.java, DateJsonAdapter())
            .build()
    }
}