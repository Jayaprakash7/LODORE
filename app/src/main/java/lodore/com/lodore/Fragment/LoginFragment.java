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
public class LoginFragment extends Fragment {

    EditText email,password;

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


        if (savedInstanceState != null){
            email.setText(savedInstanceState.getString("loginemail"));
            password.setText(savedInstanceState.getString("loginpassword"));
        }

        return view;
    }

}
