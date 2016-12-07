/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunesapplication.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunesapplication.bll.Playlist;

/**
 *
 * @author Adam
 */
public class PlaylistModel
{

    /**
     * The observable list, used for data binding the view to the model.
     */
    private final ObservableList<Playlist> playlists;

    public PlaylistModel()
    {
        playlists = FXCollections.observableArrayList();
    }

    /**
     * Gets the observable list of playlists.
     *
     * @return
     */
    public ObservableList<Playlist> getPlaylists()
    {
        return playlists;
    }

    public void addPlaylist(Playlist playlist)
    {
        playlists.add(playlist);
    }

    public void deletePlaylist(Playlist playlist)
    {
        playlists.remove(playlist);
    }
}
