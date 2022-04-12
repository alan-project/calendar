package com.example.calendar.fragment

import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CalendarPagerFragmentStateAdapter(fa: FragmentActivity) :
    FragmentStateAdapter(fa) {

    private val pageCount = Int.MAX_VALUE
    val fragmentPosition = Int.MAX_VALUE / 2

    override fun getItemCount(): Int = pageCount

    override fun createFragment(position: Int): Fragment {
        Log.d("AlanKim","position = $position")

        return CalendarFragment().apply {
            arguments = bundleOf("pos" to position)
        }
    }
}