package com.example.calendar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.calendar.databinding.ActivityMainBinding
import com.example.calendar.fragment.CalendarPagerFragmentStateAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var calendarViewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        calendarViewPager = binding.mainPager

        initView()
    }

    private fun initView() {
        val calendarPagerFragmentStateAdapter = CalendarPagerFragmentStateAdapter(this)
        calendarViewPager.adapter = calendarPagerFragmentStateAdapter
        calendarViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        calendarPagerFragmentStateAdapter.apply {
            calendarViewPager.setCurrentItem(this.fragmentPosition, false)
        }
    }

/*    override fun onBackPressed() {
        val fragmentManager: FragmentManager = supportFragmentManager
        for (fragment in fragmentManager.fragments) {
            if (fragment.isVisible) {
                val chilFrag: FragmentManager = fragment.childFragmentManager
                if (chilFrag.backStackEntryCount > 0) {
                    chilFrag.popBackStack()
                    return
                }
            }
        }
        super.onBackPressed()
    }*/
}