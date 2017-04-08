package lodore.com.lodore.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lodore.com.lodore.MainActivity;
import lodore.com.lodore.R;

import static lodore.com.lodore.R.id.login;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    private ActionBarDrawerToggle mActionBarDrawerToggle;
    //private DrawerLayout mDrawerLayout;

    private boolean mUserLearnedDrawer;
    private boolean mSavedInstanceState;

    private View containerView;
    private DrawerLayout mDrawerLayout;

    TextView home,perfume,contact_us,about_us,my_account,login,fragnance_fmaily;
    TextView quiz,blog,productdetails,branddetails,shippinganddeliveryifo,sendgift,faq,privacy_policy;

    SharedPreferences pref;
    String Id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        home = (TextView) view.findViewById(R.id.nav_home);
        perfume = (TextView) view.findViewById(R.id.nav_perfume);
        about_us = (TextView) view.findViewById(R.id.nav_about_us);
        my_account = (TextView) view.findViewById(R.id.nav_my_account);
        login = (TextView) view.findViewById(R.id.nav_login);
        fragnance_fmaily = (TextView) view.findViewById(R.id.nav_fragnance_familes);


        blog = (TextView) view.findViewById(R.id.nav_blog);
        sendgift = (TextView) view.findViewById(R.id.nav_send_gift);
        shippinganddeliveryifo = (TextView) view.findViewById(R.id.nav_shippinganddelivery);
        faq = (TextView) view.findViewById(R.id.nav_faq);
        privacy_policy = (TextView) view.findViewById(R.id.nav_privacy_policy);
        contact_us = (TextView) view.findViewById(R.id.nav_contact_us);
        quiz = (TextView) view.findViewById(R.id.nav_quiz);
        branddetails = (TextView) view.findViewById(R.id.nav_brand_details);
        productdetails = (TextView) view.findViewById(R.id.nav_product_details);

        pref = getContext().getSharedPreferences("login data", Context.MODE_MULTI_PROCESS);
        Id = pref.getString("_id", "null");



        return view;
    }

    public TextView getHome(){
        return home;
    }
    public TextView getPerfume(){
        return perfume;
    }
    public TextView getContact_us(){
        return contact_us;
    }
    public TextView getAbout_us(){
        return about_us;
    }
    public TextView getMy_account(){
        return my_account;
    }
    public TextView getLogin(){

        if(Id.equals("null")){
            login.setText("Login");
        }else{
            login.setText("LogOut");
        }
        return login;
    }
    public TextView getFragnance_fmaily(){
        return fragnance_fmaily;
    }
    public TextView getQuiz(){
        return quiz;
    }
    public TextView getBlog(){
        return blog;
    }
    public TextView getProductdetails(){return productdetails;}
    public TextView getBranddetails(){return branddetails;}
    public TextView getShippinganddeliveryifo(){return shippinganddeliveryifo;}
    public TextView getSendgift(){return sendgift;}
    public TextView getFaq(){return faq;}
    public TextView getPrivacy_policy(){return privacy_policy;}

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolBar) {

        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolBar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mActionBarDrawerToggle.syncState();
            }
        });

    }

}
