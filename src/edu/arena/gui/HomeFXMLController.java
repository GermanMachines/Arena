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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DeathKnight
 */
public class HomeFXMLController implements Initializable {

    @FXML
    private Button createBtn;
    @FXML
    private TextField tfname;
    @FXML
    private Label etat;
    @FXML
    private Button btnProduct;
    @FXML
    private Button btnReclamationCategory;
    @FXML
    private Button btnReclamation;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnTournoi;

    private Stage stage;
    private Parent root;
    private Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
      public void switchToReclamationCategoryScene(ActionEvent event) throws IOException{
         
        root = FXMLLoader.load(getClass().getResource("ReclamationCategory.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }
}
