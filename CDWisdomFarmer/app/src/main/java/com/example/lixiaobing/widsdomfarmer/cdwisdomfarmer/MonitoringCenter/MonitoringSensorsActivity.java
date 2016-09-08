package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MPAndroidChart.MyLineChart;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MPAndroidChart.MyMarkerView;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.R;
import com.github.mikephil.charting.charts.LineChart;

public class MonitoringSensorsActivity extends AppCompatActivity {

    private TextView textView;
    private LineChart mLineChart;
    private MyLineChart myLineChart;
    private MyMarkerView myMarkerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring_sensors);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Intent intent =getIntent();
        String sensorName = intent.getStringExtra("name");
        toolbar.setTitle(sensorName);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        textView = (TextView)findViewById(R.id.History_Data_Current_data_text);

        mLineChart=(LineChart)findViewById(R.id.line_chart);
        myLineChart=new MyLineChart(mLineChart);
        myLineChart.initMyLineChart();
        myMarkerView=new MyMarkerView(this,R.layout.custom_marker_view);
        myLineChart.markPoint(myMarkerView);

        for(int i=0;i<12;i++){
            String xValue =""+i;
            float yValue=(float)(Math.random()*30);
            myLineChart.setmLineChartData(xValue,yValue,i,"");
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
