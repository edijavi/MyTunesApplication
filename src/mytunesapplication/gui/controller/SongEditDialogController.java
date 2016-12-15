/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunesapplication.gui.controller;

import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
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
    private ComboBox<String> comboBoxCategory;
    @FXML
    private TextField txtFieldFile;

    @FXML
    private void handleOk(ActionEvent event)
    {
        if (isInputValid())
        {
            song.setTitle(txtFieldTitle.getText().trim());
            song.setArtist(txtFieldArtist.getText().trim());
            song.setCategory(comboBoxCategory.getValue());
            song.setTime(LocalTime.parse(txtFieldTime.getText(), DateTimeFormatter.ISO_LOCAL_TIME));
            song.setFilePath(txtFieldFile.getText());

            okClicked = true;
            dialogStage.close();
        }
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
        txtFieldTime.setText(song.getTime());
        txtFieldFile.setText(song.getFilePath());
        fillComboBox(song.getCategory());
    }

    private void fillComboBox(String stringKey)
    {
        ObservableList<String> comboItems
                = FXCollections.observableArrayList(
                        "Other", "Blues", "Funky", "Jazz", "Pop", "Punk", "Regge", "Rock", "Soul");
        comboBoxCategory.setItems(comboItems);
        comboBoxCategory.getSelectionModel().select(stringKey);
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid()
    {
        String errorMessage = "";

        if (txtFieldTitle.getText() == null || txtFieldTitle.getText().trim().length() == 0)
        {
            errorMessage += "Not valid song title!\n";
        }
        if (txtFieldArtist.getText() == null || txtFieldArtist.getText().trim().length() == 0)
        {
            errorMessage += "Not valid song artist!\n";
        }
        if (txtFieldTime.getText() == null || txtFieldTime.getText().trim().length() == 0)
        {
            errorMessage += "Not valid song time!\n";
        }

        try
        {
            LocalTime.parse(txtFieldTime.getText(), DateTimeFormatter.ISO_LOCAL_TIME);
        } catch (DateTimeParseException e)
        {
            errorMessage += "Not valid song time!\n";
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

    @FXML
    private void handleChooseFile(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MP3 files (*.mp3)", "*.mp3");
        fileChooser.getExtensionFilters().add(extFilter);
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null)
        {
            txtFieldFile.setText(file.getAbsolutePath());
        }
    }
}
