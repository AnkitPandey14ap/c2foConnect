package com.example.c2foconnect

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.c2foconnect.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    val TAG = "LoginActivity"
    private var emailId = "";
    private var password = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        signinBTN.setOnClickListener {
            Log.i(TAG, "signinBTN clicked: ")
            emailId = emailET.text.toString();
            password = passwordET.text.toString();
            loginUser()
        }


    }

    private fun loginUser() {
        //todo


    }
}