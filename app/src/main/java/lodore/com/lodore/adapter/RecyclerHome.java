package lodore.com.lodore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import lodore.com.lodore.Pojo.Perfume;
import lodore.com.lodore.R;

/**
 * Created by win7 on 24-Mar-17.
 */

public class RecyclerHome extends RecyclerView.Adapter<RecyclerHome.MyViewHolder> {

    private Context mContext;
    private List<Perfume> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);

            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }


    public RecyclerHome(Context mContext, List<Perfume> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public RecyclerHome.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_home_cart, parent, false);

        return new RecyclerHome.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerHome.MyViewHolder holder, int position) {
        Perfume album = albumList.get(position);

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

    }


    @Override
    public int getItemCount() {
        return albumList.size();
    }
}

