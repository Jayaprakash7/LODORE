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

import com.squareup.picasso.Picasso;

import java.util.List;

import lodore.com.lodore.Fragment.BranddetailsFragment;
import lodore.com.lodore.Pojo.BrandProducts;
import lodore.com.lodore.Pojo.BrandResult;
import lodore.com.lodore.R;

/**
 * Created by w7 on 11-Apr-17.
 */

public class RecyclerviewbeanddetailsAdapter extends RecyclerView.Adapter<RecyclerviewbeanddetailsAdapter.MyViewHolder> {

    private List<BrandProducts> brandList;
    Context context;
    String id;
    private FragmentActivity myContext;
    public static String selected_product_id = null;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView item_description;
        ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            item_description = (TextView) view.findViewById(R.id.item_description);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }

    public RecyclerviewbeanddetailsAdapter(Context context,List<BrandProducts> brandList,FragmentActivity myContext) {
        this.brandList = brandList;
        this.context = context;
        this.myContext = myContext;
    }

    @Override
    public RecyclerviewbeanddetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.branddetails_card, parent, false);

        return new RecyclerviewbeanddetailsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerviewbeanddetailsAdapter.MyViewHolder holder, int position) {
        final BrandProducts brand = brandList.get(position);
        holder.title.setText(brand.getName());
        holder.item_description.setText(brand.getDescription());
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
