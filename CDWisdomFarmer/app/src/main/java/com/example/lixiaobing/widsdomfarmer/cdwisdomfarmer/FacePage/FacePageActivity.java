package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.FacePage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Device.DeviceActivity;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Explore.ExploreActivity;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.FeedBack.FeedBackActivity;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter.MonitoringCenterActivity;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.R;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Trigger.TriggerActivity;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.UserCenter.UserCenterActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FacePageActivity extends AppCompatActivity implements View.OnClickListener {

    private View v_user;
    private View v_listen;
    private View v_device;
    private View v_trigger;
    private View v_feedback;
    private View v_explore;

    private Intent intent ;

    private ImageButton user_Image;
    private ImageButton listen_Image;
    private ImageButton device_Image;
    private ImageButton trigger_Image;
    private ImageButton feedback_Image;
    private ImageButton explore_Image;

    private List<ImageView> imageContainer =null;
    private int scrollTimeOffset = 5000;
    private ViewPager viewPager;
    private static int autoIndex = 0;
    Timer timer = new Timer();
    private int[] imageIDs = new int[]{
            R.drawable.sunsine,
            R.drawable.water,
            R.drawable.eye,
    };
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    if(msg.arg1!=0){
                        // int newindex = viewPager.getCurrentItem()+1;
                        viewPager.setCurrentItem(msg.arg1);
                    }else{
                        viewPager.setCurrentItem(msg.arg1,false);
                    }
                    break;
                default:
                    break;
            }
        }
    };
    private LinearLayout dotLayout;
    private int prePosition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("智慧农夫");
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        v_user=findViewById(R.id.face_page_user_center_layout);
        v_listen=findViewById(R.id.face_page_monitoring_center_layout);
        v_device=findViewById(R.id.face_page_device_layout);
        v_trigger=findViewById(R.id.face_page_trriger_layout);
        v_feedback=findViewById(R.id.face_page_feedback_layout);
        v_explore=findViewById(R.id.face_page_explore_layout);

        v_user.setOnClickListener(this);
        v_listen.setOnClickListener(this);
        v_device.setOnClickListener(this);
        v_trigger.setOnClickListener(this);
        v_feedback.setOnClickListener(this);
        v_explore.setOnClickListener(this);

        user_Image =(ImageButton)findViewById(R.id.face_page_user_center_imageButton);
        listen_Image = (ImageButton)findViewById(R.id.face_page_monitoring_center_imageButton);
        listen_Image.setImageResource(R.drawable.eyej);
        listen_Image.setScaleType(ImageView.ScaleType.CENTER_INSIDE); // 图片太大太小可以这样来解决
        device_Image = (ImageButton)findViewById(R.id.face_page_device_imageButton);
        trigger_Image = (ImageButton)findViewById(R.id.face_page_trriger_imageButton);
        feedback_Image = (ImageButton)findViewById(R.id.face_page_feedback_imageButton);
        explore_Image = (ImageButton)findViewById(R.id.face_page_explore_imageButton);

        user_Image.setOnClickListener(this);
        listen_Image.setOnClickListener(this);
        device_Image.setOnClickListener(this);
        trigger_Image.setOnClickListener(this);
        feedback_Image.setOnClickListener(this);
        explore_Image.setOnClickListener(this);

        initView();
        startScrollThread();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.face_page_user_center_layout:
                intent = new Intent(this,UserCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.face_page_monitoring_center_layout :
                intent = new Intent(this,MonitoringCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.face_page_device_layout:
                intent = new Intent(this,DeviceActivity.class);
                startActivity(intent);
                break;
            case R.id.face_page_trriger_layout:
                intent = new Intent(this,TriggerActivity.class);
                startActivity(intent);
                break;
            case R.id.face_page_feedback_layout:
                intent = new Intent(this, FeedBackActivity.class);
                startActivity(intent);
                break;
            case R.id.face_page_explore_layout:
                intent = new Intent(this,ExploreActivity.class);
                startActivity(intent);
                break;
            //
            case R.id.face_page_user_center_imageButton:
                intent = new Intent(this,UserCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.face_page_monitoring_center_imageButton:
                intent = new Intent(this,MonitoringCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.face_page_device_imageButton:
                intent = new Intent(this,DeviceActivity.class);
                startActivity(intent);
                break;
            case R.id.face_page_trriger_imageButton:
                intent = new Intent(this,TriggerActivity.class);
                startActivity(intent);
                break;
            case R.id.face_page_feedback_imageButton:
                intent = new Intent(this,FeedBackActivity.class);
                startActivity(intent);
                break;
            case R.id.face_page_explore_imageButton:
                intent = new Intent(this,ExploreActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    private void initView() {

        dotLayout =(LinearLayout)findViewById(R.id.face_page_dot);

        viewPager = (ViewPager)findViewById(R.id.face_page_viewPager);
        imageContainer = new ArrayList<ImageView>();
        for(int id:imageIDs){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(id);
            imageContainer.add(imageView);


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(3,3);
            params.width=30;
            params.height=20;
            params.setMargins(5,0,5,0);
            ImageView dot = new ImageView(this);
            dot.setBackgroundResource(R.drawable.dot);
            dot.setEnabled(false);
            dot.setLayoutParams(params);
            dotLayout.addView(dot);

        }
        dotLayout.getChildAt(0).setEnabled(true);

        viewPager.setAdapter(new myViewPagerAdapter());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                dotLayout.getChildAt(prePosition).setEnabled(false);
                dotLayout.getChildAt(position).setEnabled(true);
                prePosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class myViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageContainer.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageContainer.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = imageContainer.get(position);
            container.addView(view);
            return view;
        }
    }

    private void startScrollThread() {

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                if(autoIndex == imageContainer.size()-1){
                    autoIndex = -1;
                };
                message.arg1=autoIndex+1;
                autoIndex=autoIndex+1;
                handler.sendMessage(message);
            }
        },scrollTimeOffset,scrollTimeOffset);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_face_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
