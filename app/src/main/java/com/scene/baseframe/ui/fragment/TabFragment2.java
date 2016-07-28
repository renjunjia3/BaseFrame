package com.scene.baseframe.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scene.baseframe.R;
import com.scene.baseframe.base.BaseLazyMainFragment;
import com.scene.baseframe.util.DateTimeUtils;
import com.scene.baseframe.util.ToastUtils;
import com.scene.baselib.util.ScreenUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.scene.baseframe.ui.fragment.TabFragment1.java
 * @功能描述：
 * @author: scene
 * @date: 2016-07-14 16:25
 */
public class TabFragment2 extends BaseLazyMainFragment implements View.OnClickListener {
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.layout)
    LinearLayout layout;

    //开始日期
    private Calendar startDate;
    //选中的日期
    private Calendar selectedDate;
    //当前日期
    private Calendar calendarToday;
    //结束的日期
    private Calendar endDate;

    //可以选择的日期范围 单位：天
    private int dateRange;
    //日历每一天的高度
    private float dayHeight;

    public static TabFragment2 newInstance() {

        Bundle args = new Bundle();
        TabFragment2 fragment = new TabFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        //在这里加载页面的数据
        initCalendarView();
    }

    private void initView(View view) {
        title.setText("首页2");
    }

    /**
     * 初始化日历控件
     */
    private void initCalendarView() {
        //初始化每天的View的高度:屏幕宽度-2边的间距-每一个之间的间距的1/7
        dayHeight = (ScreenUtils.instance(getContext()).getScreenWidth()
                - ScreenUtils.instance(getContext()).dip2px(10)
                - ScreenUtils.instance(getContext()).dip2px(1.5f * 6)) / 7;

        //初始化开始日期和选择的日期 可以选择的天数
        startDate = DateTimeUtils.getCurrentDateTime();
        dateRange = 60;
        selectedDate = DateTimeUtils.getCurrentDateTime();
        calendarToday = DateTimeUtils.getCurrentDateTime();
        endDate = (Calendar) startDate.clone();
        endDate.add(Calendar.DAY_OF_MONTH, dateRange - 1);
        //开始年份与月份
        int yearOfStart = startDate.get(Calendar.YEAR);
        int monthOfStart = startDate.get(Calendar.MONTH);
        //获取日历结束日期
        Calendar localCalendarEnd = (Calendar) startDate.clone();
        localCalendarEnd.add(Calendar.DAY_OF_MONTH, dateRange - 1);
        //日历结束的年份与月份
        int yearOfEnd = localCalendarEnd.get(Calendar.YEAR);
        int monthOfEnd = localCalendarEnd.get(Calendar.MONTH);
        //要显示的年份与月份数
        int differOfYear = yearOfEnd - yearOfStart;
        int differOfMonth = monthOfEnd - monthOfStart;
        // 全部要显示的月份数
        int totalDifferOfMonth = differOfYear * 12 + differOfMonth + 1;
        /**
         * 遍历加载全部月份
         */
        for (int i = 0; i < totalDifferOfMonth; i++) {
            //月份的布局
            LinearLayout monthLayout = (LinearLayout) View.inflate(
                    getContext(), R.layout.date_pick_head, null);
            //添加月份布局到总布局
            layout.addView(monthLayout);
            //每个月头部的布局：如 2016年7月
            TextView tv_cal_year = (TextView) monthLayout.findViewById(R.id.tv_cal_year);
            TextView tv_cal_month = (TextView) monthLayout.findViewById(R.id.tv_cal_month);
            //显示当前布局的年份
            Calendar tempCalendar = (Calendar) startDate.clone();
            tempCalendar.add(Calendar.YEAR, i / 11);
            tv_cal_year.setText(tempCalendar.get(Calendar.YEAR) + "年");
            //显示当前布局的月份
            Calendar tempCalendar2 = (Calendar) startDate.clone();
            tempCalendar2.add(Calendar.MONTH, i);
            tv_cal_month.setText(tempCalendar2.get(Calendar.MONTH) + 1 + "月");
            tempCalendar2.set(Calendar.DAY_OF_MONTH, 1);
            // 星期天-星期六 Calendar.DAY_OF_WEEK = 1-7
            int weekOfDay = tempCalendar2.get(Calendar.DAY_OF_WEEK) - 1;
            int maxOfMonth = tempCalendar2.getActualMaximum(Calendar.DAY_OF_MONTH);
            //获取当前月的行数
            int lines = (int) Math.ceil((weekOfDay + maxOfMonth) / 7.0f);
            //循环添加没一周的数据
            for (int j = 0; j < lines; j++) {
                //获取一行的View
                LinearLayout oneLineLinearLayout = get1LineDayLayout();
                if (j == 0) {
                    //第一行
                    for (int k = 0; k < 7; k++) {

                        if (k >= weekOfDay) {
                            TextView tv_day_default = (TextView) oneLineLinearLayout.getChildAt(k).findViewById(R.id.tv_day_default);
                            LinearLayout showTicketLayout = (LinearLayout) oneLineLinearLayout.getChildAt(k).findViewById(R.id.showTicketLayout);
                            TextView tv_day_showTicketLayout = (TextView) oneLineLinearLayout.getChildAt(k).findViewById(R.id.tv_day_showTicketLayout);
                            TextView tv_ticket_showTicketLayout = (TextView) oneLineLinearLayout.getChildAt(k).findViewById(R.id.tv_ticket_showTicketLayout);
                            TextView tv_day_unclick = (TextView) oneLineLinearLayout.getChildAt(k).findViewById(R.id.tv_day_unclick);
                            int index = k - weekOfDay + 1;
                            bindDayView(tempCalendar2, index, tv_day_default, showTicketLayout, tv_day_showTicketLayout, tv_day_unclick);
                        } else {
                            oneLineLinearLayout.getChildAt(k).setVisibility(View.INVISIBLE);
                        }
                    }
                } else if (j == lines - 1) {
                    //最后一行
                    int temp = maxOfMonth - (lines - 2) * 7 - (7 - weekOfDay);
                    for (int k = 0; k < 7; k++) {

                        if (k < temp) {
                            TextView tv_day_default = (TextView) oneLineLinearLayout.getChildAt(k).findViewById(R.id.tv_day_default);
                            LinearLayout showTicketLayout = (LinearLayout) oneLineLinearLayout.getChildAt(k).findViewById(R.id.showTicketLayout);
                            TextView tv_day_showTicketLayout = (TextView) oneLineLinearLayout.getChildAt(k).findViewById(R.id.tv_day_showTicketLayout);
                            TextView tv_ticket_showTicketLayout = (TextView) oneLineLinearLayout.getChildAt(k).findViewById(R.id.tv_ticket_showTicketLayout);
                            TextView tv_day_unclick = (TextView) oneLineLinearLayout.getChildAt(k).findViewById(R.id.tv_day_unclick);
                            int index = (7 - weekOfDay) + (j - 1) * 7 + k + 1;
                            bindDayView(tempCalendar2, index, tv_day_default, showTicketLayout, tv_day_showTicketLayout, tv_day_unclick);
                        } else {
                            oneLineLinearLayout.getChildAt(k).setVisibility(View.INVISIBLE);
                        }
                    }
                } else {
                    //中间几行
                    for (int k = 0; k < 7; k++) {
                        TextView tv_day_default = (TextView) oneLineLinearLayout.getChildAt(k).findViewById(R.id.tv_day_default);
                        LinearLayout showTicketLayout = (LinearLayout) oneLineLinearLayout.getChildAt(k).findViewById(R.id.showTicketLayout);
                        TextView tv_day_showTicketLayout = (TextView) oneLineLinearLayout.getChildAt(k).findViewById(R.id.tv_day_showTicketLayout);
                        TextView tv_ticket_showTicketLayout = (TextView) oneLineLinearLayout.getChildAt(k).findViewById(R.id.tv_ticket_showTicketLayout);
                        TextView tv_day_unclick = (TextView) oneLineLinearLayout.getChildAt(k).findViewById(R.id.tv_day_unclick);
                        int index = (7 - weekOfDay) + (j - 1) * 7 + k + 1;
                        bindDayView(tempCalendar2, index, tv_day_default, showTicketLayout, tv_day_showTicketLayout, tv_day_unclick);
                    }
                }
                layout.addView(oneLineLinearLayout);
            }
        }
    }

    /**
     * 获取一行 七天的LinearLayout
     *
     * @return LinearLayout 一行的layout
     */
    private LinearLayout get1LineDayLayout() {
        LinearLayout weekLayout = (LinearLayout) View.inflate(getContext(), R.layout.item_calendar_week, null);
        for (int i = 0; i < 7; i++) {
            RelativeLayout dayLayout = (RelativeLayout) View.inflate(getContext(), R.layout.item_calendar_day, null);
            LinearLayout.LayoutParams dayLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, (int) dayHeight, 1.0F);
            dayLayout.setLayoutParams(dayLayoutParams);
            dayLayout.findViewById(R.id.tv_day_default).setOnClickListener(this);
            dayLayout.findViewById(R.id.showTicketLayout).setOnClickListener(this);
            weekLayout.addView(dayLayout, i);
        }

        return weekLayout;
    }


    /**
     * 比较两个日期的大小
     *
     * @param paramCalendar1
     * @param paramCalendar2
     * @return
     */
    private int compareCal(Calendar paramCalendar1, Calendar paramCalendar2) {
        if (paramCalendar1.get(Calendar.YEAR) > paramCalendar2.get(Calendar.YEAR)) {
            return 1;
        } else if (paramCalendar1.get(Calendar.YEAR) < paramCalendar2.get(Calendar.YEAR)) {
            return -1;
        } else {
            if (paramCalendar1.get(Calendar.MONTH) > paramCalendar2.get(Calendar.MONTH)) {
                return 1;
            } else if (paramCalendar1.get(Calendar.MONTH) < paramCalendar2.get(Calendar.MONTH)) {
                return -1;
            } else {
                if (paramCalendar1.get(Calendar.DAY_OF_MONTH) > paramCalendar2.get(Calendar.DAY_OF_MONTH)) {
                    return 1;
                } else if (paramCalendar1.get(Calendar.DAY_OF_MONTH) < paramCalendar2.get(Calendar.DAY_OF_MONTH)) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    /**
     * 绑定每天View的数据
     */
    private void bindDayView(Calendar calendar, int index, TextView tv_day_default, View showTicketLayout, TextView tv_day_showTicketLayout, TextView tv_day_unclick) {
        tv_day_default.setText(index + "");
        tv_day_showTicketLayout.setText(index + "");
        tv_day_unclick.setText(index + "");
        //设置tag为日期的毫秒值在点击事件的时候会用到
        Calendar tempCalendar3 = (Calendar) calendar.clone();
        tempCalendar3.set(Calendar.DAY_OF_MONTH, index);
        tv_day_default.setTag(tempCalendar3.getTimeInMillis());
        showTicketLayout.setTag(tempCalendar3.getTimeInMillis());
        tv_day_unclick.setTag(tempCalendar3.getTimeInMillis());
        changeDayView(tempCalendar3, tv_day_default, showTicketLayout, tv_day_unclick);
    }

    /**
     * 修改每天的布局样式
     */
    private void changeDayView(Calendar calendar, View tv_day_default, View showTicketLayout, View tv_day_unclick) {

        tv_day_default.setVisibility(View.VISIBLE);

        if (compareCal(calendar, calendarToday) == -1) {// 小于当天
            tv_day_default.setVisibility(View.GONE);
            showTicketLayout.setVisibility(View.GONE);
            tv_day_unclick.setVisibility(View.VISIBLE);
        }
        if (compareCal(calendar, selectedDate) == 0) {// 选择日
            tv_day_default.setVisibility(View.GONE);
            showTicketLayout.setVisibility(View.VISIBLE);
            tv_day_unclick.setVisibility(View.GONE);
        }

        if (compareCal(calendar, endDate) == 1) {// 大于截止日
            tv_day_default.setVisibility(View.GONE);
            showTicketLayout.setVisibility(View.GONE);
            tv_day_unclick.setVisibility(View.VISIBLE);
        }
    }


    private View choosedView;

    @Override
    public void onClick(View view) {
        if (view.getTag() != null && choosedView != view) {
            if (choosedView != null) {
                choosedView.setBackgroundResource(R.drawable.bg_calendar_default);
            }
            view.setBackgroundResource(R.drawable.bg_calendar_seleced);
            choosedView = view;


            Calendar choosedCalendar = Calendar.getInstance();
            choosedCalendar.setTimeInMillis(((Long) view.getTag()).longValue());
            ToastUtils.getInstance(getContext()).showToast(choosedCalendar.get(Calendar.YEAR) + "年"
                    + (choosedCalendar.get(Calendar.MONTH) + 1) + "月"
                    + choosedCalendar.get(Calendar.DAY_OF_MONTH) + "日");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
