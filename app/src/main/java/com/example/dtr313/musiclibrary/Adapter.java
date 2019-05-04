package com.example.dtr313.musiclibrary;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private MusicCollection mCol;
    private LayoutInflater mInflater;

    public Adapter (MusicCollection data)
    {
        mCol = data;
    }


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        mInflater = LayoutInflater.from(context);

        View view = mInflater.inflate(R.layout.adapter, viewGroup, false);

        ViewHolder songViewHolder = new ViewHolder(view, context);
        return songViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        //super.onBindViewHolder(holder, position, payloads);

        Music song = mCol.getMusicArrayList().get(position);

        TextView songName = holder.songName;
        TextView songAuthor = holder.songAuthor;
        TextView songGenre = holder.songGenre;

        songName.setText(song.getName());
        songAuthor.setText(song.getArtist());
        songGenre.setText(song.getGenre());

    }

    @Override
    public int getItemCount() {
        return mCol.getMusicArrayList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView songName;
        public TextView songAuthor;
        public TextView songGenre;

        private Context context;

        public ViewHolder(View itemView, Context context)
        {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(this);

            songName = itemView.findViewById(R.id.songName);
            songAuthor = itemView.findViewById(R.id.songAuthor);
            songGenre = itemView.findViewById(R.id.songGenre);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION)
            {
                Music song = mCol.getMusicArrayList().get(position);
                //Toast.makeText(context, songName.getText(),Toast.LENGTH_LONG).show();

                Intent myIntent = new Intent(v.getContext(), SongDetailActivity.class);

                myIntent.putExtra("song", song);
                myIntent.putExtra("edit", false);
                myIntent.putExtra("collection", mCol);
            }
        }
    }
}
