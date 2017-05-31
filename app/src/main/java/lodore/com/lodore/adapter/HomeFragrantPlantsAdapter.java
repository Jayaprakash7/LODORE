package lodore.com.lodore.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import lodore.com.lodore.Pojo.BrandResult;
import lodore.com.lodore.Pojo.Brandresp;
import lodore.com.lodore.R;


public class HomeFragrantPlantsAdapter extends RecyclerView.Adapter<HomeFragrantPlantsAdapter.MyViewHolder> {

    Context context;
    private List<BrandResult> fragrancePlantResultsList;

    private FragmentActivity fragmentActivity;
    public static String brand_id = "1";


    public HomeFragrantPlantsAdapter(Context context, List<BrandResult> fragrancePlantResultsList, FragmentActivity fragmentActivity) {
        this.context = context;
        this.fragrancePlantResultsList = fragrancePlantResultsList;
        this.fragmentActivity = fragmentActivity;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_home_cart, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final BrandResult brandResult = fragrancePlantResultsList.get(position);

        Glide.with(context)
                .load("http://54.201.67.32/lodore/connection/" + fragrancePlantResultsList.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageFragrancePlant);

        holder.imageFragrancePlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brand_id = brandResult.getId();
                Log.d("From Adapter", "onClick: "+brand_id);


                /*HomeFragment homeFragment = new HomeFragment();
                FragmentManager manager = fragmentActivity.getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.content_frame,homeFragment);
                transaction.commit();*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return fragrancePlantResultsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageFragrancePlant;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageFragrancePlant = (ImageView) itemView.findViewById(R.id.image_home_fragrance);
        }



    }




}
