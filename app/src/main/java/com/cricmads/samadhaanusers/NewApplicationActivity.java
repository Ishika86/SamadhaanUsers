package com.cricmads.samadhaanusers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class NewApplicationActivity extends AppCompatActivity implements buttonClick {
    SharedPreferences preferences;
    String ApplicationId;
    NonSwipeableViewPager viewPager;
    DatabaseReference ApplicationRef;
    public static final String MY_SHARED_PREFERENCES = "MySharedPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_application);
        getSupportActionBar().setTitle(R.string.new_application);
        getSupportActionBar().setElevation(0);
        ApplicationId = UUID.randomUUID().toString();
        preferences = getSharedPreferences(MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ApplicationId", ApplicationId);
        editor.commit();
        ApplicationRef = FirebaseDatabase.getInstance().getReference().child("applications").child(ApplicationId);
        Application application = new Application("","","","","","","", "","", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "","");
        ApplicationRef.setValue(application);
        viewPager = (NonSwipeableViewPager) findViewById(R.id.viewpager);
        ApplicationAdapter adapter = new ApplicationAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onStop() {
        super.onStop();
        /*Intent intent = new Intent(NewApplicationActivity.this, MainActivity.class);
        startActivity(intent);
        ApplicationRef.removeValue();*/
    }
    public void buttonClicked(View v, int i){
        if (i==3){
            Intent intent = new Intent(NewApplicationActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else {
        //get your viewPager var
        viewPager.setCurrentItem(i);
        }
    }
}
