/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunesapplication.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
        if (isInputValid())
        {
            playlist.setName(txtFieldName.getText().trim());
            okClicked = true;
            dialogStage.close();
        }
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

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid()
    {
        String errorMessage = "";

        if (txtFieldName.getText() == null || txtFieldName.getText().trim().length() == 0)
        {
            errorMessage += "Not valid playlist name!\n";
        }

        if (errorMessage.length() == 0)
        {
            return true;
        } else
        {
            // Show the error message.
            showAlert("Invalid Fields", "Please correct invalid fields", errorMessage);
            return false;
        }
    }

}
