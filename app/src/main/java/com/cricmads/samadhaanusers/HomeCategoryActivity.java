package com.cricmads.samadhaanusers;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeCategoryActivity extends AppCompatActivity {
    private TextView centralText, centralValue, stateText, stateValue, otherText, otherValue;
    String type;
    DatabaseReference dataRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_category);
        getSupportActionBar().setElevation(0);
        type = getIntent().getStringExtra("cardType");
        dataRef = FirebaseDatabase.getInstance().getReference().child("applicationsData");
        centralText = findViewById(R.id.central_text);
        centralValue = findViewById(R.id.central_value);
        stateText = findViewById(R.id.state_text);
        stateValue = findViewById(R.id.state_value);
        otherText = findViewById(R.id.other_text);
        otherValue = findViewById(R.id.other_value);
        if (type.equals("applicationFiledCard")){
            applicationFiledCard();

        }
        else if (type.equals("totalAmountCard")){
            totalAmountCard();

        }
        else if (type.equals("mutually_settledCard")){
            mutually_settledCard();

        }

        else if (type.equals("applicationPendingCard")){
            applicationPendingCard();

        }

        else if (type.equals("applicationRejectedCard")){
            applicationRejectedCard();

        }

        else if (type.equals("applicationDisposedCard")){
            applicationDisposedCard();

        }
    }

    private void applicationDisposedCard() {
        centralText.setText("Applications Disposed:");
        stateText.setText("Applications Disposed:");
        otherText.setText("Applications Disposed:");
        dataRef.child("central").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child(getString(R.string.applications_disposed_by_msefc_council)).getValue(String.class);
                centralValue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dataRef.child("state").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child(getString(R.string.applications_disposed_by_msefc_council)).getValue(String.class);
                stateValue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dataRef.child("other").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child(getString(R.string.applications_disposed_by_msefc_council)).getValue(String.class);
                otherValue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void applicationRejectedCard() {

        centralText.setText("Applications Rejected:");
        stateText.setText("Applications Rejected:");
        otherText.setText("Applications Rejected:");
        dataRef.child("central").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child(getString(R.string.applications_rejected_by_msefc)).getValue(String.class);
                centralValue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dataRef.child("state").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child(getString(R.string.applications_rejected_by_msefc)).getValue(String.class);
                stateValue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dataRef.child("other").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child(getString(R.string.applications_rejected_by_msefc)).getValue(String.class);
                otherValue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void applicationPendingCard() {

        centralText.setText(getString(R.string.applications_pending)+":");
        stateText.setText(getString(R.string.applications_pending)+":");
        otherText.setText(getString(R.string.applications_pending)+":");
        dataRef.child("central").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child(getString(R.string.applications_pending)).getValue(String.class);
                centralValue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dataRef.child("state").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child(getString(R.string.applications_pending)).getValue(String.class);
                stateValue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dataRef.child("other").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child(getString(R.string.applications_pending)).getValue(String.class);
                otherValue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void mutually_settledCard() {

        centralText.setText(getString(R.string.applications_mutually_settled)+":");
        stateText.setText(getString(R.string.applications_mutually_settled)+":");
        otherText.setText(getString(R.string.applications_mutually_settled)+":");
        dataRef.child("central").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child(getString(R.string.applications_mutually_settled)).getValue(String.class);
                centralValue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dataRef.child("state").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child(getString(R.string.applications_mutually_settled)).getValue(String.class);
                stateValue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dataRef.child("other").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child(getString(R.string.applications_mutually_settled)).getValue(String.class);
                otherValue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void totalAmountCard() {

        centralText.setText(getString(R.string.total_amount_involved)+":");
        stateText.setText(getString(R.string.total_amount_involved)+":");
        otherText.setText(getString(R.string.total_amount_involved)+":");
        dataRef.child("central").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child(getString(R.string.total_amount_involved)).getValue(String.class);
                centralValue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dataRef.child("state").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child(getString(R.string.total_amount_involved)).getValue(String.class);
                stateValue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dataRef.child("other").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child(getString(R.string.total_amount_involved)).getValue(String.class);
                otherValue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void applicationFiledCard() {
        centralText.setText(getString(R.string.applications_filed));
        stateText.setText(getString(R.string.applications_filed));
        otherText.setText(getString(R.string.applications_filed));
        dataRef.child("central").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("ApplicationFiled").getValue(String.class);
                centralValue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dataRef.child("state").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("ApplicationFiled").getValue(String.class);
                stateValue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dataRef.child("other").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("ApplicationFiled").getValue(String.class);
                otherValue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
