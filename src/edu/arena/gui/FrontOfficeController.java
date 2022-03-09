/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author DeathKnight
 */
public class FrontOfficeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label exit;
    @FXML
    private Button btnAvis;
    @FXML
    private StackPane contentArea;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnReclamationCategory;
    @FXML
    private Button btnProfile;
    @FXML
    private Button btnTeams;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             exit.setOnMouseClicked(e -> {
            System.exit(0);
        });
        try{
            Parent fxml = FXMLLoader.load(getClass().getResource("HomeFXML.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }    

    @FXML
    private void home(ActionEvent event) throws IOException {
            Parent fxml = FXMLLoader.load(getClass().getResource("HomeFXML.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
    }



    @FXML
    private void avis(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("AvisFrontOffice.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void ajouterReclamation(ActionEvent event) throws IOException {
            Parent fxml = FXMLLoader.load(getClass().getResource("ReclamationFrontOffice.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void profile(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("UserProfile.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void teams(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("EquipeFront.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
    }
    
}
