package com.example.calendar.fragment

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
    private val calendarLayout: LinearLayout,
    private val date: Date
) : RecyclerView.Adapter<CalendarAdapter.CalendarItemHolder>() {

    private var dataList: ArrayList<Int> = arrayListOf()
    private val myCalendar: MyCalendar by lazy {
        MyCalendar(date)
    }

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

        // list_item_calendar height
        val h = calendarLayout.height / 6
        holder.itemView.layoutParams.height = h

        holder.bind(dataList[position], position)
        if (itemClick != null) {
            holder.itemView.setOnClickListener { v ->
                itemClick?.onClick(v, position)

            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    class CalendarItemHolder(
        binding: ListItemCalendarBinding,
        private val myCalendar: MyCalendar,
        private val dataList: ArrayList<Int>,
        private val date: Date
    ) :
        RecyclerView.ViewHolder(binding.root) {

        var itemCalendarDateText: TextView = binding.itemCalendarDateText
        var itemCalendarDotView: View = binding.itemCalendarDotView

        fun bind(data: Int, position: Int) {
            val firstDateIndex: Int = myCalendar.prevTail
            val lastDateIndex: Int = dataList.size - myCalendar.nextHead - 1

            itemCalendarDateText.text = data.toString()

            // ?????? ?????? ??????
            val dateString: String = SimpleDateFormat("dd", Locale.KOREA).format(date)
            val dateInt = dateString.toInt()
            if (dataList[position] == dateInt) {
                itemCalendarDateText.setTypeface(itemCalendarDateText.typeface, Typeface.BOLD)
            }

            // colored(gray) for days from prev, next month
            if (position < firstDateIndex || position > lastDateIndex) {
                itemCalendarDateText.setTextAppearance(R.style.LightColorTextViewStyle)
                itemCalendarDotView.background = null
//                itemCalendarDotView.setBackgroundResource(R.drawable.shape_circle_routine_light_gray)
            }
        }

    }
}