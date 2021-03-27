package com.niranisugar.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skydoves.powerspinner.PowerSpinnerView;

public class ResellSugarSignUpActivity extends Activity {

    EditText edtCompanyName, edtAddress, edtCity, edtState, edtPincode, edtCompanyEmail, edtTelephoneNo, edtFaxNo, edtGSTNo;
    EditText edtFirstName, edtLastName, edtDesignation, edtMobileNo, edtEmailID;
    PowerSpinnerView spCompanyType;
    TextView tvBirthDate;

    LinearLayout llCompanyName, llAddress, llCity, llState, llPincode, llCompanyEmail, llTelephoneNo, llFaxNo, llGSTNo;
    LinearLayout llFirstName, llLastName, llDesignation, llMobileNo, llEmailID;
    LinearLayout llCompanyType, llBirthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_resell_sugar);

        findViews();

        edtCompanyName.setOnFocusChangeListener((view, b) -> {
            if (b) {
                llCompanyName.setBackgroundResource(R.color.colorPrimary);
                llCompanyType.setBackgroundResource(R.color.gray);
                llAddress.setBackgroundResource(R.color.gray);
                llCity.setBackgroundResource(R.color.gray);
                llState.setBackgroundResource(R.color.gray);
                llPincode.setBackgroundResource(R.color.gray);
                llCompanyEmail.setBackgroundResource(R.color.gray);
                llTelephoneNo.setBackgroundResource(R.color.gray);
                llFaxNo.setBackgroundResource(R.color.gray);
                llGSTNo.setBackgroundResource(R.color.gray);
                llFirstName.setBackgroundResource(R.color.gray);
                llLastName.setBackgroundResource(R.color.gray);
                llDesignation.setBackgroundResource(R.color.gray);
                llBirthDate.setBackgroundResource(R.color.gray);
                llMobileNo.setBackgroundResource(R.color.gray);
                llEmailID.setBackgroundResource(R.color.gray);
            }
        });

        spCompanyType.setOnFocusChangeListener((view, b) -> {
            if(b) {
                llCompanyName.setBackgroundResource(R.color.gray);
                llCompanyType.setBackgroundResource(R.color.colorPrimary);
                llAddress.setBackgroundResource(R.color.gray);
                llCity.setBackgroundResource(R.color.gray);
                llState.setBackgroundResource(R.color.gray);
                llPincode.setBackgroundResource(R.color.gray);
                llCompanyEmail.setBackgroundResource(R.color.gray);
                llTelephoneNo.setBackgroundResource(R.color.gray);
                llFaxNo.setBackgroundResource(R.color.gray);
                llGSTNo.setBackgroundResource(R.color.gray);
                llFirstName.setBackgroundResource(R.color.gray);
                llLastName.setBackgroundResource(R.color.gray);
                llDesignation.setBackgroundResource(R.color.gray);
                llBirthDate.setBackgroundResource(R.color.gray);
                llMobileNo.setBackgroundResource(R.color.gray);
                llEmailID.setBackgroundResource(R.color.gray);
            }
        });

        edtAddress.setOnFocusChangeListener((view, b) -> {
            if(b) {
                llCompanyName.setBackgroundResource(R.color.gray);
                llCompanyType.setBackgroundResource(R.color.gray);
                llAddress.setBackgroundResource(R.color.colorPrimary);
                llCity.setBackgroundResource(R.color.gray);
                llState.setBackgroundResource(R.color.gray);
                llPincode.setBackgroundResource(R.color.gray);
                llCompanyEmail.setBackgroundResource(R.color.gray);
                llTelephoneNo.setBackgroundResource(R.color.gray);
                llFaxNo.setBackgroundResource(R.color.gray);
                llGSTNo.setBackgroundResource(R.color.gray);
                llFirstName.setBackgroundResource(R.color.gray);
                llLastName.setBackgroundResource(R.color.gray);
                llDesignation.setBackgroundResource(R.color.gray);
                llBirthDate.setBackgroundResource(R.color.gray);
                llMobileNo.setBackgroundResource(R.color.gray);
                llEmailID.setBackgroundResource(R.color.gray);
            }
        });

        edtCity.setOnFocusChangeListener((view, b) -> {
            if(b) {
                llCompanyName.setBackgroundResource(R.color.gray);
                llCompanyType.setBackgroundResource(R.color.gray);
                llAddress.setBackgroundResource(R.color.gray);
                llCity.setBackgroundResource(R.color.colorPrimary);
                llState.setBackgroundResource(R.color.gray);
                llPincode.setBackgroundResource(R.color.gray);
                llCompanyEmail.setBackgroundResource(R.color.gray);
                llTelephoneNo.setBackgroundResource(R.color.gray);
                llFaxNo.setBackgroundResource(R.color.gray);
                llGSTNo.setBackgroundResource(R.color.gray);
                llFirstName.setBackgroundResource(R.color.gray);
                llLastName.setBackgroundResource(R.color.gray);
                llDesignation.setBackgroundResource(R.color.gray);
                llBirthDate.setBackgroundResource(R.color.gray);
                llMobileNo.setBackgroundResource(R.color.gray);
                llEmailID.setBackgroundResource(R.color.gray);
            }
        });

        edtState.setOnFocusChangeListener((view, b) -> {
            if(b) {
                llCompanyName.setBackgroundResource(R.color.gray);
                llCompanyType.setBackgroundResource(R.color.gray);
                llAddress.setBackgroundResource(R.color.gray);
                llCity.setBackgroundResource(R.color.gray);
                llState.setBackgroundResource(R.color.colorPrimary);
                llPincode.setBackgroundResource(R.color.gray);
                llCompanyEmail.setBackgroundResource(R.color.gray);
                llTelephoneNo.setBackgroundResource(R.color.gray);
                llFaxNo.setBackgroundResource(R.color.gray);
                llGSTNo.setBackgroundResource(R.color.gray);
                llFirstName.setBackgroundResource(R.color.gray);
                llLastName.setBackgroundResource(R.color.gray);
                llDesignation.setBackgroundResource(R.color.gray);
                llBirthDate.setBackgroundResource(R.color.gray);
                llMobileNo.setBackgroundResource(R.color.gray);
                llEmailID.setBackgroundResource(R.color.gray);
            }
        });

        edtPincode.setOnFocusChangeListener((view, b) -> {
            if(b) {
                llCompanyName.setBackgroundResource(R.color.gray);
                llCompanyType.setBackgroundResource(R.color.gray);
                llAddress.setBackgroundResource(R.color.gray);
                llCity.setBackgroundResource(R.color.gray);
                llState.setBackgroundResource(R.color.gray);
                llPincode.setBackgroundResource(R.color.colorPrimary);
                llCompanyEmail.setBackgroundResource(R.color.gray);
                llTelephoneNo.setBackgroundResource(R.color.gray);
                llFaxNo.setBackgroundResource(R.color.gray);
                llGSTNo.setBackgroundResource(R.color.gray);
                llFirstName.setBackgroundResource(R.color.gray);
                llLastName.setBackgroundResource(R.color.gray);
                llDesignation.setBackgroundResource(R.color.gray);
                llBirthDate.setBackgroundResource(R.color.gray);
                llMobileNo.setBackgroundResource(R.color.gray);
                llEmailID.setBackgroundResource(R.color.gray);
            }
        });

        edtCompanyEmail.setOnFocusChangeListener((view, b) -> {
            if(b) {
                llCompanyName.setBackgroundResource(R.color.gray);
                llCompanyType.setBackgroundResource(R.color.gray);
                llAddress.setBackgroundResource(R.color.gray);
                llCity.setBackgroundResource(R.color.gray);
                llState.setBackgroundResource(R.color.gray);
                llPincode.setBackgroundResource(R.color.gray);
                llCompanyEmail.setBackgroundResource(R.color.colorPrimary);
                llTelephoneNo.setBackgroundResource(R.color.gray);
                llFaxNo.setBackgroundResource(R.color.gray);
                llGSTNo.setBackgroundResource(R.color.gray);
                llFirstName.setBackgroundResource(R.color.gray);
                llLastName.setBackgroundResource(R.color.gray);
                llDesignation.setBackgroundResource(R.color.gray);
                llBirthDate.setBackgroundResource(R.color.gray);
                llMobileNo.setBackgroundResource(R.color.gray);
                llEmailID.setBackgroundResource(R.color.gray);
            }
        });

        edtTelephoneNo.setOnFocusChangeListener((view, b) -> {
            if(b) {
                llCompanyName.setBackgroundResource(R.color.gray);
                llCompanyType.setBackgroundResource(R.color.gray);
                llAddress.setBackgroundResource(R.color.gray);
                llCity.setBackgroundResource(R.color.gray);
                llState.setBackgroundResource(R.color.gray);
                llPincode.setBackgroundResource(R.color.gray);
                llCompanyEmail.setBackgroundResource(R.color.gray);
                llTelephoneNo.setBackgroundResource(R.color.colorPrimary);
                llFaxNo.setBackgroundResource(R.color.gray);
                llGSTNo.setBackgroundResource(R.color.gray);
                llFirstName.setBackgroundResource(R.color.gray);
                llLastName.setBackgroundResource(R.color.gray);
                llDesignation.setBackgroundResource(R.color.gray);
                llBirthDate.setBackgroundResource(R.color.gray);
                llMobileNo.setBackgroundResource(R.color.gray);
                llEmailID.setBackgroundResource(R.color.gray);
            }
        });

        edtFaxNo.setOnFocusChangeListener((view, b) -> {
            if(b) {
                llCompanyName.setBackgroundResource(R.color.gray);
                llCompanyType.setBackgroundResource(R.color.gray);
                llAddress.setBackgroundResource(R.color.gray);
                llCity.setBackgroundResource(R.color.gray);
                llState.setBackgroundResource(R.color.gray);
                llPincode.setBackgroundResource(R.color.gray);
                llCompanyEmail.setBackgroundResource(R.color.gray);
                llTelephoneNo.setBackgroundResource(R.color.gray);
                llFaxNo.setBackgroundResource(R.color.colorPrimary);
                llGSTNo.setBackgroundResource(R.color.gray);
                llFirstName.setBackgroundResource(R.color.gray);
                llLastName.setBackgroundResource(R.color.gray);
                llDesignation.setBackgroundResource(R.color.gray);
                llBirthDate.setBackgroundResource(R.color.gray);
                llMobileNo.setBackgroundResource(R.color.gray);
                llEmailID.setBackgroundResource(R.color.gray);
            }
        });

        edtGSTNo.setOnFocusChangeListener((view, b) -> {
            if(b) {
                llCompanyName.setBackgroundResource(R.color.gray);
                llCompanyType.setBackgroundResource(R.color.gray);
                llAddress.setBackgroundResource(R.color.gray);
                llCity.setBackgroundResource(R.color.gray);
                llState.setBackgroundResource(R.color.gray);
                llPincode.setBackgroundResource(R.color.gray);
                llCompanyEmail.setBackgroundResource(R.color.gray);
                llTelephoneNo.setBackgroundResource(R.color.gray);
                llFaxNo.setBackgroundResource(R.color.gray);
                llGSTNo.setBackgroundResource(R.color.colorPrimary);
                llFirstName.setBackgroundResource(R.color.gray);
                llLastName.setBackgroundResource(R.color.gray);
                llDesignation.setBackgroundResource(R.color.gray);
                llBirthDate.setBackgroundResource(R.color.gray);
                llMobileNo.setBackgroundResource(R.color.gray);
                llEmailID.setBackgroundResource(R.color.gray);
            }
        });

        edtFirstName.setOnFocusChangeListener((view, b) -> {
            if(b) {
                llCompanyName.setBackgroundResource(R.color.gray);
                llCompanyType.setBackgroundResource(R.color.gray);
                llAddress.setBackgroundResource(R.color.gray);
                llCity.setBackgroundResource(R.color.gray);
                llState.setBackgroundResource(R.color.gray);
                llPincode.setBackgroundResource(R.color.gray);
                llCompanyEmail.setBackgroundResource(R.color.gray);
                llTelephoneNo.setBackgroundResource(R.color.gray);
                llFaxNo.setBackgroundResource(R.color.gray);
                llGSTNo.setBackgroundResource(R.color.gray);
                llFirstName.setBackgroundResource(R.color.colorPrimary);
                llLastName.setBackgroundResource(R.color.gray);
                llDesignation.setBackgroundResource(R.color.gray);
                llBirthDate.setBackgroundResource(R.color.gray);
                llMobileNo.setBackgroundResource(R.color.gray);
                llEmailID.setBackgroundResource(R.color.gray);
            }
        });

        edtLastName.setOnFocusChangeListener((view, b) -> {
            if(b) {
                llCompanyName.setBackgroundResource(R.color.gray);
                llCompanyType.setBackgroundResource(R.color.gray);
                llAddress.setBackgroundResource(R.color.gray);
                llCity.setBackgroundResource(R.color.gray);
                llState.setBackgroundResource(R.color.gray);
                llPincode.setBackgroundResource(R.color.gray);
                llCompanyEmail.setBackgroundResource(R.color.gray);
                llTelephoneNo.setBackgroundResource(R.color.gray);
                llFaxNo.setBackgroundResource(R.color.gray);
                llGSTNo.setBackgroundResource(R.color.gray);
                llFirstName.setBackgroundResource(R.color.gray);
                llLastName.setBackgroundResource(R.color.colorPrimary);
                llDesignation.setBackgroundResource(R.color.gray);
                llBirthDate.setBackgroundResource(R.color.gray);
                llMobileNo.setBackgroundResource(R.color.gray);
                llEmailID.setBackgroundResource(R.color.gray);
            }
        });

        edtDesignation.setOnFocusChangeListener((view, b) -> {
            if(b) {
                llCompanyName.setBackgroundResource(R.color.gray);
                llCompanyType.setBackgroundResource(R.color.gray);
                llAddress.setBackgroundResource(R.color.gray);
                llCity.setBackgroundResource(R.color.gray);
                llState.setBackgroundResource(R.color.gray);
                llPincode.setBackgroundResource(R.color.gray);
                llCompanyEmail.setBackgroundResource(R.color.gray);
                llTelephoneNo.setBackgroundResource(R.color.gray);
                llFaxNo.setBackgroundResource(R.color.gray);
                llGSTNo.setBackgroundResource(R.color.gray);
                llFirstName.setBackgroundResource(R.color.gray);
                llLastName.setBackgroundResource(R.color.gray);
                llDesignation.setBackgroundResource(R.color.colorPrimary);
                llBirthDate.setBackgroundResource(R.color.gray);
                llMobileNo.setBackgroundResource(R.color.gray);
                llEmailID.setBackgroundResource(R.color.gray);
            }
        });

        tvBirthDate.setOnFocusChangeListener((view, b) -> {
            if(b) {
                llCompanyName.setBackgroundResource(R.color.gray);
                llCompanyType.setBackgroundResource(R.color.gray);
                llAddress.setBackgroundResource(R.color.gray);
                llCity.setBackgroundResource(R.color.gray);
                llState.setBackgroundResource(R.color.gray);
                llPincode.setBackgroundResource(R.color.gray);
                llCompanyEmail.setBackgroundResource(R.color.gray);
                llTelephoneNo.setBackgroundResource(R.color.gray);
                llFaxNo.setBackgroundResource(R.color.gray);
                llGSTNo.setBackgroundResource(R.color.gray);
                llFirstName.setBackgroundResource(R.color.gray);
                llLastName.setBackgroundResource(R.color.gray);
                llDesignation.setBackgroundResource(R.color.gray);
                llBirthDate.setBackgroundResource(R.color.colorPrimary);
                llMobileNo.setBackgroundResource(R.color.gray);
                llEmailID.setBackgroundResource(R.color.gray);
            }
        });

        edtMobileNo.setOnFocusChangeListener((view, b) -> {
            if(b) {
                llCompanyName.setBackgroundResource(R.color.gray);
                llCompanyType.setBackgroundResource(R.color.gray);
                llAddress.setBackgroundResource(R.color.gray);
                llCity.setBackgroundResource(R.color.gray);
                llState.setBackgroundResource(R.color.gray);
                llPincode.setBackgroundResource(R.color.gray);
                llCompanyEmail.setBackgroundResource(R.color.gray);
                llTelephoneNo.setBackgroundResource(R.color.gray);
                llFaxNo.setBackgroundResource(R.color.gray);
                llGSTNo.setBackgroundResource(R.color.gray);
                llFirstName.setBackgroundResource(R.color.gray);
                llLastName.setBackgroundResource(R.color.gray);
                llDesignation.setBackgroundResource(R.color.gray);
                llBirthDate.setBackgroundResource(R.color.gray);
                llMobileNo.setBackgroundResource(R.color.colorPrimary);
                llEmailID.setBackgroundResource(R.color.gray);
            }
        });

        edtEmailID.setOnFocusChangeListener((view, b) -> {
            if(b) {
                llCompanyName.setBackgroundResource(R.color.gray);
                llCompanyType.setBackgroundResource(R.color.gray);
                llAddress.setBackgroundResource(R.color.gray);
                llCity.setBackgroundResource(R.color.gray);
                llState.setBackgroundResource(R.color.gray);
                llPincode.setBackgroundResource(R.color.gray);
                llCompanyEmail.setBackgroundResource(R.color.gray);
                llTelephoneNo.setBackgroundResource(R.color.gray);
                llFaxNo.setBackgroundResource(R.color.gray);
                llGSTNo.setBackgroundResource(R.color.gray);
                llFirstName.setBackgroundResource(R.color.gray);
                llLastName.setBackgroundResource(R.color.gray);
                llDesignation.setBackgroundResource(R.color.gray);
                llBirthDate.setBackgroundResource(R.color.gray);
                llMobileNo.setBackgroundResource(R.color.gray);
                llEmailID.setBackgroundResource(R.color.colorPrimary);
            }
        });

        tvBirthDate.setOnClickListener(view -> {

        });
    }

    private void findViews() {

        edtCompanyName = findViewById(R.id.edtCompanyName);
        edtAddress = findViewById(R.id.edtAddress);
        edtCity = findViewById(R.id.edtCity);
        edtState = findViewById(R.id.edtState);
        edtPincode = findViewById(R.id.edtPincode);
        edtCompanyEmail = findViewById(R.id.edtCompanyEmail);
        edtTelephoneNo = findViewById(R.id.edtTelNo);
        edtFaxNo = findViewById(R.id.edtFaxNo);
        edtGSTNo = findViewById(R.id.edtGSTNo);

        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtDesignation = findViewById(R.id.edtDesignation);
        edtMobileNo = findViewById(R.id.edtMobileNo);
        edtEmailID = findViewById(R.id.edtEmail);

        spCompanyType = findViewById(R.id.spCompanyType);
        tvBirthDate = findViewById(R.id.tvBirthDate);

        llCompanyName = findViewById(R.id.llCompanyName);
        llAddress = findViewById(R.id.llAddress);
        llCity = findViewById(R.id.llCity);
        llState = findViewById(R.id.llState);
        llPincode = findViewById(R.id.llPincode);
        llCompanyEmail = findViewById(R.id.llCompanyEmail);
        llTelephoneNo = findViewById(R.id.llTelNo);
        llFaxNo = findViewById(R.id.llFaxNo);
        llGSTNo = findViewById(R.id.llGSTNo);

        llFirstName = findViewById(R.id.llFirstName);
        llLastName = findViewById(R.id.llLastName);
        llDesignation = findViewById(R.id.llDesignation);
        llMobileNo = findViewById(R.id.llMobileNo);
        llEmailID = findViewById(R.id.llEmail);

        llCompanyType = findViewById(R.id.llCompanyType);
        llBirthDate = findViewById(R.id.llBirthDate);
    }
}