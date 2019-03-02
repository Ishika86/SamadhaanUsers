package com.cricmads.samadhaanusers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView applicationFiledTxt, totalAmountTxt, mutually_settledTxt, applicationPendingTxt, applicationRejectedTxt, applicationDisposedTxt;
    private int applicationFiled=0, totalAmount=0, mutually_settled=0, applicationPending=0, applicationRejected=0, applicationDisposed=0;
    DatabaseReference dataref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.Samadhaan);
        dataref = FirebaseDatabase.getInstance().getReference().child("applicationsData");
        applicationFiledTxt = findViewById(R.id.applicationFiled);
        totalAmountTxt = findViewById(R.id.totalAmount);
        mutually_settledTxt = findViewById(R.id.mutually_settled);
        applicationPendingTxt = findViewById(R.id.applicationPending);
        applicationRejectedTxt = findViewById(R.id.applicationRejected);
        applicationDisposedTxt = findViewById(R.id.applicationDisposed);

        dataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot applicationSnapshot : dataSnapshot.getChildren()){
                    String AppFiled = applicationSnapshot.child("ApplicationFiled").getValue(String.class);
                    int appFiledNo = Integer.parseInt(AppFiled);
                    applicationFiled = applicationFiled+appFiledNo;

                    String AppDisposed = applicationSnapshot.child("Applications disposed by MSEFC Council").getValue(String.class);
                    int appDisposedNo = Integer.parseInt(AppDisposed);
                    applicationDisposed = applicationDisposed+appDisposedNo;

                    String AppMutuallySettled = applicationSnapshot.child("Applications mutually settled").getValue(String.class);
                    int appMutuallySettledNo = Integer.parseInt(AppMutuallySettled);
                    mutually_settled = mutually_settled+appMutuallySettledNo;

                    String AppPending = applicationSnapshot.child("Applications pending").getValue(String.class);
                    int appPendingNo = Integer.parseInt(AppPending);
                    applicationPending = applicationPending+appPendingNo;

                    String AppRejected = applicationSnapshot.child("Applications rejected by MSEFC Council").getValue(String.class);
                    int appRejectedNo = Integer.parseInt(AppRejected);
                    applicationRejected = applicationRejected+appRejectedNo;

                    String AppAmount = applicationSnapshot.child("Total amount involved").getValue(String.class);
                    int appAmountNo = Integer.parseInt(AppAmount);
                    totalAmount = totalAmount+appAmountNo;
                }

                applicationFiledTxt.setText(String.valueOf(applicationFiled));
                totalAmountTxt.setText(String.valueOf(totalAmount));
                mutually_settledTxt.setText(String.valueOf(mutually_settled));
                applicationPendingTxt.setText(String.valueOf(applicationPending));
                applicationRejectedTxt.setText(String.valueOf(applicationRejected));
                applicationDisposedTxt.setText(String.valueOf(applicationDisposed));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

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
        getMenuInflater().inflate(R.menu.home, menu);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
