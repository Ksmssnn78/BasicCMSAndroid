package com.example.cmsapp.ui.screens.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmsapp.models.CommentListModelItem
import com.example.cmsapp.networks.ApiException
import com.example.cmsapp.repositories.CommentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val repository: CommentsRepository
) : ViewModel() {
    private val _commentsInfo: MutableLiveData<List<CommentListModelItem>> by lazy {
        MutableLiveData<List<CommentListModelItem>>()
    }

    val commentInfo: LiveData<List<CommentListModelItem>?>
        get() = _commentsInfo

    fun getComments(postId: Int) = viewModelScope.launch {
        try {
            val response = repository.getCommentsDetails(postId)
            _commentsInfo.value = response
        } catch (e: ApiException) {
            _commentsInfo.value = listOf()
            Timber.e(e)
        }
    }
    fun deleteComment(CommentId: Int) = viewModelScope.launch {
        try {
            repository.deleteComment(CommentId)
        }catch (e: ApiException){
            Timber.e(e)
        }
    }
}
