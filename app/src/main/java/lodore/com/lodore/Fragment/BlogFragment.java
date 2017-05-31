package lodore.com.lodore.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lodore.com.lodore.Pojo.BlogResponse;
import lodore.com.lodore.Pojo.Blogdisplay;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.BlogAdapter;
import lodore.com.lodore.adapter.RecyclerviewblogAdapter;
import lodore.com.lodore.adapter.RecyclerviewbrandsAdapter;
import lodore.com.lodore.service.Retrofit_rest;
import retrofit.RestAdapter;

public class BlogFragment extends Fragment {

    private RecyclerView recyclerViewblog;
    private ProgressDialog progressDialog;
    private String network_error;


    public BlogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog, container, false);

        progressDialog = new ProgressDialog(getActivity());
        recyclerViewblog = (RecyclerView) view.findViewById(R.id.recycler_blog);
        new BlogItemDisplay().execute();

        return view;
    }

    public class BlogItemDisplay extends AsyncTask<Void, Void, BlogResponse> {

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
        protected BlogResponse doInBackground(Void... params) {

            BlogResponse response = null;

            try {
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                response = retrofitRest.getBlogList();

            } catch (Exception e) {
                network_error = String.valueOf(e);
            }

            return response;
        }

        @Override
        protected void onPostExecute(BlogResponse blogResponse) {

            try {

                ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();

                if (info != null && info.isConnected() && network_error == null) {
                    hideDialoge();
                    BlogAdapter adapter = new BlogAdapter(getContext(), blogResponse.getBlogs());

                    recyclerViewblog.setAdapter(adapter);
                    recyclerViewblog.setHasFixedSize(true);
                    recyclerViewblog.setNestedScrollingEnabled(false);
                    recyclerViewblog.setLayoutManager(new LinearLayoutManager(getActivity()));
                } else {
                    alertDialog();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void showDialog() {

        progressDialog.setMessage("please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    public void hideDialoge() {
        progressDialog.dismiss();
    }

    public  void alertDialog()
    {
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
