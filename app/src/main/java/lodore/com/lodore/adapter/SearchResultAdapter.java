package lodore.com.lodore.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Collections;
import java.util.List;

import lodore.com.lodore.Pojo.Product;
import lodore.com.lodore.R;


public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.MyViewHolder> {

    Context context;
    private List<Product> productList = Collections.emptyList();
    private FragmentActivity fragmentActivity;

    public SearchResultAdapter(Context context, List<Product> productList, FragmentActivity fragmentActivity) {
        this.context = context;
        this.productList = productList;
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_search_result,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.textTitleResult.setText(product.getName());
        holder.textDescResult.setText(product.getDescription());
        holder.textPriceResult.setText(product.getPrice());

        Glide.with(context)
                .load("http://54.201.67.32/lodore/connection/" + productList.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(holder.imageItem);


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textTitleResult,textDescResult,textPriceResult;
        ImageView imageItem, imageDetails;

        public MyViewHolder(View itemView) {
            super(itemView);

            textTitleResult = (TextView) itemView.findViewById(R.id.row_title_result);
            textDescResult = (TextView) itemView.findViewById(R.id.row_desc_result);
            textPriceResult = (TextView) itemView.findViewById(R.id.row_price_result);
            imageItem = (ImageView) itemView.findViewById(R.id.row_image_result);
            imageDetails = (ImageView) itemView.findViewById(R.id.image_details_result);

        }
    }
}
