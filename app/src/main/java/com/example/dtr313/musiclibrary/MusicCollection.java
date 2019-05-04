package com.example.dtr313.musiclibrary;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MusicCollection {
    private ArrayList<Music> musicArrayList = new ArrayList<Music>();

    MusicCollection(){}

    MusicCollection(ArrayList<Music> musicArrayList) {
        this.musicArrayList = musicArrayList;
    }

    public void add(Music music){
        musicArrayList.add(music);
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
            if ( music.getArtist().toLowerCase().contains( album.toLowerCase() ) ) {
                foundMusic.add(music);
            }
        }

        return foundMusic;
    }

    public ArrayList<Music> searchBySong( String song ) {
        ArrayList<Music> foundMusic = new ArrayList<Music>();
        for (Music music : this.musicArrayList ) {
            if ( music.getArtist().toLowerCase().contains( song.toLowerCase() ) ) {
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
}
