/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunesapplication.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mytunesapplication.bll.DialogWindow;
import mytunesapplication.bll.DialogWindowPlaylist;
import mytunesapplication.bll.DialogWindowSong;
import mytunesapplication.bll.Item;
import mytunesapplication.bll.Playlist;
import mytunesapplication.bll.Song;
import mytunesapplication.gui.MyTunesApplication;
import mytunesapplication.model.PlaylistModel;
import mytunesapplication.model.SongModel;

/**
 * FXML Controller class
 *
 * @author Adam
 */
public class MyTunesApplicationOverviewController implements Initializable
{
    
    private SongModel songModel;
    private PlaylistModel playlistModel;
    private final String[] songTableHeader =
    {
        "Title", "Artist", "Cathegory", "Time"
    };
    private final String[] playlistTableHeader =
    {
        "Name", "Songs", "Time"
    };
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Song> tableViewSongs;
    @FXML
    private TableView<Playlist> tableViewPlaylists;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        songModel = new SongModel();
        playlistModel = new PlaylistModel();
        this.prepareTables();
    }
    
    private void prepareTables()
    {
        tableViewSongs.setItems(songModel.getSongs());
        tableViewPlaylists.setItems(playlistModel.getPlaylists());
        
        for (String columnName : songTableHeader)
        {
            TableColumn column = new TableColumn(columnName);
            column.setMinWidth(100);
            column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            tableViewSongs.getColumns().add(column);
        }
        
        for (String columnName : playlistTableHeader)
        {
            TableColumn column = new TableColumn(columnName);
            column.setMinWidth(75);
            column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            tableViewPlaylists.getColumns().add(column);
        }
    }

    /**
     *
     * @param dialogWindow
     * @param item
     * @param title
     * @return
     */
    public boolean showDialog(DialogWindow dialogWindow, Item item, String title)
    {
        try
        {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MyTunesApplication.class.getResource(dialogWindow.getViewPath()));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            dialogStage.initOwner(stage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            EditDialogController controller = dialogWindow.prepareController(loader, dialogStage, item);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            
            return controller.isOkClicked();
            
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new song.
     */
    @FXML
    private void handleAddSong()
    {
        Song tempSong = new Song();
        DialogWindowSong dialog = new DialogWindowSong();
        boolean okClicked = this.showDialog(dialog, tempSong, "New song");
        if (okClicked)
        {
            songModel.addSong(tempSong);
        }
    }
    
    @FXML
    private void handleEditSong(ActionEvent event)
    {
        Song selectedSong = tableViewSongs.getSelectionModel().getSelectedItem();
        if (selectedSong != null)
        {
            DialogWindowSong dialog = new DialogWindowSong();
            boolean okClicked = this.showDialog(dialog, selectedSong, "Edit song");
            if (okClicked)
            {
                tableViewSongs.refresh();
            }
        } else
        {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            alert.initOwner(stage);
            alert.setTitle("No Selection");
            alert.setHeaderText("No song Selected");
            alert.setContentText("Please select a song in the table.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleDeleteSong(ActionEvent event)
    {
        Song selectedSong = tableViewSongs.getSelectionModel().getSelectedItem();
        if (selectedSong != null)
        {
            songModel.deleteSong(selectedSong);
            tableViewSongs.refresh();
        } else
        {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            alert.initOwner(stage);
            alert.setTitle("No Selection");
            alert.setHeaderText("No song Selected");
            alert.setContentText("Please select a song in the table.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleAddPlaylist(ActionEvent event)
    {
        Playlist tempPlaylist = new Playlist();
        DialogWindowPlaylist dialog = new DialogWindowPlaylist();
        boolean okClicked = this.showDialog(dialog, tempPlaylist, "New playlist");
        if (okClicked)
        {
            playlistModel.addPlaylist(tempPlaylist);
        }
    }
    
    @FXML
    private void handleEditPlaylist(ActionEvent event)
    {
        Playlist selectedPlaylist = tableViewPlaylists.getSelectionModel().getSelectedItem();
        if (selectedPlaylist != null)
        {
            DialogWindowPlaylist dialog = new DialogWindowPlaylist();
            boolean okClicked = this.showDialog(dialog, selectedPlaylist, "Edit playlist");
            if (okClicked)
            {
                tableViewPlaylists.refresh();
            }
        } else
        {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            alert.initOwner(stage);
            alert.setTitle("No Selection");
            alert.setHeaderText("No playlist Selected");
            alert.setContentText("Please select a playlist in the table.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleDeletePlaylist(ActionEvent event)
    {
        Playlist selectedPlaylist = tableViewPlaylists.getSelectionModel().getSelectedItem();
        if (selectedPlaylist != null)
        {
            playlistModel.deletePlaylist(selectedPlaylist);
            tableViewPlaylists.refresh();
        } else
        {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            alert.initOwner(stage);
            alert.setTitle("No Selection");
            alert.setHeaderText("No playlist Selected");
            alert.setContentText("Please select a playlist in the table.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleClose(ActionEvent event)
    {
        System.exit(0);
    }
    
}
