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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import lodore.com.lodore.Pojo.CartRequest;
import lodore.com.lodore.Pojo.CartResponse;
import lodore.com.lodore.Pojo.FavouriteRequest;
import lodore.com.lodore.Pojo.PerfumeDetail;
import lodore.com.lodore.Pojo.ProductDetailsResponse;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.PerfumeFragmentAdapter;
import lodore.com.lodore.adapter.ProductDetailsSimilarBrandsAdapter;
import lodore.com.lodore.service.Retrofit_rest;
import retrofit.RestAdapter;


public class ProductDetailsFragment extends Fragment {

    RecyclerView recyclerViewSimilarProduct;
    TextView textName, textDescription, textSize, textConcentration, textPerfumeStory, textPrice;
    ImageView imageProduct;
    ProductDetailsSimilarBrandsAdapter adapter;
    RelativeLayout relativeCartLayout, relativeAddFav, relativeShare;
    TextView textAddFav, textRemoveFav;
    EditText editQuantity;
    ProgressDialog progressDialog;

    SharedPreferences preferences;

    String name, description, size, concentration, perfumeStory, price, image, productID;
    private String network_error;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        progressDialog = new ProgressDialog(getContext());

        PerfumeDetail perfumeDetail = new PerfumeDetail();
        perfumeDetail.set_id(PerfumeFragmentAdapter.selected_item_id);
        perfumeDetail.setParent(PerfumeFragmentAdapter.parent_id);

        new ProductDisplay().execute(perfumeDetail);

        recyclerViewSimilarProduct = (RecyclerView) view.findViewById(R.id.recycler_view_similar_product);
        textName = (TextView) view.findViewById(R.id.text_product_name);
        textDescription = (TextView) view.findViewById(R.id.text_product_description);
        textSize = (TextView) view.findViewById(R.id.text_size);
        textConcentration = (TextView) view.findViewById(R.id.text_concentration);
        textPerfumeStory = (TextView) view.findViewById(R.id.text_perfume_story_full);
        textRemoveFav = (TextView) view.findViewById(R.id.remove_fav);
        textAddFav = (TextView) view.findViewById(R.id.add_fav);
        textPrice = (TextView) view.findViewById(R.id.text_product_price);
        imageProduct = (ImageView) view.findViewById(R.id.image_product);
        relativeCartLayout = (RelativeLayout) view.findViewById(R.id.relative_cart);
        relativeAddFav = (RelativeLayout) view.findViewById(R.id.relative_add_fav);
        relativeShare = (RelativeLayout) view.findViewById(R.id.relative_share);
        editQuantity = (EditText) view.findViewById(R.id.edit_quantity);


        relativeAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textAddFav.getVisibility() == View.GONE) {
                    textAddFav.setVisibility(View.VISIBLE);
                    textRemoveFav.setVisibility(View.GONE);
                    preferences = getContext().getSharedPreferences("login data", Context.MODE_PRIVATE);
                    String customerId = preferences.getString("_id", "null");

                    FavouriteRequest request = new FavouriteRequest();
                    request.setId_customer(customerId);
                    request.setProduct_image(image);
                    request.setProduct_name(name);
                    request.setProduct_price(price);
                    request.setProduct_id(productID);

                    new AddToFavourite().execute(request);


                } else {
                    textAddFav.setVisibility(View.GONE);
                    textRemoveFav.setVisibility(View.VISIBLE);

                    preferences = getContext().getSharedPreferences("login data", Context.MODE_PRIVATE);
                    String customerId = preferences.getString("_id", "null");

                    FavouriteRequest request = new FavouriteRequest();
                    request.setId_customer(customerId);
                    request.setProduct_id(productID);
                    new RemoveFromFavourite().execute(request);
                }

            }
        });


        relativeCartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = getContext().getSharedPreferences("login data", Context.MODE_PRIVATE);
                String customerId = preferences.getString("_id", "null");
                if (customerId.equals("null")) {
                    LoginFragment loginFragment = new LoginFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, loginFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (editQuantity.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Enter the Quantity", Toast.LENGTH_SHORT).show();
                } else {

                    String quantity = editQuantity.getText().toString().trim();
                    int intQuant = Integer.parseInt(quantity);
                    int intPrice = Integer.parseInt(price);
                    int totalPrice = intQuant * intPrice;

                    String totPrice = String.valueOf(totalPrice);

                    CartRequest request = new CartRequest();
                    request.setId_customer(preferences.getString("_id", "null"));
                    request.setProduct_image(image);
                    request.setProduct_name(name);
                    request.setQuantity(quantity);
                    request.setProduct_price(totPrice);
                    request.setProduct_id(productID);

                    new AddToCart().execute(request);
                }

            }
        });


        return view;
    }

    public class ProductDisplay extends AsyncTask<PerfumeDetail, Void, ProductDetailsResponse> {

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
        protected ProductDetailsResponse doInBackground(PerfumeDetail... params) {
            ProductDetailsResponse response = null;
            try {
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                response = retrofitRest.getProductDetails(params[0]);
            } catch (Exception e) {
                Log.d("Network Error", "doInBackground: " + e);
            }

            return response;
        }

        @Override
        protected void onPostExecute(ProductDetailsResponse productDetailsResponse) {

            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();

                if (info != null && info.isConnected()) {
                    if (productDetailsResponse.getStatus().equals("success")) {

                        name = productDetailsResponse.getPerfume_details().get(0).getName();
                        description = productDetailsResponse.getPerfume_details().get(0).getDescription();
                        size = productDetailsResponse.getPerfume_details().get(0).getSize();
                        concentration = productDetailsResponse.getPerfume_details().get(0).getConcentration();
                        perfumeStory = productDetailsResponse.getPerfume_details().get(0).getPerfume_story();
                        price = productDetailsResponse.getPerfume_details().get(0).getPrice();
                        image = productDetailsResponse.getPerfume_details().get(0).getImage();
                        productID = productDetailsResponse.getPerfume_details().get(0).get_id();


                        Glide.with(getActivity())
                                .load("http://54.201.67.32/lodore/connection/" + image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageProduct);

                        //textName, textDescription, textSize, textConcentration, textPerfumeStory, textPrice;


                        textName.setText(name);
                        textDescription.setText(description);
                        textSize.setText(size);
                        textConcentration.setText(concentration);
                        textPerfumeStory.setText(perfumeStory);
                        textPrice.setText(price + " ريا");

                        PerfumeFragmentAdapter adapter = new PerfumeFragmentAdapter(getContext(), productDetailsResponse.getResult(), getActivity());
                        recyclerViewSimilarProduct.setAdapter(adapter);
                        recyclerViewSimilarProduct.setNestedScrollingEnabled(false);
                        recyclerViewSimilarProduct.setHasFixedSize(true);
                        recyclerViewSimilarProduct.setLayoutManager(new GridLayoutManager(getContext(), 2));

                        SharedPreferences pref = getContext().getSharedPreferences("login data", Context.MODE_PRIVATE);
                        String id_customer = pref.getString("_id", "null");

                        FavouriteRequest favouriteRequest = new FavouriteRequest();
                        favouriteRequest.setId_customer(id_customer);
                        favouriteRequest.setProduct_id(productID);

                        new CheckFavorite().execute(favouriteRequest);

                        hideDialoge();

                    }
                } else {
                    HomeFragment homeFragment = new HomeFragment();
                    homeFragment.alertDialog();
                }

            } catch (Exception e) {
                System.out.println("" + e);
            }

        }
    }

    public class AddToCart extends AsyncTask<CartRequest, Void, CartResponse> {

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
        protected CartResponse doInBackground(CartRequest... params) {
            CartResponse cartResponse = null;

            try {
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                cartResponse = retrofitRest.getCartResponse(params[0]);
            } catch (Exception e) {
                network_error = String.valueOf(e);
            }

            return cartResponse;
        }

        @Override
        protected void onPostExecute(CartResponse cartResponse) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();

            if (info != null && info.isConnected() && network_error == null) {
                Toast.makeText(getContext(), "" + cartResponse.getMessage(), Toast.LENGTH_SHORT).show();
                hideDialoge();
            } else {
                alertDialog();
            }

        }
    }

    public class AddToFavourite extends AsyncTask<FavouriteRequest, Void, CartResponse> {

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
        protected CartResponse doInBackground(FavouriteRequest... params) {
            CartResponse response = null;
            try {
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                response = retrofitRest.getFavoriteInsert(params[0]);

            } catch (Exception e) {
                network_error = String.valueOf(e);
            }
            return response;
        }

        @Override
        protected void onPostExecute(CartResponse cartResponse) {
            hideDialoge();
        }
    }

    public class RemoveFromFavourite extends AsyncTask<FavouriteRequest, Void, CartResponse> {

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
        protected CartResponse doInBackground(FavouriteRequest... params) {
            CartResponse response = null;
            try {
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                response = retrofitRest.removeFavorite(params[0]);

            } catch (Exception e) {
                network_error = String.valueOf(e);
            }
            return response;
        }

        @Override
        protected void onPostExecute(CartResponse cartResponse) {
            hideDialoge();
        }
    }

    public class CheckFavorite extends AsyncTask<FavouriteRequest, Void, CartResponse>
    {
        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {
            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("http://54.201.67.32/lodore/connection/api/customer")
                    .build();
        }

        @Override
        protected CartResponse doInBackground(FavouriteRequest... params) {
            CartResponse cartResponse = null;

            try {
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                cartResponse = retrofitRest.checkFavorite(params[0]);
            }catch (Exception e)
            {
                e.printStackTrace();
            }

            return cartResponse;
        }

        @Override
        protected void onPostExecute(CartResponse cartResponse) {
            String message = cartResponse.getMessage();
            Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
            if (cartResponse.getMessage().equals("Product Already Added In favorite"))
            {
                textAddFav.setVisibility(View.VISIBLE);
                textRemoveFav.setVisibility(View.GONE);
            }
            else
            {
                textAddFav.setVisibility(View.GONE);
                textRemoveFav.setVisibility(View.VISIBLE);
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

    public void alertDialog() {

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
                        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                        alertDialog.dismiss();
                    }
                });
            }
        });

        alertDialog.show();
    }


}
