package com.example.dtr313.musiclibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        RecyclerView recycler = findViewById(R.id.recycler);

        //Database db = new Database();

        final MusicCollection songs = new MusicCollection();

        Adapter songAdapter = new Adapter(songs);

        recycler.setAdapter(songAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        Button addSong = findViewById(R.id.addbutton);

        addSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent (EntryActivity.this, SongDetailActivity.class);


                myIntent.putExtra("edit", true);
                myIntent.putExtra("collection", songs);
                myIntent.putExtra("song", new Music());

                startActivity(myIntent);
            }
        });
    }
}
