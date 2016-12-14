/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunesapplication.bll;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import mytunesapplication.gui.controller.EditDialogController;
import mytunesapplication.gui.controller.PlaylistEditDialogController;

/**
 *
 * @author Adam
 */
public class DialogWindowPlaylist extends DialogWindow
{

    /**
     *
     * @return String - filePath for view
     */
    @Override
    public String getViewPath()
    {
        return "view/PlaylistEditDialog.fxml";
    }

    @Override
    public EditDialogController prepareController(FXMLLoader loader, Stage dialogStage, Item playlist)
    {
        PlaylistEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        ((PlaylistEditDialogController) controller).setPlaylist((Playlist) playlist);
        return controller;
    }

}
