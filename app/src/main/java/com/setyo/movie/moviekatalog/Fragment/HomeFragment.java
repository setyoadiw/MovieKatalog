package com.setyo.movie.moviekatalog.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
public class HomeFragment extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<ResultsItem> listMovie = new ArrayList<>();
    EditText edtCari;
    ImageView btCari;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View fragmentView =inflater.inflate(R.layout.fragment_home, container, false);;
        initView(fragmentView);

        return fragmentView;
    }


    private void initView(View v) {

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        edtCari = v.findViewById(R.id.edtCari);
        btCari = v.findViewById(R.id.btCari);

        ambilDataMovie();

        mAdapter = new KatalogAdapter(getActivity(),listMovie);
        mRecyclerView.setAdapter(mAdapter);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        btCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cariDataMovie(edtCari.getText().toString().trim());

                // specify an adapter (see also next example)
                mAdapter = new KatalogAdapter(getActivity(),listMovie);
                mRecyclerView.setAdapter(mAdapter);

                // use a linear layout manager
                mLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.setLayoutManager(mLayoutManager);
            }
        });

    }
    private void cariDataMovie(String queri) {

        Call<ResponseModel> requestData = RetrofitConfig.getApiService().cariDataMovie(queri);

        requestData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful()){
                    listMovie = response.body().getResults();
                    mRecyclerView.setAdapter(new KatalogAdapter(getActivity(),listMovie));
                }else {
                    Log.d("error message", response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(getActivity(),"Response failure",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ambilDataMovie(){

        Call<ResponseModel> requestData = RetrofitConfig.getApiService().ambilDataPopularMovie();

        requestData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful()){
                    listMovie = response.body().getResults();
                    mRecyclerView.setAdapter(new KatalogAdapter(getActivity(),listMovie));
                }else {
                    Log.d("error message", response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(getActivity(),"Response failure",Toast.LENGTH_SHORT).show();
            }
        });

    }


}
