/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunesapplication.dal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import mytunesapplication.model.PlaylistModel;

/**
 *
 * @author Adam
 */
public class FileSystem
{

    public static void saveToFile(String FilePath, PlaylistModel playlistModel)
    {
        try
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("javaObjects.txt"));
            objectOutputStream.writeObject(playlistModel);
            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (IOException e)
        {
        }
    }

    public static PlaylistModel loadFilePlaylistModel()
    {
        try
        {
            FileInputStream fin = new FileInputStream("javaObjects.txt");
            ObjectInputStream ois = new ObjectInputStream(fin);
            PlaylistModel playlists = (PlaylistModel) ois.readObject();
            return playlists;

        } catch (IOException e)
        {
            return new PlaylistModel();
        } catch (ClassNotFoundException e)
        {
            return new PlaylistModel();
        }
    }
}
