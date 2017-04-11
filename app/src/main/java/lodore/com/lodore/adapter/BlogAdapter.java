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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.util.List;

import lodore.com.lodore.Pojo.BlogResult;
import lodore.com.lodore.R;

/**
 * Created by win7 on 10-Apr-17.
 */

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.MyViewHolder>{

    Context context;
    private List<BlogResult> blogResultList;

    public BlogAdapter(Context context, List<BlogResult> blogResultList) {
        this.context = context;
        this.blogResultList = blogResultList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_blog_details,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        BlogResult blogResult = blogResultList.get(position);
        holder.textName.setText(blogResult.getName());
        holder.textDescription.setText(blogResult.getDescription());
        //Picasso.with(context).load("http://192.168.123.10/lodore/" + blogResultList.get(position).getImage()).fit().into(holder.imageBlog);
        Glide.with(context)
                .load("http://192.168.123.10/lodore/" + blogResultList.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageBlog);
    }

    @Override
    public int getItemCount() {
        return blogResultList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageBlog;
        TextView textName,textDescription;
        Button btn_viewMore;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageBlog = (ImageView) itemView.findViewById(R.id.blog_thumbnail);
            textName = (TextView) itemView.findViewById(R.id.blog_title);
            textDescription = (TextView) itemView.findViewById(R.id.blog_description);
            btn_viewMore = (Button) itemView.findViewById(R.id.btn_viewmore);
        }
    }
}
