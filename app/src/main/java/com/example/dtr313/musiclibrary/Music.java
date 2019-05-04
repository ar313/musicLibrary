package com.example.dtr313.musiclibrary;

public class Music {

    private String id;
    private String mName;
    private String mArtist;
    private String mAlbum;
    private String genre;
    private int duration;
    private String description;

    Music(){}

    Music(String id, String mName, String mArtist, String mAlbum, String genre, int duration, String description) {

        this.id = id;
        this.mName = mName;
        this.mArtist = mArtist;
        this.mAlbum = mAlbum;
        this.genre = genre;
        this.duration = duration;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String mArtist) {
        this.mArtist = mArtist;
    }

    public String getAlbum() {
        return mAlbum;
    }

    public void setAlbum(String mAlbum) {
        this.mAlbum = mAlbum;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLength() {
        return "" + (duration/60) + ":" + (duration%60);
    }

    public static int timeToDuration(int minutes, int seconds ) {
        return minutes*60 + seconds;
    }
}
