/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunesapplication.bll;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Adam
 */
public class Song extends Item
{

    private SimpleStringProperty title;
    private SimpleStringProperty artist;
    private String category;
    private String time;
    private String filePath;

    public Song()
    {
        this.title = new SimpleStringProperty();
        this.artist = new SimpleStringProperty();
    }

    public Song(String title, String artist, String category, String time, String filePath)
    {
        this.title = new SimpleStringProperty(title);
        this.artist = new SimpleStringProperty(artist);
        this.category = category;
        this.time = time;
        this.filePath = filePath;
    }

    public void setTitle(String title)
    {
        this.title = new SimpleStringProperty(title);
    }

    public void setArtist(String artist)
    {
        this.artist = new SimpleStringProperty(artist);
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getTitle()
    {
        return title.get();
    }

    public String getArtist()
    {
        return artist.get();
    }

    public String getCategory()
    {
        return category;
    }

    public String getTime()
    {
        return time;
    }

    public String getFilePath()
    {
        return filePath;
    }

}
