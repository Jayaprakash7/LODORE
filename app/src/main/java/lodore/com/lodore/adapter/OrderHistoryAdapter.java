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
import lodore.com.lodore.Pojo.CartResult;
import lodore.com.lodore.R;

/**
 * Created by win7 on 01-Jun-17.
 */

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder> {

    Context context;
    private List<CartResult> cartResultList;

    public OrderHistoryAdapter(Context context, List<CartResult> cartResultList) {
        this.context = context;
        this.cartResultList = cartResultList;
    }

    @Override
    public OrderHistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order_history,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderHistoryAdapter.MyViewHolder holder, int position) {
        CartResult cartResult = cartResultList.get(position);
        holder.textName.setText(cartResult.getProduct_name());
        holder.textPrice.setText(cartResult.getPrice()+ " ريا");
        holder.textStatus.setText(cartResult.getStatus());
        Glide.with(context)
                .load("http://54.201.67.32/lodore/connection/" + cartResult.getProduct_image())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imagePerfume);
    }

    @Override
    public int getItemCount() {
        return cartResultList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePerfume;
        TextView textName,textPrice,textStatus;

        public MyViewHolder(View itemView) {
            super(itemView);


            imagePerfume = (ImageView) itemView.findViewById(R.id.perfume_image);
            textName = (TextView) itemView.findViewById(R.id.perfume_title);
            textPrice = (TextView) itemView.findViewById(R.id.perfume_price);
            textStatus = (TextView) itemView.findViewById(R.id.perfume_delivery_status);

        }
    }
}
