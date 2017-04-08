package lodore.com.lodore.Fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class RegisterFragment extends Fragment {

    EditText email,password,confirm_password,name,phone_one,phone_two;
    Button create_account;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("reg_email",email.getText().toString());
        outState.putString("reg_password",password.getText().toString());
        outState.putString("reg_confirm_password",confirm_password.getText().toString());
        outState.putString("reg_name",name.getText().toString());
        outState.putString("reg_phone_one",phone_one.getText().toString());
        outState.putString("reg_phone_two",phone_two.getText().toString());
    }

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        email = (EditText) view.findViewById(R.id.registration_email);
        password = (EditText) view.findViewById(R.id.registration_password);
        confirm_password = (EditText) view.findViewById(R.id.registration_confirmpassword);
        name = (EditText) view.findViewById(R.id.registration_name);
        phone_one = (EditText) view.findViewById(R.id.registration_phone_one);
        phone_two = (EditText) view.findViewById(R.id.registration_phone_two);

        create_account =  (Button) view.findViewById(R.id.create_account);

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RegResult reginput = new RegResult();

                reginput.setEmail(email.getText().toString());
                reginput.setUsername(name.getText().toString());
                reginput.setPassword(password.getText().toString());
                reginput.setMobile(phone_one.getText().toString());
                reginput.setAnotherMobile(phone_two.getText().toString());

                new RegisterFragment.RegisterUser().execute(reginput);


            }
        });


        if (savedInstanceState != null) {
            email.setText(savedInstanceState.getString("reg_email"));
            password.setText(savedInstanceState.getString("reg_password"));
            confirm_password.setText(savedInstanceState.getString("reg_confirm_password"));
            name.setText(savedInstanceState.getString("reg_name"));
            phone_one.setText(savedInstanceState.getString("reg_phone_one"));
            phone_two.setText(savedInstanceState.getString("reg_phone_two"));
        }

        return view;
    }

    public class RegisterUser extends AsyncTask<RegResult, Integer, Registerresp> {
        RestAdapter restAdapter;
        public String res;

        @Override
        protected void onPreExecute() {
            final OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setReadTimeout(5, TimeUnit.SECONDS);
            okHttpClient.setConnectTimeout(5, TimeUnit.SECONDS);

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
                status = enroll_plan.regUrlEncode(params[0]);
            }catch (Exception e){

            }
            return status;
        }

        protected void onPostExecute(Registerresp response)
        {
            try {
                if (response.getStatus().equals("success")) {

                    Intent i = new Intent(getContext(),MainActivity.class);
                    startActivity(i);
                    Toast.makeText(getContext(), "Register is succesfull", Toast.LENGTH_SHORT).show();

                }else  {

                    Toast.makeText(getContext(), "Wrong respose Credential", Toast.LENGTH_SHORT).show();

                }

            } catch (Exception e){}
        }
    }

}
