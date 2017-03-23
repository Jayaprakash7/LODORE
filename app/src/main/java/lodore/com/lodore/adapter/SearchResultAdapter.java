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

import lodore.com.lodore.Pojo.SearchResultDto;
import lodore.com.lodore.R;

/**
 * Created by w7 on 21-Mar-17.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.MyViewHolder> {

    LayoutInflater inflater;
    List<SearchResultDto> searchResultDtoList = Collections.emptyList();

    public SearchResultAdapter(Context context, List<SearchResultDto> searchResultDtoList) {
        inflater = LayoutInflater.from(context);
        this.searchResultDtoList = searchResultDtoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_search_result,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        SearchResultDto searchResultDto = searchResultDtoList.get(position);
        holder.imageItem.setImageResource(searchResultDto.image);


    }

    @Override
    public int getItemCount() {
        return searchResultDtoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textTitleResult,textDescResult,textPriceResult;
        ImageView imageItem,imageDelete;

        public MyViewHolder(View itemView) {
            super(itemView);

            textTitleResult = (TextView) itemView.findViewById(R.id.row_title_result);
            textDescResult = (TextView) itemView.findViewById(R.id.row_desc_result);
            textPriceResult = (TextView) itemView.findViewById(R.id.row_price_result);
            imageItem = (ImageView) itemView.findViewById(R.id.row_image_result);
            imageDelete = (ImageView) itemView.findViewById(R.id.image_delete_result);

        }
    }
}
