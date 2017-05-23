package lodore.com.lodore.Fragment;


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

import lodore.com.lodore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment3 extends Fragment {

    public static String spices = "";
    public static String oud = "";
    public static String fragrant_plants = "";
    public static String amber = "";
    public static String musk = "";
    public static String flowers = "";
    public static String sweet = "";
    public static String leather = "";
    public static String citrus = "";
    public static String powder = "";
    public static String wood = "";


    private void quizFragment() {
        QuizFragment4 quizFragment = new QuizFragment4();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.content_frame, quizFragment);
        transaction.commit();
    }

    public QuizFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_fragment3, container, false);

        Button btn_spices = (Button) view.findViewById(R.id.btn_spices);
        Button btn_oud = (Button) view.findViewById(R.id.btn_oud);
        Button btn_fragrant_plant = (Button) view.findViewById(R.id.btn_fragrant_plant);
        Button btn_amber = (Button) view.findViewById(R.id.btn_amber);
        Button btn_musk = (Button) view.findViewById(R.id.btn_musk);
        Button btn_flowers = (Button) view.findViewById(R.id.btn_flowers);
        Button btn_sweet = (Button) view.findViewById(R.id.btn_sweet);
        Button btn_leather = (Button) view.findViewById(R.id.btn_leather);
        Button btn_citrus = (Button) view.findViewById(R.id.btn_citrus);
        Button btn_powder = (Button) view.findViewById(R.id.btn_powder);
        Button btn_wood = (Button) view.findViewById(R.id.btn_wood);
        Button btn_result = (Button) view.findViewById(R.id.btn_show_result);

        final Button btn_check_spices = (Button) view.findViewById(R.id.btn_check_spices);
        final Button btn_check_oud = (Button) view.findViewById(R.id.btn_check_oud);
        final Button btn_check_fragrant_plant = (Button) view.findViewById(R.id.btn_check_fragrant_plant);
        final Button btn_check_amber = (Button) view.findViewById(R.id.btn_check_amber);
        final Button btn_check_musk = (Button) view.findViewById(R.id.btn_check_musk);
        final Button btn_check_flowers = (Button) view.findViewById(R.id.btn_check_flowers);
        final Button btn_check_sweet = (Button) view.findViewById(R.id.btn_check_sweet);
        final Button btn_check_leather = (Button) view.findViewById(R.id.btn_check_leather);
        final Button btn_check_citrus = (Button) view.findViewById(R.id.btn_check_citrus);
        final Button btn_check_powder = (Button) view.findViewById(R.id.btn_check_powder);
        final Button btn_check_wood = (Button) view.findViewById(R.id.btn_check_wood);


        btn_spices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spices = "spices";
                btn_check_spices.setVisibility(View.VISIBLE);


            }
        });
        btn_oud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oud = "oud";
                btn_check_oud.setVisibility(View.VISIBLE);


            }
        });
        btn_fragrant_plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragrant_plants = "fragrant plant";
                btn_check_fragrant_plant.setVisibility(View.VISIBLE);


            }
        });
        btn_amber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amber = "amber";
                btn_check_amber.setVisibility(View.VISIBLE);


            }
        });
        btn_musk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                musk = "musk";
                btn_check_musk.setVisibility(View.VISIBLE);


            }
        });
        btn_flowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                flowers = "flower";
                btn_check_flowers.setVisibility(View.VISIBLE);


            }
        });
        btn_sweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sweet = "sweet";
                btn_check_sweet.setVisibility(View.VISIBLE);


            }
        });
        btn_leather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                leather = "leather";
                btn_check_leather.setVisibility(View.VISIBLE);


            }
        });
        btn_citrus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                citrus = "fruit";
                btn_check_citrus.setVisibility(View.VISIBLE);


            }
        });
        btn_powder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                powder = "powder";
                btn_check_powder.setVisibility(View.VISIBLE);


            }
        });
        btn_wood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wood = "wood";
                btn_check_wood.setVisibility(View.VISIBLE);


            }
        });
        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quizFragment();

            }
        });


        return view;
    }
}
