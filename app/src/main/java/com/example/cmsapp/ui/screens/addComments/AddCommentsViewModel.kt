package com.example.cmsapp.ui.screens.addComments

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
class AddCommentsViewModel @Inject constructor(
    private val repository: CommentsRepository
) : ViewModel() {
    fun addComment(postId: Int, name: String, email: String, body: String) =
        viewModelScope.launch {
            try {
                repository.addComment(
                    postId = postId,
                    CommentListModelItem(
                        body = body,
                        email = email,
                        name = name
                    )
                )
            } catch (e: ApiException) {
                Timber.e(e)
            }
        }
}
