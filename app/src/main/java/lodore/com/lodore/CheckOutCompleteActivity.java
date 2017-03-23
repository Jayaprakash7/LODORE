package lodore.com.lodore;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lodore.com.lodore.Fragment.CheckOutFragment1;

public class CheckOutCompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_complete);

        if (savedInstanceState == null) {
            CheckOutFragment1 checkOutFragment1 = new CheckOutFragment1();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.checkout_container_layout, checkOutFragment1);
            transaction.commit();
        }


    }
}
