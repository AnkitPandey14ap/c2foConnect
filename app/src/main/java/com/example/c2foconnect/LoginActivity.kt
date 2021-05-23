package com.example.c2foconnect

import android.os.Bundle
import com.example.c2foconnect.base.BaseActivity
import com.example.c2foconnect.helper.ActivityHelper
import com.example.c2foconnect.helper.BPreference
import com.example.c2foconnect.helper.BToastHelper
import com.example.c2foconnect.helper.BUtility
import com.example.c2foconnect.video.model.UserBean
import kotlinx.android.synthetic.main.activity_login.*


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
        val email = emailET.text.toString()
        val password = passwordET.text.toString()
        if (BUtility.isStringEmpty(email)) {
            BToastHelper.showToast(this, "invalid Email address!")
            return
        } else if (BUtility.isStringEmpty(password)) {
            BToastHelper.showToast(this, "invalid password!")
            return
        }
        BPreference.setUser(this, UserBean(1, "name", "https://pbs.twimg.com/profile_images/1366466342354751491/JyhZpbtu_400x400.jpg", email, password))
        ActivityHelper.openProfileActivity(this)
    }
}