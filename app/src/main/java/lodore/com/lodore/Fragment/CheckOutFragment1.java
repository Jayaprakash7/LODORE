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
public class CheckOutFragment1 extends Fragment {

    EditText editText1, editText2, editText3, editText4, editText5, editText6;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("edit1", editText1.getText().toString());
        outState.putString("edit2", editText2.getText().toString());
        outState.putString("edit3", editText3.getText().toString());
        outState.putString("edit4", editText4.getText().toString());
        outState.putString("edit5", editText5.getText().toString());
        outState.putString("edit6", editText6.getText().toString());

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
        editText4 = (EditText) view.findViewById(R.id.editText4);
        editText5 = (EditText) view.findViewById(R.id.editText5);
        editText6 = (EditText) view.findViewById(R.id.editText6);


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
            editText4.setText(savedInstanceState.getString("edit4"));
            editText5.setText(savedInstanceState.getString("edit5"));
            editText6.setText(savedInstanceState.getString("edit6"));
        }

        btnCheckOutComplte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckOutFragment();
            }
        });
        return view;
    }

}
