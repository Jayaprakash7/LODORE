package lodore.com.lodore.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
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

import lodore.com.lodore.Pojo.PerfumeList;
import lodore.com.lodore.R;


public class ProductDetailsSimilarBrandsAdapter extends RecyclerView.Adapter<ProductDetailsSimilarBrandsAdapter.MyViewHolder> {

    Context context;
    private List<PerfumeList> perfumeLists;
    private FragmentActivity fragmentActivity;

    public ProductDetailsSimilarBrandsAdapter(Context context, List<PerfumeList> perfumeLists, FragmentActivity fragmentActivity) {
        this.context = context;
        this.perfumeLists = perfumeLists;
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quiz4,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PerfumeList perfumeList = perfumeLists.get(position);
        holder.textPerfumeName.setText(perfumeList.getName());
        Glide.with(context)
                .load("http://54.201.67.32/lodore/connection/" + perfumeLists.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(holder.perfumeImage);

    }

    @Override
    public int getItemCount() {
        return perfumeLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView perfumeImage;
        TextView textPerfumeName;
        Button btnPerfume;

        public MyViewHolder(View itemView) {
            super(itemView);

            perfumeImage = (ImageView) itemView.findViewById(R.id.image_card_perfume);
            textPerfumeName = (TextView) itemView.findViewById(R.id.text_card_perfume);
            btnPerfume = (Button) itemView.findViewById(R.id.btn_card_perfume);
        }
    }
}
