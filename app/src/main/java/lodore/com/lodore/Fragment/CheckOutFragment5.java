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

import lodore.com.lodore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckOutFragment5 extends Fragment {


    public CheckOutFragment5() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_check_out_fragment5, container, false);

        Button btnCheckout = (Button) layout.findViewById(R.id.btn_checkout5);
        Toolbar toolbar = (Toolbar) layout.findViewById(R.id.toolbar);

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


        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GiftFragment1 fragment1 = new GiftFragment1();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.checkout_container_layout, fragment1);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        return layout;
    }

}
