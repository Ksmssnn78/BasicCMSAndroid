package com.example.cmsapp.repositories

import android.content.Context
import com.example.cmsapp.models.CommentListModelItem
import com.example.cmsapp.networks.SafeApiRequest
import com.example.cmsapp.networks.api.CommentsInterface
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CommentsRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val commentsApi: CommentsInterface
) {
    suspend fun getCommentsDetails(postId: Int) = withContext(Dispatchers.IO) {
        val commentsItem = SafeApiRequest.apiRequest(context) {
            commentsApi.getCommentsDetails(postId)
        }
        commentsItem
    }

    suspend fun addComment(postId: Int, comment: CommentListModelItem) =
        withContext(Dispatchers.IO) {
            SafeApiRequest.apiRequest(context) {
                commentsApi.addComment(postId, comment)
            }
        }

    suspend fun deleteComment(postId: Int) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            commentsApi.deleteComment(postId)
        }
    }

    suspend fun updateComment(commentId: Int, comment: CommentListModelItem) =
        withContext(Dispatchers.IO) {
            SafeApiRequest.apiRequest(context) {
                commentsApi.updateComment(commentId, comment)
            }
        }
}
