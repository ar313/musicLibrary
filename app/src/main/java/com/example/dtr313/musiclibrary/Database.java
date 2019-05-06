package com.example.dtr313.musiclibrary;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import java.io.File;
import java.util.ArrayList;


public class Database extends SQLiteOpenHelper {
    private static String musicDBName = "MusicLibrary.db";

    private static int DATABASE_VERSION = 1;

    Database(Context context) {
        super(context, Environment.getExternalStorageDirectory() + File.separator + "MusicLibrary/db/"
                + File.separator
                + musicDBName, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE IF NOT EXISTS MusicLibrary(id INT, " +
                "name VARCHAR(32), " +
                "artist VARCHAR(32), " +
                "album VARCHAR(32), " +
                "genre VARCHAR(32)," +
                "duration INT," +
                "description VARCHAR(128));";

        db.execSQL(query);               //create table
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<Music> load() {
        ArrayList<Music> allMusic = new ArrayList<Music>();

        SQLiteDatabase musicDB = this.getReadableDatabase();
        Cursor ptr = musicDB.rawQuery("Select * FROM MusicLibrary", null);

        int cID = ptr.getColumnIndex("id");
        int cName = ptr.getColumnIndex("name");
        int cArtist = ptr.getColumnIndex("artist");
        int cAlbum = ptr.getColumnIndex("album");
        int cGenre = ptr.getColumnIndex("genre");
        int cDuration = ptr.getColumnIndex("duration");
        int cDescription = ptr.getColumnIndex("description");

        ptr.moveToFirst();

        if ( ptr != null && ptr.getCount()>=1 ) {
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

        SQLiteDatabase musicDB = this.getWritableDatabase();

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

        SQLiteDatabase musicDB = this.getWritableDatabase();

        String query ="UPDATE MusicLibrary SET " +
                "name = '" + music.getName() +
                "' , artist = '" + music.getArtist() +
                "', album = '" + music.getAlbum() +
                "', genre = '" + music.getGenre() +
                "', duration = " + music.getDuration() +
                ", description = '" + music.getDescription() +
                "' WHERE  id = " + music.getId() + ";";

        musicDB.execSQL(query);
    }

    public void deleteMusic(Music music) {
        SQLiteDatabase musicDB = this.getWritableDatabase();

        String query = "DELETE FROM MusicLibrary WHERE id= " +music.getId() + ";";
        String query2 = "SELECT * FROM MusicLibrary WHERE id>" +music.getId();

        musicDB.execSQL(query);
        Cursor ptr = musicDB.rawQuery(query2, null);

        int cID = ptr.getColumnIndex("id");

        ptr.moveToFirst();

        if ( ptr != null ) {
            do {
                int ID = ptr.getInt(cID)-1;

                String updateQuery = "UPDATE MusicLibrary SET " + "id = " + ID + " WHERE id= " +ptr.getInt(cID) + ";";

                musicDB.execSQL(updateQuery);
            } while(ptr.moveToNext());
        }

    }
}
