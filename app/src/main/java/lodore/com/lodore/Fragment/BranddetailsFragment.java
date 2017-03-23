package lodore.com.lodore.Fragment;


import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lodore.com.lodore.Pojo.Perfume;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.RecyclerviewhomeAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class BranddetailsFragment extends Fragment {

    private RecyclerView recyclerViewbottom,recyclerViewtop;
    private RecyclerviewhomeAdapter adapter;
    private List<Perfume> albumList;


    public BranddetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_branddetails, container, false);

        recyclerViewbottom = (RecyclerView) view.findViewById(R.id.recycler_viewbottom);
        recyclerViewtop = (RecyclerView) view.findViewById(R.id.recycler_viewtop);

        albumList = new ArrayList<>();
        adapter = new RecyclerviewhomeAdapter(getContext(), albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewbottom.setLayoutManager(mLayoutManager);
        recyclerViewbottom.addItemDecoration(new BranddetailsFragment.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerViewbottom.setItemAnimator(new DefaultItemAnimator());
        recyclerViewbottom.setAdapter(adapter);


        prepareAlbums();

        try {
            //Glide.with(this).load(R.drawable.sample).into((ImageView) view.findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;
    }

    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.sample,
                R.drawable.sample,
                R.drawable.sample,
                R.drawable.sample,
                R.drawable.sample,
                R.drawable.sample,
                R.drawable.sample,
                R.drawable.sample,
                R.drawable.sample,
                R.drawable.sample,
                R.drawable.sample};

        Perfume a = new Perfume("perfume", 13, covers[0]);
        albumList.add(a);

        a = new Perfume("perfume", 8, covers[1]);
        albumList.add(a);

        a = new Perfume("perfume ", 11, covers[2]);
        albumList.add(a);

        a = new Perfume("perfume", 12, covers[3]);
        albumList.add(a);

        a = new Perfume("perfume", 14, covers[4]);
        albumList.add(a);

        a = new Perfume("perfume", 1, covers[5]);
        albumList.add(a);

        a = new Perfume("perfume", 11, covers[6]);
        albumList.add(a);

        a = new Perfume("perfume", 14, covers[7]);
        albumList.add(a);

        a = new Perfume("perfume", 11, covers[8]);
        albumList.add(a);

        a = new Perfume("perfume", 17, covers[9]);
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
