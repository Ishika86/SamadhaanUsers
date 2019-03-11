package com.cricmads.samadhaanusers;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class LockActivity extends AppCompatActivity {
    PhoneAuthProvider.OnVerificationStateChangedCallbacks manufacturerCallback, sellerCallback;
    private boolean sellerVerified, manufacturerVerified;
    private EditText manufacturer, seller;
    private Button manufacturerCheck, sellerCheck, manufacturerGenerate, sellerGenerate, lock;
    private TextView manufacturerStatus, sellerStatus, amountText;
    private String sellerPhone, manufacturerPhone;
    DatabaseReference usersRef, transRef;
    String userNameSeller, userNameManufacturer, amount, sellerCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        sellerVerified = false;
        manufacturerVerified = false;
        sellerPhone = ""; manufacturerPhone = "";
        userNameSeller = getIntent().getStringExtra("userName");
        amount = getIntent().getStringExtra("amount");
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        String saveCurrentDate = currentDate.format(calForDate.getTime());
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String year = saveCurrentDate.substring(saveCurrentDate.length()-4, saveCurrentDate.length());
        userNameManufacturer = email.replace(".",",");
        transRef = FirebaseDatabase.getInstance().getReference().child("orders").child(userNameSeller).child(year);
        usersRef = FirebaseDatabase.getInstance().getReference().child("profiles").child("users");
        usersRef.child(userNameManufacturer).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                manufacturerPhone = dataSnapshot.child("phone").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        lock = findViewById(R.id.lock);
        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String random = UUID.randomUUID().toString();
                Calendar calForDate = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                 String saveCurrentDate = currentDate.format(calForDate.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                String saveCurrentTime = currentTime.format(calForDate.getTime());

                Transaction transaction = new Transaction(saveCurrentDate, saveCurrentTime, amount, userNameSeller, random, "Pending", userNameManufacturer);
                transRef.child("transactions").child(random).setValue(transaction);

                FirebaseDatabase.getInstance().getReference().child("bank").child(userNameSeller).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String amountStr = dataSnapshot.child("amount").getValue(String.class);
                        String lockedStr = dataSnapshot.child("locked").getValue(String.class);
                        int Totalamount = Integer.parseInt(amountStr);
                        int locked = Integer.parseInt(lockedStr);
                        Totalamount = Totalamount - Integer.parseInt(amount);
                        locked = locked + Integer.parseInt(amount);
                        FirebaseDatabase.getInstance().getReference().child("bank").child(userNameSeller).child("amount").setValue(String.valueOf(Totalamount));
                        FirebaseDatabase.getInstance().getReference().child("bank").child(userNameSeller).child("locked").setValue(String.valueOf(locked));
                        Intent intent  = new Intent(LockActivity.this, MainActivity.class);
                        startActivity(intent);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
        usersRef.child(userNameSeller).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sellerPhone = dataSnapshot.child("phone").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        manufacturer = findViewById(R.id.manufacturer);
        seller = findViewById(R.id.seller);
        amountText = findViewById(R.id.amountTxt);
        manufacturerCheck = findViewById(R.id.checkManufacturer);
        sellerCheck = findViewById(R.id.checkSeller);
        manufacturerGenerate = findViewById(R.id.generateManufacturer);
        sellerGenerate = findViewById(R.id.generateSeller);

        manufacturerStatus = findViewById(R.id.manufacturerStatus);
        sellerStatus = findViewById(R.id.sellerStatus);
        amountText.setText(amount);
        amountText.setVisibility(View.GONE);
        lock.setVisibility(View.GONE);
        seller.setVisibility(View.GONE);
        sellerCheck.setVisibility(View.GONE);
        sellerGenerate.setVisibility(View.GONE);
        sellerStatus.setVisibility(View.GONE);
        manufacturerGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PhoneAuthProvider.getInstance().verifyPhoneNumber(manufacturerPhone, 60, TimeUnit.SECONDS, LockActivity.this, manufacturerCallback);

            }
        });
        sellerGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PhoneAuthProvider.getInstance().verifyPhoneNumber(sellerPhone, 60, TimeUnit.SECONDS, LockActivity.this, sellerCallback);

            }
        });
        manufacturerCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(LockActivity.this, "Manufacturer verified", Toast.LENGTH_SHORT).show();
                manufacturerVerified = true;
                seller.setVisibility(View.VISIBLE);
                sellerCheck.setVisibility(View.VISIBLE);
                sellerGenerate.setVisibility(View.VISIBLE);
                sellerStatus.setVisibility(View.VISIBLE);
                manufacturerCheck.setVisibility(View.GONE);
                manufacturerGenerate.setVisibility(View.GONE);
                manufacturer.setVisibility(View.GONE);
                manufacturerStatus.setText(" Manufacturer Verified");
                manufacturerStatus.setTextSize(24);
                manufacturerStatus.setTextColor(Color.parseColor("#003d00"));
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }
        };
        sellerCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(LockActivity.this, "Seller verified", Toast.LENGTH_SHORT).show();
                sellerVerified = true;
                sellerStatus.setText("Verifed");
                sellerStatus.setTextColor(Color.parseColor("#003d00"));
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                sellerCode = s;
                Toast.makeText(LockActivity.this, "Code is sent to the seller, please enter the code and press Check button", Toast.LENGTH_LONG).show();
            }
        };
        sellerCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sellerVerified== false){
                    if (seller.getText().toString().isEmpty()){
                        Toast.makeText(LockActivity.this, "Enter the seller code", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(sellerCode, seller.getText().toString().trim());
                        if (seller.getText().toString().trim().equals(credential.getSmsCode())){

                            amountText.setVisibility(View.VISIBLE);
                            lock.setVisibility(View.VISIBLE);
                            sellerVerified = true;
                            sellerCheck.setVisibility(View.GONE);
                            sellerGenerate.setVisibility(View.GONE);
                            seller.setVisibility(View.GONE);
                            sellerStatus.setTextSize(24);
                            sellerStatus.setText("Seller is Verifed");
                            sellerStatus.setTextColor(Color.parseColor("#003d00"));
                        }
                    }
                }
            }
        });
    }
}
