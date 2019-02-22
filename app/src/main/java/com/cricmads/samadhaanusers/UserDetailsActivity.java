package com.cricmads.samadhaanusers;

import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class UserDetailsActivity extends AppCompatActivity {
    private EditText udhyogAdhaarEditText, udhyogNameEditText, AddressEditText, DistrictEditText, pincodeEditText, noOfEmployeeEditText;
    private Spinner stateSpinner, businessActivitySpinner, typeOfOrgSpinner;
    private String state, businessActivity, typeOfOrg;
    private ArrayAdapter<String> stateSpinnerAdapter, businessActivitySpinnerAdapter, typeOfOrgSpinnerAdapter;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("1/3");
        state=""; businessActivity=""; typeOfOrg="";
        udhyogAdhaarEditText = findViewById(R.id.editTextUdhyogAdhaar);
        next = findViewById(R.id.Next_Button);
        udhyogNameEditText = findViewById(R.id.editTextUdhyogName);
        AddressEditText = findViewById(R.id.editTextUdhyogAddress);
        DistrictEditText = findViewById(R.id.editTextUdhyogDistrict);
        pincodeEditText = findViewById(R.id.editTextUdhyogPincode);
        noOfEmployeeEditText = findViewById(R.id.editTextNoOfEmployees);
        stateSpinner = findViewById(R.id.spinnerState);
        businessActivitySpinner = findViewById(R.id.spinnerBusinessActivity);
        typeOfOrgSpinner = findViewById(R.id.spinnerTypeOfOrg);


        final ArrayList<String> states = new ArrayList<String>();
        states.add(getString(R.string.andhraPradesh));
        states.add(getString(R.string.arunachalPradesh));
        states.add(getString(R.string.assam));
        states.add(getString(R.string.bihar));
        states.add(getString(R.string.chattisgarh));
        states.add(getString(R.string.goa));
        states.add(getString(R.string.gujarat));
        states.add(getString(R.string.haryana));
        states.add(getString(R.string.himachalPradesh));
        states.add(getString(R.string.jammuAndKashmir));
        states.add(getString(R.string.Jharkhand));
        states.add(getString(R.string.karnataka));
        states.add(getString(R.string.Kerala));
        states.add(getString(R.string.madhyaPradesh));
        states.add(getString(R.string.maharashtra));
        states.add(getString(R.string.manipur));
        states.add(getString(R.string.meghalaya));
        states.add(getString(R.string.mizoram));
        states.add(getString(R.string.nagaland));
        states.add(getString(R.string.odhisha));
        states.add(getString(R.string.punjab));
        states.add(getString(R.string.rajasthan));
        states.add(getString(R.string.sikkim));
        states.add(getString(R.string.tamilNadu));
        states.add(getString(R.string.telangana));
        states.add(getString(R.string.Tripura));
        states.add(getString(R.string.uttarakhand));
        states.add(getString(R.string.UttarPradesh));
        states.add(getString(R.string.WestBengal));
        states.add(getString(R.string.AndamanAndNicobarIslands));
        states.add(getString(R.string.DadraAndNagarHaveli));
        states.add(getString(R.string.NCTofDelhi));
        states.add(getString(R.string.puducherry));
        states.add(getString(R.string.chandigarh));
        states.add(getString(R.string.damanAndDiu));
        states.add(getString(R.string.lakshadweep));
        stateSpinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,states);
        stateSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        stateSpinner.setAdapter(stateSpinnerAdapter);
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    state = selection;
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                state = "Unknown"; // Unknown
            }
        });


        final ArrayList<String> businessActivities = new ArrayList<String>();
        businessActivities.add(getString(R.string.manufacturing));
        businessActivities.add(getString(R.string.service));
        businessActivitySpinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,businessActivities);
        businessActivitySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        businessActivitySpinner.setAdapter(businessActivitySpinnerAdapter);
        businessActivitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    businessActivity = selection;
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                businessActivity = "Unknown"; // Unknown
            }
        });

        final ArrayList<String> typeOfOrgs = new ArrayList<String>();
        typeOfOrgs.add(getString(R.string.proprietorship));
        typeOfOrgs.add(getString(R.string.partnership));
        typeOfOrgs.add(getString(R.string.limited_liability_partnership));
        typeOfOrgs.add(getString(R.string.cooperativeSociety));
        typeOfOrgs.add(getString(R.string.private_limited));
        typeOfOrgs.add(getString(R.string.public_limited));
        typeOfOrgs.add(getString(R.string.self_help_group));
        typeOfOrgs.add(getString(R.string.society));
        typeOfOrgs.add(getString(R.string.trust));
        typeOfOrgSpinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,typeOfOrgs);
        typeOfOrgSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        typeOfOrgSpinner.setAdapter(typeOfOrgSpinnerAdapter);
        typeOfOrgSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    typeOfOrg = selection;
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                typeOfOrg = "Unknown"; // Unknown
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (udhyogAdhaarEditText.getText().toString().isEmpty()||udhyogNameEditText.getText().toString().isEmpty()|| AddressEditText.getText().toString().isEmpty()||DistrictEditText.getText().toString().isEmpty()||pincodeEditText.getText().toString().isEmpty()||noOfEmployeeEditText.getText().toString().isEmpty()||state.isEmpty()||businessActivity.isEmpty()||typeOfOrg.isEmpty()){

                    Toast.makeText(UserDetailsActivity.this, "Please Enter all details", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(UserDetailsActivity.this, UserDetails2Activity.class);
                    intent.putExtra("udhyogAdhaar", udhyogAdhaarEditText.getText().toString());
                    intent.putExtra("udhyogName", udhyogNameEditText.getText().toString());
                    intent.putExtra("Address", AddressEditText.getText().toString());
                    intent.putExtra("District", DistrictEditText.getText().toString());
                    intent.putExtra("pincode", pincodeEditText.getText().toString());
                    intent.putExtra("noOfEmployee", noOfEmployeeEditText.getText().toString());
                    intent.putExtra("state", state);
                    intent.putExtra("businessActivity",businessActivity );
                    intent.putExtra("typeOfOrg", typeOfOrg);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

}
