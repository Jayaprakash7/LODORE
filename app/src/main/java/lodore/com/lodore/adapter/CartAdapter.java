package lodore.com.lodore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import lodore.com.lodore.Pojo.CartDTO;
import lodore.com.lodore.R;

/**
 * Created by w7 on 21-Mar-17.
 */

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    LayoutInflater inflater;
    List<CartDTO> cartDTOList = Collections.emptyList();

    public CartAdapter(Context context, List<CartDTO> cartDTOList) {
        inflater = LayoutInflater.from(context);
        this.cartDTOList = cartDTOList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cart_main,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CartDTO cartDTO = cartDTOList.get(position);
        holder.imageItem.setImageResource(cartDTO.imageCart);
        holder.textTitleCart.setText(cartDTO.titleCart);
        holder.textPriceCart.setText(cartDTO.priceCart);
    }

    @Override
    public int getItemCount() {
        return cartDTOList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textTitleCart,textQuansCart,textPriceCart;
        ImageView imageItem,imageDelete;

        public MyViewHolder(View itemView) {
            super(itemView);

            textTitleCart = (TextView) itemView.findViewById(R.id.row_text_title_cart);
            textQuansCart = (TextView) itemView.findViewById(R.id.row_text_quan_cart);
            textPriceCart = (TextView) itemView.findViewById(R.id.row_text_price_cart);
            imageItem = (ImageView) itemView.findViewById(R.id.row_image_cart);
            imageDelete = (ImageView) itemView.findViewById(R.id.image_delete_cart);

        }
    }
}
