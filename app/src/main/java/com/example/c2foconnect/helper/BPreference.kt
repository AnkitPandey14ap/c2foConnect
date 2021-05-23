package com.example.c2foconnect.helper

import android.content.Context
import android.content.Context.MODE_PRIVATE

import android.content.SharedPreferences
import com.example.c2foconnect.video.model.UserBean
import com.google.gson.Gson


class BPreference {


    companion object {
        private val MY_PREFS_NAME = "MY_PREFS_NAME"
        private val USER_KEY = "user"
        public fun setUser(context: Context, user: UserBean) {
            val editor =
                context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit()
            val gson = Gson()
            val userJson = gson.toJson(user)
            editor.putString("name", userJson)
            editor.apply()
        }

        public fun getUser(context: Context): UserBean {
            val prefs: SharedPreferences = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
            val userJson =
                prefs.getString("name", "") //"No name defined" is the default value.

            if (!BUtility.isStringEmpty(userJson)) {
                val gson = Gson()
                val user: UserBean = gson.fromJson(userJson, UserBean::class.java)
                return user
            }
            return UserBean(-1,"","","","")
        }

        public fun isLogedIn(context: Context): Boolean {
            val user = getUser(context);
            return !BUtility.isStringEmpty(user?.email)
        }

        public fun logout(context: Context) {
            val preferences: SharedPreferences =
                context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
            val editor = preferences.edit()
              editor.clear()
            editor.apply()
        }


    }
}