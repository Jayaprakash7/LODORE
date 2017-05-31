package lodore.com.lodore.Fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lodore.com.lodore.MainActivity;
import lodore.com.lodore.Pojo.Brandresp;
import lodore.com.lodore.Pojo.PerfumeResponse;
import lodore.com.lodore.Pojo.SearchRequest;
import lodore.com.lodore.Pojo.SearchResultDto;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.RecyclerviewbrandsAdapter;
import lodore.com.lodore.adapter.SearchResultAdapter;
import lodore.com.lodore.service.Retrofit_rest;
import retrofit.RestAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends Fragment {


    RecyclerView recyclerView;
    SearchResultAdapter adapter;
    LinearLayout linearLayoutPerfumeHouse;
    LinearLayout linearLayoutPerfumes;
    String search_text;
    TextView totalProductText;
    private String network_error;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_search);
        linearLayoutPerfumes = (LinearLayout) view.findViewById(R.id.linear_perfumes);
        linearLayoutPerfumeHouse = (LinearLayout) view.findViewById(R.id.linear_perfume_house);


        totalProductText = (TextView) view.findViewById(R.id.total_product_count);

        SharedPreferences pref = getActivity().getSharedPreferences("Search_Result", Context.MODE_PRIVATE);
        search_text = pref.getString("text", null);

        Toast.makeText(getActivity(), "" + search_text, Toast.LENGTH_LONG).show();

        final SearchRequest searchRequest = new SearchRequest();
        searchRequest.setPerfume_search(search_text);

        new PerfumeSearch().execute(searchRequest);

        linearLayoutPerfumes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new PerfumeSearch().execute(searchRequest);
            }
        });

        linearLayoutPerfumeHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchRequest.setBrand_search(search_text);
                new BrandSearch().execute(searchRequest);

            }
        });


        return view;
    }

    public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable divider;

        public SimpleDividerItemDecoration(Context context) {
            divider = context.getResources().getDrawable(R.drawable.line_divider);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + divider.getIntrinsicHeight();

                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }
    }

    public class PerfumeSearch extends AsyncTask<SearchRequest, Void, PerfumeResponse> {
        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {
            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("http://54.201.67.32/lodore/connection/api/customer")
                    .build();
        }

        @Override
        protected PerfumeResponse doInBackground(SearchRequest... params) {
            PerfumeResponse response = null;
            try {
                Retrofit_rest rest = restAdapter.create(Retrofit_rest.class);
                response = rest.getsearchPerfume(params[0]);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(PerfumeResponse perfumeResponse) {

            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();

                if (info != null && info.isConnected()) {
                    totalProductText.setText("تم العثور على" + perfumeResponse.getProduct().size() + "  نتائج");
                    adapter = new SearchResultAdapter(getContext(), perfumeResponse.getProduct(), getActivity());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setNestedScrollingEnabled(false);
                    recyclerView.setAdapter(adapter);
                    recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                } else {
                    alertDialog();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public class BrandSearch extends AsyncTask<SearchRequest, Void, Brandresp> {
        RestAdapter restAdapter = null;

        @Override
        protected void onPreExecute() {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://54.201.67.32/lodore/connection/api/customer/")
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
        }

        @Override
        protected Brandresp doInBackground(SearchRequest... params) {
            Brandresp response = null;
            try {
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                response = retrofitRest.getsearchBrand(params[0]);

            } catch (Exception e) {
                network_error = String.valueOf(e);
            }
            return response;
        }

        @Override
        protected void onPostExecute(Brandresp perfumeResponse) {
            try {

                ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();

                if (info != null && info.isConnected() && network_error == null) {
                    totalProductText.setText("تم العثور على" + perfumeResponse.getProduct_result().size() + "  نتائج");
                    RecyclerviewbrandsAdapter adapter = new RecyclerviewbrandsAdapter(getContext(), perfumeResponse.getProduct_result(), getActivity());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setNestedScrollingEnabled(false);
                    recyclerView.setAdapter(adapter);
                    recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                } else {
                    alertDialog();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
