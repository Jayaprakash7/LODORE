package lodore.com.lodore;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import lodore.com.lodore.Fragment.CartFragment;
import lodore.com.lodore.Fragment.HomeFragment;
import lodore.com.lodore.Fragment.NavigationDrawerFragment;
import lodore.com.lodore.Fragment.PerfumeFragment;
import lodore.com.lodore.Fragment.SearchResultFragment;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    EditText search_txt;
    Toolbar toolbar;
    FrameLayout frameLayoutSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        linearLayout = (LinearLayout) findViewById(R.id.search_txt);
        search_txt = (EditText) findViewById(R.id.search_here);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer_fragment);

        frameLayoutSearch = (FrameLayout) findViewById(R.id.frame_search);

        frameLayoutSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        drawerFragment.setUp(R.id.navigation_drawer_fragment, drawerLayout, toolbar);

        if(null == savedInstanceState) {
            HomeFragment homeFragment = new HomeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, homeFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            if(linearLayout.getVisibility() == View.GONE)
            {
                linearLayout.setVisibility(View.VISIBLE);

            }else if(linearLayout.getVisibility() == View.VISIBLE){

                linearLayout.setVisibility(View.GONE);

            }
            return true;
        }
        if (id == R.id.action_cart) {
            /*Intent i = new Intent(MainActivity.this,CartActivity.class);
            startActivity(i);
            return true;*/
            CartFragment cartFragment = new CartFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, cartFragment);
            fragmentTransaction.commit();

        }

        return super.onOptionsItemSelected(item);
    }

}
