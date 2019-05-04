package com.example.dtr313.musiclibrary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Database {
    private String musicDBName;
    private SQLiteDatabase musicDB;
    Database(){}

    Database(String musicDBName) {
        this.musicDBName = musicDBName;
        this.musicDB = SQLiteDatabase.openOrCreateDatabase(musicDBName, null,null);

        String query = "CREATE TABLE IF NOT EXISTS MusicLibrary(id VARCHAR(32), " +
                "name VARCHAR(32), " +
                "artist VARCHAR(32), " +
                "album VARCHAR(32), " +
                "genre VARCHAR(32)," +
                "duration INT," +
                "description VARCHAR(128));";

        musicDB.execSQL(query);               //create table
    }

    public ArrayList<Music> load( String dbName ) {
        ArrayList<Music> allMusic = new ArrayList<Music>();

        Cursor ptr = musicDB.rawQuery("Select * FROM MusicLibrary", null);

        int cID = ptr.getColumnIndex("id");
        int cName = ptr.getColumnIndex("name");
        int cArtist = ptr.getColumnIndex("artist");
        int cAlbum = ptr.getColumnIndex("album");
        int cGenre = ptr.getColumnIndex("genre");
        int cDuration = ptr.getColumnIndex("duration");
        int cDescripton = ptr.getColumnIndex("description");

        ptr.moveToFirst();

        if ( ptr != null ) {
            do {
                String ID = ptr.getString(cID);
                String Name = ptr.getString(cName);
                String Artist = ptr.getString(cArtist);
                String Album = ptr.getString(cAlbum);
                String Genre = ptr.getString(cGenre);
                int duration = ptr.getInt(cDuration);
                String description = ptr.getString(cDescripton);

                allMusic.add( new Music(ID, Name, Artist, Album, Genre, duration, description) );
            } while(ptr.moveToNext());
        }
        return allMusic;
    }
}
