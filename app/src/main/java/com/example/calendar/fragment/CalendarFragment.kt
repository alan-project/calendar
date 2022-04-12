package com.example.calendar.fragment

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
import com.example.calendar.databinding.FragmentCalendarBinding
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment() : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!


    //temp
    private var pageIndex = 0
    private lateinit var currentDate: Date

    private lateinit var calendarYearMonthText: TextView
    private lateinit var calendarLayout: LinearLayout
    private lateinit var calendarView: RecyclerView
    private lateinit var calendarAdapter: CalendarAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Log.d("AlanKim", "pageIndex: $pageIndex")
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)

        initView()
        initCalendar()

        return binding.root
    }

    private fun initView() {
        pageIndex = arguments?.getInt("pos") ?: 0

        pageIndex -= (Int.MAX_VALUE / 2)
        Log.e("AlanKim", "Calender Index: $pageIndex")
        if (_binding != null) {
            calendarYearMonthText = _binding!!.calendarYearMonthText
            calendarLayout = _binding!!.calendarLayout
            calendarView = _binding!!.calendarView
        }

        val date = Calendar.getInstance().run {
            add(Calendar.MONTH, pageIndex)
            time
        }
        currentDate = date
        Log.e("AlanKim", "$date")
        val datetime: String = SimpleDateFormat(
            "MMMM yyyy",
            Locale.CANADA
        ).format(date.time)
        calendarYearMonthText.text = datetime
    }

    private fun initCalendar() {

        calendarAdapter = CalendarAdapter(calendarLayout, currentDate)
        calendarView.adapter = calendarAdapter
        calendarView.layoutManager =
            GridLayoutManager(requireContext(), 7, GridLayoutManager.VERTICAL, false)
        calendarView.setHasFixedSize(true)


/*

        // 각 월의 1일의 요일을 구해
        // 첫 주의 일요일~해당 요일 전까지는 ""으로
        // 말일까지 해당 날짜
        // 마지막 날짜 뒤로는 ""으로 처리하여
        // CalendarAdapter로 List를 넘김

        calendarAdapter.itemClick = object :
            CalendarAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val firstDateIndex = calendarAdapter.dataList.indexOf(1)
                val lastDateIndex =
                    calendarAdapter.dataList.lastIndexOf(calendarAdapter.myCalendar.currentMaxDate)
                // disable click of dates from pre, next month
                if (position < firstDateIndex || position > lastDateIndex) {
                    return
                }
                val day = calendarAdapter.dataList[position].toString()
                val date = "${calendar_year_month_text.text}${day}일"
                Log.d("AlanKim", "$date")

                val mainViewPager = requireActivity().main_pager
                mainViewPager.currentItem = 1
            }
        }*/
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}