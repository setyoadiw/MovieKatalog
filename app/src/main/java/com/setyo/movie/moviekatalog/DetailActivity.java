package com.setyo.movie.moviekatalog;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.setyo.movie.moviekatalog.Model.MovieModel.ResultsItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    List<ResultsItem> listMovie = new ArrayList<>();
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initView();
    }

    private void initView() {
        TextView tvSinopsis = (TextView)findViewById(R.id.tvSinopsis);
        ImageView imgFilm = (ImageView)findViewById(R.id.imgGambarMovie);

        listMovie = getIntent().getParcelableArrayListExtra("DATA_MOVIE");
        position = getIntent().getIntExtra("POSISI",0);

        getSupportActionBar().setTitle(listMovie.get(position).getTitle());
        tvSinopsis.setText(listMovie.get(position).getOverview());
        Picasso.with(this).load("https://image.tmdb.org/t/p/w500"+listMovie.get(position).getPosterPath()).into(imgFilm);


    }
}
