/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunesapplication.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mytunesapplication.bll.Song;

/**
 *
 * @author Adam
 */
public class SongEditDialogController extends EditDialogController
{

    private Song song;

    @FXML
    private TextField txtFieldTitle;
    @FXML
    private TextField txtFieldArtist;
    @FXML
    private TextField txtFieldTime;
    @FXML
    private TextField txtFieldFilePath;
    @FXML
    private ComboBox<String> comboBoxCategory;

    @FXML
    private void handleOk(ActionEvent event)
    {
        song.setTitle(txtFieldTitle.getText());
        song.setArtist(txtFieldArtist.getText());

        okClicked = true;
        dialogStage.close();
    }

    /**
     * Sets the song to be edited in the dialog.
     *
     * @param song
     */
    public void setSong(Song song)
    {
        this.song = song;

        txtFieldTitle.setText(song.getTitle());
        txtFieldArtist.setText(song.getArtist());
    }

}
