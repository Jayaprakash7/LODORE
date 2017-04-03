package lodore.com.lodore.Fragment;


import android.content.Intent;
import android.os.Bundle;
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
import android.widget.TextView;

import lodore.com.lodore.CheckOutActivity;
import lodore.com.lodore.CheckOutCompleteActivity;
import lodore.com.lodore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckoutLoginFragment extends Fragment {

    Toolbar toolbar;
    Button btnCheckOut;
    EditText edit_email, edit_password;
    TextView textViewNewAcc;

    public CheckoutLoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_checkout_login, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        btnCheckOut = (Button) view.findViewById(R.id.btnCheckOut);
        edit_email = (EditText) view.findViewById(R.id.edit_email);
        edit_password = (EditText) view.findViewById(R.id.edit_password);
        textViewNewAcc = (TextView) view.findViewById(R.id.new_acc);

        textViewNewAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment();
            }
        });


        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CheckOutCompleteActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });



        return view;
    }

    public void RegisterFragment (){
        RegisterFragment registerFragment = new RegisterFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.checkout_container,registerFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
