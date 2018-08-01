package com.setyo.movie.moviekatalog.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.setyo.movie.moviekatalog.KatalogAdapter;
import com.setyo.movie.moviekatalog.Model.MovieModel.ResponseModel;
import com.setyo.movie.moviekatalog.Model.MovieModel.ResultsItem;
import com.setyo.movie.moviekatalog.Network.RetrofitConfig;
import com.setyo.movie.moviekatalog.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<ResultsItem> listMovie = new ArrayList<>();


    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmenView = inflater.inflate(R.layout.fragment_pop, container, false);

        initView(fragmenView);
        return  fragmenView;
    }

    private void initView(View v) {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);


        ambilDataMovie();

        // specify an adapter (see also next example)
        mAdapter = new KatalogAdapter(getActivity(),listMovie);
        mRecyclerView.setAdapter(mAdapter);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    private void ambilDataMovie() {

        Call<ResponseModel> requestData = RetrofitConfig.getApiService().ambilDataTopMovie();
        requestData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful()){
                    listMovie = response.body().getResults();
                    mRecyclerView.setAdapter(new KatalogAdapter(getActivity(),listMovie));
                }else {
//                    Toast.makeText(getActivity(), "Response not succesfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(getActivity(),"Response failure",Toast.LENGTH_LONG).show();
            }
        });

    }

}
