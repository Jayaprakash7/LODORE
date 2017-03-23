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
public class GiftFragment1 extends Fragment {


    EditText editText1, editText2, editText3, editText4, editText5, editText6;

    private static String EDIT_1 = "EDIT_1";
    private static String EDIT_2 = "EDIT_2";
    private static String EDIT_3 = "EDIT_3";
    private static String EDIT_4 = "EDIT_4";
    private static String EDIT_5 = "EDIT_5";
    private static String EDIT_6 = "EDIT_6";

    public GiftFragment1() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EDIT_1, editText1.getText().toString());
        outState.putString(EDIT_2, editText2.getText().toString());
        outState.putString(EDIT_3, editText3.getText().toString());
        outState.putString(EDIT_4, editText4.getText().toString());
        outState.putString(EDIT_5, editText5.getText().toString());
        outState.putString(EDIT_6, editText6.getText().toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_gift_fragment1, container, false);

        editText1 = (EditText) layout.findViewById(R.id.editText1);
        editText2 = (EditText) layout.findViewById(R.id.editText2);
        editText3 = (EditText) layout.findViewById(R.id.editText3);
        editText4 = (EditText) layout.findViewById(R.id.editText4);
        editText5 = (EditText) layout.findViewById(R.id.editText5);
        editText6 = (EditText) layout.findViewById(R.id.editText6);

        if (savedInstanceState != null) {
            editText1.setText(savedInstanceState.getString(EDIT_1));
            editText2.setText(savedInstanceState.getString(EDIT_2));
            editText3.setText(savedInstanceState.getString(EDIT_3));
            editText4.setText(savedInstanceState.getString(EDIT_4));
            editText5.setText(savedInstanceState.getString(EDIT_5));
            editText6.setText(savedInstanceState.getString(EDIT_6));
        }

        Button btnGift1 = (Button) layout.findViewById(R.id.btn_gift1);
        Toolbar toolbar = (Toolbar) layout.findViewById(R.id.toolbar);

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setTitle("Gift");
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btnGift1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiftFragment2();
            }
        });

        return layout;
    }

    public void GiftFragment2() {
        GiftFragment2 giftFragment2 = new GiftFragment2();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.checkout_container_layout, giftFragment2);
        transaction.commit();

    }

}
