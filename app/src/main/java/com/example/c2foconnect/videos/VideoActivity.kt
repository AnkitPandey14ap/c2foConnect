package com.example.c2foconnect.videos

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.c2foconnect.R
import com.example.c2foconnect.base.BaseActivity
import com.example.c2foconnect.helper.ActivityHelper
import com.example.c2foconnect.helper.BPreference
import com.example.c2foconnect.helper.ImageHelper
import com.example.c2foconnect.video.model.User
import com.jakewharton.rxbinding.widget.RxTextView
import kotlinx.android.synthetic.main.activity_video.*
import java.util.concurrent.TimeUnit


class VideoActivity : BaseActivity() {
    private var ifFirstCall: Boolean = true
    private lateinit var simpleFragment: RootFragment
    val TAG = "VideoActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        var user = BPreference.getUser(this);
        if (user != null) {
            initView(user)
            initListners()
            addHomeFragment()
        }
    }

    private fun initView(userBean: User) {
        nameTV.text = userBean.name;
        ImageHelper.setRoundImage(this, userIV, userBean.profileImageUrl, 48)
    }

    private fun initListners() {
        chatIV.setOnClickListener {
            ActivityHelper.openConnectionListActivity(this)
        }
//        searchIV.setOnClickListener {
//            showSearchView(true)
//        }

        userIV.setOnClickListener {
            ActivityHelper.openProfileActivity(this)
        }

        homeTV.setOnClickListener {
            addHomeFragment()
            homeTV.setTextColor(ContextCompat.getColor(this, R.color.white));
            homeTV.setBackground(ContextCompat.getDrawable(this, R.drawable.corner_36_green));
            myVideosTV.setTextColor(ContextCompat.getColor(this, R.color.text_color));
            myVideosTV.setBackgroundResource(0)
        }
        myVideosTV.setOnClickListener {
            addMyVideosFragment()

            myVideosTV.setTextColor(ContextCompat.getColor(this, R.color.white));
            myVideosTV.background = ContextCompat.getDrawable(this, R.drawable.corner_36_green);
            homeTV.setTextColor(ContextCompat.getColor(this, R.color.text_color));
            homeTV.setBackgroundResource(0)
        }

        subscribeSearchInputChangeListner()

//        Handler().postDelayed({
//            subscribeSearchInputChangeListner()
//        }, 4000)
    }

    private fun subscribeSearchInputChangeListner() {
        RxTextView.textChanges(searchET)
            .debounce(1000, TimeUnit.MILLISECONDS)
            .subscribe {
                if (ifFirstCall)
                    ifFirstCall = false
                else
                    simpleFragment.refreshList(it.toString())

            }
    }

    private fun addHomeFragment() {
        simpleFragment = RootFragment.newInstance()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager
            .beginTransaction()

        simpleFragment?.let {
            fragmentTransaction.replace(
                R.id.fragmentContainer,
                it
            ).addToBackStack(null).commit()
        };
    }

    private fun addMyVideosFragment() {
        val simpleFragment: MyVideosFragment? = MyVideosFragment.newInstance()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager
            .beginTransaction()

        simpleFragment?.let {
            fragmentTransaction.replace(
                R.id.fragmentContainer,
                it
            ).addToBackStack(null).commit()
        };
    }

    private fun showSearchView(show: Boolean) {
        if (show) {
            headerContainer.visibility = View.GONE
            searchView.visibility = View.VISIBLE

            searchET.requestFocus()
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(searchET, InputMethodManager.SHOW_IMPLICIT)
        } else {
            searchView.visibility = View.GONE
            headerContainer.visibility = View.VISIBLE
        }
    }


    override fun onBackPressed() {
        if (searchView.visibility == View.VISIBLE) {
            showSearchView(false)
            return
        }
        finish();
        super.onBackPressed()
    }
}