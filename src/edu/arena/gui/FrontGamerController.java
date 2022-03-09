/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tarek
 */
public class FrontGamerController implements Initializable {

    @FXML
    private BorderPane DashbordPane;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnTeams;
    @FXML
    private JFXButton btnReclamationCategory;
    @FXML
    private JFXButton btnAvis;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void JeuxAction(ActionEvent event) {
                      loadUi("DashbordJeuxFront");

    }

    @FXML
    private void TournoisAction(ActionEvent event) {
                      loadUi("DashbordTournoisFront");

    }
       @FXML
    private void FreeGamesAction(ActionEvent event) {
        loadUi("DashbordFreeGamesFront");
    }
    
      private void loadUi(String ui) {
        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        DashbordPane.setCenter(root);
    

    }
      
      
        private Stage getStage() {
        return (Stage) DashbordPane.getScene().getWindow();
    }
    
        private void loadScreen() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FrontGamer.fxml"));
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
        DashbordPane.getScene().setRoot(root);
    }

    @FXML
    private void PostAction(ActionEvent event) {
        loadUi("PostFront");
    }

    @FXML
    private void Profile(ActionEvent event) {
         loadUi("UserProfile");
    }

    @FXML
    private void Equipe(ActionEvent event) {
         loadUi("EquipeFront");
    }

    @FXML
    private void Reclamation(ActionEvent event) {
         loadUi("ReclamationFrontOffice");
    }

    @FXML
    private void Avis(ActionEvent event) {
        
         loadUi("AvisFrontOffice");
    }

 
}
