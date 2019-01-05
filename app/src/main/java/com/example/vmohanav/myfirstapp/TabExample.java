package com.example.vmohanav.myfirstapp;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

public class TabExample extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    public static Integer id=-1;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_example);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab_example, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";



        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tab_example, container, false);

            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            Button button = (Button) rootView.findViewById(R.id.submit);
            final EditText fname = (EditText) rootView.findViewById(R.id.FNameText);

            final DatePicker dob = (DatePicker) rootView.findViewById(R.id.DateOfBirthvalue);


            final Spinner Gender = (Spinner) rootView.findViewById(R.id.GenderSpinner);


            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String data=fname.getText().toString().trim();
                    String Givendob = dateformatting(dob.getYear(),dob.getMonth(),dob.getDayOfMonth());
                    String SelectedGender = Gender.getSelectedItem().toString();


                    String url = "https://webapp-181209061846.azurewebsites.net/Leprosy/";
                    RequestQueue MyRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                    JSONObject json = new JSONObject();

                    try {
                        json.put("name",data);
                        json.put("dob",Givendob);
                        json.put("gender",SelectedGender);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest (Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //This code is executed if the server responds, whether or not the response contains data.
                            //The String 'response' contains the server's response.
                            String id=null;
                            try {
                                id=response.getString("id");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //ContactsTab fragment = new ContactsTab();
                            //Bundle bundle = new Bundle();
                            //bundle.putString("id",id);
                            //fragment.setArguments(bundle);
                            TabExample.id = Integer.parseInt(id);

                            //Inflate the fragment
                            //getFragmentManager().beginTransaction().add(R.id.container,fragment).commit();
                            Log.e("request:","correct response");
                            Toast toast = Toast.makeText(getActivity(),
                                    "Profile updated successfully",
                                    Toast.LENGTH_SHORT);
                            toast.show();


                        }
                    }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //This code is executed if there is an error.
                            Log.e("request:","Incorrect response");
                            Toast toast = Toast.makeText(getActivity(),
                                    error.toString(),
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });
                    MyRequestQueue.add(jsonObjectRequest );
                }

            });
            return rootView;
        }

        public String dateformatting(int year,int month,int day){
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
            Date d = new Date(year-1901, month, day);
            String date = dateFormatter.format(d);
            return date;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {

                case 1:
                    ContactsTab Contactsfragment = new ContactsTab();
                    return Contactsfragment.newInstance(position + 1);
                case 2:
                    if(TabExample.id>0){
                        LeprosyTab Leprosyfragment = new LeprosyTab();
                        return Leprosyfragment.newInstance(position + 1);
                    }else {
                        PlaceholderFragment personal=new PlaceholderFragment();
                        return personal.newInstance(1);
                    }

                case 3:
                    DosageTab Dosagefragment = new DosageTab();
                    return Dosagefragment.newInstance(position + 1);
                case 4:
                    OtherdetailsTab Otherdetailsfragment = new OtherdetailsTab();
                    return Otherdetailsfragment.newInstance(position + 1);
                case 5:
                    PhotoTab Photofragment = new PhotoTab();
                    return Photofragment.newInstance(position + 1);
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 6 total pages.
            return 6;
        }
    }
}
