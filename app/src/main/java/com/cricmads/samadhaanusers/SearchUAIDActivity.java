package com.cricmads.samadhaanusers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchUAIDActivity extends AppCompatActivity {
    private TextView uaidTxt, udhyogNameValueTxt, NameValueTxt, udhyogDistrictValueTxt, udhyogStateValueTxt;
    private CardView cardView;
    private EditText inputText;
    private Button searchBtn;
    private String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_uaid);
        getSupportActionBar().hide();

        searchBtn = findViewById(R.id.search_button);
        inputText = findViewById(R.id.search_uaid_name);
        cardView = findViewById(R.id.card);
        uaidTxt = findViewById(R.id.uaid);
        udhyogNameValueTxt = findViewById(R.id.udhyogNameValue);
        NameValueTxt = findViewById(R.id.NameValue);
        udhyogDistrictValueTxt = findViewById(R.id.udhyogDistrictValue);
        udhyogStateValueTxt = findViewById(R.id.udhyogStateValue);
        cardView.setVisibility(View.GONE);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchUAIDActivity.this, AvailableActivity.class);
                intent.putExtra("uaid", username);
                startActivity(intent);

            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetails();


            }
        });
    }

    private void showDetails() {

        final String input;
        if (!inputText.getText().toString().isEmpty()){
            input = inputText.getText().toString();

            FirebaseDatabase.getInstance().getReference().child("profiles").child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    int flag = 0;
                    for (DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                        if (userSnapshot.child("udhyogAadhaar").getValue(String.class).equals(input)){
                            flag = 1;
                            cardView.setVisibility(View.VISIBLE);
                            username =  userSnapshot.getKey();
                            uaidTxt.setText(userSnapshot.child("udhyogAadhaar").getValue(String.class));
                            udhyogNameValueTxt.setText(userSnapshot.child("udyogName").getValue(String.class));
                            NameValueTxt.setText(userSnapshot.child("name").getValue(String.class));
                            udhyogDistrictValueTxt.setText(userSnapshot.child("district").getValue(String.class));
                            udhyogStateValueTxt.setText(userSnapshot.child("state").getValue(String.class));
                        }
                    }
                    if (flag==0){
                        Toast.makeText(SearchUAIDActivity.this, "Record not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }
}
