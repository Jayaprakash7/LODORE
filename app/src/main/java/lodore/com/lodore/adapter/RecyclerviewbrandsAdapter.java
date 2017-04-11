package lodore.com.lodore.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.transform.Result;

import lodore.com.lodore.Fragment.BranddetailsFragment;
import lodore.com.lodore.Fragment.CartFragment;
import lodore.com.lodore.Fragment.GiftFragment1;
import lodore.com.lodore.MainActivity;
import lodore.com.lodore.Pojo.BrandDetailsResponse;
import lodore.com.lodore.Pojo.BrandInfo;
import lodore.com.lodore.Pojo.BrandResult;
import lodore.com.lodore.Pojo.Brandresp;
import lodore.com.lodore.R;
import lodore.com.lodore.service.Retrofit_rest;
import retrofit.RestAdapter;

public class RecyclerviewbrandsAdapter extends RecyclerView.Adapter<RecyclerviewbrandsAdapter.MyViewHolder> {

    private List<BrandResult> brandList;
    Context context;
    String id;
    private FragmentActivity myContext;
    public static String selected_product_id = null;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }


    public RecyclerviewbrandsAdapter(Context context,List<BrandResult> brandList,FragmentActivity myContext) {
        this.brandList = brandList;
        this.context = context;
        this.myContext = myContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.brands_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final BrandResult brand = brandList.get(position);
        holder.title.setText(brand.getName());
        Picasso.with(context).load("http://192.168.123.10/lodore/"+brandList.get(position).getImage()).fit().into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selected_product_id = brand.getId();
               System.out.println("prdeeep printing id ************ "+id);

                BranddetailsFragment fragment2 = new BranddetailsFragment();
                FragmentManager fragmentManager = myContext.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment2);
                fragmentTransaction.commit();


            }
        });
    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }

}

