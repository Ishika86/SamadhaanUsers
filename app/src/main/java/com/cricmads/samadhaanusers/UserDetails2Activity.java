package com.cricmads.samadhaanusers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserDetails2Activity extends AppCompatActivity {
    private EditText PANEditText, GSTINEditText, BankAccNoEditText, IFSCtEditText;
    private Button next, previous;
    private String udhyogAdhaar, udhyogName, Address, District, pincode, noOfEmployee, state, businessActivity, typeOfOrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details2);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("2/3");
        udhyogAdhaar = getIntent().getStringExtra("udhyogAdhaar");
        udhyogName = getIntent().getStringExtra("udhyogName");
        Address = getIntent().getStringExtra("Address");
        District = getIntent().getStringExtra("District");
        pincode = getIntent().getStringExtra("pincode");
        noOfEmployee = getIntent().getStringExtra("noOfEmployee");
        state = getIntent().getStringExtra("state");
        businessActivity = getIntent().getStringExtra("businessActivity");
        typeOfOrg = getIntent().getStringExtra("typeOfOrg");
        next = findViewById(R.id.nextButton);
        previous = findViewById(R.id.previousButton);
        PANEditText = findViewById(R.id.editTextPAN);
        GSTINEditText = findViewById(R.id.editTextGSTIN);
        BankAccNoEditText = findViewById(R.id.editTextBankAccountNumber);
        IFSCtEditText = findViewById(R.id.editTextIFSC);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetails2Activity.this, UserDetailsActivity.class);
                startActivity(intent);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PANEditText.getText().toString().isEmpty()||GSTINEditText.getText().toString().isEmpty()||BankAccNoEditText.getText().toString().isEmpty()||IFSCtEditText.getText().toString().isEmpty()){
                    Toast.makeText(UserDetails2Activity.this, "Please Enter all details", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(UserDetails2Activity.this, UserDetails3Activity.class);
                    intent.putExtra("udhyogAdhaar", udhyogAdhaar);
                    intent.putExtra("udhyogName", udhyogName);
                    intent.putExtra("Address", Address);
                    intent.putExtra("District", District);
                    intent.putExtra("pincode",pincode );
                    intent.putExtra("noOfEmployee",noOfEmployee );
                    intent.putExtra("state", state);
                    intent.putExtra("businessActivity",businessActivity );
                    intent.putExtra("typeOfOrg", typeOfOrg);
                    intent.putExtra("pan", PANEditText.getText().toString());
                    intent.putExtra("gstin", GSTINEditText.getText().toString());
                    intent.putExtra("bankAcNo",BankAccNoEditText.getText().toString());
                    intent.putExtra("IFSC", IFSCtEditText.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }



}
