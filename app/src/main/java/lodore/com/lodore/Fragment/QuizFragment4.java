package lodore.com.lodore.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import lodore.com.lodore.Pojo.PerfumeDTO;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.PerfumeAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment4 extends Fragment {


    RecyclerView recyclerView;
    PerfumeAdapter perfumeAdapter;
    LinearLayout linearLayout;

    public QuizFragment4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_fragment4, container, false);

        linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout_quiz4);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_quiz4);
        perfumeAdapter = new PerfumeAdapter(getActivity(), getData());
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setTitle("Quiz");
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SearchResultFragment resultFragment = new SearchResultFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.checkout_container_layout, resultFragment);
                transaction.commit();

            }
        });

        recyclerView.setAdapter(perfumeAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return view;
    }

    public static List<PerfumeDTO> getData() {
        List<PerfumeDTO> perfumeDTOList = new ArrayList<>();
        String[] text = {" تسجيل الدخول", " تسجيل الدخول", " تسجيل الدخول", " تسجيل الدخول"};
        int[] image = {R.drawable.perfume2, R.drawable.perfume4, R.drawable.perfume2, R.drawable.perfume4};

        for (int i = 0; i < text.length; i++) {
            PerfumeDTO perfumeDTO = new PerfumeDTO();
            perfumeDTO.image = image[i];
            perfumeDTO.text = text[i];
            perfumeDTOList.add(perfumeDTO);
        }
        return perfumeDTOList;
    }

}
