package lodore.com.lodore.Fragment;


import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lodore.com.lodore.Pojo.BlogResponse;
import lodore.com.lodore.Pojo.Blogdisplay;
import lodore.com.lodore.R;
import lodore.com.lodore.adapter.BlogAdapter;
import lodore.com.lodore.adapter.RecyclerviewblogAdapter;
import lodore.com.lodore.adapter.RecyclerviewbrandsAdapter;
import lodore.com.lodore.service.Retrofit_rest;
import retrofit.RestAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogFragment extends Fragment {

    private RecyclerView recyclerViewblog;
    private ProgressDialog progressDialog;


    public BlogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog, container, false);

        progressDialog = new ProgressDialog(getActivity());
        recyclerViewblog = (RecyclerView) view.findViewById(R.id.recycler_blog);
        new BlogItemDisplay().execute();

        return view;
    }

    public class BlogItemDisplay extends AsyncTask<Void,Void,BlogResponse>{

        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {
            showDialog();
            restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("http://54.201.67.32/lodore/connection/api")
                    .build();
        }

        @Override
        protected BlogResponse doInBackground(Void... params) {

            BlogResponse response = null;

            try{
                Retrofit_rest retrofitRest = restAdapter.create(Retrofit_rest.class);
                response = retrofitRest.getBlogList();

            }catch (Exception e){
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(BlogResponse blogResponse) {

            try {

                hideDialoge();
                BlogAdapter adapter = new BlogAdapter(getContext(), blogResponse.getBlogs());

                recyclerViewblog.setAdapter(adapter);
                recyclerViewblog.setHasFixedSize(true);
                recyclerViewblog.setNestedScrollingEnabled(false);
                recyclerViewblog.setLayoutManager(new LinearLayoutManager(getActivity()));

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public void showDialog(){

        progressDialog.setMessage("please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }
    public void hideDialoge(){
        progressDialog.dismiss();
    }


}
