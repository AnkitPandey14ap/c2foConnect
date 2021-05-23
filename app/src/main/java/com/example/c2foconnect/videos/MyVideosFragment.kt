package com.example.c2foconnect.videos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.c2foconnect.R
import com.example.c2foconnect.connectins.Contact
import kotlinx.android.synthetic.main.fragment_my_videos.*


class MyVideosFragment : Fragment() {

    val TAG = "MyVideosFragment";
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_videos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        var contacts = Contact.createContactsList(20)
        val adapter = MyVideosAdapter(contacts)
        messageRV.adapter = adapter
        messageRV.layoutManager = GridLayoutManager(context, 3)
    }


    companion object {
        fun newInstance(): MyVideosFragment? {
            return MyVideosFragment()
        }
    }

}