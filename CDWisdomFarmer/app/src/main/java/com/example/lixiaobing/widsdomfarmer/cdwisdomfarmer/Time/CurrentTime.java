package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Time;

import java.util.Calendar;

/**
 * Created by lixiaobing on 2016/6/14.
 */
public class CurrentTime {
    private  int year;
    private  int month;
    private  int date;
    private  int hour;
    private  int minute;
    private  int second;

    public CurrentTime() {
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        this.year = c.get(Calendar.YEAR);
        this.month = c.get(Calendar.MONTH)+1;
        this.date = c.get(Calendar.DATE);
        this.hour = c.get(Calendar.HOUR_OF_DAY);
        this.minute = c.get(Calendar.MINUTE);
        this.second = c.get(Calendar.SECOND);
    }

    public int getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return ""+year+"-"+month+"-"+date+"\n"+hour+":"+minute+":"+second;
    }
}
