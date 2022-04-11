package com.example.calendar.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar.MainActivity
import com.example.calendar.R
import com.example.calendar.databinding.FragmentCalendarBinding
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment(index: Int) : Fragment() {

    private val TAG = javaClass.simpleName

    private var _binding:FragmentCalendarBinding? = null
    private val binding get() = _binding!!


    private var pageIndex = index
    private lateinit var currentDate: Date

    private lateinit var calendarYearMonthText: TextView
    private lateinit var calendarLayout: LinearLayout
    private lateinit var calendarView: RecyclerView
    private lateinit var calendarAdapter: CalendarAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCalendarBinding.inflate(inflater,container,false)
        initAdapter()
        initView()
        initCalendar()
        return binding.root
    }

    private fun initAdapter() {

    }


    private fun initView() {
        pageIndex -= (Int.MAX_VALUE / 2)
        Log.e(TAG, "Calender Index: $pageIndex")
        if(_binding!=null){
            calendarYearMonthText = _binding!!.calendarYearMonthText
            calendarLayout = _binding!!.calendarLayout
            calendarView = _binding!!.calendarView
        }

        val date = Calendar.getInstance().run {
            add(Calendar.MONTH, pageIndex)
            time
        }
        currentDate = date
        Log.e(TAG, "$date")
        val datetime: String = SimpleDateFormat(
            "y년M월",
            Locale.KOREA
        ).format(date.time)
        calendarYearMonthText.text = datetime
    }

    private fun initCalendar() {
        // 각 월의 1일의 요일을 구해
        // 첫 주의 일요일~해당 요일 전까지는 ""으로
        // 말일까지 해당 날짜
        // 마지막 날짜 뒤로는 ""으로 처리하여
        // CalendarAdapter로 List를 넘김
        calendarAdapter = CalendarAdapter(calendarLayout, currentDate)
        calendarView.adapter = calendarAdapter
        calendarView.layoutManager = GridLayoutManager(requireContext(), 7, GridLayoutManager.VERTICAL, false)
        calendarView.setHasFixedSize(true)
   /*     calendarAdapter.itemClick = object :
            CalendarAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val firstDateIndex = calendarAdapter.dataList.indexOf(1)
                val lastDateIndex =
                    calendarAdapter.dataList.lastIndexOf(calendarAdapter.furangCalendar.currentMaxDate)
                // 현재 월의 1일 이전, 현재 월의 마지막일 이후는 터치 disable
                if (position < firstDateIndex || position > lastDateIndex) {
                    return
                }
                val day = calendarAdapter.dataList[position].toString()
                val date = "${calendar_year_month_text.text}${day}일"
                Log.d(TAG, "$date")

                val mainViewPager = mActivity.main_pager
                mainViewPager.currentItem = 1
            }
        }*/
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}