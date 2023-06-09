package com.example.cmsapp.networks

import android.content.Context
import com.example.cmsapp.Utils.Extensions.NoInternetUtils
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber
import java.net.HttpURLConnection

object SafeApiRequest {
    suspend fun <T : Any?> apiRequest(context: Context, call: suspend () -> Response<T>): T? {
        try {
            if (!NoInternetUtils.isConnectedToInternet(context.applicationContext)) {
                throw ApiException("No internet connection!")
            }

            val response = call.invoke()

            if (response.isSuccessful &&
                response.code() >= HttpURLConnection.HTTP_OK &&
                response.code() < HttpURLConnection.HTTP_MULT_CHOICE
            ) {
                return response.body()
            } else {
                val error = response.errorBody()?.string()

                val message = StringBuilder()
                error?.let {
                    try {
                        message.append(JSONObject(it).getString("message"))
                    } catch (e: JSONException) {
                        /* no-op */
                    }

                    message.append("\n")
                }

                message.append("Error Code: ${response.code()}")

                Timber.e("SafeApiRequest: ApiException: $message")

                throw ApiException(message.toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()

            throw ApiException(e.message ?: "Unknown Error!")
        }
    }
}
