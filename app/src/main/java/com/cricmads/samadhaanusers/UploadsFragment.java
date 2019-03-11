package com.cricmads.samadhaanusers;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class UploadsFragment extends Fragment {
    public static final String MY_SHARED_PREFERENCES = "MySharedPrefs";
    private EditText transId, workOrder1,dateWorkOrder1, workOrder2, workOrder3,dateWorkOrder2, dateWorkOrder3, Invoice1, Invoice2, Invoice3, Invoice1date, Invoice2date, Invoice3date;
    private String workOrderURL="", InvoiceURL="";
    private StorageReference storageWorkOrdersRef;
    private StorageReference storageInvoiceRef;
    private Button submit, WorkOrderUploadBtn, InvoiceUploadBtn;
    private TextView workOrderStatus, InvoiceStatus;
    DatabaseReference ApplicationRef;
    buttonClick click;
    final static int PICK_PDF_CODE_WORKORDER = 2342;
    final static int PICK_PDF_CODE_INVOICE = 2341;
    private String userName;
    public UploadsFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_uploads, container, false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmailId = user.getEmail();
        userName = userEmailId.replace(".",",");

        workOrderStatus = rootView.findViewById(R.id.workOrderUploadStatus);
        InvoiceStatus = rootView.findViewById(R.id.invoiceUploadStatus);
        ApplicationRef = FirebaseDatabase.getInstance().getReference().child("applications").child(ApplicationId);
        storageWorkOrdersRef = FirebaseStorage.getInstance().getReference().child("workOrders");
        storageInvoiceRef = FirebaseStorage.getInstance().getReference().child("invoices");
        workOrder1 = rootView.findViewById(R.id.editTextWorkOrder1NewApplication);
        //transId = rootView.findViewById(R.id.editTextTransactionIdNewApplication);
        workOrder2 = rootView.findViewById(R.id.editTextWorkOrder2NewApplication);
        workOrder3 = rootView.findViewById(R.id.editTextWorkOrder3NewApplication);
        dateWorkOrder1 = rootView.findViewById(R.id.editTextWorkOrder1DateNewApplication);
        dateWorkOrder2 = rootView.findViewById(R.id.editTextWorkOrder2DateNewApplication);
        dateWorkOrder3 = rootView.findViewById(R.id.editTextWorkOrder3DateNewApplication);
        Invoice1 = rootView.findViewById(R.id.editTextInvoice1NewApplication);
        Invoice2 = rootView.findViewById(R.id.editTextInvoice2NewApplication);
        Invoice3 = rootView.findViewById(R.id.editTextInvoice3NewApplication);
        Invoice1date = rootView.findViewById(R.id.editTextInvoice1DateNewApplication);
        Invoice2date = rootView.findViewById(R.id.editTextInvoice2DateNewApplication);
        Invoice3date = rootView.findViewById(R.id.editTextInvoice3DateNewApplication);
        submit = rootView.findViewById(R.id.Submit_Button);
        WorkOrderUploadBtn = rootView.findViewById(R.id.uploadWorkOrdersBtn);
        InvoiceUploadBtn = rootView.findViewById(R.id.uploadInvoiceBtn);
        WorkOrderUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:" + "com.cricmads.samadhaanusers"));
                    startActivity(intent);
                    return;
                }*/

                //creating an intent for file chooser
                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PDF_CODE_WORKORDER);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(workOrder1.getText().toString().isEmpty()||dateWorkOrder1.getText().toString().isEmpty()||Invoice1.getText().toString().isEmpty()||Invoice1date.getText().toString().isEmpty()||workOrderURL.isEmpty()||InvoiceURL.isEmpty()){
                    Toast.makeText(getActivity(), "Please enter all details", Toast.LENGTH_SHORT).show();
                }
                else {
                    ApplicationRef.child("workOrder1Number").setValue(workOrder1.getText().toString());
                    ApplicationRef.child("workOrder1Date").setValue(dateWorkOrder1.getText().toString());
                    if (!(workOrder2.getText().toString().isEmpty()||dateWorkOrder2.getText().toString().isEmpty())){
                        ApplicationRef.child("workOrder2Number").setValue(workOrder2.getText().toString());
                        ApplicationRef.child("workOrder2Date").setValue(dateWorkOrder2.getText().toString());
                    }
                    if (!(workOrder3.getText().toString().isEmpty()||dateWorkOrder3.getText().toString().isEmpty())){
                        ApplicationRef.child("workOrder3Number").setValue(workOrder3.getText().toString());
                        ApplicationRef.child("workOrder3Date").setValue(dateWorkOrder3.getText().toString());
                    }

                    ApplicationRef.child("invoice1Number").setValue(Invoice1.getText().toString());
                    ApplicationRef.child("invoice1Date").setValue(Invoice1date.getText().toString());
                    if (!(Invoice2.getText().toString().isEmpty()||Invoice2date.getText().toString().isEmpty())){
                        ApplicationRef.child("invoice2Number").setValue(Invoice2.getText().toString());
                        ApplicationRef.child("invoice2Date").setValue(Invoice2date.getText().toString());

                    }
                    if (!(Invoice3.getText().toString().isEmpty()||Invoice3date.getText().toString().isEmpty())){
                        ApplicationRef.child("invoice3Number").setValue(Invoice3.getText().toString());
                        ApplicationRef.child("invoice3Date").setValue(Invoice3date.getText().toString());

                    }

                    ApplicationRef.child("workOrderPDFurl").setValue(workOrderURL);
                    ApplicationRef.child("invoicePDFurl").setValue(InvoiceURL);
                    String saveCurrentTime, saveCurrentDate;

                    Calendar calForDate = Calendar.getInstance();
                    SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                    saveCurrentDate = currentDate.format(calForDate.getTime());

                    SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                    saveCurrentTime = currentTime.format(calForDate.getTime());
                    ApplicationRef.child("applicationDate").setValue(saveCurrentDate);
                    ApplicationRef.child("applicationTime").setValue(saveCurrentTime);
                    ApplicationRef.child("status").setValue(getString(R.string.pending_approval));
                    ApplicationRef.child("applicantUsername").setValue(userName);
                    ApplicationRef.child("escalated").setValue("no");

                    FirebaseDatabase.getInstance().getReference().child("applicationsData").child("central").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String appNo = dataSnapshot.child("ApplicationFiled").getValue(String.class);

                          int appNumber = Integer.parseInt(appNo);
                          appNumber++;
                          final String applicationNumber = String.valueOf(appNumber);
                          ApplicationRef.child("applicationNumber").setValue(applicationNumber).addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {
                                  ApplicationRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                      @Override
                                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                          final Application application = dataSnapshot.getValue(Application.class);
                                          FirebaseDatabase.getInstance().getReference().child("allApplications").child(applicationNumber).setValue(application).addOnCompleteListener(new OnCompleteListener<Void>() {
                                              @Override
                                              public void onComplete(@NonNull Task<Void> task) {
                                                  ApplicationRef.removeValue();
                                                  //FirebaseDatabase.getInstance().getReference().child("applicationsData").child("totalApplications").setValue(applicationNumber);
                                                  FirebaseDatabase.getInstance().getReference().child("applicationsData").child("central").addListenerForSingleValueEvent(new ValueEventListener() {
                                                      @Override
                                                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                          String pending = dataSnapshot.child("Applications pending").getValue(String.class);
                                                          int pendingApps = Integer.parseInt(pending);
                                                          pendingApps++;
                                                          String pendingApplications = String.valueOf(pendingApps);
                                                          //FirebaseDatabase.getInstance().getReference().child("applicationsData").child("pendingApproval").setValue(pendingApplications);
                                                          FirebaseDatabase.getInstance().getReference().child("profiles").child("officers").child(application.getCouncil()).child("Support Team")
                                                                  .addListenerForSingleValueEvent(new ValueEventListener() {
                                                                      String pending, assignedTo;
                                                                      int pendingApps= Integer.MAX_VALUE;
                                                                      @Override
                                                                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                          for (DataSnapshot supportSnapshot: dataSnapshot.getChildren()){
                                                                              String pendingNo = supportSnapshot.child("pending").getValue(String.class);
                                                                              int pendingNumber = Integer.parseInt(pendingNo);
                                                                              if (pendingNumber<pendingApps){
                                                                                  pendingApps = pendingNumber;
                                                                                  assignedTo = supportSnapshot.getKey();
                                                                              }
                                                                          }
                                                                          pending = String.valueOf(pendingApps);
                                                                          DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("allApplications").child(applicationNumber);
                                                                          ref.child("assignedToAndStatus").setValue(assignedTo+application.getStatus());
                                                                          ref.child("usernameAndStatus").setValue(userName+application.getStatus());
                                                                          FirebaseDatabase.getInstance().getReference().child("allApplications").child(applicationNumber).child("assignedTo").setValue(assignedTo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                              @Override
                                                                              public void onComplete(@NonNull Task<Void> task) {
                                                                                  FirebaseDatabase.getInstance().getReference().child("profiles").child("officers").child(application.getCouncil()).child("Support Team").child(assignedTo).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                      @Override
                                                                                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                          String pending = dataSnapshot.child("pending").getValue(String.class);
                                                                                          int pendingNumber = Integer.parseInt(pending);
                                                                                          pendingNumber++;
                                                                                          String newPending = String.valueOf(pendingNumber);
                                                                                          FirebaseDatabase.getInstance().getReference().child("profiles").child("officers").child(application.getCouncil()).child("Support Team").child(assignedTo).child("pending").setValue(newPending).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                              @Override
                                                                                              public void onComplete(@NonNull Task<Void> task) {
                                                                                                  Toast.makeText(getActivity(), "Application Number: " +applicationNumber, Toast.LENGTH_SHORT).show();
                                                                                                  click.buttonClicked(v, 3);
                                                                                              }
                                                                                          });
                                                                                      }

                                                                                      @Override
                                                                                      public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                                      }
                                                                                  });
                                                                              }
                                                                          });

                                                                      }

                                                                      @Override
                                                                      public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                      }
                                                                  });
                                                      }

                                                      @Override
                                                      public void onCancelled(@NonNull DatabaseError databaseError) {

                                                      }
                                                  });
                                              }
                                          });
                                      }

                                      @Override
                                      public void onCancelled(@NonNull DatabaseError databaseError) {

                                      }
                                  });
                              }
                          });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }
        });

        InvoiceUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:" + "com.cricmads.samadhaanusers"));
                    startActivity(intent);
                    return;
                }*/

                //creating an intent for file chooser
                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PDF_CODE_INVOICE);
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //when the user choses the file
        if (requestCode == PICK_PDF_CODE_WORKORDER && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //if a file is selected
            if (data.getData() != null) {
                //uploading the file
                uploadWorkOrderFile(data.getData());
            }else{
                Toast.makeText(getActivity(), "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == PICK_PDF_CODE_INVOICE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //if a file is selected
            if (data.getData() != null) {
                //uploading the file
                uploadInvoiceFile(data.getData());
            }else{
                Toast.makeText(getActivity(), "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void  uploadInvoiceFile(Uri data){

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Uploading File");
        progressDialog.setMessage("Please wait, while we are uploading your PDF file");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        final StorageReference sRef = storageInvoiceRef.child(userName).child(System.currentTimeMillis()+".pdf");
        sRef.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        sRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri downloadUri = uri;
                                InvoiceURL = downloadUri.toString();
                                progressDialog.dismiss();
                                InvoiceStatus.setText(getString(R.string.uploaded_successfully));
                                InvoiceStatus.setTextColor(Color.GREEN);
                                ApplicationRef.child("invoicePDFurl").setValue(InvoiceURL);
                            }
                        });

                    }
                });

    }
    private void uploadWorkOrderFile(Uri data){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Uploading File");
        progressDialog.setMessage("Please wait, while we are uploading your PDF file");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        final StorageReference sRef = storageWorkOrdersRef.child(userName).child(System.currentTimeMillis()+".pdf");
        sRef.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        sRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri downloadUri = uri;
                                workOrderURL = downloadUri.toString();
                                progressDialog.dismiss();
                                workOrderStatus.setText(getString(R.string.uploaded_successfully));
                                workOrderStatus.setTextColor(Color.GREEN);
                                ApplicationRef.child("workOrderPDFurl").setValue(workOrderURL);
                            }
                        });

                    }
                });
    }
}
