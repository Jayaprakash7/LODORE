package lodore.com.lodore.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import lodore.com.lodore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    EditText email,password,confirm_password,name,phone_one,phone_two;

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

}
