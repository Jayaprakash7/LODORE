package lodore.com.lodore.Fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import lodore.com.lodore.R;

public class CheckOutFragment1 extends Fragment {

    EditText editText1, editText2, editText3, editAddress, editCity, editPostal;
    Button buttonLocation;

    LocationListener locationListener;
    LocationManager locationManager;
    List<Address> addresses;

    String address, city, state, country, postalCode, knownName;

    /*Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    if(location != null && location.getTime() > Calendar.getInstance().getTimeInMillis() - 2 * 60 * 1000) {
        // Do something with the recent location fix
        //  otherwise wait for the update below
    }
    else {
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }*/

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("edit1", editText1.getText().toString());
        outState.putString("edit2", editText2.getText().toString());
        outState.putString("edit3", editText3.getText().toString());
        outState.putString("edit4", editAddress.getText().toString());
        outState.putString("edit5", editCity.getText().toString());
        outState.putString("edit6", editPostal.getText().toString());

    }


    public void CheckOutFragment() {

        CheckOutFragment2 checkOutFragment2 = new CheckOutFragment2();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.checkout_container_layout, checkOutFragment2);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public CheckOutFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_out_fragment1, container, false);
        Button btnCheckOutComplte = (Button) view.findViewById(R.id.btn_checkout_complete);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        editText1 = (EditText) view.findViewById(R.id.editText1);
        editText2 = (EditText) view.findViewById(R.id.editText2);
        editText3 = (EditText) view.findViewById(R.id.editText3);
        editAddress = (EditText) view.findViewById(R.id.edit_address);
        editCity = (EditText) view.findViewById(R.id.edit_city);
        editPostal = (EditText) view.findViewById(R.id.edit_postal);
        buttonLocation = (Button) view.findViewById(R.id.btn_share_location);


        btnCheckOutComplte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckOutFragment();
            }
        });

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setTitle("CheckOut");
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        if (savedInstanceState != null) {
            editText1.setText(savedInstanceState.getString("edit1"));
            editText2.setText(savedInstanceState.getString("edit2"));
            editText3.setText(savedInstanceState.getString("edit3"));
            editAddress.setText(savedInstanceState.getString("edit4"));
            editCity.setText(savedInstanceState.getString("edit5"));
            editPostal.setText(savedInstanceState.getString("edit6"));
        }

        final Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                try {
                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                    address = addresses.get(0).getAddressLine(0);
                    city = addresses.get(0).getLocality();
                    state = addresses.get(0).getAdminArea();
                    country = addresses.get(0).getCountryName();
                    postalCode = addresses.get(0).getPostalCode();
                    knownName = addresses.get(0).getFeatureName();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION
                }, 10);
            } else {
                locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);

            }

        }

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);

                return;
        }
    }


    private void configButton() {
        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //address, city, state, country, postalCode, knownName;

                editAddress.setText(address);
                editCity.setText(city+","+state);
                editPostal.setText(country+","+postalCode);

            }
        });
    }


}
