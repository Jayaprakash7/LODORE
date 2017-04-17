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

import java.util.Collections;
import java.util.List;

import lodore.com.lodore.Fragment.BranddetailsFragment;
import lodore.com.lodore.Fragment.CartFragment;
import lodore.com.lodore.Pojo.PerfumeDTO;
import lodore.com.lodore.Pojo.PerfumeFilterDTO;
import lodore.com.lodore.R;

/**
 * Created by w7 on 21-Mar-17.
 */

public class PerfumeAdapter extends RecyclerView.Adapter<PerfumeAdapter.MyViewHolder>{

    LayoutInflater inflater;
    private FragmentActivity myContext;

    private List<PerfumeDTO> perfumeDTOList = Collections.emptyList();

    private class VIEW_TYPES{
        public static final int HEADER = 1;
        public static final int NORMAL = 2;
    }

    public PerfumeAdapter(Context context, List<PerfumeDTO> perfumeDTOList) {
        this.inflater = LayoutInflater.from(context);
        this.perfumeDTOList = perfumeDTOList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View rowView;
        switch (viewType){
            case VIEW_TYPES.HEADER:
                rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_quiz4_header,parent,false);
                break;
            case VIEW_TYPES.NORMAL:
                rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quiz4,parent,false);
                break;
            default:
                rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quiz4,parent,false);
                break;
        }
        return new MyViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        PerfumeDTO perfumeDTO = perfumeDTOList.get(position);
        //holder.textViewPerfume.setText(perfumeDTO.text);
        holder.imageViewPerfume.setImageResource(perfumeDTO.image);

        holder.buttonPerfume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("prdeeep printing id ************ ");

                CartFragment cartFragment = new CartFragment();
                FragmentManager fragmentManager = myContext.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, cartFragment);
                fragmentTransaction.commit();


            }
        });

    }

    @Override
    public int getItemViewType(int position) {
       /* if (perfumeDTOList.get(position).isHeader)
            return VIEW_TYPES.HEADER;
        else*/
            return VIEW_TYPES.NORMAL;
    }

    @Override
    public int getItemCount() {
        return perfumeDTOList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textViewPerfume;
        ImageView imageViewPerfume;
        Button buttonPerfume;

        public MyViewHolder(View itemView) {
            super(itemView);

            textViewPerfume = (TextView) itemView.findViewById(R.id.text_card_perfume);
            imageViewPerfume = (ImageView) itemView.findViewById(R.id.image_card_perfume);
            buttonPerfume = (Button) itemView.findViewById(R.id.btn_card_perfume);

        }
    }
}

