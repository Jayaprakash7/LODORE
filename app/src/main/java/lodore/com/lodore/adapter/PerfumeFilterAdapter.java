package lodore.com.lodore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import lodore.com.lodore.Pojo.PerfumeFilterDTO;
import lodore.com.lodore.R;

/**
 * Created by w7 on 21-Mar-17.
 */

public class PerfumeFilterAdapter extends RecyclerView.Adapter<PerfumeFilterAdapter.MyViewHolder>{

    LayoutInflater inflater;
    List<PerfumeFilterDTO> perfumeFilterDTOList = Collections.emptyList();

    public PerfumeFilterAdapter(Context context, List<PerfumeFilterDTO> perfumeFilterDTOList) {
        inflater = LayoutInflater.from(context);
        this.perfumeFilterDTOList = perfumeFilterDTOList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_perfume_filter,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PerfumeFilterDTO perfumeFilterDTO = perfumeFilterDTOList.get(position);
        holder.imageViewPerfume.setImageResource(perfumeFilterDTO.image);
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
