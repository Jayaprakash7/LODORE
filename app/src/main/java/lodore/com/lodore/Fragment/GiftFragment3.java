package lodore.com.lodore.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import lodore.com.lodore.MainActivity;
import lodore.com.lodore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GiftFragment3 extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gift_fragment3, container, false);
        Button back_home = (Button) view.findViewById(R.id.btn_back_home);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);



        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}
