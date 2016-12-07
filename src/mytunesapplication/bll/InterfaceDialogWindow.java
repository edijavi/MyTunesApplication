/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunesapplication.bll;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import mytunesapplication.gui.controller.EditDialogController;

/**
 *
 * @author Adam
 */
public interface InterfaceDialogWindow
{

    public String getViewPath();

    public EditDialogController prepareController(FXMLLoader loader, Stage dialogStage, Item item);

}
