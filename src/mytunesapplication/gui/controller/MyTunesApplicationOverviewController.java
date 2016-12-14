/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunesapplication.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    private boolean filterOn = true;
    private final String[] songTableHeader =
    {
        "Title", "Artist", "Category", "Time"
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
    @FXML
    private ListView<String> listViewSongs;
    @FXML
    private TextField txtFieldFilter;
    @FXML
    private Button buttonFilter;
    
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

    /**
     * Method bind data to tableViews and prepare columns, creates listeners
     */
    private void prepareTables()
    {
        tableViewSongs.setItems(songModel.getSongs());
        tableViewPlaylists.setItems(playlistModel.getPlaylists());

        for (String columnName : songTableHeader)
        {
            TableColumn column = new TableColumn(columnName);
            column.setMinWidth(75);
            column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            tableViewSongs.getColumns().add(column);
        }

        for (String columnName : playlistTableHeader)
        {
            TableColumn column = new TableColumn(columnName);
            column.setMinWidth(70);
            column.setCellValueFactory(new PropertyValueFactory<>(columnName));
            tableViewPlaylists.getColumns().add(column);
        }

        // listener for selecting playlist
        tableViewPlaylists.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> 
                {
                    if (newSelection != null)
                    {
                        listViewSongs.setItems(tableViewPlaylists.getSelectionModel().getSelectedItem().getSongsStrings());
                    } else
                    {
                        listViewSongs.getItems().clear();
                    }
        });
    }

    /**
     * Shows dialog window to user
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
                playlistModel.updateSongTitleInAllPlaylists(selectedSong);
                tableViewSongs.refresh();
                tableViewPlaylists.refresh();
            }
        } else
        {
            // Nothing selected.
            showAlert("No Selection", "No song Selected", "Please select a song in the table.");
        }
    }

    @FXML
    private void handleDeleteSong(ActionEvent event)
    {
        Song selectedSong = tableViewSongs.getSelectionModel().getSelectedItem();
        if (selectedSong != null)
        {
            if (showConfirmation("Are you sure you want to delete song from application?"))
            {
                if (showConfirmation("Are you sure you want to delete song from PC?"))
                {
                    // selectedSong.deleteFile();
                }
                songModel.deleteSong(selectedSong);
                playlistModel.removeSongFromAllPlaylists(selectedSong);
                tableViewPlaylists.refresh();
            }
        } else
        {
            // Nothing selected.
            showAlert("No Selection", "No song Selected", "Please select a song in the table.");
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
            showAlert("No Selection", "No playlist Selected", "Please select a playlist in the table.");
        }
    }

    @FXML
    private void handleDeletePlaylist(ActionEvent event)
    {
        Playlist selectedPlaylist = tableViewPlaylists.getSelectionModel().getSelectedItem();
        if (selectedPlaylist != null)
        {
            if (showConfirmation("Are you sure you want to delete playlist?"))
            {
                playlistModel.deletePlaylist(selectedPlaylist);
            }
        } else
        {
            // Nothing selected.
            showAlert("No Selection", "No playlist Selected", "Please select a playlist in the table.");
        }
    }

    @FXML
    private void handleAddSongToPlaylist(ActionEvent event)
    {
        Song selectedSong = tableViewSongs.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = tableViewPlaylists.getSelectionModel().getSelectedItem();
        if (selectedSong != null && selectedPlaylist != null)
        {
            if (!selectedPlaylist.containsSong(selectedSong))
            {
                selectedPlaylist.addSong(selectedSong);
                tableViewPlaylists.refresh();
            } else
            {
                // Playlist contains selected song
                showAlert("Playlist contains selected song", "Playlist contains selected song", "Selected playlist contains selected song.");

            }
        } else
        {
            // Nothing selected.
            showAlert("No Selection", "No song and playlist Selected", "Please select song and playlist in the tables.");
        }
    }

    @FXML
    private void handleClose(ActionEvent event)
    {
        System.exit(0);
    }

    private void showAlert(String title, String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        alert.initOwner(stage);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private boolean showConfirmation(String content)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        alert.initOwner(stage);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirmation Dialog");
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            return true;
        } else
        {
            return false;
        }
    }

    private void handleMovingSongInPlaylist(boolean movement)
    {
        Playlist selectedPlaylist = tableViewPlaylists.getSelectionModel().getSelectedItem();
        String songKey = listViewSongs.getSelectionModel().getSelectedItem();
        if (selectedPlaylist != null && songKey != null)
        {
            selectedPlaylist.moveSong(songKey, movement);
        } else
        {
            // Nothing selected.
            showAlert("No Selection", "No song Selected", "Please select a song in the playlist.");
        }
    }

    @FXML
    private void handleMoveUpSongInPlaylist(ActionEvent event)
    {
        handleMovingSongInPlaylist(true);
    }

    @FXML
    private void handleMoveDownSongInPlaylist(ActionEvent event)
    {
        handleMovingSongInPlaylist(false);
    }

    @FXML
    private void handleDeleteSongFromPlaylist(ActionEvent event)
    {
        Playlist selectedPlaylist = tableViewPlaylists.getSelectionModel().getSelectedItem();
        String songKey = listViewSongs.getSelectionModel().getSelectedItem();
        if (selectedPlaylist != null && songKey != null)
        {
            if (showConfirmation("Are you sure you want to delete song from playlist?"))
            {
                selectedPlaylist.deleteSong(songKey);
                tableViewPlaylists.refresh();
            }

        } else
        {
            // Nothing selected.
            showAlert("No Selection", "No song Selected", "Please select a song in the playlist.");
        }
    }

    @FXML
    private void handFilterRecords(ActionEvent event)
    {
        if (filterOn)
        {
            tableViewSongs.setItems(songModel.getSongsFilter(txtFieldFilter.getText()));
            tableViewPlaylists.setItems(playlistModel.getPlaylistsFilter(txtFieldFilter.getText()));
            filterOn = false;
            buttonFilter.setText("Clear");
        } else
        {
            tableViewSongs.setItems(songModel.getSongs());
            tableViewPlaylists.setItems(playlistModel.getPlaylists());
            filterOn = true;
            buttonFilter.setText("GO");
            txtFieldFilter.setText("");
        }
    }

}
