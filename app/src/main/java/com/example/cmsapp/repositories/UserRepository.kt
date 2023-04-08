package com.example.cmsapp.repositories

import android.content.Context
import com.example.cmsapp.Utils.Extensions.NoInternetUtils
import com.example.cmsapp.networks.SafeApiRequest
import com.example.cmsapp.networks.api.UserInterface
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class UserRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: UserInterface
) {
    suspend fun getUserDetails() = withContext(Dispatchers.IO) {
        if (NoInternetUtils.isConnectedToInternet(context.applicationContext)) {
            // Online
            val userItem = SafeApiRequest.apiRequest(context) {
                api.getUserData()
            }

            userItem
        } else {
            // Offline
           Timber.tag("User_Repo").d("No internet connection")
        }
    }
}
