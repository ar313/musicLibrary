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

    final int REQUEST_CODE_ADD = 13;
    final int REQUEST_CODE_EDIT = 14;



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

        final MusicCollection songs = new MusicCollection(db);

        Adapter songAdapter = new Adapter(songs);


        //db.addMusicToDB(new Music(2,"Despacito","Lois Fancy", "Spanish", "Country", 300, "Alexa Play"));

        recycler.setAdapter(songAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        Button addSong = findViewById(R.id.addbutton);

        addSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent (EntryActivity.this, SongDetailActivity.class);

                Music song = new Music();


                myIntent.putExtra("edit", true);
                myIntent.putExtra("collection", songs);
                myIntent.putExtra("song", song);

                startActivityForResult(myIntent, REQUEST_CODE_ADD);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        File f = new File(Environment.getExternalStorageDirectory(), "/MusicLibrary/db");
        if (!f.exists()) {
            f.mkdirs();
        }

        Database db = new Database(EntryActivity.this);

        final MusicCollection songs = new MusicCollection(db);



        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK) {
            Music song = (Music)intent.getSerializableExtra("song");

            db.addMusicToDB(song);

            songs.reload(db);

            Adapter songAdapter = new Adapter(songs);

            songAdapter.notifyItemInserted(songs.getMusicArrayList().size() - 1);

            RecyclerView rv=findViewById(R.id.recycler);

            rv.setAdapter(songAdapter);
            rv.setLayoutManager(new LinearLayoutManager(this));

            rv.scrollToPosition(songs.getMusicArrayList().size()-1);
        }

        if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK) {
            Music song = (Music)intent.getSerializableExtra("song");

            db.editMusic(song);

            songs.reload(db);

            Adapter songAdapter = new Adapter(songs);

            songAdapter.notifyItemChanged(songs.geteditMusic(song));

            RecyclerView rv=findViewById(R.id.recycler);

            rv.setAdapter(songAdapter);
            rv.setLayoutManager(new LinearLayoutManager(this));

            rv.scrollToPosition(songs.getMusicArrayList().size()-1);
        }
    }
}
