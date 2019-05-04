package com.example.dtr313.musiclibrary;

public class Music {

    private String mName;
    private String mArtist;
    private String mAlbum;
    private int duration;
    private String description;

    Music(){}

    Music(String mName, String mArtist, String mAlbum, int duration, String description) {

        this.mName = mName;
        this.mArtist = mArtist;
        this.mAlbum = mAlbum;
        this.duration = duration;
        this.description = description;
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
}
