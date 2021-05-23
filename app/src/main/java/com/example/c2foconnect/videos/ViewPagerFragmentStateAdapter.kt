package com.example.c2foconnect.videos

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.c2foconnect.video.model.StoriesDataModel

class ViewPagerFragmentStateAdapter(fragment: Fragment, val dataList: MutableList<StoriesDataModel> = mutableListOf()) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun createFragment(position: Int): Fragment {
        return  PagerFragment.newInstance(dataList[position]) as Fragment
    }
}