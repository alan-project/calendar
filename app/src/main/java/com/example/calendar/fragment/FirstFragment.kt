package com.example.calendar.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.calendar.MainActivity
import com.example.calendar.R
import com.example.calendar.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_first.view.*

class FirstFragment : BaseFragment() {

    private val TAG = javaClass.simpleName
    lateinit var mContext: Context

    lateinit var calendarViewPager: ViewPager2

    companion object {
        var instance: FirstFragment? = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            mContext = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        calendarViewPager = view.calendarViewPager
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView() {
        val calendarPagerFragmentStateAdapter = CalendarPagerFragmentStateAdapter(requireActivity())
        calendarViewPager.adapter = calendarPagerFragmentStateAdapter
        calendarViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        calendarPagerFragmentStateAdapter.apply {
            calendarViewPager.setCurrentItem(this.firstFragmentPosition, false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }
}