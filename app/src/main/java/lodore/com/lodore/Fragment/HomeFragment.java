package lodore.com.lodore.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import lodore.com.lodore.Pojo.HomeFragrancePlantResponse;
import lodore.com.lodore.Pojo.Perfume;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.HomeFragrantPlantsAdapter;
import lodore.com.lodore.adapter.RecyclerHome;
import lodore.com.lodore.service.Retrofit_rest;
import retrofit.RestAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Button reg;
    Toolbar toolbarHome;

    private RecyclerView recyclerViewFragrancePlants;
    private HomeFragrantPlantsAdapter adapter;
    private List<HomeFragrancePlantResponse> homeFragrancePlantResponses;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        reg = (Button) view.findViewById(R.id.btn_heading_home);


        recyclerViewFragrancePlants = (RecyclerView) view.findViewById(R.id.recycler_view_fragrance_families);
        new FragrantPlantDisplay().execute();

        return view;
    }

    public class FragrantPlantDisplay extends AsyncTask<Void, Void, HomeFragrancePlantResponse>{

        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {
            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("http://192.168.123.10/lodore/api/")
                    .build();
        }

        @Override
        protected HomeFragrancePlantResponse doInBackground(Void... params) {
            HomeFragrancePlantResponse plantResponse = null;
            try {
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                plantResponse = retrofitRest.getPlantList();
            }catch (Exception e){
                e.printStackTrace();
            }

            return plantResponse;
        }

        @Override
        protected void onPostExecute(HomeFragrancePlantResponse homeFragrancePlantResponse) {

            try{
                adapter = new HomeFragrantPlantsAdapter(getActivity(),homeFragrancePlantResponse.getResult());

                recyclerViewFragrancePlants.setAdapter(adapter);
                recyclerViewFragrancePlants.setHasFixedSize(true);
                recyclerViewFragrancePlants.setNestedScrollingEnabled(false);
                recyclerViewFragrancePlants.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


}
