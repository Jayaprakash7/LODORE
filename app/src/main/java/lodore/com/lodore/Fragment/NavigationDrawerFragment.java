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
    TextView register,brands,blog,productdetails,branddetails,shippinganddeliveryifo,sendgift,faq,privacy_policy;


    public void perfumeFragment(){
        PerfumeFragment perfumeFragment = new PerfumeFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,perfumeFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void ContactusFragment(){
        ContactusFragment contactusFragment = new ContactusFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,contactusFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void AboutusFragment(){
        AboutusFragment aboutusFragment = new AboutusFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,aboutusFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void MyaccountFragment(){
        MyaccountFragment myaccountFragment = new MyaccountFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,myaccountFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void LoginFragment(){
        LoginFragment loginFragment = new LoginFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,loginFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void RegisterFragment(){
        RegisterFragment registerFragment = new RegisterFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,registerFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void PrivacypolicyFragment(){
        PrivacypolicyFragment privacypolicyFragment = new PrivacypolicyFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,privacypolicyFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void BlogFragment(){
        BlogFragment blogFragment = new BlogFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,blogFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void FaqFragment(){
        FaqFragment faq_Fragment = new FaqFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,faq_Fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void GiftFragment1(){
        GiftFragment1 giftFragment1 = new GiftFragment1();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,giftFragment1);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

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


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        perfume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfumeFragment();
                mDrawerLayout.closeDrawers();

            }
        });

        fragnance_fmaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //perfumeFragment();
                mDrawerLayout.closeDrawers();

                SearchResultFragment searchResultFragment = new SearchResultFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.content_frame, searchResultFragment);
                transaction.commit();



            }
        });

        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutusFragment();
                mDrawerLayout.closeDrawers();
            }
        });


        my_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyaccountFragment();
                mDrawerLayout.closeDrawers();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment();
                mDrawerLayout.closeDrawers();
            }
        });

        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlogFragment();
                mDrawerLayout.closeDrawers();
            }
        });

        sendgift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiftFragment1();
                mDrawerLayout.closeDrawers();
            }
        });

        shippinganddeliveryifo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //BlogFragment();
                mDrawerLayout.closeDrawers();
            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FaqFragment();
                mDrawerLayout.closeDrawers();
            }
        });

        privacy_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrivacypolicyFragment();
                mDrawerLayout.closeDrawers();
            }
        });

        contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactusFragment();
                mDrawerLayout.closeDrawers();
            }
        });


        /*contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactusFragment();
                mDrawerLayout.closeDrawers();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment();
                mDrawerLayout.closeDrawers();
            }
        });

        brands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrandsFragment();
                mDrawerLayout.closeDrawers();
            }
        });



        productdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDetailsFragment();
                mDrawerLayout.closeDrawers();
            }
        });

        branddetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BranddetailsFragment();
                mDrawerLayout.closeDrawers();
            }
        });
*/
        return view;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolBar) {

        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolBar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                /*if (!mUserLearnedDrawer){
                    mUserLearnedDrawer = true;
                    savedPreferences(getActivity(), "user_learned_drawer",mUserLearnedDrawer+"");
                }*/
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

        /*if (!mUserLearnedDrawer && !mSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }*/

    }
   /* public static void savedPreferences(Context context, String preferencesName, String preferencesValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences("test",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferencesName,preferencesValue);
        editor.apply();
    }
    public static String readPreferences(Context context, String preferencesName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences("test",Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferencesName,defaultValue);

    }*/

}
