/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tarek
 */
public class DashbordAdminHomeController implements Initializable {

    /**
     * Initializes the controller class.
     *
     */
    
    

    //@FXML
    //private AnchorPane PaneAdmin;
    @FXML
    private BorderPane DashbordPane;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                 //loadUi("DashbordAdmin");

    }    

    @FXML
    private void AdminMainAction(ActionEvent event) {
         loadUi("DashbordAdmin");
    }

    private void loadUi(String ui) {
        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashbordAdminHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        DashbordPane.setCenter(root);
    

    }
    
    
        @FXML
    private void AdminHomeSpace(ActionEvent event) {
          loadUi("DashbordAdminHome");
    }
    
        private Stage getStage() {
        return (Stage) DashbordPane.getScene().getWindow();
    }
    
        private void loadScreen() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("DashboardAdminHome.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashbordAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        DashbordPane.getScene().setRoot(root);
    }





    
}
