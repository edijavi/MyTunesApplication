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
     * The observable list, used for data binding the view to the model.
     */
    private final ObservableList<Song> songs;
    
    public SongModel()
    {
        songs = FXCollections.observableArrayList();
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
    
    public void addSong(Song song)
    {
        songs.add(song);
    }
    
    public void deleteSong(Song song)
    {
        songs.remove(song);
    }
    
}
