package lodore.com.lodore.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lodore.com.lodore.Pojo.CartRequest;
import lodore.com.lodore.Pojo.CartResponse;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.OrderHistoryAdapter;
import lodore.com.lodore.service.Retrofit_rest;
import retrofit.RestAdapter;


public class OrderHistoryFragment extends Fragment {

    RecyclerView recyclerOrderHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_history, container, false);
        recyclerOrderHistory = (RecyclerView) view.findViewById(R.id.recycler_oder_history);

        CartRequest cartRequest = new CartRequest();
        SharedPreferences preferences = getContext().getSharedPreferences("login data", Context.MODE_PRIVATE);
        cartRequest.setId_customer(preferences.getString("_id", "null"));
        new OrderHistory().execute(cartRequest);
        return view;
    }

    private class OrderHistory extends AsyncTask<CartRequest, Void, CartResponse>{

        RestAdapter restAdapter;
        @Override
        protected void onPreExecute() {
            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("http://54.201.67.32/lodore/connection/api/customer")
                    .build();
        }

        @Override
        protected CartResponse doInBackground(CartRequest... params) {

            CartResponse response = null;

            try {
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                response = retrofitRest.orderHIstory(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;

        }

        @Override
        protected void onPostExecute(CartResponse cartResponse) {
            try {

                ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();

                if (info != null && info.isConnected()) {

                    OrderHistoryAdapter orderHistoryAdapter = new OrderHistoryAdapter(getContext(),cartResponse.getResult());

                    recyclerOrderHistory.setAdapter(orderHistoryAdapter);
                    recyclerOrderHistory.setHasFixedSize(true);
                    recyclerOrderHistory.setNestedScrollingEnabled(false);
                    recyclerOrderHistory.setLayoutManager(new LinearLayoutManager(getContext()));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
