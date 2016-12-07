/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunesapplication.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mytunesapplication.bll.Playlist;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class PlaylistEditDialogController extends EditDialogController
{

    private Playlist playlist;

    @FXML
    private TextField txtFieldName;

    @FXML
    private void handleOk(ActionEvent event)
    {
        playlist.setName(txtFieldName.getText());
        okClicked = true;
        dialogStage.close();
    }

    /**
     * Sets the playlist to be edited in the dialog.
     *
     * @param playlist
     */
    public void setPlaylist(Playlist playlist)
    {
        this.playlist = playlist;
        txtFieldName.setText(playlist.getName());
    }

}
