package com.cricmads.samadhaanusers;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class PetitionerFragment extends Fragment {
    public static final String MY_SHARED_PREFERENCES = "MySharedPrefs";
    private EditText udhyogAdhaar, udhyogName, address, district, name, adhaar, phone, email;
    private Spinner stateSpinner, councilSpinner, socialCategorySpinner, typeOfPetitionerSpinner, categorySpinner, ministrySpinner;
    private ArrayAdapter<String> stateSpinnerAdapter, councilSpinnerAdapter, socialCategorySpinnerAdapter, typeOfPetitionerSpinnerAdapter, ministrySpinnerAdapter, categorySpinnerAdapter;
    private String state, council, socialCategory, typeOfPetitioner, category, ministry;
    private Button next;
    DatabaseReference ApplicationRef, usersRef;
    String userName;
    buttonClick click;

    public PetitionerFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        click = (buttonClick) activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SharedPreferences myPreferences = this.getActivity().getSharedPreferences(MY_SHARED_PREFERENCES , MODE_PRIVATE);
        String ApplicationId = myPreferences.getString("ApplicationId", null);
        state=""; council=""; socialCategory=""; typeOfPetitioner="";
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_petitioner, container, false);
        ApplicationRef = FirebaseDatabase.getInstance().getReference().child("applications").child(ApplicationId);
        usersRef = FirebaseDatabase.getInstance().getReference().child("profiles").child("users");

        udhyogAdhaar = rootView.findViewById(R.id.editTextUdhyogAdhaarNewApplication);
        udhyogName = rootView.findViewById(R.id.editTextUdhyogNameNewApplication);
        district = rootView.findViewById(R.id.editTextUdhyogDistrictNewApplication);
        address = rootView.findViewById(R.id.editTextUdhyogAddressNewApplication);
        name = rootView.findViewById(R.id.editTextNameNewApplication);
        adhaar = rootView.findViewById(R.id.editTextAdhaarNewApplication);
        phone = rootView.findViewById(R.id.editTextPhoneNewApplication);
        email = rootView.findViewById(R.id.editTextEmailNewApplication);
        stateSpinner = rootView.findViewById(R.id.spinnerStateNewApplication);
        councilSpinner = rootView.findViewById(R.id.spinnerCouncilNewApplication);
        typeOfPetitionerSpinner = rootView.findViewById(R.id.spinnertypeOfPetitionerNewApplication);
        socialCategorySpinner = rootView.findViewById(R.id.SpinnerSocialCategoryNewApplication);
        next = rootView.findViewById(R.id.Next_Button);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmailId = user.getEmail();
        userName = userEmailId.replace(".",",");
        usersRef.child(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            UserData userData = dataSnapshot.getValue(UserData.class);
            udhyogAdhaar.setText(userData.getUdhyogAadhaar());
            udhyogAdhaar.setEnabled(false);
            udhyogName.setText(userData.getUdyogName());
            udhyogName.setEnabled(false);
            address.setText(userData.getAddress());
            address.setEnabled(false);
            district.setText(userData.getDistrict());
            district.setEnabled(false);
            name.setText(userData.getName());
            name.setEnabled(false);
            adhaar.setText(userData.getAadhaar());
            adhaar.setEnabled(false);
            phone.setText(userData.getPhone());
            phone.setEnabled(false);
            email.setText(userData.getEmail());
            email.setEnabled(false);
            state = userData.getState();
                final ArrayList<String> states = new ArrayList<String>();
                states.add(state);
                stateSpinnerAdapter = new ArrayAdapter<String>(getActivity(),
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
                stateSpinner.setEnabled(false);

                final ArrayList<String> councils = new ArrayList<String>();
                councils.add("MSEFC "+state);
                councilSpinnerAdapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item,councils);
                councilSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                councilSpinner.setAdapter(councilSpinnerAdapter);
                councilSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selection = (String) parent.getItemAtPosition(position);
                        if (!TextUtils.isEmpty(selection)) {
                            council = selection;
                        }
                    }

                    // Because AdapterView is an abstract class, onNothingSelected must be defined
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        council = "Unknown"; // Unknown
                    }
                });
                socialCategory = userData.getSocialCategory();
                councilSpinner.setEnabled(false);
                final ArrayList<String> socialCategories = new ArrayList<String>();
                socialCategories.add(socialCategory);
                socialCategorySpinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,socialCategories);
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
                socialCategorySpinner.setEnabled(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

















        final ArrayList<String> typeOfPetitioners = new ArrayList<String>();
        typeOfPetitioners.add(getString(R.string.micro));
        typeOfPetitioners.add(getString(R.string.small));
        typeOfPetitionerSpinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,typeOfPetitioners);
        typeOfPetitionerSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        typeOfPetitionerSpinner.setAdapter(typeOfPetitionerSpinnerAdapter);
        typeOfPetitionerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    typeOfPetitioner = selection;
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                typeOfPetitioner = "Unknown"; // Unknown
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (udhyogAdhaar.getText().toString().isEmpty()||udhyogName.getText().toString().isEmpty()||address.getText().toString().isEmpty()||district.getText().toString().isEmpty()||state.isEmpty()||name.getText().toString().isEmpty()||adhaar.getText().toString().isEmpty()||phone.getText().toString().isEmpty()||socialCategory.isEmpty()||email.getText().toString().isEmpty()||council.isEmpty()||typeOfPetitioner.isEmpty()){
                    Toast.makeText(getActivity(), "Please enter all details", Toast.LENGTH_SHORT).show();
                }
                else {


                    ApplicationRef.child("udhyogAdhaar").setValue(udhyogAdhaar.getText().toString());
                    ApplicationRef.child("udhyogName").setValue(udhyogName.getText().toString());
                    ApplicationRef.child("address").setValue(address.getText().toString());
                    ApplicationRef.child("district").setValue(district.getText().toString());
                    ApplicationRef.child("state").setValue(state);
                    ApplicationRef.child("name").setValue(name.getText().toString());
                    ApplicationRef.child("adhaar").setValue(adhaar.getText().toString());
                    ApplicationRef.child("phone").setValue(phone.getText().toString());
                    ApplicationRef.child("socialCategory").setValue(socialCategory);
                    ApplicationRef.child("email").setValue(email.getText().toString());
                    ApplicationRef.child("council").setValue(council);
                    ApplicationRef.child("typeOfPetitioner").setValue(typeOfPetitioner);
                    click.buttonClicked(v, 1);


                }
            }
        });

        return rootView;
    }

}
