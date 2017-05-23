package lodore.com.lodore.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import lodore.com.lodore.Pojo.PerfumeDTO;
import lodore.com.lodore.Pojo.PerfumeFilterRequest;
import lodore.com.lodore.Pojo.PerfumeResponse;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.PerfumeAdapter;
import lodore.com.lodore.adapter.PerfumeFragmentAdapter;
import lodore.com.lodore.service.Retrofit_rest;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class QuizFragment4 extends Fragment {


    RecyclerView recyclerViewResult;
    LinearLayout linearLayout;

    public QuizFragment4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_fragment4, container, false);

        linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout_quiz4);
        recyclerViewResult = (RecyclerView) view.findViewById(R.id.recycler_view_quiz_result);


        System.out.println("cool value of the filters");
        System.out.println("cool" + QuizFragment1.cool);
        System.out.println("warm" + QuizFragment1.warm);
        System.out.println("morning" + QuizFragment2.morning);
        System.out.println("daily" + QuizFragment2.daily);
        System.out.println("night" + QuizFragment2.night);
        System.out.println("wood" + QuizFragment3.wood);
        System.out.println("powder" + QuizFragment3.powder);
        System.out.println("citrus" + QuizFragment3.citrus);
        System.out.println("leather" + QuizFragment3.leather);
        System.out.println("amber" + QuizFragment3.amber);
        System.out.println("flowers" + QuizFragment3.flowers);
        System.out.println("fragrant_plants" + QuizFragment3.fragrant_plants);
        System.out.println("musk" + QuizFragment3.musk);
        System.out.println("oud" + QuizFragment3.oud);
        System.out.println("spices" + QuizFragment3.spices);
        System.out.println("sweet" + QuizFragment3.sweet);


        PerfumeFilterRequest perfumeFilterRequest = new PerfumeFilterRequest();
        perfumeFilterRequest.setCool(QuizFragment1.cool);
        perfumeFilterRequest.setWarm(QuizFragment1.warm);
        perfumeFilterRequest.setMorning(QuizFragment2.morning);
        perfumeFilterRequest.setDaily(QuizFragment2.daily);
        perfumeFilterRequest.setNight(QuizFragment2.night);
        perfumeFilterRequest.setWood(QuizFragment3.wood);
        perfumeFilterRequest.setPowder(QuizFragment3.powder);
        perfumeFilterRequest.setFruit(QuizFragment3.citrus);
        perfumeFilterRequest.setLeather(QuizFragment3.leather);
        perfumeFilterRequest.setAmber(QuizFragment3.amber);
        perfumeFilterRequest.setFlower(QuizFragment3.flowers);
        perfumeFilterRequest.setFragrant_plant(QuizFragment3.fragrant_plants);
        perfumeFilterRequest.setMusk(QuizFragment3.musk);
        perfumeFilterRequest.setOud(QuizFragment3.oud);
        perfumeFilterRequest.setSpices(QuizFragment3.spices);
        perfumeFilterRequest.setSweet(QuizFragment3.sweet);

        new QuizFilter().execute(perfumeFilterRequest);
        return view;
    }

    public class QuizFilter extends AsyncTask<PerfumeFilterRequest, Void, PerfumeResponse> {

        RestAdapter restAdapter;
        @Override
        protected void onPreExecute() {
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setConnectTimeout(5, TimeUnit.SECONDS);
            okHttpClient.setReadTimeout(5,TimeUnit.SECONDS);

            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setClient(new OkClient(okHttpClient))
                    .setEndpoint("http://54.201.67.32/lodore/connection/api/customer")
                    .build();
        }

        @Override
        protected PerfumeResponse doInBackground(PerfumeFilterRequest... params) {
            PerfumeResponse perfumeResponse = null;

            try {
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                perfumeResponse = retrofitRest.getQuizFilter(params[0]);

            }
            catch (Exception e){
                System.out.print(""+e);
            }
            return perfumeResponse;
        }

        @Override
        protected void onPostExecute(PerfumeResponse perfumeResponse) {
            try {

                PerfumeFragmentAdapter adapter = new PerfumeFragmentAdapter(getContext(), perfumeResponse.getProduct(), getActivity());

                recyclerViewResult.setAdapter(adapter);
                recyclerViewResult.setHasFixedSize(true);
                recyclerViewResult.setNestedScrollingEnabled(false);
                recyclerViewResult.setLayoutManager(new GridLayoutManager(getActivity(), 2));

            } catch (Exception e) {
                System.out.print("" + e);
            }
        }
    }


}
