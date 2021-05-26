package com.example.c2foconnect.helper

import android.app.Activity
import android.content.Intent
import com.example.c2foconnect.ConnectionsActivity
import com.example.c2foconnect.LoginActivity
import com.example.c2foconnect.ProfileActivity
import com.example.c2foconnect.SplashActivity
import com.example.c2foconnect.connectins.ChatActivity
import com.example.c2foconnect.videos.VideoActivity


class ActivityHelper {
    companion object {

        public fun openSplashActivity(activity: Activity) {
            val intent = Intent(activity, SplashActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }

        public fun openHomeActivity(activity: Activity) {
            val intent = Intent(activity, VideoActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }

        public fun openLoginActivity(activity: Activity) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }

        public fun openConnectionListActivity(activity: Activity) {
            val intent = Intent(activity, ConnectionsActivity::class.java)
            activity.startActivity(intent)
        }

        public fun openChatActivity(
            activity: Activity,
            connectionId: String,
            clientName: String,
            clientProfileUrl: String,
            clientId: String?
        ) {
            val intent = Intent(activity, ChatActivity::class.java)
            intent.putExtra(ChatActivity.CONNECTION_ID, connectionId)
            intent.putExtra(ChatActivity.CLIENT_NAME, clientName)
            intent.putExtra(ChatActivity.CLIENT_PROFILE_URL, clientProfileUrl)
            intent.putExtra(ChatActivity.CLIENT_ID, clientId)
            activity.startActivity(intent)
        }

        public fun openProfileActivity(activity: Activity) {
            val intent = Intent(activity, ProfileActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }
}