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

    private val _userinfo: MutableLiveData<List<UserDataModelItem>> by lazy {
        MutableLiveData<List<UserDataModelItem>>()
    }

    val userInfo: LiveData<List<UserDataModelItem>?>
        get() = _userinfo

    fun getDetails() = viewModelScope.launch {
        try {
            val response = repository.getUserDetails()

            _userinfo.value = response
        } catch (e: ApiException) {
            _userinfo.value = listOf()
            Timber.tag("UserDetails").d("$e")
        }
    }
}

