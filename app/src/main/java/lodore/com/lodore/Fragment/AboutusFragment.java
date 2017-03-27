package lodore.com.lodore.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import lodore.com.lodore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutusFragment extends Fragment {


    Toolbar toolbar;
    ImageView image_nav_menu,image_tool_cart,image_tool_search;

   /* NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) ((AppCompatActivity)getActivity()).getSupportFragmentManager().findFragmentById(R.id.navigation_drawer_fragment);
    DrawerLayout drawerLayout;*/


    public AboutusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_aboutus, container, false);

        return view;
    }

}
