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

import lodore.com.lodore.Pojo.CheckOut4DTO;
import lodore.com.lodore.R;

/**
 * Created by w7 on 21-Mar-17.
 */

public class Checkout4RecyclerAdapter extends RecyclerView.Adapter<Checkout4RecyclerAdapter.MyViewHolder> {

    LayoutInflater inflater;
    List<CheckOut4DTO> checkOut4DTOList = Collections.emptyList();

    public Checkout4RecyclerAdapter(Context context, List<CheckOut4DTO> checkOut4DTOList) {
        this.inflater = LayoutInflater.from(context);
        this.checkOut4DTOList = checkOut4DTOList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_checkout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        CheckOut4DTO checkOut4DTO = checkOut4DTOList.get(position);
        holder.imagePerume.setImageResource(checkOut4DTO.imagePerfume);
        holder.textTitle.setText(checkOut4DTO.title);
        holder.textQuantity.setText(checkOut4DTO.quantity);
        holder.textPrice.setText(checkOut4DTO.price);

    }

    @Override
    public int getItemCount() {
        return checkOut4DTOList.size();
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
