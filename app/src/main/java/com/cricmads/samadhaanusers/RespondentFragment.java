package com.cricmads.samadhaanusers;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class RespondentFragment extends Fragment {
    public static final String MY_SHARED_PREFERENCES = "MySharedPrefs";
    private EditText respondentName, respondentAddress, respondentDistrict, respondentPhone, respondentEmail, respondentAmount;
    private Spinner respondentStateSpinner;
    private String respondentState="";
    private ArrayAdapter<String> stateSpinnerAdapter;
    private Button next;
    DatabaseReference ApplicationRef;
    buttonClick click;

    public RespondentFragment() {
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_respondent, container, false);
        ApplicationRef = FirebaseDatabase.getInstance().getReference().child("applications").child(ApplicationId);
        respondentName = rootView.findViewById(R.id.editTextRespondentNameNewApplication);
        respondentAddress = rootView.findViewById(R.id.editTextRespondentAddressNewApplication);
        respondentDistrict = rootView.findViewById(R.id.editTextRespondentDistrictNewApplication);
        respondentPhone=rootView.findViewById(R.id.editTextRespondentPhoneNewApplication);
        respondentEmail = rootView.findViewById(R.id.editTextRespondentEmailNewApplication);
        respondentAmount = rootView.findViewById(R.id.editTextRespondentAmountNewApplication);
        respondentStateSpinner = rootView.findViewById(R.id.spinnerRespondentStateNewApplication);
        next = rootView.findViewById(R.id.Next_ButtonRespondent);


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
        respondentStateSpinner.setAdapter(stateSpinnerAdapter);
        respondentStateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    respondentState = selection;
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                respondentState = "Unknown"; // Unknown
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (respondentName.getText().toString().isEmpty()||respondentAddress.getText().toString().isEmpty()||respondentDistrict.getText().toString().isEmpty()||respondentState.isEmpty()||respondentPhone.getText().toString().isEmpty()||respondentEmail.getText().toString().isEmpty()||respondentAmount.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please enter all details", Toast.LENGTH_SHORT).show();
                }
                else {
                    ApplicationRef.child("respondentName").setValue(respondentName.getText().toString());
                    ApplicationRef.child("respondentAddress").setValue(respondentAddress.getText().toString());
                    ApplicationRef.child("respondentDistrict").setValue(respondentDistrict.getText().toString());
                    ApplicationRef.child("respondentState").setValue(respondentState);
                    ApplicationRef.child("respondentPhone").setValue(respondentPhone.getText().toString());
                    ApplicationRef.child("respondentEmail").setValue(respondentEmail.getText().toString());
                    ApplicationRef.child("respondentAmount").setValue(respondentAmount.getText().toString());
                    click.buttonClicked(v, 2);
                }
            }
        });


        return rootView;
    }

}
