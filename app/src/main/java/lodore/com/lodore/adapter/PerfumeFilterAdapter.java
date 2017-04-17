package lodore.com.lodore.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import lodore.com.lodore.Fragment.BranddetailsFragment;
import lodore.com.lodore.Fragment.CartFragment;
import lodore.com.lodore.Pojo.PerfumeFilterDTO;
import lodore.com.lodore.R;

/**
 * Created by w7 on 21-Mar-17.
 */

public class PerfumeFilterAdapter extends RecyclerView.Adapter<PerfumeFilterAdapter.MyViewHolder>{

    LayoutInflater inflater;
    List<PerfumeFilterDTO> perfumeFilterDTOList = Collections.emptyList();
    private FragmentActivity myContext;

    public PerfumeFilterAdapter(Context context, List<PerfumeFilterDTO> perfumeFilterDTOList,FragmentActivity myContext) {
        inflater = LayoutInflater.from(context);
        this.perfumeFilterDTOList = perfumeFilterDTOList;
        this.myContext = myContext;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_perfume_filter,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        PerfumeFilterDTO perfumeFilterDTO = perfumeFilterDTOList.get(position);
        holder.imageViewPerfume.setImageResource(perfumeFilterDTO.image);

        holder.buttonPerfume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CartFragment cartFragment = new CartFragment();
                FragmentManager fragmentManager = myContext.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, cartFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });
    }

    @Override
    public int getItemCount() {
        return perfumeFilterDTOList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewPerfume;
        ImageView imageViewPerfume;
        Button buttonPerfume;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewPerfume = (TextView) itemView.findViewById(R.id.text_card_perfume_filter);
            imageViewPerfume = (ImageView) itemView.findViewById(R.id.image_card_perfume_filter);
            buttonPerfume = (Button) itemView.findViewById(R.id.btn_card_perfume_filter);
        }
    }
}
