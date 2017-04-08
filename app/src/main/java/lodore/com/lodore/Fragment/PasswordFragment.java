package lodore.com.lodore.Fragment;


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
public class PasswordFragment extends Fragment {

    EditText password,confirmpassword;
    Button updatepassword;
    SharedPreferences pref;


    public PasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_password, container, false);

        password = (EditText) view.findViewById(R.id.password);
        confirmpassword = (EditText) view.findViewById(R.id.confirmpassword);
        updatepassword = (Button) view.findViewById(R.id.update_password);

        updatepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getText().toString().equals(confirmpassword.getText().toString()) )
                {
                    pref = getContext().getSharedPreferences("login data", Context.MODE_MULTI_PROCESS);
                    RegResult reginput = new RegResult();
                    reginput.setId(pref.getString("_id", "null"));
                    reginput.setPassword(password.getText().toString());
                    Toast.makeText(getContext(), "Password Updated", Toast.LENGTH_SHORT).show();
                    new PasswordFragment.UpdatePassword().execute(reginput);

                }
                else
                {
                    Toast.makeText(getContext(), "Password is mismatch", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public class UpdatePassword extends AsyncTask<RegResult, Integer, Registerresp> {
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
                status = enroll_plan.updatepasswordUrlEncode(params[0]);
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


