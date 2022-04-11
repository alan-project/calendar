package com.example.calendar.fragment

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar.R
import com.example.calendar.databinding.ListItemCalendarBinding
import java.text.SimpleDateFormat
import java.util.*

class CalendarAdapter(
    private val context: Context,
    private val calendarLayout: LinearLayout,
    val date: Date
) :
    RecyclerView.Adapter<CalendarAdapter.CalendarItemHolder>() {

    private val TAG = javaClass.simpleName
    var dataList: ArrayList<Int> = arrayListOf()


    // FurangCalendar을 이용하여 날짜 리스트 세팅
    var myCalendar: MyCalendar = MyCalendar(date)

    init {
        myCalendar.initBaseCalendar()
        dataList = myCalendar.dateList
    }

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarItemHolder {
        val binding =
            ListItemCalendarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarItemHolder(binding, myCalendar, dataList, date)
    }


    override fun onBindViewHolder(holder: CalendarItemHolder, position: Int) {

        // list_item_calendar 높이 지정
        val h = calendarLayout.height / 6
        holder.itemView.layoutParams.height = h

        holder.bind(dataList[position], position, context)
        if (itemClick != null) {
            holder.itemView.setOnClickListener { v ->
                itemClick?.onClick(v, position)

            }
        }
    }


    override fun getItemCount(): Int = dataList.size

    class CalendarItemHolder(
        private val binding: ListItemCalendarBinding,
        private val myCalendar: MyCalendar,
        private val dataList: ArrayList<Int>,
        private val date: Date
    ) :
        RecyclerView.ViewHolder(binding.root) {

        var itemCalendarDateText: TextView = binding.itemCalendarDateText
        var itemCalendarDotView: View = binding.itemCalendarDotView

        fun bind(data: Int, position: Int, context: Context) {
//            Log.d(TAG, "${furangCalendar.prevTail}, ${furangCalendar.nextHead}")
            val firstDateIndex: Int = myCalendar.prevTail
            val lastDateIndex: Int = dataList.size - myCalendar.nextHead - 1

            itemCalendarDateText.text = data.toString()

            // 오늘 날짜 처리
            var dateString: String = SimpleDateFormat("dd", Locale.KOREA).format(date)
            var dateInt = dateString.toInt()
            if (dataList[position] == dateInt) {
                itemCalendarDateText.setTypeface(itemCalendarDateText.typeface, Typeface.BOLD)
            }

            // 현재 월의 1일 이전, 현재 월의 마지막일 이후 값의 텍스트를 회색처리
            if (position < firstDateIndex || position > lastDateIndex) {
                itemCalendarDateText.setTextAppearance(R.style.LightColorTextViewStyle)
                itemCalendarDotView.background = null
//                itemCalendarDotView.setBackgroundResource(R.drawable.shape_circle_routine_light_gray)
            }
        }

    }
}