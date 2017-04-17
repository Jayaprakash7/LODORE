package lodore.com.lodore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import lodore.com.lodore.Fragment.AboutusFragment;
import lodore.com.lodore.Fragment.BlogFragment;
import lodore.com.lodore.Fragment.BranddetailsFragment;
import lodore.com.lodore.Fragment.BrandsFragment;
import lodore.com.lodore.Fragment.CartFragment;
import lodore.com.lodore.Fragment.ContactusFragment;
import lodore.com.lodore.Fragment.FaqFragment;
import lodore.com.lodore.Fragment.GiftFragment1;
import lodore.com.lodore.Fragment.HomeFragment;
import lodore.com.lodore.Fragment.LoginFragment;
import lodore.com.lodore.Fragment.MyaccountFragment;
import lodore.com.lodore.Fragment.NavigationDrawerFragment;
import lodore.com.lodore.Fragment.PerfumeFragment;
import lodore.com.lodore.Fragment.PrivacypolicyFragment;
import lodore.com.lodore.Fragment.ProductDetailsFragment;
import lodore.com.lodore.Fragment.QuizFragment1;
import lodore.com.lodore.Fragment.RegisterFragment;
import lodore.com.lodore.Fragment.SearchResultFragment;

import static lodore.com.lodore.R.id.login;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    TextView toolbar_text,login;
    Toolbar toolbar;
    FrameLayout frameLayoutSearch;
    Button search_btn;
    ImageView image_cart,image_search;

    SharedPreferences pref;
    String Id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_text = (TextView) findViewById(R.id.toolbar_text);
        image_cart = (ImageView) findViewById(R.id.toolbar_cart);
        image_search = (ImageView) findViewById(R.id.toolbar_search);
        linearLayout = (LinearLayout) findViewById(R.id.search_txt);
        search_btn = (Button) findViewById(R.id.search_btn);
        toolbar_text.setText("الرئيسية");

        login =(TextView) findViewById(R.id.nav_login);

        pref = getSharedPreferences("login data", Context.MODE_MULTI_PROCESS);
        Id = pref.getString("_id", "null");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer_fragment);

        image_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartFragment();
                drawerLayout.closeDrawers();
            }
        });
        image_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(linearLayout.getVisibility() == View.GONE)
                {
                    drawerLayout.closeDrawers();
                    linearLayout.setVisibility(View.VISIBLE);

                }else if(linearLayout.getVisibility() == View.VISIBLE){

                    drawerLayout.closeDrawers();
                    linearLayout.setVisibility(View.GONE);

                }

            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchFragment();
                drawerLayout.closeDrawers();
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        drawerFragment.getHome().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                toolbar_text.setText("الرئيسية");
                drawerLayout.closeDrawers();
            }
        });

        drawerFragment.getPerfume().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfumeFragment();
                toolbar_text.setText("العطور");
                drawerLayout.closeDrawers();
            }
        });

        drawerFragment.getContact_us().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactusFragment();
                toolbar_text.setText("تصل بنا");
                drawerLayout.closeDrawers();
            }
        });

        drawerFragment.getAbout_us().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutusFragment();
                toolbar_text.setText("من نحن");
                drawerLayout.closeDrawers();
            }
        });

        drawerFragment.getMy_account().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyaccountFragment();
                toolbar_text.setText("حسابي");
                drawerLayout.closeDrawers();
            }
        });

        drawerFragment.getLinearLayoutLogout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings = getSharedPreferences("login data", Context.MODE_PRIVATE);
                settings.edit().clear().apply();
                drawerLayout.closeDrawers();
                homeActivity();
            }
        });

        drawerFragment.getLogin().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginFragment();
                toolbar_text.setText("تسجيل الدخول");
                drawerLayout.closeDrawers();
            }
        });

        drawerFragment.getLinearRegister().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterFragment();
                toolbar_text.setText("إنشاء حساب");
                drawerLayout.closeDrawers();
            }
        });

        /*drawerFragment.getQuiz().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz();
                toolbar_text.setText("اختبار العطر");
                drawerLayout.closeDrawers();
            }
        });*/

        drawerFragment.getBlog().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlogFragment();
                toolbar_text.setText("المـدونة");
                drawerLayout.closeDrawers();
            }
        });

        drawerFragment.getProductdetails().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productdetails();
                toolbar_text.setText("العطور");
                drawerLayout.closeDrawers();
            }
        });

        drawerFragment.getSendgift().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                perfumeFragment();
                toolbar_text.setText("العطور");

               // GiftFragment1();
                //toolbar_text.setText("أرسل هدية");
                drawerLayout.closeDrawers();
            }
        });

        drawerFragment.getFaq().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FaqFragment();
                toolbar_text.setText("أسئلة الخصوصية");
                drawerLayout.closeDrawers();
            }
        });

        drawerFragment.getFragnance_fmaily().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrandsFragment();
                toolbar_text.setText("دور العطور");
                drawerLayout.closeDrawers();
            }
        });

        drawerFragment.getPrivacy_policy().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrivacypolicyFragment();
                toolbar_text.setText("سياسة الخصوصية");
                drawerLayout.closeDrawers();
            }
        });


        drawerFragment.setUp(R.id.navigation_drawer_fragment, drawerLayout, toolbar);

        if (null == savedInstanceState) {
            HomeFragment homeFragment = new HomeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, homeFragment);
            fragmentTransaction.commit();
        }
    }

    private void homeActivity() {
        Intent intent = new Intent(MainActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void searchFragment() {
        SearchResultFragment searchResultFragment = new SearchResultFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,searchResultFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        linearLayout.setVisibility(View.GONE);
    }

    private void cartFragment() {

        CartFragment cartFragment = new CartFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, cartFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


    public void homeFragment() {
        HomeFragment homeFragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, homeFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void perfumeFragment() {
        PerfumeFragment perfumeFragment = new PerfumeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, perfumeFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void productdetails() {
        ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, productDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void branddetails() {
        BranddetailsFragment branddetails = new BranddetailsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, branddetails);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void quiz() {
        QuizFragment1 quiz = new QuizFragment1();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, quiz);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void ContactusFragment() {
        ContactusFragment contactusFragment = new ContactusFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, contactusFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void AboutusFragment() {
        AboutusFragment aboutusFragment = new AboutusFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, aboutusFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void MyaccountFragment() {
        MyaccountFragment myaccountFragment = new MyaccountFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, myaccountFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void LoginFragment() {
        LoginFragment loginFragment = new LoginFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, loginFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void RegisterFragment(){
        RegisterFragment registerFragment = new RegisterFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, registerFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void PrivacypolicyFragment() {
        PrivacypolicyFragment privacypolicyFragment = new PrivacypolicyFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, privacypolicyFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void BlogFragment() {
        BlogFragment blogFragment = new BlogFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, blogFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void FaqFragment() {
        FaqFragment faq_Fragment = new FaqFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, faq_Fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void GiftFragment1() {
        GiftFragment1 giftFragment1 = new GiftFragment1();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, giftFragment1);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    public void BrandsFragment() {
        BrandsFragment brandsFragment = new BrandsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, brandsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
