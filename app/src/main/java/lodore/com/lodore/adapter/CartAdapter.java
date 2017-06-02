package lodore.com.lodore.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Collections;
import java.util.List;

import lodore.com.lodore.Fragment.BranddetailsFragment;
import lodore.com.lodore.Fragment.CartFragment;
import lodore.com.lodore.Pojo.CartDTO;
import lodore.com.lodore.Pojo.CartRequest;
import lodore.com.lodore.Pojo.CartResponse;
import lodore.com.lodore.Pojo.CartResult;
import lodore.com.lodore.R;
import lodore.com.lodore.service.Retrofit_rest;
import retrofit.RestAdapter;



public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    Context context;
    private List<CartResult> cartResultList;
    private FragmentActivity fragmentActivity;
    public static int c;


    public CartAdapter(Context context, List<CartResult> cartResultList, FragmentActivity fragmentActivity) {
        this.context = context;
        this.cartResultList = cartResultList;
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cart_main, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final CartResult cartResult = cartResultList.get(position);

        holder.textTitleCart.setText(cartResult.getProduct_name());
        holder.textPriceCart.setText(cartResult.getProduct_price() + " ريا");
        holder.textQuansCart.setText(cartResult.getQuantity());
        holder.deleteCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartRequest cartRequest = new CartRequest();
                cartRequest.set_id(cartResult.get_id());

                new CartDelete().execute(cartRequest);
            }
        });

        holder.textQuansCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater mInflater = (LayoutInflater) fragmentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final ViewGroup viewGroup = (ViewGroup) mInflater.inflate(R.layout.custom_dialog, null);
                final EditText editQuans = (EditText) viewGroup.findViewById(R.id.edit_alert);
                final TextView textError = (TextView) viewGroup.findViewById(R.id.text_error);

                AlertDialog.Builder builder = new AlertDialog.Builder(fragmentActivity);
                builder.setTitle("Enter The Quantity");
                builder.setView(viewGroup);
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Save", null);

                final AlertDialog alertDialog = builder.create();
                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button btnPos = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        btnPos.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String quans = editQuans.getText().toString().trim();
                                if (quans.length() > 2) {
                                    textError.setVisibility(View.VISIBLE);
                                } else if (quans.isEmpty()) {
                                    textError.setVisibility(View.GONE);
                                    Toast.makeText(fragmentActivity, "Field Should not empty", Toast.LENGTH_SHORT).show();
                                } else {

                                    int quans_int = Integer.parseInt(quans);
                                    int price = Integer.parseInt(cartResult.getProduct_price());
                                    int b_quantity = Integer.parseInt(cartResult.getQuantity());

                                    int original_price = price / b_quantity;
                                    int total_price = original_price * quans_int;
                                    String totalPrice = String.valueOf(total_price);

                                    CartRequest cartRequest = new CartRequest();
                                    cartRequest.set_id(cartResult.get_id());
                                    cartRequest.setQuantity(quans);
                                    cartRequest.setProduct_price(totalPrice);

                                    System.out.println(cartResult.get_id());
                                    System.out.println(quans);
                                    System.out.println(totalPrice);

                                    new CartUpdate().execute(cartRequest);

                                    Toast.makeText(fragmentActivity, "Success", Toast.LENGTH_SHORT).show();
                                    alertDialog.dismiss();

                                }
                            }
                        });

                        Button btnNeg = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                        btnNeg.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });
                    }
                });
                alertDialog.show();

            }
        });

        Glide.with(context)
                .load("http://54.201.67.32/lodore/connection/" + cartResultList.get(position).getProduct_image())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageItem);

    }

    @Override
    public int getItemCount() {
        return cartResultList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textTitleCart, textPriceCart;
        TextView textQuansCart;
        ImageView imageItem;
        ImageView deleteCart;

        public MyViewHolder(View itemView) {
            super(itemView);

            textTitleCart = (TextView) itemView.findViewById(R.id.row_text_title_cart);
            textQuansCart = (TextView) itemView.findViewById(R.id.row_text_quan_cart);
            textPriceCart = (TextView) itemView.findViewById(R.id.row_text_price_cart);
            imageItem = (ImageView) itemView.findViewById(R.id.row_image_cart);
            deleteCart = (ImageView) itemView.findViewById(R.id.delete_cart);

        }
    }

    public class CartUpdate extends AsyncTask<CartRequest, Void, CartResponse>{

        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://54.201.67.32/lodore/connection/api/customer")
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
        }

        @Override
        protected CartResponse doInBackground(CartRequest... params) {
            CartResponse cartResponse = null;

            try{
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                cartResponse = retrofitRest.updateCart(params[0]);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return cartResponse;
        }

        @Override
        protected void onPostExecute(CartResponse cartResponse) {
            CartFragment cartFragment = new CartFragment();
            FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, cartFragment);
            fragmentTransaction.commit();

        }
    }

    private class CartDelete extends AsyncTask<CartRequest, Void, CartResponse>{

        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://54.201.67.32/lodore/connection/api/customer")
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
        }

        @Override
        protected CartResponse doInBackground(CartRequest... params) {
            CartResponse cartResponse = null;

            try{
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                cartResponse = retrofitRest.deleteCart(params[0]);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return cartResponse;
        }

        @Override
        protected void onPostExecute(CartResponse cartResponse) {
            Toast.makeText(fragmentActivity, ""+cartResponse.getMessage(), Toast.LENGTH_SHORT).show();
            CartFragment cartFragment = new CartFragment();
            FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, cartFragment);
            fragmentTransaction.commit();

        }
    }

}
