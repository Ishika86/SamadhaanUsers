package com.cricmads.samadhaanusers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AvailableActivity extends AppCompatActivity {
    String uaid;
    private EditText amount;
    DatabaseReference usersRef;
    private Button check, available, lock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available);

        uaid = getIntent().getStringExtra("uaid");
        usersRef = FirebaseDatabase.getInstance().getReference().child("bank").child(uaid);
        amount = findViewById(R.id.amount);
        check = findViewById(R.id.check);
        available = findViewById(R.id.status);
        lock = findViewById(R.id.lock);
        available.setVisibility(View.GONE);
        lock.setVisibility(View.GONE);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!amount.getText().toString().isEmpty()){
                    showOptions();
                }
            }
        });

        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AvailableActivity.this, LockActivity.class);
                intent.putExtra("userName", uaid);
                intent.putExtra("amount", amount.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void showOptions() {
        String amountString = amount.getText().toString();
        final int amountInt = Integer.parseInt(amountString);
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String balanceString = dataSnapshot.child("amount").getValue(String.class);
                int balance = Integer.parseInt(balanceString);
                if (amountInt<balance){
                    available.setText(getString(R.string.available));
                    available.setVisibility(View.VISIBLE);
                    lock.setVisibility(View.VISIBLE);
                }
                else {
                    available.setText("Not available");
                    available.setVisibility(View.VISIBLE);
                    lock.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
