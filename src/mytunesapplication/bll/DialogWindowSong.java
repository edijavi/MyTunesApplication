/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunesapplication.bll;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import mytunesapplication.gui.controller.EditDialogController;
import mytunesapplication.gui.controller.SongEditDialogController;

/**
 *
 * @author Adam
 */
public class DialogWindowSong extends DialogWindow
{

    @Override
    public String getViewPath()
    {
        return "view/SongEditDialog.fxml";
    }

    @Override
    public EditDialogController prepareController(FXMLLoader loader, Stage dialogStage, Item song)
    {
        SongEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        ((SongEditDialogController) controller).setSong((Song) song);
        return controller;
    }

}
