package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.ConnectWithUs.ConnectWithUsFragment;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Device.Device;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Device.DeviceActivity;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Device.DeviceFragment;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Explore.ExploreActivity;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Explore.ExploreFragment;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.FeedBack.FeedBackActivity;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.FeedBack.FeedBackFragmentMyWords;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.FeedBack.FeedBackRecyclerFragment;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter.mointoring_center_item.Item;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter.monitoring_center_adapters.ItemClickListener;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter.monitoring_center_adapters.Section;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter.monitoring_center_adapters.SectionedExpandableLayoutHelper;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.R;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Service.SubscribService;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Trigger.TriggerActivity;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Trigger.TriggerFragment;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.UserCenter.UserCenterActivity;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.UserCenter.UserFragment;

import java.io.FileInputStream;
import java.util.ArrayList;

public class MonitoringCenterActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ItemClickListener {

    private Intent intent;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;

    private static MonitoringCenterFragment monitoringCenterFragment;
    private UserFragment userFragment;
    private DeviceFragment deviceFragment;
    private TriggerFragment triggerFragment;
    private FeedBackFragmentMyWords feedBackFragmentMyWords;
    private ExploreFragment exploreFragment;
    private ConnectWithUsFragment connectWithUsFragment;

    private long exitTime = 0;

//    static Device  device;
//    public static Handler handler = new Handler() {
//        @Override
//        public  void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == 1) {
//                //
//                Log.d("MoniActivity---------->","订阅的消息来到handler中接受处理");
//                device = new Device();
//                device = (Device)msg.obj;
//                ArrayList<Item> arrayList0 = new ArrayList<>();
//                for(int i=0;i<device.getSensorDatas().size();i++){
//                    arrayList0.add(new Item(device.getSensorDatas().get(i).getValue(),""+device.getSensorDatas().get(i).getSensorsId()));
//                }
//                monitoringCenterFragment.sectionedExpandableLayoutHelper.addSection("lxbDevice",arrayList0);
//                monitoringCenterFragment.sectionedExpandableLayoutHelper.notifyDataSetChanged();
//            } else if (msg.what == 2) {
//                //
//            } else if (msg.what == 3) {
//                Log.d("handler-------------->"," ");
//                //
//            }
//        }
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring_center);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ////
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("监控中心");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView=navigationView.inflateHeaderView(R.layout.nav_header_monitoring_center);
        View headerImageView= headerView.findViewById(R.id.navigation_header_Image);
        if(headerImageView!=null){
            headerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    connectWithUsFragment=new ConnectWithUsFragment();
                    android.app.FragmentManager fragmentManager=getFragmentManager();
                    android.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container,connectWithUsFragment);
                    fragmentTransaction.commit();
                    toolbar.setTitle("智慧农夫");
                    drawer.closeDrawers();
                }
            });
        }
        ///////
        userFragment=new UserFragment(this);
        monitoringCenterFragment=new MonitoringCenterFragment(this,this);
        deviceFragment=new DeviceFragment(this);
        triggerFragment=new TriggerFragment();
        feedBackFragmentMyWords=new FeedBackFragmentMyWords();
        exploreFragment=new ExploreFragment();

        android.app.FragmentManager fragmentManager=getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,monitoringCenterFragment,"m");
        fragmentTransaction.addToBackStack("m");
        fragmentTransaction.commit();
        ////

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
//             super.onBackPressed();
            doExitApp();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.monitoring_center, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_userCenter) {
            android.app.FragmentManager fragmentManager=getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,userFragment,"u");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            toolbar.setTitle("用户中心");

            drawer.closeDrawer(GravityCompat.START);
            return false;
        } else if (id == R.id.nav_monitoringCenter) {
            android.app.FragmentManager fragmentManager=getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,monitoringCenterFragment,"m");
            fragmentTransaction.addToBackStack("m");
            fragmentTransaction.commit();
            toolbar.setTitle("监控中心");

            drawer.closeDrawer(GravityCompat.START);
            return false;
        } else if (id == R.id.nav_device) {
            android.app.FragmentManager fragmentManager=getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,deviceFragment,"d");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            toolbar.setTitle("设备");

            drawer.closeDrawer(GravityCompat.START);
            return false;
        } else if (id == R.id.nav_trigger) {
            android.app.FragmentManager fragmentManager=getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,triggerFragment,"t");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            toolbar.setTitle("触发器");

            drawer.closeDrawer(GravityCompat.START);
            return false;

        }  else if (id == R.id.nav_feedBack ) {
          //  FeedBackRecyclerFragment feedBackRecyclerFragment=new FeedBackRecyclerFragment(this);
            android.app.FragmentManager fragmentManager=getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,feedBackFragmentMyWords,"f");
           // fragmentTransaction.replace(R.id.fragment_container,feedBackRecyclerFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            toolbar.setTitle("反馈");

            drawer.closeDrawer(GravityCompat.START);
            return false;

        } else if (id == R.id.nav_explore) {
            android.app.FragmentManager fragmentManager=getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,exploreFragment,"e");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            toolbar.setTitle("探索");

            drawer.closeDrawer(GravityCompat.START);
            return false;
        } else if (id == R.id.nav_share) {
            return false;
        } else if (id == R.id.nav_send) {
            return false;
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this,SubscribService.class);
        startService(intent);
        Log.d("-------------->","启动服务");
        Log.d("activity-------------->","onstart");


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
//        Intent intent = new Intent(this, SubscribService.class);
//        stopService(intent);
    }

    @Override
    public void itemClicked(Section item) {
       //
    }

    @Override
    public void itemClicked(Item section) {
        intent = new Intent(this,MonitoringSensorsActivity.class);
        intent.putExtra("name",section.getSensorName());
        startActivity(intent);
    }

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this,"再按一次退出应用", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
           // super.onBackPressed();
        }
    }

}
