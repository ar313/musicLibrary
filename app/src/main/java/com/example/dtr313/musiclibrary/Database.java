package com.example.dtr313.musiclibrary;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class Database {
    private String musicDBName = "MusicLibrary";
    private SQLiteDatabase musicDB;

    Database() {
        this.musicDB = SQLiteDatabase.openOrCreateDatabase(this.musicDBName, null,null);

        String query = "CREATE TABLE IF NOT EXISTS MusicLibrary(id INT, " +
                "name VARCHAR(32), " +
                "artist VARCHAR(32), " +
                "album VARCHAR(32), " +
                "genre VARCHAR(32)," +
                "duration INT," +
                "description VARCHAR(128));";

        musicDB.execSQL(query);               //create table
    }

    public ArrayList<Music> load() {
        ArrayList<Music> allMusic = new ArrayList<Music>();

        Cursor ptr = musicDB.rawQuery("Select * FROM MusicLibrary", null);

        int cID = ptr.getColumnIndex("id");
        int cName = ptr.getColumnIndex("name");
        int cArtist = ptr.getColumnIndex("artist");
        int cAlbum = ptr.getColumnIndex("album");
        int cGenre = ptr.getColumnIndex("genre");
        int cDuration = ptr.getColumnIndex("duration");
        int cDescription = ptr.getColumnIndex("description");

        ptr.moveToFirst();

        if ( ptr != null ) {
            do {
                int ID = ptr.getInt(cID);
                String Name = ptr.getString(cName);
                String Artist = ptr.getString(cArtist);
                String Album = ptr.getString(cAlbum);
                String Genre = ptr.getString(cGenre);
                int duration = ptr.getInt(cDuration);
                String description = ptr.getString(cDescription);

                allMusic.add( new Music(ID, Name, Artist, Album, Genre, duration, description) );
            } while(ptr.moveToNext());
        }
        return allMusic;
    }

    public void addMusicToDB(Music music) {

        String query = "INSERT INTO MusicLibrary (id, name, artist, album, genre, duration, description)" +
                "VALUES ("+
                music.getId() + ",'" +
                music.getName() + "','" +
                music.getArtist() + "','" +
                music.getAlbum() + "','" +
                music.getGenre() + "'," +
                music.getDuration() + ",'" +
                music.getDescription() + "');";

        musicDB.execSQL(query);
    }

    public void editMusic(Music music) {

        String query ="UPDATE MusicLibrary SET " +
                "name = " + music.getName() +
                ", artist = " + music.getArtist() +
                ", album = " + music.getAlbum() +
                ", genre = " + music.getGenre() +
                ", duration = " + music.getDuration() +
                ", description = " + music.getDescription() +
                " WHERE  id = " + music.getId() + ";";

        musicDB.execSQL(query);
    }
}
