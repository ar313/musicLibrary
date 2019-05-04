package com.example.dtr313.musiclibrary;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.Serializable;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        RecyclerView recycler = findViewById(R.id.recycler);

        File f = new File(Environment.getExternalStorageDirectory(), "/MusicLibrary/db");
        if (!f.exists()) {
            f.mkdirs();
        }

        Database db = new Database(EntryActivity.this);
        //db.addMusicToDB(new Music(1,"Despacito","Lois Fancy", "Spanish", "Country", 300, "Alexa Play"));
        final MusicCollection songs = new MusicCollection(db);

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
