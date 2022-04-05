package com.example.calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.calendar.common.BaseFragmentFactory
import com.example.calendar.fragment.FirstFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName

    lateinit var pagerAdapter: PagerFragmentStateAdapter

    companion object {
        var instance: MainActivity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = BaseFragmentFactory(Int.MAX_VALUE / 2)
        super.onCreate(savedInstanceState)

        instance = this

        setContentView(R.layout.activity_main)
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.add(
//                R.id.fragmentFrame,
//                TimerFragment()
//        )
//        transaction.commit()

        pagerAdapter = PagerFragmentStateAdapter(this)

        // 3개의 Fragment Add
        pagerAdapter.addFragment(FirstFragment())


        // ViewPager2 Adapter
        main_pager.adapter = pagerAdapter
        main_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("MainViewPager", "Page ${position + 1}")
            }
        })

    }

    override fun onBackPressed() {
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
    }
}