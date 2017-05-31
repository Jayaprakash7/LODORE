package lodore.com.lodore.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.EventLogTags;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.net.SocketException;
import java.util.List;
import java.util.logging.Logger;

import lodore.com.lodore.Pojo.BrandInfo;
import lodore.com.lodore.Pojo.Brandresp;
import lodore.com.lodore.Pojo.CartRequest;
import lodore.com.lodore.Pojo.HomeFragrancePlantResponse;
import lodore.com.lodore.Pojo.PerfumeResponse;
import lodore.com.lodore.Pojo.SearchRequest;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.CartAdapter;
import lodore.com.lodore.adapter.HomeFragrantPlantsAdapter;
import lodore.com.lodore.adapter.PerfumeAdapter;
import lodore.com.lodore.adapter.PerfumeFragmentAdapter;
import lodore.com.lodore.service.Retrofit_rest;
import lodore.com.lodore.utils.RecyclerItemClickListener;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;


public class HomeFragment extends Fragment {

    Button start_journey;
    private RecyclerView recyclerViewFragrancePlants, recyclerSuggestProduct;
    private ProgressDialog progressDialog;
    private String brandId;
    private String network_error;


    private void QuizFragment1() {

        QuizFragment1 quizFragment1 = new QuizFragment1();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, quizFragment1);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        start_journey = (Button) view.findViewById(R.id.start_jeorney);
        progressDialog = new ProgressDialog(getActivity());

        start_journey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuizFragment1();
            }
        });

        recyclerViewFragrancePlants = (RecyclerView) view.findViewById(R.id.recycler_view_fragrance_families);
        recyclerSuggestProduct = (RecyclerView) view.findViewById(R.id.recycler_suggest_brand);

        new FragrantPlantDisplay().execute();
        return view;
    }

    public class FragrantPlantDisplay extends AsyncTask<Void, Void, Brandresp> {

        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {

            showDialog();
            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("http://54.201.67.32/lodore/connection/api/customer")
                    .build();
        }

        @Override
        protected Brandresp doInBackground(Void... params) {
            Brandresp plantResponse = null;
            try {
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                plantResponse = retrofitRest.getBrandList();
            } catch (Exception e) {
                network_error = String.valueOf(e);
            }

            return plantResponse;
        }


        @Override
        protected void onPostExecute(final Brandresp homeFragrancePlantResponse) {

            try {

                ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();

                if (info != null && info.isConnected() && network_error == null) {


                    hideDialoge();
                    HomeFragrantPlantsAdapter adapter = new HomeFragrantPlantsAdapter(getContext(), homeFragrancePlantResponse.getProduct_result(), getActivity());
                    String first_brand_id = homeFragrancePlantResponse.getProduct_result().get(0).getId();

                    BrandInfo brandInfo = new BrandInfo();
                    brandInfo.setBrand_id(first_brand_id);
                    new SuggestBrands().execute(brandInfo);

                    recyclerViewFragrancePlants.setAdapter(adapter);
                    recyclerViewFragrancePlants.setHasFixedSize(true);
                    recyclerViewFragrancePlants.setNestedScrollingEnabled(false);
                    recyclerViewFragrancePlants.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

                    recyclerViewFragrancePlants.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {


                            brandId = homeFragrancePlantResponse.getProduct_result().get(position).getId();

                            Toast.makeText(getContext(), "" + brandId, Toast.LENGTH_SHORT).show();

                            BrandInfo brandInfo = new BrandInfo();
                            brandInfo.setBrand_id(brandId);
                            new SuggestBrands().execute(brandInfo);
                        }
                    }));


                }

                else {

                    alertDialog();
                }


            } catch (Exception e) {
                System.out.println(""+e);
            }

        }
    }

    public class SuggestBrands extends AsyncTask<BrandInfo, Void, PerfumeResponse> {

        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {
            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("http://54.201.67.32/lodore/connection/api/customer/")
                    .build();
        }

        @Override
        protected PerfumeResponse doInBackground(BrandInfo... params) {
            PerfumeResponse response = null;
            try {

                Retrofit_rest rest = restAdapter.create(Retrofit_rest.class);
                response = rest.getSuggestPerfume(params[0]);

            } catch (Exception e) {
                Log.d("Network Error", "doInBackground: " + e);

            }
            return response;
        }

        @Override
        protected void onPostExecute(PerfumeResponse perfumeResponse) {

            try {


                ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();

                if (info != null && info.isConnected() && network_error == null) {
                    PerfumeFragmentAdapter adapter = new PerfumeFragmentAdapter(getContext(), perfumeResponse.getProduct(), getActivity());


                    recyclerSuggestProduct.setAdapter(adapter);
                    recyclerSuggestProduct.setHasFixedSize(true);
                    recyclerSuggestProduct.setNestedScrollingEnabled(false);
                    recyclerSuggestProduct.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                }

            } catch (Exception e) {
                System.out.print("" + e);
            }

        }
    }


    public void showDialog() {
        progressDialog.setMessage("please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    public void hideDialoge() {
        progressDialog.dismiss();
    }

    public void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Please Check The InternetConnection");
        builder.setNegativeButton("Setting", null);
        builder.setPositiveButton("Ok", null);

        final AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button btnPos = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnPos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        getActivity().finish();
                    }
                });

                Button btnNeg = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                btnNeg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                        alertDialog.dismiss();
                    }
                });
            }
        });

        alertDialog.show();
    }


}
