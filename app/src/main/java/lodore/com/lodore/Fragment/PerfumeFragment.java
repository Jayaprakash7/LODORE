package lodore.com.lodore.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import lodore.com.lodore.Pojo.PerfumeFilterDTO;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.PerfumeFilterAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfumeFragment extends Fragment {


    PerfumeFilterAdapter adapter;

    public PerfumeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfume, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.perfume_filter_recycler);
        Button btnFilter = (Button) view.findViewById(R.id.btn_filter);
        Button btnCheck = (Button) view.findViewById(R.id.btn_perfume_filter);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setTitle("Perfume Filter");
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linear_check_filter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayout.getVisibility() == View.GONE)
                    linearLayout.setVisibility(View.VISIBLE);
                else if (linearLayout.getVisibility() == View.VISIBLE)
                    linearLayout.setVisibility(View.GONE);
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.GONE);
            }
        });

        adapter = new PerfumeFilterAdapter(getActivity(), getData());
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return view;
    }

    public static List<PerfumeFilterDTO> getData() {
        List<PerfumeFilterDTO> perfumeFilterDTOList = new ArrayList<>();

        int[] image = {R.drawable.perfume2, R.drawable.perfume4, R.drawable.perfume2, R.drawable.perfume4, R.drawable.perfume2, R.drawable.perfume4, R.drawable.perfume2, R.drawable.perfume4, R.drawable.perfume2, R.drawable.perfume4, R.drawable.perfume2, R.drawable.perfume4};


        for (int i = 0; i < image.length; i++) {
            PerfumeFilterDTO perfumeFilterDTO = new PerfumeFilterDTO();
            perfumeFilterDTO.image = image[i];

            perfumeFilterDTOList.add(perfumeFilterDTO);
        }
        return perfumeFilterDTOList;
    }

}
