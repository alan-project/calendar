package com.example.calendar.fragment

import android.os.Bundle
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
        //        calendarFragment.pageIndex = position

        return CalendarFragment().apply {
            arguments = bundleOf("pos" to position)
        }
    }
//    override fun createFragment(position: Int): Fragment =
//        CalendarFragment(position)


//    var fragments = mutableListOf<CalendarFragment>()
//    val firstFragmentPosition = Int.MAX_VALUE / 2

//    override fun getItemCount(): Int = if (fragments.isNotEmpty()) Int.MAX_VALUE else 0
//
//    override fun createFragment(position: Int): Fragment =
//        CalendarFragment(fragments[position.rem(fragments.size)], position)
//
//    fun updateFragments(list: List<CalendarFragment>) {
//        fragments.apply {
//            clear()
//            addAll(list)
//        }
//        notifyDataSetChanged()
//    }
}