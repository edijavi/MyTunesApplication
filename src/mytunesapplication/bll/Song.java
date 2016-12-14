/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunesapplication.bll;

import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Adam
 */
public class Song extends Item
{

    private SimpleStringProperty title;
    private SimpleStringProperty artist;
    private SimpleStringProperty category;
    private LocalTime time;
    private String filePath;

    public Song()
    {
        this.title = new SimpleStringProperty();
        this.artist = new SimpleStringProperty();
        this.category = new SimpleStringProperty("Other");
        this.time = LocalTime.parse("00:00:00", DateTimeFormatter.ISO_LOCAL_TIME);
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
        this.category = new SimpleStringProperty(category);
    }

    public void setTime(LocalTime time)
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
        return category.get();
    }

    public String getTime()
    {
        return time.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public LocalTime getTimeDuration()
    {
        return time;
    }

    public String getFilePath()
    {
        return filePath;
    }

    /**
     * Deletes song file from PC
     */
    public void deleteFile()
    {
        try
        {
            File file = new File(filePath);
            file.delete();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
