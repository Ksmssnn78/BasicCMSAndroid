package com.example.cmsapp.ui.screens.profiles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmsapp.models.PostListModelItem
import com.example.cmsapp.networks.ApiException
import com.example.cmsapp.repositories.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: PostRepository
): ViewModel() {
    private val _postInfo: MutableLiveData<List<PostListModelItem>> by lazy {
        MutableLiveData<List<PostListModelItem>>()
    }

    val postInfo: LiveData<List<PostListModelItem>?>
        get() = _postInfo

    fun getPostDetails(userId: Int) = viewModelScope.launch {
        try {
            val response = repository.getPostDetails(userId)
            _postInfo.value = response
        }catch (e: ApiException){
            _postInfo.value = listOf()
            Timber.e(e)
        }
    }
}
