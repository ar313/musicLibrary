package com.example.dtr313.musiclibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SongDetailActivity extends AppCompatActivity {

    EditText songName = findViewById(R.id.songdetailName);
    EditText songAuthor = findViewById(R.id.songdetailAuthor);
    //EditText songYear = findViewById(R.id.songdetailYear);
    EditText songDuration = findViewById(R.id.songdetailDuration);
    EditText songGenre = findViewById(R.id.songdetailGenre);
    EditText songAlbum = findViewById(R.id.songdetailAlbum);
    EditText songDescription = findViewById(R.id.songdetailDescription);

    Button buttonSave = findViewById(R.id.songdetailSave);
    Button buttonEdit = findViewById(R.id.songdetailEdit);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final MusicCollection songs = (MusicCollection)getIntent().getSerializableExtra("collection");

        final boolean isEdit = getIntent().getBooleanExtra("edit", true);


        final Music song = (Music)getIntent().getSerializableExtra("song");

        if(song.getId() != 0)
        {
            songName.setText(song.getName());
            songAuthor.setText(song.getArtist());
            //songYear.setText(song.getYear());
            songDuration.setText(song.getLength());
            songGenre.setText(song.getGenre());
            songAlbum.setText(song.getAlbum());
            songDescription.setText(song.getDescription());
        }

        if(isEdit)
        {
            setVisible(isEdit);

        }

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisible(true);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isEdit)
                {
                    song.setId(songs.getMusicArrayList().size() + 1);
                    song.setName(songName.getText().toString());
                    song.setArtist(songAuthor.getText().toString());
                    song.setGenre(songGenre.getText().toString());
                    song.setDuration(Integer.parseInt(songDuration.getText().toString()));
                    song.setDescription(songDescription.getText().toString());

                    songs.add(song);
                }

            }
        });


    }

    public void setVisible(boolean to)
    {

        songName.setEnabled(to);
        songAuthor.setEnabled(to);
        //songYear.setEnabled(to);
        songDuration.setEnabled(to);
        songGenre.setEnabled(to);
        songAlbum.setEnabled(to);
        songDescription.setEnabled(to);

        if(to)
        {
            buttonSave.setVisibility(View.VISIBLE);
        }
        else
        {
            buttonSave.setVisibility(View.INVISIBLE);
        }
    }
}
