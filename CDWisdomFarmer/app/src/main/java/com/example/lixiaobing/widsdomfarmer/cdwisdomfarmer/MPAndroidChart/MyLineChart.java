package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MPAndroidChart;

import android.graphics.Color;
import android.graphics.Typeface;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/24.
 */
public class MyLineChart {
    private LineChart mLineChart;
    private ArrayList<String> xVals;
    private ArrayList<Entry> yVals;

    public MyLineChart(LineChart mLineChart) {
        this.mLineChart = mLineChart;
        this.mLineChart.setData(new LineData());
        this.mLineChart.invalidate();
    }

    public MyLineChart(ArrayList<String> xVals, ArrayList<Entry> yVals, LineChart mLineChart) {
        this.xVals = xVals;
        this.yVals = yVals;
        this.mLineChart = mLineChart;
    }

    public void initMyLineChart(){
        //与描述相关
        mLineChart.setDescription("MPAndroidChart");  // 与描述相关
        mLineChart.setNoDataTextDescription("You need to provide data for the chart");
        mLineChart.setDescriptionTextSize(20f);
        //与背景、颜色相关
        mLineChart.setBackgroundColor(Color.WHITE); // 整个表格的背景颜色
        //与触摸缩放相关
        mLineChart.setTouchEnabled(true); // 是否可触摸
        mLineChart.setDragEnabled(true); // 是否可拖拽
        mLineChart.setScaleEnabled(true); // 是否可缩放
        mLineChart.setDrawGridBackground(false);
        mLineChart.setHighlightPerDragEnabled(true);
        //与触摸点时显示标记相关

        //与横轴相关
        XAxis xAxis = mLineChart.getXAxis(); // 获得横轴实例
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 横轴在底部
        //与数据上下限相关
        LimitLine ll1 = new LimitLine(30f,"上限"); // 设置上限线 上限值为30.0
        ll1.setLineWidth(4f); // 线宽
        ll1.enableDashedLine(10f, 10f, 0f); //虚线
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP); // 上限 标签位置 在上线线右上
        ll1.setTextSize(10f);
        ll1.setTypeface(Typeface.DEFAULT);

        LimitLine ll2 = new LimitLine(0f,"下限"); // 设置上限线 下限值为0.0
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);
        ll2.setTypeface(Typeface.DEFAULT);
        //与左纵轴相关
        YAxis leftAxis = mLineChart.getAxisLeft(); // 获得左边的纵轴实例
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.addLimitLine(ll1); // 添加最大极限线
        leftAxis.addLimitLine(ll2); // 添加最小极限线
        leftAxis.setAxisMaxValue(40f); // 左纵轴最大值
        leftAxis.setAxisMinValue(-10f); // 左纵轴最小值
        leftAxis.enableGridDashedLine(10f,10f,0f); // 使表格里的横线为虚线
        leftAxis.setDrawZeroLine(false); //
        //与右纵轴相关
        mLineChart.getAxisRight().setEnabled(false); // 不显示右边的纵轴
        //与标注相关
        Legend legend = mLineChart.getLegend(); // 与标注相关
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTypeface(Typeface.DEFAULT); // 标注的字体类型
        legend.setTextSize(20f);
        legend.setTextColor(Color.RED);
        legend.setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT); // 图的标注的位置
        //与显示数据动画相关
        mLineChart.animateX(2000, Easing.EasingOption.EaseInOutQuart);  // 数据曲线依次出现的动画  第二个输入意思未知？
        //额外必须
        mLineChart.setPinchZoom(true); // must needed
        //与数据相关的初始化
        xVals = new ArrayList<String>();
        yVals = new ArrayList<Entry>();
    }

    public void setmLineChartData(String xValue,float yValue,int i,String legend){
        // 设置数据
        xVals.add(xValue+""); // 可制定横轴刻度
        yVals.add(new Entry(yValue,i));
        //LineDataSet set1 = new LineDataSet(yVals,"东区设备1的温度传感器");  // 纵轴数据和图的标注
        LineDataSet set1 = new LineDataSet(yVals,legend);  // 纵轴数据和图的标注
        set1.enableDashedLine(10f,0f,0f); // 设置曲线的类型
        // 第一个表示虚线段长度
        // 第二个表示虚线段间隔大小
        // 第三个表示虚线段 ？未知
        //根据这三个输入就可设置曲线的类型为实线、虚线、散点
        set1.enableDashedHighlightLine(10f,5f,0f); //  ? 未知
        set1.setColor(Color.RED); // 曲线的颜色
        set1.setLineWidth(1f);  // 曲线的宽度
        set1.setCircleColor(Color.RED); // 曲线中数据圆形点的颜色
        set1.setCircleRadius(6f); // 曲线中数据圆形点的半径 ，可设置大小
        set1.setDrawCircleHole(true); //  曲线中数据圆形点是否为空心
        set1.setValueTextSize(9f);  // 曲线中数据圆形点处显示数据字体大小
        set1.setDrawFilled(true); // 数据曲线下方是否有填充
        set1.setFillColor(Color.rgb(50,50,100)); // 设置填充颜色
        set1.setDrawCubic(true); // 曲线平滑

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1);
        LineData data = new LineData(xVals,dataSets);
        mLineChart.setData(data);  // 图标添加数据
        mLineChart.notifyDataSetChanged(); // 告诉mLineChart知道数据有变化
        mLineChart.setVisibleXRangeMaximum(6); //视野范围为6个数据
        mLineChart.moveViewTo(data.getXValCount()-7, 500f, YAxis.AxisDependency.LEFT);//先添加数据时mLineChart会在视野范围内自动移动
    }

    public void markPoint(MyMarkerView mv){
        mLineChart.setMarkerView(mv); // 设置标记
    }


}
