package lodore.com.lodore.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import lodore.com.lodore.CartActivity;
import lodore.com.lodore.CheckOutActivity;
import lodore.com.lodore.Pojo.CartDTO;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.CartAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {

    EditText editTextMain;
    Button btnHome;
    RecyclerView recyclerView;
    CartAdapter adapter;
    Toolbar toolbar;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        editTextMain = (EditText) view.findViewById(R.id.edit_main);
        recyclerView = (RecyclerView) view.findViewById(R.id.cart_recycler_view);
        btnHome = (Button) view.findViewById(R.id.btn_home);
        adapter = new CartAdapter(getActivity(), getData());

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CheckOutActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        return view;
    }

    public static List<CartDTO> getData() {
        List<CartDTO> cartDTOList = new ArrayList<>();
        String[] title = {"الآسيوية", "الآسيوية"};
        String[] price = {"السع", "السعر"};
        int[] image = {R.drawable.perfume2, R.drawable.perfume4};

        for (int i = 0; i < title.length; i++) {
            CartDTO cartDTO = new CartDTO();
            cartDTO.titleCart = title[i];
            cartDTO.priceCart = price[i];
            cartDTO.imageCart = image[i];

            cartDTOList.add(cartDTO);
        }
        return cartDTOList;
    }

}
