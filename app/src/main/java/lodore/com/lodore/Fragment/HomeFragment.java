package lodore.com.lodore.Fragment;


import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import lodore.com.lodore.Pojo.Perfume;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.RecyclerHome;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Button reg;
    Toolbar toolbarHome;

    private RecyclerView recyclerViewbottom;
    private RecyclerHome adapter;
    private List<Perfume> albumList;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        reg = (Button) view.findViewById(R.id.btn_heading_home);


        recyclerViewbottom = (RecyclerView) view.findViewById(R.id.recycler_viewbottom);

        albumList = new ArrayList<>();
        adapter = new RecyclerHome(getContext(), albumList);
        recyclerViewbottom.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true));
        recyclerViewbottom.setNestedScrollingEnabled(false);
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
                R.drawable.flower,
                R.drawable.flower,
                R.drawable.flower,R.drawable.flower,R.drawable.flower,
                R.drawable.flower,

                };

        Perfume a = new Perfume("True Romance", 13, covers[0]);
        albumList.add(a);

        a = new Perfume("Xscpae", 8, covers[1]);
        albumList.add(a);

        a = new Perfume("Maroon 5", 11, covers[2]);
        albumList.add(a);

        a = new Perfume("Born to Die", 12, covers[3]);
        albumList.add(a);

        a = new Perfume("Honeymoon", 14, covers[4]);
        albumList.add(a);

        a = new Perfume("I Need a Doctor", 1, covers[5]);
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
