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

import java.util.List;

import lodore.com.lodore.Pojo.BlogResult;
import lodore.com.lodore.R;


public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.MyViewHolder>{

    Context context;
    private List<BlogResult> blogResultList;

    public BlogAdapter(Context context, List<BlogResult> blogResultList) {
        this.context = context;
        this.blogResultList = blogResultList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_card,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        BlogResult blogResult = blogResultList.get(position);
        holder.textName.setText(blogResult.getName());
        holder.textDescription.setText(blogResult.getDescription());
        Glide.with(context)
                .load("http://54.201.67.32/lodore/connection/" + blogResultList.get(position).getImage())
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
