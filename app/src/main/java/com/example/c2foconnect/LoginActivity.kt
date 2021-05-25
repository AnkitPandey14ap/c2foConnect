package com.example.c2foconnect

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.c2foconnect.api.Api
import com.example.c2foconnect.base.BaseActivity
import com.example.c2foconnect.helper.ActivityHelper
import com.example.c2foconnect.helper.BPreference
import com.example.c2foconnect.helper.BToastHelper
import com.example.c2foconnect.helper.BUtility
import com.example.c2foconnect.video.model.User
import com.example.c2foconnect.video.model.UserResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response


class LoginActivity : BaseActivity() {
    val TAG = "LoginActivity"
    private var emailId = "";
    private var password = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        signinBTN.setOnClickListener {
            emailId = emailET.text.toString();
            password = passwordET.text.toString();
            loginUser()
        }


    }

    private fun loginUser() {
//        emailId = emailET.text.toString()
//        password = passwordET.text.toString()

        emailId = "enquiry@tesco.com"
        password = "dsfsdfs"


        if (BUtility.isStringEmpty(emailId)) {
            BToastHelper.showToast(this, "invalid Email address!")
            return
        } else if (BUtility.isStringEmpty(password)) {
            BToastHelper.showToast(this, "invalid password!")
            return
        }

        val progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false) // set cancelable to false
        progressDialog.setMessage("Please Wait") // set message
        progressDialog.show() // show progress dialog

        Api.getClient().registration(emailId, password, object : Callback<UserResponse?> {
            override fun success(
                userListResponses: UserResponse?,
                response: Response
            ) {
                progressDialog.dismiss() //dismiss progress dialog
                var userListResponseData = userListResponses
                Log.i(TAG, "success: " + userListResponseData?.data?.id)

                userListResponses?.data?.let { saveUserAndOpenHomePage(it) }
            }

            override fun failure(error: RetrofitError) {
                Toast.makeText(baseContext, error.toString(), Toast.LENGTH_LONG).show()
                Log.i(TAG, "failure: $error.toString()")
                progressDialog.dismiss() //dismiss progress dialog
            }

        })
    }

    private fun saveUserAndOpenHomePage(user: User) {
        BPreference.setUser(
            this,
            user
        )
        ActivityHelper.openProfileActivity(this)

    }

}