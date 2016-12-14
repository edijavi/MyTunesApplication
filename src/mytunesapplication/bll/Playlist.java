/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunesapplication.bll;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Adam
 */
public class Playlist extends Item
{

    private SimpleStringProperty name;
    private final ObservableList<Song> songs;
    private final ObservableList<String> songsStrings;
    private final Map<String, Song> songsHash;

    public Playlist()
    {
        name = new SimpleStringProperty();
        songs = FXCollections.observableArrayList();
        songsStrings = FXCollections.observableArrayList();
        songsHash = new HashMap<>();
    }

    public void setName(String name)
    {
        this.name = new SimpleStringProperty(name);
    }

    public String getName()
    {
        return name.get();
    }

    public int getSongs()
    {
        return songs.size();
    }

    public String getTime()
    {
        LocalTime totalTime = LocalTime.parse("00:00:00", DateTimeFormatter.ISO_LOCAL_TIME);

        for (Song song : songs)
        {
            LocalTime songTime = song.getTimeDuration();
            totalTime = totalTime.plusHours(songTime.getHour()).plusMinutes(songTime.getMinute()).plusSeconds(songTime.getSecond());
        }
        return totalTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public ObservableList<Song> getSongsList()
    {
        return songs;
    }

    public ObservableList<String> getSongsStrings()
    {
        return songsStrings;
    }

    /** 
     * Adding song to playlist
     *
     * @param song to add to playlist
     */
    public void addSong(Song song)
    {
        songs.add(song);
        String stringKey = songs.size() + ". " + song.getTitle();
        songsHash.put(stringKey, song);
        songsStrings.add(stringKey);
    }

    /**
     *
     * @param song
     * @return boolean if the playlist contains song
     */
    public boolean containsSong(Song song)
    {
        return songs.contains(song);
    }

    /** 
     * Deleting song from playlist under the given key
     *
     * @param songKey key for the song
     */
    public void deleteSong(String songKey)
    {
        Song song = songsHash.get(songKey);
        songs.remove(song);
        rebuildSongsStrings();
    }

    /**
     * Actualization of the song list to shown in the listView
     */
    public void rebuildSongsStrings()
    {
        songsHash.clear();
        songsStrings.clear();

        for (int i = 0; i < songs.size(); i++)
        {
            String stringKey = (i + 1) + ". " + songs.get(i).getTitle();
            songsHash.put(stringKey, songs.get(i));
            songsStrings.add(stringKey);
        }
    }

    /**
     * Deleting song from playlist
     * @param song
     */
    public void removeSong(Song song)
    {
        songs.remove(song);
        rebuildSongsStrings();
    }

    /**
     * Moving song under given key on position i to position i + 1 or i - 1
     * @param songKey
     * @param upTrueDownFalse
     */
    public void moveSong(String songKey, boolean upTrueDownFalse)
    {
        Song toMove = songsHash.get(songKey);
        int index = songs.indexOf(toMove);

        boolean change = false;

        if (upTrueDownFalse)
        {
            if (index > 0)
            {
                songs.set(index, songs.get(index - 1));
                songs.set(index - 1, toMove);
                change = true;
            }
        } else if (index < songs.size() - 1)
        {
            songs.set(index, songs.get(index + 1));
            songs.set(index + 1, toMove);
            change = true;
        }

        if (change)
        {
            rebuildSongsStrings();
        }
    }
}
