package com.cricmads.samadhaanusers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class UserDetails3Activity extends AppCompatActivity {
    private EditText nameEditText, adhaarText, phoneEditText;
    private Button submit, previous;
    private Spinner genderSpinner, socialCategorySpinner;
    private String gender, socialCategory, physicallyChalleneged;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUsersDatabaseReference;
    private RadioGroup radioGroupPhysicallyChallenged;
    private FirebaseAuth mFirebaseAuth;
    private String mUserName;
    private String typeOfAccount, udhyogAdhaar, udhyogName, Address, District, pincode, noOfEmployee, state, businessActivity, typeOfOrg,pan,gstin,bankAcNo,IFSC;
    private ArrayAdapter<String> socialCategorySpinnerAdapter, genderSpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details3);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("3/3");
        typeOfAccount = getIntent().getStringExtra("typeOfAccount");
        mUserName = "";
        gender=""; socialCategory = "";physicallyChalleneged="";
        mUsersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("profiles").child("users");
        nameEditText = findViewById(R.id.editTextName);
        adhaarText = findViewById(R.id.editTextAdhaar);
        phoneEditText = findViewById(R.id.editTextPhone);
        submit = findViewById(R.id.submitButton);
        previous = findViewById(R.id.previous_Button);
        genderSpinner = findViewById(R.id.SpinnerGender);
        socialCategorySpinner = findViewById(R.id.SpinnerSocialCategory);
        udhyogAdhaar = getIntent().getStringExtra("udhyogAdhaar");
        udhyogName = getIntent().getStringExtra("udhyogName");
        Address = getIntent().getStringExtra("Address");
        District = getIntent().getStringExtra("District");
        pincode = getIntent().getStringExtra("pincode");
        noOfEmployee = getIntent().getStringExtra("noOfEmployee");
        state = getIntent().getStringExtra("state");
        businessActivity = getIntent().getStringExtra("businessActivity");
        pan = getIntent().getStringExtra("pan");
        gstin = getIntent().getStringExtra("gstin");
        bankAcNo = getIntent().getStringExtra("bankAcNo");
        IFSC = getIntent().getStringExtra("IFSC");
        typeOfOrg = getIntent().getStringExtra("typeOfOrg");
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        nameEditText.setText(user.getDisplayName());

        final ArrayList<String> genders = new ArrayList<String>();
        genders.add("Male");
        genders.add("Female");
        genders.add("Other");
        genderSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,genders);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        genderSpinner.setAdapter(genderSpinnerAdapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    gender = selection;
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                gender = "Unknown"; // Unknown
            }
        });

        final ArrayList<String> socialCategories = new ArrayList<String>();
        socialCategories.add(getString(R.string.general));
        socialCategories.add(getString(R.string.obc));
        socialCategories.add(getString(R.string.sc));
        socialCategories.add(getString(R.string.st));
        socialCategories.add(getString(R.string.minority));
        socialCategorySpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,socialCategories);
        socialCategorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        socialCategorySpinner.setAdapter(socialCategorySpinnerAdapter);
        socialCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    socialCategory = selection;
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                socialCategory = "Unknown"; // Unknown
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetails3Activity.this, UserDetails2Activity.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameEditText.getText().toString().isEmpty()||adhaarText.getText().toString().isEmpty()||phoneEditText.getText().toString().isEmpty()||gender.isEmpty()||socialCategory.isEmpty()||physicallyChalleneged.isEmpty()){
                    Toast.makeText(UserDetails3Activity.this, "Please Enter all details", Toast.LENGTH_LONG).show();
                }
                else {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String userEmailId = user.getEmail();
                    final String userName = userEmailId.replace(".",",");
                    mUsersDatabaseReference.child(userName).child("name").setValue(nameEditText.getText().toString());
                    mUsersDatabaseReference.child(userName).child("email").setValue(user.getEmail());
                    mUsersDatabaseReference.child(userName).child("udhyogAadhaar").setValue(udhyogAdhaar);
                    mUsersDatabaseReference.child(userName).child("aadhaar").setValue(adhaarText.getText().toString());
                    mUsersDatabaseReference.child(userName).child("udyogName").setValue(udhyogName);
                    mUsersDatabaseReference.child(userName).child("typeOfAccount").setValue(typeOfAccount);
                    mUsersDatabaseReference.child(userName).child("address").setValue(Address);
                    mUsersDatabaseReference.child(userName).child("pincode").setValue(pincode);
                    mUsersDatabaseReference.child(userName).child("gender").setValue(gender);
                    mUsersDatabaseReference.child(userName).child("phone").setValue(phoneEditText.getText().toString());
                    mUsersDatabaseReference.child(userName).child("socialCategory").setValue(socialCategory);
                    mUsersDatabaseReference.child(userName).child("physicallyHandicaped").setValue(physicallyChalleneged);
                    mUsersDatabaseReference.child(userName).child("typeOfOrganisation").setValue(typeOfOrg);
                    mUsersDatabaseReference.child(userName).child("panNumber").setValue(pan);
                    mUsersDatabaseReference.child(userName).child("gstin").setValue(gstin);
                    mUsersDatabaseReference.child(userName).child("district").setValue(District);
                    mUsersDatabaseReference.child(userName).child("state").setValue(state);
                    mUsersDatabaseReference.child(userName).child("bankAccountNumber").setValue(bankAcNo);
                    mUsersDatabaseReference.child(userName).child("ifsc").setValue(IFSC);
                    mUsersDatabaseReference.child(userName).child("businessActivity").setValue(businessActivity);
                    mUsersDatabaseReference.child(userName).child("noOfEmployess").setValue(noOfEmployee).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            String saveCurrentTime, saveCurrentDate;

                            Calendar calForDate = Calendar.getInstance();
                            SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                            saveCurrentDate = currentDate.format(calForDate.getTime());

                            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                            saveCurrentTime = currentTime.format(calForDate.getTime());
                            if (typeOfAccount.equals("Seller")){

                                Summary summary = new Summary("0", "6","30", "0");
                                String year = saveCurrentDate.substring(saveCurrentDate.length()-4, saveCurrentDate.length());
                                FirebaseDatabase.getInstance().getReference().child("orders").child(userName).child(year).child("summary").setValue(summary);
                            }

                            Bank bank = new Bank( udhyogAdhaar, "0", saveCurrentDate, saveCurrentDate, "0","0");
                            FirebaseDatabase.getInstance().getReference().child("bank").child(userName).setValue(bank).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Intent intent = new Intent(UserDetails3Activity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                        }
                    });





                }
            }
        });

    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioYes:
                if (checked){
                    physicallyChalleneged = "yes";
                    break;}
            case R.id.radioNo:
                if (checked){
                    physicallyChalleneged = "no";
                    break;}
        }
    }
}
