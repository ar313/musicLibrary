package com.example.dtr313.musiclibrary;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class MusicCollection implements Serializable {
    private ArrayList<Music> musicArrayList = new ArrayList<Music>();

    MusicCollection(Database db){
        this.musicArrayList = db.load();
    }

    MusicCollection(ArrayList<Music> musicArrayList) {
        this.musicArrayList = musicArrayList;
    }

    public void reload(Database db)
    {
        this.musicArrayList = db.load();
    }

    public void add(Music music){
        musicArrayList.add(music);
    }

    public ArrayList<Music> searchByGenre( String genre ) {
        ArrayList<Music> foundMusic = new ArrayList<Music>();
        for (Music music : this.musicArrayList ) {
            if ( music.getGenre().toLowerCase().contains( genre.toLowerCase() ) ) {
                foundMusic.add(music);
            }
        }

        return foundMusic;
    }

    public ArrayList<Music> searchByArtist( String artist ) {
        ArrayList<Music> foundMusic = new ArrayList<Music>();
        for (Music music : this.musicArrayList ) {
            if ( music.getArtist().toLowerCase().contains( artist.toLowerCase() ) ) {
                foundMusic.add(music);
            }
        }

        return foundMusic;
    }

    public ArrayList<Music> searchByAlbum( String album ) {
        ArrayList<Music> foundMusic = new ArrayList<Music>();
        for (Music music : this.musicArrayList ) {
            if ( music.getAlbum().toLowerCase().contains( album.toLowerCase() ) ) {
                foundMusic.add(music);
            }
        }

        return foundMusic;
    }

    public ArrayList<Music> searchBySong( String song ) {
        ArrayList<Music> foundMusic = new ArrayList<Music>();
        for (Music music : this.musicArrayList ) {
            if ( music.getName().toLowerCase().contains( song.toLowerCase() ) ) {
                foundMusic.add(music);
            }
        }

        return foundMusic;
    }

    public ArrayList<Music> getMusicArrayList() {
        return musicArrayList;
    }

    public void setMusicArrayList(ArrayList<Music> musicArrayList) {
        this.musicArrayList = musicArrayList;
    }

    public void editMusic(Music musicToEdit) {
        for (int i = 0; i< this.musicArrayList.size(); i++ ) {
            if ( this.musicArrayList.get(i).getId() ==  musicToEdit.getId() ) {
                this.musicArrayList.set(i, musicToEdit);

                break;
            }
        }
    }

    public int geteditMusic(Music musicToEdit) {

        int temp = -1;

        for (int i = 0; i< this.musicArrayList.size(); i++ ) {
            if ( this.musicArrayList.get(i).getId() ==  musicToEdit.getId() ) {
                //this.musicArrayList.set(i, musicToEdit);

                temp = i;

                break;
            }
        }

        return temp;
    }
}
