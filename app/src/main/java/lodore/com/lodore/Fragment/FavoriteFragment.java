package lodore.com.lodore.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import lodore.com.lodore.Pojo.CartResponse;
import lodore.com.lodore.Pojo.FavouriteRequest;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.CartAdapter;
import lodore.com.lodore.adapter.FavoriteAdapter;
import lodore.com.lodore.service.Retrofit_rest;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    RecyclerView recyclerViewFavorite;
    SharedPreferences pref;
    private String network_error;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        recyclerViewFavorite = (RecyclerView) view.findViewById(R.id.recycler_view_favorite_list);

        pref = getContext().getSharedPreferences("login data", Context.MODE_PRIVATE);
        String id = pref.getString("_id", "null");

        FavouriteRequest favouriteRequest = new FavouriteRequest();
        favouriteRequest.setId_customer(id);

        new FavouriteHistory().execute(favouriteRequest);
        return view;
    }

    public class FavouriteHistory extends AsyncTask<FavouriteRequest, Void, CartResponse>
    {
        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
            okHttpClient.setReadTimeout(10, TimeUnit.SECONDS);

            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("http://54.201.67.32/lodore/connection/api/customer")
                    .setClient(new OkClient(okHttpClient))
                    .build();

        }

        @Override
        protected CartResponse doInBackground(FavouriteRequest... params) {

            CartResponse cartResponse = null;

            try {

                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                cartResponse = retrofitRest.getFavoriteList(params[0]);

            }catch (Exception e)
            {
                network_error = String.valueOf(e);
            }

            return cartResponse;
        }

        @Override
        protected void onPostExecute(CartResponse cartResponse) {

            FavoriteAdapter adapter = new FavoriteAdapter(getContext(),cartResponse.getResult(),getActivity());

            recyclerViewFavorite.setAdapter(adapter);
            recyclerViewFavorite.setHasFixedSize(true);
            recyclerViewFavorite.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        }
    }

}
