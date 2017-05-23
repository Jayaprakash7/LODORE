package lodore.com.lodore.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import lodore.com.lodore.CheckOutActivity;
import lodore.com.lodore.Pojo.CartRequest;
import lodore.com.lodore.Pojo.CartResponse;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.CartAdapter;
import lodore.com.lodore.service.Retrofit_rest;
import retrofit.RestAdapter;


public class CartFragment extends Fragment {

    EditText editTextMain;
    Button btnHome, btnSendGift;
    RecyclerView recyclerView;
    TextView textTotal, textFullTotal;
    int totalPrice;
    ProgressDialog progressDialog;
    LinearLayout linearLayoutEmpty;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        progressDialog = new ProgressDialog(getContext());
        editTextMain = (EditText) view.findViewById(R.id.edit_main);
        recyclerView = (RecyclerView) view.findViewById(R.id.cart_recycler_view);
        btnHome = (Button) view.findViewById(R.id.btn_home);
        btnSendGift = (Button) view.findViewById(R.id.btn_send_gift);
        textTotal = (TextView) view.findViewById(R.id.total_price);
        textFullTotal = (TextView) view.findViewById(R.id.text_full_total);
        linearLayoutEmpty = (LinearLayout) view.findViewById(R.id.linear_empty);



        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CheckOutActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        btnSendGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiftFragment1 giftFragment = new GiftFragment1();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, giftFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });
        SharedPreferences preferences = getActivity().getSharedPreferences("login data", Context.MODE_PRIVATE);
        CartRequest cartRequest = new CartRequest();
        cartRequest.setId_customer(preferences.getString("_id", "null"));

        new CartDisplay().execute(cartRequest);

        return view;
    }

    public class CartDisplay extends AsyncTask<CartRequest, Void, CartResponse> {
        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {
            showDialog();
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
                System.out.println("" + e);
            }

            return response;
        }

        @Override
        protected void onPostExecute(CartResponse cartResponse) {

            try {
                totalPrice = 0;
                for (int i = 0; i < cartResponse.getResult().size(); i++) {
                    totalPrice += Integer.parseInt(cartResponse.getResult().get(i).getProduct_price());
                }

                System.out.println(totalPrice);
                String total = String.valueOf(totalPrice);
                textTotal.setText(total + " ريا");
                textFullTotal.setText(total + " ريا");

                hideDialoge();

                if (cartResponse.getResult().size()>0){
                    linearLayoutEmpty.setVisibility(View.GONE);
                    CartAdapter adapter = new CartAdapter(getContext(), cartResponse.getResult(),getActivity());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setNestedScrollingEnabled(false);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                }
                else{
                    hideDialoge();
                    linearLayoutEmpty.setVisibility(View.VISIBLE);
                }



            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public void showDialog(){
        progressDialog.setMessage("please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);
    }
    public void hideDialoge(){
        progressDialog.dismiss();
    }

}
