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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import lodore.com.lodore.Pojo.BrandDetailsResponse;
import lodore.com.lodore.Pojo.BrandInfo;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.RecyclerviewbeanddetailsAdapter;
import lodore.com.lodore.adapter.RecyclerviewbrandsAdapter;
import lodore.com.lodore.service.Retrofit_rest;
import retrofit.RestAdapter;

public class BranddetailsFragment extends Fragment {

    private RecyclerView recyclerViewbrandsdetails;
    private RecyclerviewbeanddetailsAdapter adapter;

    ImageView brand_details_image;
    TextView brand_title, brand_description;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_branddetails, container, false);

        BrandInfo id = new BrandInfo();
        id.setIdCategory(RecyclerviewbrandsAdapter.selected_product_id);

        recyclerViewbrandsdetails = (RecyclerView) view.findViewById(R.id.recycler_branddetails);

        new BrandDetailsFragmentDispaly().execute(id);

        brand_details_image = (ImageView) view.findViewById(R.id.brand_details_image);
        brand_title = (TextView) view.findViewById(R.id.brand_title);
        brand_description = (TextView) view.findViewById(R.id.brand_description);

        return view;
    }

    public class BrandDetailsFragmentDispaly extends AsyncTask<BrandInfo, Void, BrandDetailsResponse> {
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

        protected BrandDetailsResponse doInBackground(BrandInfo... params) {
            BrandDetailsResponse response = null;

            try {
                Retrofit_rest list = restAdapter.create(Retrofit_rest.class);
                response = list.getBrandDeatails(params[0]);
            } catch (Exception e) {

            }
            return response;

        }

        protected void onPostExecute(BrandDetailsResponse response) {
            try {
                if (response.getStatus().equals("success")) {

                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }

                    Glide.with(getActivity())
                            .load("http://54.201.67.32/lodore/connection/" + response.getBrand_detail().get(0).getBrandPic())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(brand_details_image);

                    brand_title.setText(response.getBrand_detail().get(0).getBrandHead());
                    brand_description.setText(response.getBrand_detail().get(0).getDescription()); }
                else

                {  Toast.makeText(getContext(), "Wrong respose Credential", Toast.LENGTH_SHORT).show(); }

                adapter = new RecyclerviewbeanddetailsAdapter(getContext(), response.getbrandProducts(), getActivity());
                recyclerViewbrandsdetails.setAdapter(adapter);
                recyclerViewbrandsdetails.setHasFixedSize(true);
                recyclerViewbrandsdetails.setNestedScrollingEnabled(false);
                recyclerViewbrandsdetails.setLayoutManager(new GridLayoutManager(getActivity(), 2));

            } catch (Exception e) {
            }
        }
    }


}
