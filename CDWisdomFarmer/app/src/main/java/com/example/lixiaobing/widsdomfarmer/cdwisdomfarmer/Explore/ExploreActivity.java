package com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Explore;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Device.Device;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Device.DeviceActivity;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.FeedBack.FeedBackActivity;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.MonitoringCenter.MonitoringCenterActivity;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.R;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.Trigger.TriggerActivity;
import com.example.lixiaobing.widsdomfarmer.cdwisdomfarmer.UserCenter.UserCenterActivity;

public class ExploreActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Intent intent;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.explore, menu);
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
            // Handle the camera action
            intent=new Intent(this, UserCenterActivity.class);
            drawer.closeDrawer(GravityCompat.START);
            startActivity(intent);
            return false;
        } else if (id == R.id.nav_monitoringCenter) {
            intent=new Intent(this, MonitoringCenterActivity.class);
            drawer.closeDrawer(GravityCompat.START);
            startActivity(intent);
            return false;
        } else if (id == R.id.nav_device) {
            intent=new Intent(this, DeviceActivity.class);
            drawer.closeDrawer(GravityCompat.START);
            startActivity(intent);
            return false;
        } else if (id == R.id.nav_trigger) {
            intent=new Intent(this, TriggerActivity.class);
            drawer.closeDrawer(GravityCompat.START);
            startActivity(intent);
            return false;

        }  else if (id == R.id.nav_feedBack ) {
            intent=new Intent(this, FeedBackActivity.class);
            drawer.closeDrawer(GravityCompat.START);
            startActivity(intent);
            return false;

        } else if (id == R.id.nav_explore) {
            intent=new Intent(this, ExploreActivity.class);
            drawer.closeDrawer(GravityCompat.START);
            startActivity(intent);
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
