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
public class QuizFragment2 extends Fragment {

    public static String daily = "";
    public static String morning = "";
    public static String night = "";

    private void quizFragment() {
        QuizFragment3 quizFragment = new QuizFragment3();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.content_frame, quizFragment);
        transaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_fragment2, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        Button btndaily = (Button) view.findViewById(R.id.btn_daily);
        Button btnMorning = (Button) view.findViewById(R.id.btn_morning);
        Button btnNight = (Button) view.findViewById(R.id.btn_night);


        btndaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daily = "daily";
                morning = "";
                night = "";
                quizFragment();

            }
        });
        btnMorning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daily = "";
                morning = "morning";
                night = "";
                quizFragment();

            }
        });
        btnNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daily = "";
                morning = "";
                night = "night";
                quizFragment();

            }
        });
        return view;
    }

}
