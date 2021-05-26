package com.example.c2foconnect

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.c2foconnect.api.Api
import com.example.c2foconnect.base.BaseActivity
import com.example.c2foconnect.connectins.ConnectionsAdapter
import com.example.c2foconnect.helper.BPreference
import com.example.c2foconnect.video.model.AllConnectionsDataItem
import com.example.c2foconnect.video.model.AllConnectionsResponse
import com.example.c2foconnect.videos.VideoActivity
import kotlinx.android.synthetic.main.activity_connections.*
import retrofit.RetrofitError
import retrofit.client.Response


class ConnectionsActivity : BaseActivity() {
    val TAG = "ConnectionsActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connections)

        getConnections()

    }

    private fun initUI(connectionsData: MutableList<AllConnectionsDataItem>) {

        backIV.setOnClickListener {
            onBackPressed()
        }

        if (connectionsData != null) {
            val rvContacts = findViewById<View>(R.id.connectionRV) as RecyclerView
            val adapter = ConnectionsAdapter(connectionsData)
            rvContacts.adapter = adapter
            rvContacts.layoutManager = LinearLayoutManager(this)
        }
    }


    private fun getConnections() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false) // set cancelable to false
        progressDialog.setMessage("Please Wait") // set message
        progressDialog.show() // show progress dialog

        val user = BPreference.getUser(this)
        Api.getClient()
            .getConnections(user?.id, object : retrofit.Callback<AllConnectionsResponse?> {
                override fun success(
                    storyResponse: AllConnectionsResponse?,
                    response: Response
                ) {
                    progressDialog.dismiss() //dismiss progress dialog
                    Log.i(TAG, "success: " + storyResponse?.data)
                    storyResponse?.data?.let { initUI(it) };
                }

                override fun failure(error: RetrofitError) {
                    Toast.makeText(this@ConnectionsActivity, error.toString(), Toast.LENGTH_LONG)
                        .show()
                    Log.i(TAG, "failure: $error.toString()")
                    progressDialog.dismiss()
                }

            })
    }


    override fun onBackPressed() {
        if (!isTaskRoot) {
            super.onBackPressed()
        } else {
            val intent = Intent(this, VideoActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}