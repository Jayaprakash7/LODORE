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
import android.widget.FrameLayout;
import android.widget.RadioButton;

import lodore.com.lodore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GiftFragment2 extends Fragment {


    RadioButton radioButton1, radioButton2, radioButton3;
    EditText editText1, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9, editText10, editText11, editText12;

    private static String EDIT_1 = "EDIT_1";
    private static String EDIT_2 = "EDIT_2";
    private static String EDIT_3 = "EDIT_3";
    private static String EDIT_4 = "EDIT_4";
    private static String EDIT_5 = "EDIT_5";
    private static String EDIT_6 = "EDIT_6";
    private static String EDIT_7 = "EDIT_7";
    private static String EDIT_8 = "EDIT_8";
    private static String EDIT_9 = "EDIT_9";
    private static String EDIT_10 = "EDIT_10";
    private static String EDIT_11 = "EDIT_11";
    private static String EDIT_12 = "EDIT_12";

    Button btnGift2;


    boolean button1Flags = false;
    boolean button2Flags = false;
    boolean button3Flags = false;

    public GiftFragment2() {
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
        outState.putString(EDIT_7, editText7.getText().toString());
        outState.putString(EDIT_8, editText8.getText().toString());
        outState.putString(EDIT_9, editText9.getText().toString());
        outState.putString(EDIT_10, editText10.getText().toString());
        outState.putString(EDIT_11, editText11.getText().toString());
        outState.putString(EDIT_12, editText12.getText().toString());
    }

    private void GiftFragment() {
        GiftFragment3 giftFragment3 = new GiftFragment3();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.checkout_container_layout, giftFragment3);
        transaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layoutCheckout = inflater.inflate(R.layout.fragment_gift_fragment2, container, false);

        radioButton1 = (RadioButton) layoutCheckout.findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) layoutCheckout.findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) layoutCheckout.findViewById(R.id.radioButton3);
        editText1 = (EditText) layoutCheckout.findViewById(R.id.editText1);
        editText2 = (EditText) layoutCheckout.findViewById(R.id.editText2);
        editText3 = (EditText) layoutCheckout.findViewById(R.id.editText3);
        editText4 = (EditText) layoutCheckout.findViewById(R.id.editText4);
        editText5 = (EditText) layoutCheckout.findViewById(R.id.editText5);
        editText6 = (EditText) layoutCheckout.findViewById(R.id.editText6);
        editText7 = (EditText) layoutCheckout.findViewById(R.id.editText7);
        editText8 = (EditText) layoutCheckout.findViewById(R.id.editText8);
        editText9 = (EditText) layoutCheckout.findViewById(R.id.editText9);
        editText10 = (EditText) layoutCheckout.findViewById(R.id.editText10);
        editText11 = (EditText) layoutCheckout.findViewById(R.id.editText11);
        editText12 = (EditText) layoutCheckout.findViewById(R.id.editText12);
        Toolbar toolbar = (Toolbar) layoutCheckout.findViewById(R.id.toolbar);

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

        if (savedInstanceState != null) {
            editText1.setText(savedInstanceState.getString(EDIT_1));
            editText2.setText(savedInstanceState.getString(EDIT_2));
            editText3.setText(savedInstanceState.getString(EDIT_3));
            editText4.setText(savedInstanceState.getString(EDIT_4));
            editText5.setText(savedInstanceState.getString(EDIT_5));
            editText6.setText(savedInstanceState.getString(EDIT_6));
            editText7.setText(savedInstanceState.getString(EDIT_7));
            editText8.setText(savedInstanceState.getString(EDIT_8));
            editText9.setText(savedInstanceState.getString(EDIT_9));
            editText10.setText(savedInstanceState.getString(EDIT_10));
            editText11.setText(savedInstanceState.getString(EDIT_11));
            editText12.setText(savedInstanceState.getString(EDIT_12));
        }

        btnGift2 = (Button) layoutCheckout.findViewById(R.id.btn_gift2);

        btnGift2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GiftFragment();

            }
        });


        final FrameLayout frameLayoutButton1 = (FrameLayout) layoutCheckout.findViewById(R.id.button1_frame);
        final FrameLayout frameLayoutButton2 = (FrameLayout) layoutCheckout.findViewById(R.id.button2_frame);
        final FrameLayout frameLayoutButton3 = (FrameLayout) layoutCheckout.findViewById(R.id.button3_frame);


        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButton1.isChecked()) {
                    if (!button1Flags) {
                        radioButton1.setChecked(true);
                        radioButton2.setChecked(false);
                        radioButton3.setChecked(false);
                        button1Flags = true;
                        button2Flags = false;
                        button3Flags = false;
                        frameLayoutButton1.setVisibility(View.VISIBLE);
                        frameLayoutButton2.setVisibility(View.GONE);
                        frameLayoutButton3.setVisibility(View.GONE);
                    } else {
                        button1Flags = false;
                        radioButton1.setChecked(false);
                        radioButton2.setChecked(false);
                        radioButton3.setChecked(false);
                        frameLayoutButton1.setVisibility(View.GONE);
                        frameLayoutButton2.setVisibility(View.GONE);
                        frameLayoutButton3.setVisibility(View.GONE);
                    }
                }
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButton2.isChecked()) {
                    if (!button2Flags) {
                        radioButton2.setChecked(true);
                        radioButton1.setChecked(false);
                        radioButton3.setChecked(false);
                        button1Flags = false;
                        button2Flags = true;
                        button3Flags = false;
                        frameLayoutButton2.setVisibility(View.VISIBLE);
                        frameLayoutButton1.setVisibility(View.GONE);
                        frameLayoutButton3.setVisibility(View.GONE);
                    } else {
                        button2Flags = false;
                        radioButton1.setChecked(false);
                        radioButton2.setChecked(false);
                        radioButton3.setChecked(false);
                        frameLayoutButton1.setVisibility(View.GONE);
                        frameLayoutButton2.setVisibility(View.GONE);
                        frameLayoutButton3.setVisibility(View.GONE);
                    }
                }
            }
        });
        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButton3.isChecked()) {
                    if (!button3Flags) {
                        radioButton3.setChecked(true);
                        radioButton2.setChecked(false);
                        radioButton1.setChecked(false);
                        button3Flags = true;
                        button2Flags = false;
                        button1Flags = false;
                        frameLayoutButton3.setVisibility(View.VISIBLE);
                        frameLayoutButton1.setVisibility(View.GONE);
                        frameLayoutButton2.setVisibility(View.GONE);
                    } else {
                        button3Flags = false;
                        radioButton1.setChecked(false);
                        radioButton2.setChecked(false);
                        radioButton3.setChecked(false);
                        frameLayoutButton1.setVisibility(View.GONE);
                        frameLayoutButton2.setVisibility(View.GONE);
                        frameLayoutButton3.setVisibility(View.GONE);
                    }
                }
            }
        });

        return layoutCheckout;
    }

}
