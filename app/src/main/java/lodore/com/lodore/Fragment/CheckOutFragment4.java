package lodore.com.lodore.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lodore.com.lodore.Pojo.CartRequest;
import lodore.com.lodore.Pojo.CartResponse;
import lodore.com.lodore.Pojo.CheckOut4DTO;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.CartAdapter;
import lodore.com.lodore.adapter.Checkout4RecyclerAdapter;
import lodore.com.lodore.service.Retrofit_rest;
import retrofit.RestAdapter;


public class CheckOutFragment4 extends Fragment {


    RecyclerView recyclerView;
    Checkout4RecyclerAdapter adapter;
    TextView textPrice,textTotalPrice;
    private Button btnComplete;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_out_fragment4, container, false);
        btnComplete = (Button) view.findViewById(R.id.checkout_button4);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_checkout4);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        textPrice = (TextView) view.findViewById(R.id.text_price);
        textTotalPrice = (TextView) view.findViewById(R.id.text_total_price);

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

        SharedPreferences preferences = getActivity().getSharedPreferences("login data", Context.MODE_PRIVATE);
        CartRequest cartRequest = new CartRequest();
        cartRequest.setId_customer(preferences.getString("_id", "null"));

        new CartDisplay().execute(cartRequest);

        /*textPrice.setText(CartFragment.totalPrice);
        textTotalPrice.setText(CartFragment.totalPrice);*/



        return view;
    }

    public class CartDisplay extends AsyncTask<CartRequest, Void, CartResponse> {
        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {

            restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://54.201.67.32/lodore/connection/api/customer")
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
        }

        @Override
        protected CartResponse doInBackground(CartRequest... params) {
            CartResponse response = null;

            try {
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                response = retrofitRest.getCart(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(final CartResponse cartResponse) {

            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();

                if (info != null && info.isConnected() ) {

                        Checkout4RecyclerAdapter adapter = new Checkout4RecyclerAdapter(getContext(), cartResponse.getResult());
                        recyclerView.setAdapter(adapter);
                        recyclerView.setNestedScrollingEnabled(false);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                   int totalPrice = 0;
                    for (int i = 0; i < cartResponse.getResult().size(); i++) {
                        totalPrice += Integer.parseInt(cartResponse.getResult().get(i).getProduct_price());
                    }

                    System.out.println(totalPrice);
                    String total = String.valueOf(totalPrice);
                    textPrice.setText(total + " ريا");
                    textTotalPrice.setText(total + " ريا");

                    btnComplete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            for (int i=0;i<cartResponse.getResult().size();i++)
                            {

                                SharedPreferences preferences = getActivity().getSharedPreferences("login data", Context.MODE_PRIVATE);
                                String id_customer = preferences.getString("_id", "null");

                                CartRequest cartRequest = new CartRequest();
                                cartRequest.setProduct_name(cartResponse.getResult().get(i).getProduct_name());
                                cartRequest.setProduct_image(cartResponse.getResult().get(i).getProduct_image());
                                cartRequest.setPrice(cartResponse.getResult().get(i).getProduct_price());
                                cartRequest.setQuantity(cartResponse.getResult().get(i).getQuantity());
                                cartRequest.setId_customer(id_customer);
                                cartRequest.setStatus("pending");

                                new OrderHistory().execute(cartRequest);
                            }

                            SharedPreferences preferences = getActivity().getSharedPreferences("login data", Context.MODE_PRIVATE);
                            String id_customer = preferences.getString("_id", "null");

                            CartRequest cartRequest = new CartRequest();
                            cartRequest.setId_customer(id_customer);
                            new CartDelete().execute(cartRequest);



                        }
                    });

                }



            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private class OrderHistory extends AsyncTask<CartRequest, Void, CartResponse> {
        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {

            restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://54.201.67.32/lodore/connection/api/customer")
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
        }

        @Override
        protected CartResponse doInBackground(CartRequest... params) {
            CartResponse response = null;

            try {
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                response = retrofitRest.insertToOrderHistory(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(CartResponse cartResponse) {


        }
    }

    private class CartDelete extends AsyncTask<CartRequest, Void, CartResponse>{

        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://54.201.67.32/lodore/connection/api/customer")
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
        }

        @Override
        protected CartResponse doInBackground(CartRequest... params) {
            CartResponse cartResponse = null;

            try{
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                cartResponse = retrofitRest.deleteCartByCustomer(params[0]);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return cartResponse;
        }

        @Override
        protected void onPostExecute(CartResponse cartResponse) {
            CheckOutFragment5 checkOutFragment5 = new CheckOutFragment5();
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.addToBackStack(null);
            transaction.replace(R.id.checkout_container_layout, checkOutFragment5);
            transaction.commit();
        }
    }
}
