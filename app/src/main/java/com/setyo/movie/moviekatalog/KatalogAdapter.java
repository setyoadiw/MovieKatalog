package com.setyo.movie.moviekatalog;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.setyo.movie.moviekatalog.Model.MovieModel.ResultsItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class KatalogAdapter extends RecyclerView.Adapter<KatalogAdapter.MyHolder>{

    List<ResultsItem> mListMovie = new ArrayList<>();
    Context mContext;


    public KatalogAdapter(Context context, List<ResultsItem> listMovie) {
        mContext = context;
        mListMovie = listMovie;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.recycler_view_item, parent,false);

        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.txtJudul.setText(mListMovie.get(position).getTitle());
        holder.txtTanggal.setText(mListMovie.get(position).getReleaseDate());

        Picasso.with(mContext).load("https://image.tmdb.org/t/p/w500"+mListMovie
                .get(position).getPosterPath()).into(holder.imgMovie);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent pindah = new Intent(mContext,DetailActivity.class);

            //kirim data parcel
            pindah.putParcelableArrayListExtra("DATA_MOVIE", (ArrayList<? extends Parcelable>) mListMovie);

            pindah.putExtra("POSISI",position);

            mContext.startActivity(pindah);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListMovie.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView imgMovie;
        TextView txtJudul;
        TextView txtTanggal;

        public MyHolder(View itemView) {
            super(itemView);

            imgMovie = itemView.findViewById(R.id.imgMovie);
            txtJudul = itemView.findViewById(R.id.tvJudulMovie);
            txtTanggal = itemView.findViewById(R.id.tvTgl);
        }
    }
}
