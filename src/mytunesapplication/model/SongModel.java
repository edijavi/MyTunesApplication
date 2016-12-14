/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunesapplication.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunesapplication.bll.Song;

/**
 *
 * @author Adam
 */
public class SongModel
{

    /**
     * The observable lists, used for data binding the view to the model.
     */
    private final ObservableList<Song> songs;
    private final ObservableList<Song> songsFilter;

    public SongModel()
    {
        songs = FXCollections.observableArrayList();
        songsFilter = FXCollections.observableArrayList();
    }

    /**
     * Gets the observable list of songs.
     *
     * @return
     */
    public ObservableList<Song> getSongs()
    {
        return songs;
    }

    /**
     * Gets the observable list of songs with filter.
     *
     * @param filter
     * @return
     */
    public ObservableList<Song> getSongsFilter(String filter)
    {
        songsFilter.clear();
        for (Song song : songs)
        {
            if (song.getArtist().contains(filter) || song.getTitle().contains(filter) || song.getCategory().contains(filter))
            {
                songsFilter.add(song);
            }
        }
        return songsFilter;
    }

    /**
     *
     * @param song
     */
    public void addSong(Song song)
    {
        songs.add(song);
    }

    /**
     *
     * @param song
     */
    public void deleteSong(Song song)
    {
        songs.remove(song);
    }

}
