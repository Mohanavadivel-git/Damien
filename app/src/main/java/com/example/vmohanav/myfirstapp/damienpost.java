package com.example.vmohanav.myfirstapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class damienpost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damienpost);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Lepra Patient Enrollment Form");
        SpinnerSelection(savedInstanceState);
    }

    public String dateformatting(int year,int month,int day){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        Date d = new Date(year-1901, month, day);
        String date = dateFormatter.format(d);
        return date;
    }

    public void submitclick(View button){
        postrequest();
        //values();
    }


    public void values(){
        /*
        Spinner State = (Spinner) findViewById(R.id.StateSpinner);
        Spinner District = (Spinner) findViewById(R.id.DistrictSpinner);
        Spinner HealthFacility = (Spinner) findViewById(R.id.HealthFacilitySpinner);
        TextView PatientName = (TextView) findViewById(R.id.FNameText);
        DatePicker dob = (DatePicker) findViewById(R.id.DateOfBirthvalue);
        Spinner Gender = (Spinner) findViewById(R.id.GenderSpinner);
        TextView PhoneNumber = (TextView) findViewById(R.id.PhoneNumberText);
        Spinner LeprosyType = (Spinner) findViewById(R.id.LeprosyTypeSpinner);
        DatePicker ReactionDate = (DatePicker) findViewById(R.id.Date_React_identify_val);
        TextView DisabilityStatus = (TextView) findViewById(R.id.Disab_Status_Text);
        Spinner ReactionType = (Spinner) findViewById(R.id.TypeReactionSpinner);
        DatePicker Dose40mg = (DatePicker) findViewById(R.id.DoseIssued_40mg_dt);
        DatePicker Dose30mg = (DatePicker) findViewById(R.id.DoseIssued_30mg_dt);
        DatePicker Dose20mg = (DatePicker) findViewById(R.id.DoseIssued_20mg_dt);
        DatePicker Dose15mg = (DatePicker) findViewById(R.id.DoseIssued_15mg_dt);
        DatePicker Dose10mg = (DatePicker) findViewById(R.id.DoseIssued_10mg_dt);
        DatePicker Dose05mg = (DatePicker) findViewById(R.id.DoseIssued_05mg_dt);
        TextView OtherDrugsValue = (TextView) findViewById(R.id.OtherDrugsValue);
        RadioGroup TreatmentStatus = (RadioGroup) findViewById(R.id.Treatmentstatus);
        RadioGroup personstatus = (RadioGroup) findViewById(R.id.personstatus);
        TextView Remarks = (TextView) findViewById(R.id.Remarks);

        String SelectedState = State.getSelectedItem().toString();
        String SelectedDistrict = District.getSelectedItem().toString();
        String SelectedHealthFacility = HealthFacility.getSelectedItem().toString();
        String SelectedGender = Gender.getSelectedItem().toString();
        String SelectedLeprosyType = LeprosyType.getSelectedItem().toString();
        String SelectedReactionType = ReactionType.getSelectedItem().toString();

        String GivenPatientName = PatientName.getText().toString();
        String GivenPhoneNumber = PhoneNumber.getText().toString();
        String GivenDisabilityStatus = DisabilityStatus.getText().toString();
        String GivenOtherDrugsValue = OtherDrugsValue.getText().toString();
        String GivenRemarks = Remarks.getText().toString();


        String Givendob = dateformatting(dob.getYear(),dob.getMonth()+1,dob.getDayOfMonth());
        String GivenReactiondate=dateformatting(ReactionDate.getYear(),ReactionDate.getMonth()+1,ReactionDate.getDayOfMonth());
        String GivenDose40mg = dateformatting(Dose40mg.getYear(),Dose40mg.getMonth()+1,Dose40mg.getDayOfMonth());
        String GivenDose30mg = dateformatting(Dose30mg.getYear(),Dose30mg.getMonth()+1,Dose30mg.getDayOfMonth());
        String GivenDose20mg = dateformatting(Dose20mg.getYear(),Dose20mg.getMonth()+1,Dose20mg.getDayOfMonth());
        String GivenDose15mg = dateformatting(Dose15mg.getYear(),Dose15mg.getMonth()+1,Dose15mg.getDayOfMonth());
        String GivenDose10mg = dateformatting(Dose10mg.getYear(),Dose10mg.getMonth()+1,Dose10mg.getDayOfMonth());
        String GivenDose05mg = dateformatting(Dose05mg.getYear(),Dose05mg.getMonth()+1,Dose05mg.getDayOfMonth());


        int TreatmentStatusGroup = TreatmentStatus.getCheckedRadioButtonId();
        int personstatusGroup = personstatus.getCheckedRadioButtonId();
        RadioButton selectedTreatmentStatus = (RadioButton) findViewById(TreatmentStatusGroup);
        String selectedTreatmentStatusText = selectedTreatmentStatus.getText().toString();
        RadioButton selectedpersonstatus= (RadioButton) findViewById(personstatusGroup);
        String selectedpersonstatusText = selectedpersonstatus.getText().toString();

        //---------Spinner--------------------//
        Log.e("statename","------------------"+SelectedState);
        Log.e("Districtname","------------------"+SelectedDistrict);
        Log.e("HealthFacility","------------------"+SelectedHealthFacility);
        Log.e("Gender","------------------"+SelectedGender);
        Log.e("LeprosyType","------------------"+SelectedLeprosyType);
        Log.e("ReactionType","------------------"+SelectedReactionType);

        //---------Text View-------------------//
        Log.e("PatientName","------------------"+GivenPatientName);
        Log.e("PhoneNumber","------------------"+GivenPhoneNumber);
        Log.e("DisabilityStatus","------------------"+GivenDisabilityStatus);
        Log.e("OtherDrugs","------------------"+GivenOtherDrugsValue);
        Log.e("Remarks","------------------"+GivenRemarks);

        //--------Date Picker-------------------//
        Log.e("dateofbirth","------------------"+Givendob);
        Log.e("GivenReactiondate","------------------"+GivenReactiondate);
        Log.e("GivenDose40mg","------------------"+GivenDose40mg);
        Log.e("GivenDose30mg","------------------"+GivenDose30mg);
        Log.e("GivenDose20mg","------------------"+GivenDose20mg);
        Log.e("GivenDose15mg","------------------"+GivenDose15mg);
        Log.e("GivenDose10mg","------------------"+GivenDose10mg);
        Log.e("GivenDose05mg","------------------"+GivenDose05mg);

        //-------Radio button------------------//
        Log.e("selectedTreatmentStatus","------------------"+selectedTreatmentStatusText);
        Log.e("selectedpersonstatus","------------------"+selectedpersonstatusText);
    */}

    public void SpinnerSelection(Bundle savedInstanceState){

        final Spinner StateSpinner = (Spinner)findViewById(R.id.StateSpinner);
        final Spinner DistrictSpinner = (Spinner)findViewById(R.id.DistrictSpinner);
        final Spinner HealthFacilitySpinner = (Spinner) findViewById(R.id.HealthFacilitySpinner);
        //final Spinner

        ArrayAdapter<CharSequence> StateAdapter = ArrayAdapter.createFromResource(this, R.array.state_list, android.R.layout.simple_spinner_item);
        StateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        final ArrayAdapter<CharSequence> TN_DistrictAdapter = ArrayAdapter.createFromResource(this, R.array.tn_districts, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> AP_DistrictAdapter = ArrayAdapter.createFromResource(this, R.array.ap_districts, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> Bihar_DistrictAdapter = ArrayAdapter.createFromResource(this, R.array.bihar_district, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> Jharkhand_DistrictAdapter = ArrayAdapter.createFromResource(this, R.array.jharkhand_district, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> Delhi_DistrictAdapter = ArrayAdapter.createFromResource(this, R.array.delhi, android.R.layout.simple_spinner_item);

        final ArrayAdapter<CharSequence> TN_HealthFacilityAdapter = ArrayAdapter.createFromResource(this, R.array.tn_hf, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> AP_HealthFacilityAdapter = ArrayAdapter.createFromResource(this, R.array.ap_hf, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> Bihar_HealthFacilityAdapter = ArrayAdapter.createFromResource(this, R.array.br_hf, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> Jharkhand_HealthFacilityAdapter = ArrayAdapter.createFromResource(this, R.array.jh_hf, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> Delhi_HealthFacilityAdapter = ArrayAdapter.createFromResource(this, R.array.dl_hf, android.R.layout.simple_spinner_item);

        TN_DistrictAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AP_DistrictAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Bihar_DistrictAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Jharkhand_DistrictAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Delhi_DistrictAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        TN_HealthFacilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AP_HealthFacilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Bihar_HealthFacilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Jharkhand_HealthFacilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Delhi_HealthFacilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        StateSpinner.setAdapter(StateAdapter);
        StateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    DistrictSpinner.setAdapter(TN_DistrictAdapter);
                    HealthFacilitySpinner.setAdapter(TN_HealthFacilityAdapter);
                }
                else if(position==1){
                    DistrictSpinner.setAdapter(AP_DistrictAdapter);
                    HealthFacilitySpinner.setAdapter(AP_HealthFacilityAdapter);
                }
                else if(position==2){
                    DistrictSpinner.setAdapter(Bihar_DistrictAdapter);
                    HealthFacilitySpinner.setAdapter(Bihar_HealthFacilityAdapter);
                }
                else if(position==3){
                    DistrictSpinner.setAdapter(Jharkhand_DistrictAdapter);
                    HealthFacilitySpinner.setAdapter(Jharkhand_HealthFacilityAdapter);
                }
                else if(position==4){
                    DistrictSpinner.setAdapter(Delhi_DistrictAdapter);
                    HealthFacilitySpinner.setAdapter(Delhi_HealthFacilityAdapter);
                }

                DistrictSpinner.setEnabled(true);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void postrequest(){

        Spinner State = (Spinner) findViewById(R.id.StateSpinner);
        Spinner District = (Spinner) findViewById(R.id.DistrictSpinner);
        Spinner HealthFacility = (Spinner) findViewById(R.id.HealthFacilitySpinner);
        TextView PatientName = (TextView) findViewById(R.id.FNameText);
        DatePicker dob = (DatePicker) findViewById(R.id.DateOfBirthvalue);
        Spinner Gender = (Spinner) findViewById(R.id.GenderSpinner);
        TextView PhoneNumber = (TextView) findViewById(R.id.PhoneNumberText);
        TextView Address = (TextView) findViewById(R.id.AddressText);
        Spinner LeprosyType = (Spinner) findViewById(R.id.LeprosyTypeSpinner);
        DatePicker ReactionDate = (DatePicker) findViewById(R.id.Date_React_identify_val);
        TextView DisabilityStatus = (TextView) findViewById(R.id.Disab_Status_Text);
        Spinner ReactionType = (Spinner) findViewById(R.id.TypeReactionSpinner);
        DatePicker Dose40mg = (DatePicker) findViewById(R.id.DoseIssued_40mg_dt);
        DatePicker Dose30mg = (DatePicker) findViewById(R.id.DoseIssued_30mg_dt);
        DatePicker Dose20mg = (DatePicker) findViewById(R.id.DoseIssued_20mg_dt);
        DatePicker Dose15mg = (DatePicker) findViewById(R.id.DoseIssued_15mg_dt);
        DatePicker Dose10mg = (DatePicker) findViewById(R.id.DoseIssued_10mg_dt);
        DatePicker Dose05mg = (DatePicker) findViewById(R.id.DoseIssued_05mg_dt);
        TextView OtherDrugsValue = (TextView) findViewById(R.id.OtherDrugsValue);
        RadioGroup TreatmentStatus = (RadioGroup) findViewById(R.id.Treatmentstatus);
        RadioGroup personstatus = (RadioGroup) findViewById(R.id.personstatus);
        TextView Remarks = (TextView) findViewById(R.id.Remarks);

        String SelectedState = State.getSelectedItem().toString();
        String SelectedDistrict = District.getSelectedItem().toString();
        String SelectedHealthFacility = HealthFacility.getSelectedItem().toString();
        String SelectedGender = Gender.getSelectedItem().toString();
        String SelectedLeprosyType = LeprosyType.getSelectedItem().toString();
        String SelectedReactionType = ReactionType.getSelectedItem().toString();

        String GivenPatientName = PatientName.getText().toString();
        String GivenPhoneNumber = PhoneNumber.getText().toString();
        String GivenAddress = Address.getText().toString();
        String GivenDisabilityStatus = DisabilityStatus.getText().toString();
        String GivenOtherDrugsValue = OtherDrugsValue.getText().toString();
        String GivenRemarks = Remarks.getText().toString();


        String Givendob = dateformatting(dob.getYear(),dob.getMonth()+1,dob.getDayOfMonth());
        String GivenReactiondate=dateformatting(ReactionDate.getYear(),ReactionDate.getMonth()+1,ReactionDate.getDayOfMonth());
        String GivenDose40mg = dateformatting(Dose40mg.getYear(),Dose40mg.getMonth()+1,Dose40mg.getDayOfMonth());
        String GivenDose30mg = dateformatting(Dose30mg.getYear(),Dose30mg.getMonth()+1,Dose30mg.getDayOfMonth());
        String GivenDose20mg = dateformatting(Dose20mg.getYear(),Dose20mg.getMonth()+1,Dose20mg.getDayOfMonth());
        String GivenDose15mg = dateformatting(Dose15mg.getYear(),Dose15mg.getMonth()+1,Dose15mg.getDayOfMonth());
        String GivenDose10mg = dateformatting(Dose10mg.getYear(),Dose10mg.getMonth()+1,Dose10mg.getDayOfMonth());
        String GivenDose05mg = dateformatting(Dose05mg.getYear(),Dose05mg.getMonth()+1,Dose05mg.getDayOfMonth());

        Boolean selectedTreatmentStatusbool;
        Boolean selectedpersonstatusbool;
        int TreatmentStatusGroup = TreatmentStatus.getCheckedRadioButtonId();
        int personstatusGroup = personstatus.getCheckedRadioButtonId();
        RadioButton selectedTreatmentStatus = (RadioButton) findViewById(TreatmentStatusGroup);
        String selectedTreatmentStatusText = selectedTreatmentStatus.getText().toString();
        if (selectedTreatmentStatusText=="yes"){
            selectedTreatmentStatusbool=true;
        }
        else {
            selectedTreatmentStatusbool=false;
        }
        RadioButton selectedpersonstatus= (RadioButton) findViewById(personstatusGroup);
        String selectedpersonstatusText = selectedpersonstatus.getText().toString();
        if (selectedpersonstatusText=="yes"){
            selectedpersonstatusbool=true;
        }
        else {
            selectedpersonstatusbool=false;
        }
        String url = "https://webapp-181209061846.azurewebsites.net/Leprosy/";
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
         try {

             json.put("State",SelectedState);
             json.put("District",SelectedDistrict);
             json.put("Healthfacility",SelectedHealthFacility);
             json.put("Name",GivenPatientName);
             json.put("Dob",Givendob);
             json.put("Gender",SelectedGender);
             json.put("Phonenumber",GivenPhoneNumber);
             json.put("Address",GivenAddress);
             json.put("Leprosytype",SelectedLeprosyType);
             json.put("Identifiedtime",GivenReactiondate);
             json.put("Disabilitystatus",GivenDisabilityStatus);
             json.put("Reactiontype",SelectedReactionType);
             json.put("Dose40mg",GivenDose40mg);
             json.put("Dose30mg",GivenDose30mg);
             json.put("Dose20mg",GivenDose20mg);
             json.put("Dose15mg",GivenDose15mg);
             json.put("Dose10mg",GivenDose10mg);
             json.put("Dose5mg",GivenDose05mg);
             json.put("Otherdrugs",GivenOtherDrugsValue);
             json.put("Treatmentcomplete",selectedTreatmentStatusbool);
             json.put("Personstatus",selectedpersonstatusbool);
             json.put("Remarks",GivenRemarks);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest (Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Log.e("request:","correct response");
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Profile updated successfully",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                Log.e("request:","Incorrect response");
                Toast toast = Toast.makeText(getApplicationContext(),
                        error.toString(),
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        MyRequestQueue.add(jsonObjectRequest );
    }
}
