package lodore.com.lodore.Fragment;


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

import lodore.com.lodore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckOutFragment3 extends Fragment {


    Button btnCheckOut3;
    EditText editEmail, editPass, editConfirmPass;
    private static String EDIT_EMAIL = "EDIT_EMAIL";
    private static String EDIT_PASS = "EDIT_PASS";
    private static String EDIT_CONFIRM = "EDIT_CONFIRM";


    public CheckOutFragment3() {
        // Required empty public constructor
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EDIT_EMAIL, editEmail.getText().toString());
        outState.putString(EDIT_PASS, editPass.getText().toString());
        outState.putString(EDIT_CONFIRM, editConfirmPass.getText().toString());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_out_fragment3, container, false);

        btnCheckOut3 = (Button) view.findViewById(R.id.btn_check_complete3);
        editEmail = (EditText) view.findViewById(R.id.editEmail);
        editPass = (EditText) view.findViewById(R.id.edit_password);
        editConfirmPass = (EditText) view.findViewById(R.id.edit_confirm_password);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

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

        btnCheckOut3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (savedInstanceState != null) {
                    editEmail.setText(savedInstanceState.getString(EDIT_EMAIL));
                    editPass.setText(savedInstanceState.getString(EDIT_PASS));
                    editConfirmPass.setText(savedInstanceState.getString(EDIT_CONFIRM));
                }

                CheckOutFragment4 checkOutFragment4 = new CheckOutFragment4();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.checkout_container_layout, checkOutFragment4);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        return view;
    }

}
