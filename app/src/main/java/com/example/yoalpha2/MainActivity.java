package com.example.yoalpha2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
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
        getMenuInflater().inflate(R.menu.main, menu);
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
            Intent settings = new Intent(this, SettingsActivity.class);
            startActivity(settings);
        }

        return super.onOptionsItemSelected(item);
    }

    public void switchView(int id) {
        if(findViewById(id) instanceof View) {
            return;
        }
        try {
            ((ViewGroup) findViewById(R.id.content_directory).getParent()).removeViewAt(1);
            return;
        } catch(Exception e) {}
        View appBarView = findViewById(R.id.app_bar_layout);
        ViewGroup viewParent = ((ViewGroup) appBarView.getParent());
        ViewGroup.LayoutParams layoutParams = viewParent.getChildAt(1).getLayoutParams();
        viewParent.removeViewAt(1);
        int layoutId = 0;
        switch(id) {
            case R.id.content_home:
                layoutId = R.layout.content_home;
                break;
            case R.id.content_announcements:
                layoutId = R.layout.content_announcements;
                break;
            case R.id.content_info:
                layoutId = R.layout.content_info;
                break;
            case R.id.content_club_sport:
                layoutId = R.layout.content_club_sport;
                break;
            case R.id.content_directory:
                layoutId = R.layout.content_directory;
                break;
            default:
                return;
        }
        LayoutInflater.from(appBarView.getContext()).inflate(layoutId, viewParent);
        //viewParent.addView(LayoutInflater.from(getApplicationContext()).inflate(layoutId, null), 1);
        //addContentView(LayoutInflater.from(getApplicationContext()).inflate(layoutId, null), layoutParams);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        return Util.switchTo(this, id);
    }
}
