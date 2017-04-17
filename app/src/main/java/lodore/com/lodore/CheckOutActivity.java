package lodore.com.lodore;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import lodore.com.lodore.Fragment.CheckoutLoginFragment;
import lodore.com.lodore.Fragment.PerfumeFragment;
import lodore.com.lodore.Fragment.RegisterFragment;

public class CheckOutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        CheckoutLoginFragment loginFragment = new CheckoutLoginFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.checkout_container,loginFragment);
        fragmentTransaction.commit();

    }


}
