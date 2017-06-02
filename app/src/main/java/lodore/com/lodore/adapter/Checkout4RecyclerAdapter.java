package lodore.com.lodore.adapter;

import android.content.Context;
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

import lodore.com.lodore.Pojo.CartResponse;
import lodore.com.lodore.Pojo.CartResult;
import lodore.com.lodore.Pojo.CheckOut4DTO;
import lodore.com.lodore.R;



public class Checkout4RecyclerAdapter extends RecyclerView.Adapter<Checkout4RecyclerAdapter.MyViewHolder> {

    Context context;
    private List<CartResult> cartResultList;

    public Checkout4RecyclerAdapter(Context context, List<CartResult> cartResultList) {
        this.context = context;
        this.cartResultList = cartResultList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_checkout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CartResult cartResult = cartResultList.get(position);

        holder.textTitle.setText(cartResult.getProduct_name());
        holder.textPrice.setText(cartResult.getProduct_price() + " ريا");
        holder.textQuantity.setText(cartResult.getQuantity());

        Glide.with(context)
                .load("http://54.201.67.32/lodore/connection/" + cartResultList.get(position).getProduct_image())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imagePerume);


    }

    @Override
    public int getItemCount() {
        return cartResultList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle,textQuantity,textPrice;
        ImageView imagePerume;

        public MyViewHolder(View itemView) {
            super(itemView);

            textTitle = (TextView) itemView.findViewById(R.id.row_text_title);
            textQuantity = (TextView) itemView.findViewById(R.id.row_text_quan);
            textPrice = (TextView) itemView.findViewById(R.id.row_text_price);
            imagePerume = (ImageView) itemView.findViewById(R.id.image_recycler_row);

        }
    }
}
