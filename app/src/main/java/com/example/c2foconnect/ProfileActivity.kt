package com.example.c2foconnect

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import com.example.c2foconnect.base.BaseActivity
import com.example.c2foconnect.helper.ActivityHelper
import com.example.c2foconnect.helper.BPreference
import com.example.c2foconnect.helper.ImageHelper
import com.example.c2foconnect.video.model.User
import com.example.c2foconnect.videos.VideoActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : BaseActivity() {
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        user = BPreference.getUser(this)
        if (user != null) {
            initView()
        }
        initListeners()
    }

    private fun initView() {
        var user = this.user
        if (user == null)
            return
        ImageHelper.setRoundImage(this, userIV, user.profileImageUrl, 60)
        nameET.setText(user.name)
        detailET.setText(user.companyDetails)
        phoneET.setText(user.phone)
        emailET.setText(user.email)
        sectorET.setText(user.sector)
        buisinessTypeET.setText(user.businessType)
        geographicalET.setText(user.address)
//        invoiceToolET.setText(user.in)
        user?.requirements?.forEach {
            addChip(it, requirementsChipGroup)
        }
        user.products.forEach {
            addChip(it, productsChipGroup)
        }

        industryET.setText(user.industry)
        serviceAreaET.setText(user.serviceArea)
        annualRevenueET.setText(user.annualRevenue)
        numberOfEmployeesET.setText(user.numberOfEmployees)
        yearOfEstablishmentET.setText(user.yearOfEstablishment)
        ceoET.setText(user.ceo)
        officeAddressET.setText(user.officeAddress)
        websiteET.setText(user.website)

    }

    private fun initListeners() {
        saveIV.setOnClickListener {
            ActivityHelper.openHomeActivity(this)
        }

        logoutTV.setOnClickListener {
            BPreference.logout(this)
            triggerRebirth(this)
        }

        closeIV.setOnClickListener {
            if (!isTaskRoot) {
                super.onBackPressed()
            } else {
                val intent = Intent(this, VideoActivity::class.java)
                startActivity(intent)
                finish()

            }
        }
    }

    private fun addChip(pItem: String, pChipGroup: ChipGroup) {
        val lChip = Chip(this)
        lChip.text = pItem
        lChip.setTextColor(resources.getColor(R.color.text_color))
        lChip.chipBackgroundColor = resources.getColorStateList(R.color.grey_f1f1f1)
        pChipGroup.addView(lChip, pChipGroup.childCount - 1)
    }

    fun triggerRebirth(context: Context) {
        val intent = Intent(context, SplashActivity::class.java)
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
//        intent.putExtra(KEY_RESTART_INTENT, nextIntent)
        context.startActivity(intent)
        if (context is Activity) {
            context.finish()
        }
        Runtime.getRuntime().exit(0)
    }

}