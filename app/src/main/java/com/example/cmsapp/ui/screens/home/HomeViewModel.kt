package com.example.cmsapp.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmsapp.models.UserDataModelItem
import com.example.cmsapp.networks.ApiException
import com.example.cmsapp.repositories.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _userinfo: MutableLiveData<UserDataModelItem?> by lazy {
        MutableLiveData<UserDataModelItem?>()
    }

    val todo: LiveData<UserDataModelItem?>
        get() = _userinfo

    fun getDetails() = viewModelScope.launch {
        try {
            val userInfo = repository.getUserDetails()

            _userinfo.postValue(userInfo as UserDataModelItem?)
        } catch (e: ApiException) {
            Timber.tag("UserDetails").d("$e")
        }
    }
}
