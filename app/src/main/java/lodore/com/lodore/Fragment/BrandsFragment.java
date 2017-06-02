package lodore.com.lodore.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import lodore.com.lodore.Pojo.Brandresp;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.RecyclerviewbrandsAdapter;
import lodore.com.lodore.service.Retrofit_rest;
import retrofit.RestAdapter;


public class BrandsFragment extends Fragment {

    private RecyclerView recyclerViewbrands;
    private RecyclerviewbrandsAdapter adapter;
    private String network_error;


    public BrandsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_brands, container, false);

        recyclerViewbrands = (RecyclerView) view.findViewById(R.id.recycler_viewbrands);

        new BrandItemDispaly().execute();

        return view;
    }

    public class BrandItemDispaly extends AsyncTask<Void, Void, Brandresp> {
        RestAdapter restAdapter;
        ProgressDialog dialog;


        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(getContext());
            dialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            dialog.setMessage("Please wait...");
            dialog.setCancelable(false);
            dialog.show();
            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("http://54.201.67.32/lodore/connection/api/customer")
                    .build();
        }

        protected Brandresp doInBackground(Void... params) {
            Brandresp response = null;

            try {
                Retrofit_rest list = restAdapter.create(Retrofit_rest.class);
                response = list.getBrandList();


            } catch (Exception e) {
                network_error = String.valueOf(e);
            }
            return response;

        }

        protected void onPostExecute(Brandresp response) {
            try {

                ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();

                if (info != null && info.isConnected() && network_error == null) {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }


                    adapter = new RecyclerviewbrandsAdapter(getContext(), response.getProduct_result(), getActivity());
                    recyclerViewbrands.setAdapter(adapter);
                    recyclerViewbrands.setHasFixedSize(true);
                    recyclerViewbrands.setNestedScrollingEnabled(false);
                    recyclerViewbrands.setLayoutManager(new GridLayoutManager(getContext(), 2));
                }
                else {
                    alertDialog();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public  void alertDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
                    }
                });

                Button btnNeg = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                btnNeg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivityForResult(new Intent(Settings.ACTION_SETTINGS),0);
                        alertDialog.dismiss();
                    }
                });
            }
        });

        alertDialog.show();
    }

}
