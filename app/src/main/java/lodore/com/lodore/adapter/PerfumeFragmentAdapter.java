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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Collections;
import java.util.List;

import lodore.com.lodore.Fragment.BranddetailsFragment;
import lodore.com.lodore.Fragment.ProductDetailsFragment;
import lodore.com.lodore.Pojo.PerfumeResponse;
import lodore.com.lodore.Pojo.Product;
import lodore.com.lodore.R;


public class PerfumeFragmentAdapter extends RecyclerView.Adapter<PerfumeFragmentAdapter.MyViewHolder> {

    Context context;
    private FragmentActivity fragmentActivity;
    private List<Product> productList = Collections.emptyList();
    public static  String selected_item_id,parent_id;

    public PerfumeFragmentAdapter(Context context, List<Product> productList,FragmentActivity fragmentActivity) {
        this.context = context;
        this.productList = productList;
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quiz4,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Product product = productList.get(position);
        holder.textPerfume.setText(product.getName());

        Glide.with(context)
                .load("http://54.201.67.32/lodore/connection/" + productList.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(holder.imagePerfume);

        holder.btnPerfume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_item_id = product.get_id();

                parent_id = product.getParent();
                System.out.println(""+selected_item_id);
                System.out.println(""+parent_id);

                ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
                FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.content_frame, productDetailsFragment);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePerfume;
        TextView textPerfume;
        Button btnPerfume;
        public MyViewHolder(View itemView) {
            super(itemView);

            imagePerfume = (ImageView) itemView.findViewById(R.id.image_card_perfume);
            textPerfume = (TextView) itemView.findViewById(R.id.text_card_perfume);
            btnPerfume = (Button) itemView.findViewById(R.id.btn_card_perfume);
        }
    }
}
