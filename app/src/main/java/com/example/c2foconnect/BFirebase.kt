package com.example.c2foconnect

import android.util.Log
import com.example.c2foconnect.helper.ChatCallBack
import com.example.c2foconnect.video.model.ChatMessageModal
import com.google.firebase.database.*
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class BFirebase(connectionId: String) {
    val KEY_MESSAGES = "messages"
    private var messageRef: DatabaseReference = FirebaseDatabase.getInstance()
        .reference.child(KEY_MESSAGES).child(connectionId)

    val TAG = "BFirebase"
    fun sendMessage(text: String, userId: String, callback: ChatCallBack) {

        var msg: ChatMessageModal = ChatMessageModal()
        val currentTimestamp = System.currentTimeMillis()

//        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
//        val currentDate = sdf.format(Date())
//        msg.message = currentDate.substring(10).replace(":","-")
        msg.message = text

        msg.timeStamp = currentTimestamp.toString()
        msg.userId = userId
        messageRef.push().setValue(msg).addOnCompleteListener {
            callback.onSendMessageSuccess(msg)
        }.addOnCanceledListener {
            callback.onSendMessageFailure(msg)
        }
    }


    fun subscribeRealTimeDatabase(callback: ChatCallBack) {

        messageRef.addChildEventListener(object : ChildEventListener {

            override fun onCancelled(error: DatabaseError) {
                Log.i(TAG, "onCancelled: ")

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                Log.i(TAG, "onChildMoved: ")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                Log.i(TAG, "onChildChanged: ")
            }

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.i(TAG, "onChildAdded: " + snapshot.value)

                val gson = Gson()
                var newMsg = gson.fromJson(gson.toJson(snapshot.value), ChatMessageModal::class.java)

                callback.newMessageReceived(newMsg)
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                Log.i(TAG, "onChildRemoved: ")
            }
        })
    }
}