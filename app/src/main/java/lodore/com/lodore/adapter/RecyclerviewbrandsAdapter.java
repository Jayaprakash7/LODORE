package lodore.com.lodore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import lodore.com.lodore.Pojo.Brandsdispay;
import lodore.com.lodore.R;

/**
 * Created by w7 on 21-Mar-17.
 */

public class RecyclerviewbrandsAdapter extends RecyclerView.Adapter<RecyclerviewbrandsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Brandsdispay> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

        }
    }

    public RecyclerviewbrandsAdapter(Context mContext, List<Brandsdispay> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public RecyclerviewbrandsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.brands_card, parent, false);

        return new RecyclerviewbrandsAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final RecyclerviewbrandsAdapter.MyViewHolder holder, int position) {
        Brandsdispay album = albumList.get(position);
        holder.title.setText(album.getName());

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
