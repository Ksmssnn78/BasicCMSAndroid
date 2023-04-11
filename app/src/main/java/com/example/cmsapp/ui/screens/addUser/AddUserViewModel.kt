package com.example.cmsapp.ui.screens.addUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmsapp.models.UserDataModelItem
import com.example.cmsapp.networks.ApiException
import com.example.cmsapp.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    fun addUser(name: String, mail: String, gender: String, status: String) =
        viewModelScope.launch {
            try {
                repository.addUser(
                    UserDataModelItem(
                        name = name,
                        email = mail,
                        gender = gender,
                        status = status
                    )
                )
            } catch (e: ApiException) {
                Timber.e(e)
            }
        }
}
