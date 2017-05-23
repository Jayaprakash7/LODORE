package lodore.com.lodore.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
public class LoginFragment extends Fragment {

    EditText email,password;
    Button login;
    SharedPreferences pref;
    String emailString,passwordString;
    private ProgressDialog progressDialog;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("loginemail",email.getText().toString());
        outState.putString("loginpassword",password.getText().toString());

    }



    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        email = (EditText) view.findViewById(R.id.login_email);
        password = (EditText) view.findViewById(R.id.login_password);
        progressDialog = new ProgressDialog(getActivity());

        login = (Button) view.findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                RegResult reginput = new RegResult();

                emailString = email.getText().toString().trim();
                passwordString = password.getText().toString().trim();

                if ((emailString.matches("")) || (passwordString.matches(""))){
                    Toast.makeText(getActivity(), "Enter all fields", Toast.LENGTH_SHORT).show();
                }

                else
                reginput.setEmail(emailString);
                reginput.setPassword(passwordString);

                new LoginFragment.LoginUser().execute(reginput);


            }
        });


        if (savedInstanceState != null){
            email.setText(savedInstanceState.getString("loginemail"));
            password.setText(savedInstanceState.getString("loginpassword"));
        }

        return view;
    }

    public class LoginUser extends AsyncTask<RegResult, Integer, Registerresp> {
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
                    .setEndpoint("http://54.201.67.32/lodore/connection/api/customer")
                    .setClient(new OkClient(okHttpClient))
                    .build();
        }

        protected Registerresp doInBackground(RegResult... params) {
            Registerresp status = null;
            try {
                Retrofit_rest enroll_plan = restAdapter.create(Retrofit_rest.class);
                status = enroll_plan.loginUrlEncode(params[0]);
            }catch (Exception e){

            }
            return status;
        }

        protected void onPostExecute(Registerresp response)
        {
            try {
                if (response.getStatus().equals("success")) {

                    Intent i = new Intent(getContext(),MainActivity.class);

                    SharedPreferences.Editor editor = getContext().getSharedPreferences("login data", Context.MODE_MULTI_PROCESS).edit();
                    editor.putString("_id",response.getResult().get(0).getId());
                    editor.putString("email", response.getResult().get(0).getEmail());
                    editor.putString("username", response.getResult().get(0).getUsername());
                    editor.putString("mobile", response.getResult().get(0).getMobile());
                    editor.putString("anothermobile", response.getResult().get(0).getAnotherMobile());

                    editor.apply();
                    startActivity(i);
                    hideDialoge();
                    Toast.makeText(getContext(), "Login is succesfull", Toast.LENGTH_SHORT).show();

                }else  {
                    hideDialoge();
                    Toast.makeText(getContext(), "Wrong respose Credential", Toast.LENGTH_SHORT).show();

                }

            } catch (Exception e){}
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
