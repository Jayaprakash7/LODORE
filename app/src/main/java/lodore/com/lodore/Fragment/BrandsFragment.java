package lodore.com.lodore.Fragment;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import lodore.com.lodore.Pojo.BrandResult;
import lodore.com.lodore.Pojo.Brandresp;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.PerfumeFilterAdapter;
import lodore.com.lodore.adapter.RecyclerviewbrandsAdapter;
import lodore.com.lodore.service.Retrofit_rest;
import retrofit.RestAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrandsFragment extends Fragment {

    private RecyclerView recyclerViewbrands;
    private RecyclerviewbrandsAdapter adapter;


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
                    .setEndpoint("http://192.168.123.10/lodore/api/")
                    .build();
        }

        protected Brandresp doInBackground(Void... params) {
            Brandresp response = null;

            try {
                Retrofit_rest list = restAdapter.create(Retrofit_rest.class);
                response = list.getBrandList();


            } catch (Exception e) {

                System.out.println("catch print  ************ "+e);
            }
            return response;

        }

        protected void onPostExecute(Brandresp response) {
            try {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }


                adapter = new RecyclerviewbrandsAdapter(getContext(), response.getProduct_result(),getActivity());
                recyclerViewbrands.setAdapter(adapter);
                recyclerViewbrands.setHasFixedSize(true);
                recyclerViewbrands.setNestedScrollingEnabled(false);
                recyclerViewbrands.setLayoutManager(new GridLayoutManager(getActivity(), 2));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
