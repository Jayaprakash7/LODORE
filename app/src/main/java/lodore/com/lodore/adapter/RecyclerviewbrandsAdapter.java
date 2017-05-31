package lodore.com.lodore.adapter;

import android.content.Context;
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

import lodore.com.lodore.Fragment.BranddetailsFragment;
import lodore.com.lodore.Pojo.BrandResult;
import lodore.com.lodore.R;

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


    public RecyclerviewbrandsAdapter(Context context, List<BrandResult> brandList, FragmentActivity myContext) {
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

        Glide.with(context)
                .load("http://54.201.67.32/lodore/connection/" + brandList.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selected_product_id = brand.getId();

                BranddetailsFragment fragment2 = new BranddetailsFragment();
                FragmentManager fragmentManager = myContext.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
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

