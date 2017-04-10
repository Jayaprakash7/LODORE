package lodore.com.lodore.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import lodore.com.lodore.MainActivity;
import lodore.com.lodore.Pojo.RegResult;
import lodore.com.lodore.Pojo.Registerresp;
import lodore.com.lodore.R;
import lodore.com.lodore.service.Retrofit_rest;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyaccountFragment extends Fragment {

    ProgressDialog progressDialog;

    public MyaccountFragment() {
        // Required empty public constructor
    }

    private void PasswordFragment() {
        PasswordFragment passwordFragment = new PasswordFragment();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.content_frame, passwordFragment);
        transaction.commit();
    }

    TextView personal_info, email, name, phone, anotherphone, city, neighborhood, street_name;
    Button order_history, my_fav_perfume, change_acc_info, change_password, change_adress_info;
    EditText et_email, et_name, et_phone, et_anotherphone, et_city, et_neighborhood, et_streetname;
    SharedPreferences pref;

    String et_nameString, et_phoneString, et_anotherphoneString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myaccount, container, false);

        progressDialog = new ProgressDialog(getActivity());

        personal_info = (TextView) view.findViewById(R.id.personal_info);
        email = (TextView) view.findViewById(R.id.myacc_eamil);
        name = (TextView) view.findViewById(R.id.myacc_name);
        phone = (TextView) view.findViewById(R.id.myacc_phone);
        anotherphone = (TextView) view.findViewById(R.id.myacc_anotherphone);
        city = (TextView) view.findViewById(R.id.my_acc_city);
        neighborhood = (TextView) view.findViewById(R.id.my_acc_neighborhood);
        street_name = (TextView) view.findViewById(R.id.my_acc_streetname);

        order_history = (Button) view.findViewById(R.id.order_history);
        my_fav_perfume = (Button) view.findViewById(R.id.my_fav_perfume);
        change_acc_info = (Button) view.findViewById(R.id.acct_change_info);
        change_password = (Button) view.findViewById(R.id.change_password);
        change_adress_info = (Button) view.findViewById(R.id.address_change_info);


        et_email = (EditText) view.findViewById(R.id.et_myacc_eamil);
        et_name = (EditText) view.findViewById(R.id.et_myacc_name);
        et_phone = (EditText) view.findViewById(R.id.et_myacc_phone);
        et_anotherphone = (EditText) view.findViewById(R.id.et_myacc_anotherphone);
        et_city = (EditText) view.findViewById(R.id.et_my_acc_city);
        et_neighborhood = (EditText) view.findViewById(R.id.et_my_acc_neighborhood);
        et_streetname = (EditText) view.findViewById(R.id.et_my_acc_streetname);

        pref = getContext().getSharedPreferences("login data", Context.MODE_MULTI_PROCESS);
        String email = pref.getString("email", "null");

        et_email.setText(email);
        et_name.setText(pref.getString("username", "null"));
        et_anotherphone.setText(pref.getString("anothermobile", "null"));
        et_phone.setText(pref.getString("mobile", "null"));

        if(email.equals("null")){
            et_email.setText("");
            et_name.setText("");
            et_anotherphone.setText("");
            et_phone.setText("");
        }


        change_acc_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegResult reginput = new RegResult();

                String et_nameString = et_name.getText().toString().trim();
                String et_phoneString = et_phone.getText().toString().trim();
                String et_anotherphoneString = et_anotherphone.getText().toString().trim();

                if (et_nameString.matches("") || et_anotherphoneString.matches("") || et_anotherphoneString.matches("")){
                    Toast.makeText(getActivity(), "Enter all fields", Toast.LENGTH_SHORT).show();
                }
                else
                reginput.setEmail(et_email.getText().toString());
                reginput.setUsername(et_nameString);
                reginput.setMobile(et_phoneString);
                reginput.setAnotherMobile(et_anotherphoneString);
                reginput.setId(pref.getString("_id", "null"));
                //reginput.setPassword();


                new MyaccountFragment.UpdateUser().execute(reginput);

            }
        });


        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasswordFragment();
            }
        });

        return view;
    }

    public class UpdateUser extends AsyncTask<RegResult, Integer, Registerresp> {
        RestAdapter restAdapter;
        public String res;

        @Override
        protected void onPreExecute() {
            final OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setReadTimeout(5, TimeUnit.SECONDS);
            okHttpClient.setConnectTimeout(5, TimeUnit.SECONDS);
            showDialog();

            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("http://192.168.123.10/lodore/api")
                    .setClient(new OkClient(okHttpClient))
                    .build();
        }

        protected Registerresp doInBackground(RegResult... params) {
            Registerresp status = null;
            try {
                Retrofit_rest enroll_plan = restAdapter.create(Retrofit_rest.class);
                status = enroll_plan.updateUrlEncode(params[0]);
            } catch (Exception e) {

            }
            return status;
        }

        protected void onPostExecute(Registerresp response) {
            try {
                if (response.getStatus().equals("success")) {

                    Intent i = new Intent(getContext(), MainActivity.class);

                    SharedPreferences.Editor editor = getContext().getSharedPreferences("login data", Context.MODE_MULTI_PROCESS).edit();
                    editor.putString("email", response.getResult().get(0).getEmail());
                    editor.putString("username", response.getResult().get(0).getUsername());
                    editor.putString("mobile", response.getResult().get(0).getMobile());
                    editor.putString("anothermobile", response.getResult().get(0).getAnotherMobile());

                    editor.commit();
                    hideDialoge();
                    Toast.makeText(getContext(), "Update is succesfull", Toast.LENGTH_SHORT).show();

                    startActivity(i);

                } else {
                    hideDialoge();
                    Toast.makeText(getContext(), "Wrong respose Credential", Toast.LENGTH_SHORT).show();

                }

            } catch (Exception e) {
            }
        }
    }

    public void showDialog(){
        progressDialog.setMessage("please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }
    public void hideDialoge(){
        progressDialog.dismiss();
    }


}
