package com.example.cmsapp.ui.screens.postAdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmsapp.models.PostListModelItem
import com.example.cmsapp.networks.ApiException
import com.example.cmsapp.repositories.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class AddPostViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {
    fun addPost(userId: Int, title: String, body: String) = viewModelScope.launch {
        try {
            repository.addPost(
                userId = userId,
                PostListModelItem( title = title, body = body)
            )
        } catch (e: ApiException) {
            Timber.e(e)
        }
    }
}
