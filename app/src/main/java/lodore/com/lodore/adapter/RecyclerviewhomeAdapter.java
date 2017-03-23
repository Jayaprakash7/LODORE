package lodore.com.lodore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import lodore.com.lodore.Pojo.Perfume;
import lodore.com.lodore.R;

/**
 * Created by w7 on 21-Mar-17.
 */

public class RecyclerviewhomeAdapter extends RecyclerView.Adapter<RecyclerviewhomeAdapter.MyViewHolder> {

    private Context mContext;
    private List<Perfume> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail;
        public Button addtocart;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.flavour);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            addtocart = (Button) view.findViewById(R.id.button2);
        }
    }


    public RecyclerviewhomeAdapter(Context mContext, List<Perfume> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Perfume album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getFlavour() + " songs");

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // showPopupMenu(holder.addtocart);
            }
        });
    }


    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
