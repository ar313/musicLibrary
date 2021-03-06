package com.example.dtr313.musiclibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SongDetailActivity extends AppCompatActivity {

    EditText songName;// = findViewById(R.id.songdetailName);
    EditText songAuthor;// = findViewById(R.id.songdetailAuthor);
    //EditText songYear = findViewById(R.id.songdetailYear);
    EditText songDuration;// = findViewById(R.id.songdetailDuration);
    EditText songGenre;// = findViewById(R.id.songdetailGenre);
    EditText songAlbum;//= findViewById(R.id.songdetailAlbum);
    EditText songDescription;// = findViewById(R.id.songdetailDescription);
    TextView songID;

    Button buttonSave;// = findViewById(R.id.songdetailSave);
    Button buttonEdit;// = findViewById(R.id.songdetailEdit);
    Button buttonDelete;

    final int REQUEST_CODE_ADD = 13;
    final int REQUEST_CODE_EDIT = 14;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songdetail);

        final MusicCollection songs = (MusicCollection)getIntent().getSerializableExtra("collection");

        final boolean isEdit = getIntent().getBooleanExtra("edit", true);

        final Music song = (Music)getIntent().getSerializableExtra("song");

        songName = findViewById(R.id.songdetailName);
        songAuthor = findViewById(R.id.songdetailAuthor);
        //EditText songYear = findViewById(R.id.songdetailYear);
        songDuration = findViewById(R.id.songdetailDuration);
        songGenre = findViewById(R.id.songdetailGenre);
        songAlbum = findViewById(R.id.songdetailAlbum);
        songDescription = findViewById(R.id.songdetailDescription);
        //songID = findViewById(R.id.songdetailID);

        buttonSave = findViewById(R.id.songdetailSave);
        buttonEdit = findViewById(R.id.songdetailEdit);
        buttonDelete = findViewById(R.id.songdetailDelete);


        if(song.getId() != 0)
        {
            songName.setText(song.getName());
            songAuthor.setText(song.getArtist());
            //songYear.setText(song.getYear());
            songDuration.setText(song.getLength());
            songGenre.setText(song.getGenre());
            songAlbum.setText(song.getAlbum());
            songDescription.setText(song.getDescription());
           // songID.setText(String.valueOf(song.getId()));
        }

        else
        {
           // songID.setText(songs.getMusicArrayList().size());
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

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent();
                Database db = new Database(SongDetailActivity.this);

                db.deleteMusic(song);

               setResult(RESULT_FIRST_USER, myIntent);

                finish();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(songName.getText().toString().isEmpty() || songAuthor.getText().toString().isEmpty() || songGenre.getText().toString().isEmpty())
                {

                    Toast.makeText(SongDetailActivity.this,"Input song name, artist and genre before saving",Toast.LENGTH_LONG).show();
                }

                else {

                    Intent myIntent = new Intent();

                    int duration;

                    if (songDuration.getText().toString().contains(":")) {
                        String buf[] = songDuration.getText().toString().split(":");

                        duration = Integer.parseInt(buf[0]) * 60 + Integer.parseInt(buf[1]);
                    } else {
                        duration = Integer.parseInt(songDuration.getText().toString());
                    }

                    if (isEdit) {
                        song.setId(songs.getMusicArrayList().size() + 1);
                        song.setName(songName.getText().toString());
                        song.setArtist(songAuthor.getText().toString());
                        song.setGenre(songGenre.getText().toString());


                        song.setDuration(duration);


                        song.setDescription(songDescription.getText().toString());
                        song.setAlbum(songAlbum.getText().toString());

                        //songs.add(song);

                        myIntent.putExtra("song", song);
                        myIntent.putExtra("edit", true);

                        setResult(RESULT_OK, myIntent);

                        finish();
                    } else {
                        song.setName(songName.getText().toString());
                        song.setArtist(songAuthor.getText().toString());
                        song.setGenre(songGenre.getText().toString());
                        song.setDuration(duration);
                        song.setDescription(songDescription.getText().toString());
                        song.setAlbum(songAlbum.getText().toString());


                        //songs.editMusic(song);

                        myIntent.putExtra("song", song);
                        myIntent.putExtra("edit", false);

                        setResult(RESULT_OK, myIntent);

                        finish();
                    }
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

            if(getIntent().getExtras().getInt("request") == REQUEST_CODE_EDIT)
            {
                buttonDelete.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            buttonSave.setVisibility(View.INVISIBLE);
            buttonDelete.setVisibility(View.INVISIBLE);
        }
    }
}
