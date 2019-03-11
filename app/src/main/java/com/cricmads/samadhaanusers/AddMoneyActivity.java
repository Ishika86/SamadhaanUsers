package com.cricmads.samadhaanusers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddMoneyActivity extends AppCompatActivity {
    private TextView liquid, freezed;
    private EditText editText;
    private Button add;
    int Totalamount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
        liquid = findViewById(R.id.amountFree);
        freezed = findViewById(R.id.amountLocked);
        editText = findViewById(R.id.editText);
        add = findViewById(R.id.add);
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        final String userName = email.replace(".",",");
        FirebaseDatabase.getInstance().getReference().child("bank").child(userName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String amountStr = dataSnapshot.child("amount").getValue(String.class);
                String lockedStr = dataSnapshot.child("locked").getValue(String.class);
                Totalamount = Integer.parseInt(amountStr);
                int locked = Integer.parseInt(lockedStr);
                liquid.setText("Liquid: "+amountStr);
                freezed.setText("Freezed:"+lockedStr);
                //Totalamount = Totalamount - Integer.parseInt(amount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().isEmpty()){
                    Toast.makeText(AddMoneyActivity.this, "Enter valid amount", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        int input  = Integer.parseInt(editText.getText().toString());
                        Totalamount = Totalamount + input;
                        FirebaseDatabase.getInstance().getReference().child("bank").child(userName).child("amount").setValue(String.valueOf(Totalamount));
                    }
                    catch (Exception e){

                    }
                }
            }
        });
    }
}
