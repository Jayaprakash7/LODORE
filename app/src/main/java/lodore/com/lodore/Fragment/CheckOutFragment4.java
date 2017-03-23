package lodore.com.lodore.Fragment;


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
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import lodore.com.lodore.Pojo.CheckOut4DTO;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.Checkout4RecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckOutFragment4 extends Fragment {


    RecyclerView recyclerView;
    Checkout4RecyclerAdapter adapter;

    public CheckOutFragment4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_out_fragment4, container, false);
        Button button = (Button) view.findViewById(R.id.checkout_button4);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_checkout4);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setTitle("CheckOut");
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        adapter = new Checkout4RecyclerAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckOutFragment5 checkOutFragment5 = new CheckOutFragment5();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.checkout_container_layout, checkOutFragment5);
                transaction.commit();

            }
        });

        return view;
    }

    public static List<CheckOut4DTO> getData() {
        List<CheckOut4DTO> checkOut4DTOList = new ArrayList<>();
        String[] title = {"الآسيوية", "الآسيوية"};
        String[] quans = {"1", "1"};
        String[] price = {"السع", "السعر"};
        int[] image = {R.drawable.perfume2, R.drawable.perfume4};

        for (int i = 0; i < title.length; i++) {
            CheckOut4DTO checkOut4DTO = new CheckOut4DTO();
            checkOut4DTO.title = title[i];
            checkOut4DTO.price = price[i];
            checkOut4DTO.quantity = quans[i];
            checkOut4DTO.imagePerfume = image[i];

            checkOut4DTOList.add(checkOut4DTO);
        }
        return checkOut4DTOList;

    }


}
