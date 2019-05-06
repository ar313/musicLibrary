package com.example.dtr313.musiclibrary;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import java.io.File;
import java.io.Serializable;

public class EntryActivity extends AppCompatActivity {

    final int REQUEST_CODE_ADD = 13;
    final int REQUEST_CODE_EDIT = 14;

    MusicCollection songs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        final RecyclerView recycler = findViewById(R.id.recycler);

        File f = new File(Environment.getExternalStorageDirectory(), "/MusicLibrary/db");
        if (!f.exists()) {
            f.mkdirs();
        }

        Database db = new Database(EntryActivity.this);

        if ( db.load().size() <= 0) {
             db.addMusicToDB(new Music(1,"Despacito","Lois Fancy", "Spanish", "Country", 300, "Alexa Play"));
        }

        songs = new MusicCollection(db);

        final Adapter songAdapter = new Adapter(songs);

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

        final int[] search = {1}; // 1 - name, 2 - artist, 3 - genre, 4 - album

        final Button advSearch = findViewById(R.id.advsearch);

        advSearch.setText(getString(R.string.advSearch) + "Song Name");

        advSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String choices[] = {"Song name", "Artist", "Genre", "Album"};

                AlertDialog.Builder builder = new AlertDialog.Builder(EntryActivity.this);
                builder.setTitle("Search by: ");
                builder.setItems(choices, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        search[0] = which + 1;

                        switch(search[0])
                        {
                            case 1:
                                advSearch.setText(getString(R.string.advSearch) + " Song Name");
                                break;
                            case 2:
                                advSearch.setText(getString(R.string.advSearch) + " Artist");
                                break;
                            case 3:
                                advSearch.setText(getString(R.string.advSearch) + " Genre");
                                break;
                            case 4:
                                advSearch.setText(getString(R.string.advSearch) + " Album");
                                break;
                            default:
                                advSearch.setText(getString(R.string.advSearch) + " Song Name");
                        }

                    }
                });

                builder.show();
            }
        });

        SearchView sv = findViewById(R.id.searchView);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                MusicCollection found;

                switch(search[0])
                {
                    case 1:
                        found = new MusicCollection(songs.searchBySong(s));
                        break;
                    case 2:
                        found = new MusicCollection(songs.searchByArtist(s));
                        break;
                    case 3:
                        found = new MusicCollection(songs.searchByGenre(s));
                        break;
                    case 4:
                        found = new MusicCollection(songs.searchByAlbum(s));
                        break;
                    default:
                        found = new MusicCollection(songs.searchBySong(s));
                }

                Adapter foundAdapter = new Adapter(found);

                recycler.setAdapter(foundAdapter);
                recycler.setLayoutManager(new LinearLayoutManager(EntryActivity.this));

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                recycler.setAdapter(songAdapter);
                recycler.setLayoutManager(new LinearLayoutManager(EntryActivity.this));

                return false;
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

        songs.reload(db);



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

        if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_FIRST_USER) {

            if ( db.load().size() <= 0) {
                db.addMusicToDB(new Music(1,"Despacito","Lois Fancy", "Spanish", "Country", 300, "Alexa Play"));
            }

            songs.reload(db);

            Adapter songAdapter = new Adapter(songs);


            RecyclerView rv=findViewById(R.id.recycler);

            rv.setAdapter(songAdapter);
            rv.setLayoutManager(new LinearLayoutManager(this));

            rv.scrollToPosition(songs.getMusicArrayList().size()-1);
        }
    }
}
