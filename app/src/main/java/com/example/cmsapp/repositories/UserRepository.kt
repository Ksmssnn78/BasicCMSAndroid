package com.example.cmsapp.repositories

import android.content.Context
import com.example.cmsapp.models.UserDataModelItem
import com.example.cmsapp.networks.SafeApiRequest
import com.example.cmsapp.networks.api.UserInterface
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: UserInterface
) {
    suspend fun getUserDetails() = withContext(Dispatchers.IO) {
        val userItem = SafeApiRequest.apiRequest(context) {
            api.getUserData()
        }

        userItem
    }

    suspend fun addUser(user: UserDataModelItem) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.addUser(user)
        }
    }
}
