package lodore.com.lodore.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import lodore.com.lodore.Fragment.FavoriteFragment;
import lodore.com.lodore.Fragment.ProductDetailsFragment;
import lodore.com.lodore.Pojo.CartRequest;
import lodore.com.lodore.Pojo.CartResponse;
import lodore.com.lodore.Pojo.CartResult;
import lodore.com.lodore.Pojo.FavouriteRequest;
import lodore.com.lodore.R;
import lodore.com.lodore.service.Retrofit_rest;
import retrofit.RestAdapter;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {

    Context context;
    private List<CartResult> cartResultList;
    private FragmentActivity fragmentActivity;

    public FavoriteAdapter(Context context, List<CartResult> cartResultList, FragmentActivity fragmentActivity) {
        this.context = context;
        this.cartResultList = cartResultList;
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public FavoriteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_favorite_list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteAdapter.MyViewHolder holder, int position) {
        final CartResult cartResult = cartResultList.get(position);

        holder.textViewTitle.setText(cartResult.getProduct_name());
        holder.textViewPrice.setText(cartResult.getProduct_price()+ " ريا");

        Glide.with(context)
                .load("http://54.201.67.32/lodore/connection/"+cartResult.getProduct_image())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageViewProduct);

        holder.imageViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = fragmentActivity.getSharedPreferences("login data", Context.MODE_PRIVATE);
                String customerId = preferences.getString("_id", "null");
                String product_id = cartResult.getProduct_id();
                FavouriteRequest request = new FavouriteRequest();
                request.setId_customer(customerId);
                request.setProduct_id(product_id);
                new RemoveFromFavourite().execute(request);
            }
        });


    }

    @Override
    public int getItemCount() {
        return cartResultList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle,textViewPrice;
        ImageView imageViewProduct,imageViewCart;

        public MyViewHolder(View itemView) {
            super(itemView);

            textViewTitle = (TextView) itemView.findViewById(R.id.row_text_title_favorite);
            textViewPrice = (TextView) itemView.findViewById(R.id.row_text_price_favorite);
            imageViewProduct = (ImageView) itemView.findViewById(R.id.row_image_favorite);
            imageViewCart = (ImageView) itemView.findViewById(R.id.delete_favorite);

        }
    }
    private class RemoveFromFavourite extends AsyncTask<FavouriteRequest, Void, CartResponse> {

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
            CartResponse response = null;
            try {
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                response = retrofitRest.removeFavorite(params[0]);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(CartResponse cartResponse) {
            FavoriteFragment favoriteFragment = new FavoriteFragment();
            FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, favoriteFragment);
            fragmentTransaction.commit();
        }
    }

}
