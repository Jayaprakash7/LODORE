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

import lodore.com.lodore.Pojo.Blogdisplay;
import lodore.com.lodore.R;

/**
 * Created by w7 on 21-Mar-17.
 */

public class RecyclerviewblogAdapter extends RecyclerView.Adapter<RecyclerviewblogAdapter.MyViewHolder> {

    private Context mContext;
    private List<Blogdisplay> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,discription;
        public ImageView thumbnail;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.blog_title);
            discription = (TextView) view.findViewById(R.id.discp);
            thumbnail = (ImageView) view.findViewById(R.id.blog_thumbnail);

        }
    }

    public RecyclerviewblogAdapter(Context mContext, List<Blogdisplay> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }


    @Override
    public RecyclerviewblogAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.blog_card, parent, false);

        return new RecyclerviewblogAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerviewblogAdapter.MyViewHolder holder, int position) {

        Blogdisplay album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.discription.setText(album.getDiscription());


        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
