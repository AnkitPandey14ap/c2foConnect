package com.example.c2foconnect

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.c2foconnect.api.Api
import com.example.c2foconnect.helper.ActivityHelper
import com.example.c2foconnect.helper.BPreference
import com.example.c2foconnect.helper.BToastHelper
import com.example.c2foconnect.helper.BUtility
import com.example.c2foconnect.video.model.UserResponse
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response

class SplashActivity : AppCompatActivity() {
    val TAG = "SplashActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        Log.d("Firebase", "token "+ FirebaseInstanceId.getInstance().getToken());
        Log.i("Token", "onCreate: Token " + MyFirebaseMessagingService.getToken(this))


        registerFcmToken()


        if (BPreference.isLogedIn(this)) {
            ActivityHelper.openHomeActivity(this)
//            ActivityHelper.openChatActivity(this)
        } else {
            ActivityHelper.openLoginActivity(this)
        }
    }

    private fun registerFcmToken() {

        val user = BPreference.getUser(this)
        if (user != null) {
            Api.getClient().registerFcmToken(
                user.id,
                MyFirebaseMessagingService.getToken(this),
                object : Callback<UserResponse?> {
                    override fun success(
                        userListResponses: UserResponse?,
                        response: Response
                    ) {
                        var userListResponseData = userListResponses
                        Log.i(TAG, "success: " + userListResponseData?.data?.id)
                    }

                    override fun failure(error: RetrofitError) {
                        Toast.makeText(baseContext, error.toString(), Toast.LENGTH_LONG).show()
                        Log.i(TAG, "failure: $error.toString()")
                    }

                })
        }

    }

}