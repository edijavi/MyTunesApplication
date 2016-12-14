/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunesapplication.model;

import java.io.Serializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunesapplication.bll.Playlist;
import mytunesapplication.bll.Song;

/**
 *
 * @author Adam
 */
public class PlaylistModel
{

    /**
     * The observable lists, used for data binding the view to the model.
     */
    private final ObservableList<Playlist> playlists;
    private final ObservableList<Playlist> playlistsFilter;

    public PlaylistModel()
    {
        playlists = FXCollections.observableArrayList();
        playlistsFilter = FXCollections.observableArrayList();
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

    /**
     * Adds new playlist to the application
     * @param playlist
     */
    public void addPlaylist(Playlist playlist)
    {
        playlists.add(playlist);
    }

    /**
     * Deletes playlist from application
     * @param playlist
     */
    public void deletePlaylist(Playlist playlist)
    {
        playlists.remove(playlist);
    }

    /**
     * Gets the observable list of playlists.
     *
     * @param filter
     * @return
     */
    public ObservableList<Playlist> getPlaylistsFilter(String filter)
    {
        playlistsFilter.clear();
        for (Playlist playlist : playlists)
        {
            if (playlist.getName().contains(filter))
            {
                playlistsFilter.add(playlist);
            }
        }
        return playlistsFilter;
    }

    /**
     * Checks all the playlists and delete song if it is contained
     * @param song
     */
    public void removeSongFromAllPlaylists(Song song)
    {
        for (Playlist playlist : playlists)
        {
            if (playlist.containsSong(song))
            {
                playlist.removeSong(song);
            }
        }
    }
    
    /**
     * Checks all the playlists and and if the song is contained, refresh playlist 
     * @param song
     */
    public void updateSongTitleInAllPlaylists(Song song)
    {
        for (Playlist playlist : playlists)
        {
            if (playlist.containsSong(song))
            {
                playlist.rebuildSongsStrings();
            }
        }
    }

}
