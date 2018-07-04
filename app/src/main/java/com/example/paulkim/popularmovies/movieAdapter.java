package com.example.paulkim.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class movieAdapter extends RecyclerView.Adapter<movieAdapter.Viewholder> {

  private   List<Movie_Items> listItems ;
  private Context context;
  private OnItemClickListener mListener;

  public interface OnItemClickListener {
      void onItemClick(int position);

  }

  public void setOnItemClickListener(OnItemClickListener listener) {
      mListener = listener;
  }

    public movieAdapter(Context context, List<Movie_Items> listItems) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.movie_thumbnail, parent, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder( Viewholder holder, int position) {
         Movie_Items movie_items = listItems.get(position);
         String imageURL = movie_items.getMovieThumbnailPoster();
        Picasso.with(context)
                .load( "http://image.tmdb.org/t/p/w185/"+ imageURL).fit().centerInside()
                .into(holder.movie_picture);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public ImageView movie_picture;
        public Viewholder(View itemView) {
            super(itemView);
            movie_picture = (ImageView) itemView.findViewById(R.id.single_movie);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
