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


    private void quizFragment() {
        QuizFragment3 quizFragment = new QuizFragment3();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.checkout_container_layout, quizFragment);
        transaction.commit();
    }

    public QuizFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_fragment2, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        Button btnQuiz2 = (Button) view.findViewById(R.id.btn_quiz2);

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


        btnQuiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quizFragment();

            }
        });
        return view;
    }

}
