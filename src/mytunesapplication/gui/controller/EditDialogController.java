/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunesapplication.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Adam
 */
public abstract class EditDialogController
{

    protected Stage dialogStage;
    protected boolean okClicked = false;

    @FXML
    protected AnchorPane anchorPane;

    /**
     * Closes application
     * @param event
     */
    @FXML
    protected void handleCancel(ActionEvent event)
    {
        dialogStage.close();
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked()
    {
        return okClicked;
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage)
    {
        this.dialogStage = dialogStage;
    }

    /**
     * Method for showing alert for user
     * @param title
     * @param header
     * @param content
     */
    protected void showAlert(String title, String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        alert.initOwner(stage);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
