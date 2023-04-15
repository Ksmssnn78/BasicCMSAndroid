package com.example.cmsapp.ui.screens.updateComment

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
class UpdateCommentViewModel @Inject constructor(
    private val repo: CommentsRepository
) : ViewModel() {

    fun updateComments(commentId: Int, userName: String, mail: String, cmnt: String,postId: Int) =
        viewModelScope.launch {
            if (cmnt != null) {
                try {
                    repo.updateComment(
                        commentId = commentId,
                        CommentListModelItem(id = commentId,body = cmnt, email = mail, name = userName, postId = postId)
                    )
                } catch (e: ApiException) {
                    Timber.e(e)
                }
            }
        }
}
