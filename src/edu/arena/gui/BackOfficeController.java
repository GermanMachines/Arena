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
public class BackOfficeController implements Initializable {

    @FXML
    private Label exit;
    @FXML
    private Button btnReclamation;
    @FXML
    private Button btnProducts;
    @FXML
    private Button btnAvis;
    @FXML
    private StackPane contentArea;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnReclamationCategory;
    @FXML
    private Button btnEmail;
    @FXML
    private Button btnUsers;
    @FXML
    private Button btnTeams;
    @FXML
    private Button btnpost;
    @FXML
    private Button btncomm;
    @FXML
    private Button btnjeux;
    @FXML
    private Button btntournois;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnProducts1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        exit.setOnMouseClicked(e -> {
            System.exit(0);
        });
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("HomeFXML.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void Home(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("HomeFXML.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void reclamationCategory(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("ReclamationCategory.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void products(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Products.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void avis(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Avis.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void email(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Email.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void users(ActionEvent event) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void Teams(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("DashboardEquipe.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void post(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("PostBack.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void commentaire(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("ComBack.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void jeux(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("DashbordJeux.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void tournois(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("DashbordTournois.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void productsCat(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("productcat.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void orders(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("orders.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

   
 

}
