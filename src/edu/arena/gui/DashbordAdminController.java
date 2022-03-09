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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tarek
 */
public class DashbordAdminController implements Initializable {

    @FXML
    private BorderPane DashbordPane;
    @FXML
    private TabPane PaneAdmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
         loadUi("DashbordJeux");
    }    

    
    
    
    @FXML
    private void FullScreen(ActionEvent event) {
                    Stage stage = getStage();
        stage.setFullScreen(!stage.isFullScreen());
    }

    @FXML
    private void AboutUsAction(ActionEvent event) {

    }

    private void profileAction(ActionEvent event) {
           // loadScreen();
        loadUi("DashbordProfile");
    }

    private void UsersAction(ActionEvent event) {
                loadUi("DashbordUsers");

    }

    @FXML
    private void TournoisAction(ActionEvent event) {
               loadUi("DashbordTournois");

    }

    private void ActualiteAction(ActionEvent event) {
                loadUi("DashbordActualite");

    }

    private void MarchandiseAction(ActionEvent event) {
                loadUi("DashbordMarchandise");

    }

    private void ReclamationAction(ActionEvent event) {
        loadUi("DashbordReclamation");
    }
    
     @FXML
    private void JeuxAction(ActionEvent event) {
              loadUi("DashbordJeux");

    }

    private void loadUi(String ui) {
        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashbordAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        DashbordPane.setCenter(root);
    

    }

    private Stage getStage() {
        return (Stage) DashbordPane.getScene().getWindow();
    }
    
        private void loadScreen() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("DashboardAdmin.fxml"));
        } catch (IOException ex) {
            System.out.println("load error");
        }
        DashbordPane.getScene().setRoot(root);
    }

   

    
    
}
