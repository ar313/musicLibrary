package com.example.dtr313.musiclibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SongDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MusicCollection songs = (MusicCollection)getIntent().getSerializableExtra("collection");

        boolean isEdit = getIntent().getBooleanExtra("edit", true);

        if(isEdit)
        {
            setVisible(isEdit);

        }

        Music song = (Music)getIntent().getSerializableExtra("song");


    }

    public void setVisible(boolean to)
    {
        EditText songName = findViewById(R.id.songdetailName);
        EditText songAuthor = findViewById(R.id.songdetailAuthor);
        EditText songYear = findViewById(R.id.songdetailYear);
        EditText songDuration = findViewById(R.id.songdetailDuration);
        EditText songGenre = findViewById(R.id.songdetailGenre);
        EditText songAlbum = findViewById(R.id.songdetailAlbum);
        EditText songDescription = findViewById(R.id.songdetailDescription);

        Button buttonSave = findViewById(R.id.songdetailSave);

        songName.setEnabled(to);
        songAuthor.setEnabled(to);
        songYear.setEnabled(to);
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
