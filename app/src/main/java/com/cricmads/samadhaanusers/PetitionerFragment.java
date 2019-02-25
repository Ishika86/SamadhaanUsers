package com.cricmads.samadhaanusers;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.UUID;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class PetitionerFragment extends Fragment {
    public static final String MY_SHARED_PREFERENCES = "MySharedPrefs";
    private EditText udhyogAdhaar, udhyogName, address, district, name, adhaar, phone, email;
    private Spinner stateSpinner, councilSpinner, socialCategorySpinner, typeOfPetitionerSpinner;
    private ArrayAdapter<String> stateSpinnerAdapter, councilSpinnerAdapter, socialCategorySpinnerAdapter, typeOfPetitionerSpinnerAdapter;
    private String state, council, socialCategory, typeOfPetitioner;
    private Button next;
    DatabaseReference ApplicationRef;
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





        final ArrayList<String> councils = new ArrayList<String>();
        councils.add("MSEFC"+getString(R.string.andhraPradesh));
        councils.add("MSEFC"+getString(R.string.arunachalPradesh));
        councils.add("MSEFC"+getString(R.string.assam));
        councils.add("MSEFC"+getString(R.string.bihar));
        councils.add("MSEFC"+getString(R.string.chattisgarh));
        councils.add("MSEFC"+getString(R.string.goa));
        councils.add("MSEFC"+getString(R.string.gujarat));
        councils.add("MSEFC"+getString(R.string.haryana));
        councils.add("MSEFC"+getString(R.string.himachalPradesh));
        councils.add("MSEFC"+getString(R.string.jammuAndKashmir));
        councils.add("MSEFC"+getString(R.string.Jharkhand));
        councils.add("MSEFC"+getString(R.string.karnataka));
        councils.add("MSEFC"+getString(R.string.Kerala));
        councils.add("MSEFC"+getString(R.string.madhyaPradesh));
        councils.add("MSEFC"+getString(R.string.maharashtra));
        councils.add("MSEFC"+getString(R.string.manipur));
        councils.add("MSEFC"+getString(R.string.meghalaya));
        councils.add("MSEFC"+getString(R.string.mizoram));
        councils.add("MSEFC"+getString(R.string.nagaland));
        councils.add("MSEFC"+getString(R.string.odhisha));
        councils.add("MSEFC"+getString(R.string.punjab));
        councils.add("MSEFC"+getString(R.string.rajasthan));
        councils.add("MSEFC"+getString(R.string.sikkim));
        councils.add("MSEFC"+getString(R.string.tamilNadu));
        councils.add("MSEFC"+getString(R.string.telangana));
        councils.add("MSEFC"+getString(R.string.Tripura));
        councils.add("MSEFC"+getString(R.string.uttarakhand));
        councils.add("MSEFC"+getString(R.string.UttarPradesh));
        councils.add("MSEFC"+getString(R.string.WestBengal));
        councils.add("MSEFC"+getString(R.string.AndamanAndNicobarIslands));
        councils.add("MSEFC"+getString(R.string.DadraAndNagarHaveli));
        councils.add("MSEFC"+getString(R.string.NCTofDelhi));
        councils.add("MSEFC"+getString(R.string.puducherry));
        councils.add("MSEFC"+getString(R.string.chandigarh));
        councils.add("MSEFC"+getString(R.string.damanAndDiu));
        councils.add("MSEFC"+getString(R.string.lakshadweep));
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






        final ArrayList<String> socialCategories = new ArrayList<String>();
        socialCategories.add(getString(R.string.general));
        socialCategories.add(getString(R.string.obc));
        socialCategories.add(getString(R.string.sc));
        socialCategories.add(getString(R.string.st));
        socialCategories.add(getString(R.string.minority));
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
