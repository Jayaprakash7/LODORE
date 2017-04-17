package lodore.com.lodore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import lodore.com.lodore.Pojo.HomeFragrancePlantResult;
import lodore.com.lodore.R;

/**
 * Created by win7 on 11-Apr-17.
 */

public class HomeFragrantPlantsAdapter extends RecyclerView.Adapter<HomeFragrantPlantsAdapter.MyViewHolder> {

    Context context;
    List<HomeFragrancePlantResult> fragrancePlantResultsList;

    public HomeFragrantPlantsAdapter(Context context, List<HomeFragrancePlantResult> fragrancePlantResultsList) {
        this.context = context;
        this.fragrancePlantResultsList = fragrancePlantResultsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_home_cart,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Glide.with(context)
                .load("http://54.201.67.32/lodore/connection/" + fragrancePlantResultsList.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageFragrancePlant);
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
