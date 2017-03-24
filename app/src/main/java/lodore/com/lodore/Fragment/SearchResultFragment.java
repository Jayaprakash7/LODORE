package lodore.com.lodore.Fragment;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import lodore.com.lodore.Pojo.SearchResultDto;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.SearchResultAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends Fragment {


    RecyclerView recyclerView;
    SearchResultAdapter adapter;
    LinearLayout linearLayoutPerfumeHouse;
    LinearLayout linearLayoutPerfumes;


    public SearchResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_search);
        linearLayoutPerfumes = (LinearLayout) view.findViewById(R.id.linear_perfumes);
        linearLayoutPerfumeHouse = (LinearLayout) view.findViewById(R.id.linear_perfume_house);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setTitle("Seaech Result");
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        linearLayoutPerfumes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerfumeFragment perfumeFragment = new PerfumeFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.content_frame, perfumeFragment);
                transaction.commit();

            }
        });

        linearLayoutPerfumeHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrandsFragment brandsFragment = new BrandsFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.content_frame, brandsFragment);
                transaction.commit();

            }
        });


        adapter = new SearchResultAdapter(getActivity(), getData());
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    public static List<SearchResultDto> getData() {
        List<SearchResultDto> searchResultDtoList = new ArrayList<>();

        int[] image = {R.drawable.perfume2, R.drawable.perfume4, R.drawable.perfume2, R.drawable.perfume4, R.drawable.perfume2, R.drawable.perfume4, R.drawable.perfume2, R.drawable.perfume4, R.drawable.perfume2, R.drawable.perfume4, R.drawable.perfume2, R.drawable.perfume4};


        for (int i = 0; i < image.length; i++) {
            SearchResultDto searchResultDto = new SearchResultDto();
            searchResultDto.image = image[i];

            searchResultDtoList.add(searchResultDto);
        }
        return searchResultDtoList;
    }

    public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable divider;

        public SimpleDividerItemDecoration(Context context) {
            divider = context.getResources().getDrawable(R.drawable.line_divider);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + divider.getIntrinsicHeight();

                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }
    }

}
