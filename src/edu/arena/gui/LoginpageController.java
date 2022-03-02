/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tarek
 */
public class LoginpageController implements Initializable {

    @FXML
    private JFXTextField txtusername;
    @FXML
    private JFXPasswordField txtpassword;
    @FXML
    private JFXButton Singup;
    @FXML
    private JFXButton forgotBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LoginAction(ActionEvent event) {
        
        
        try {


                    Parent root = FXMLLoader.load(getClass().getResource("FrontGamer.fxml"));
                    Scene scene = new Scene(root, 1100, 650);
                    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    appStage.setScene(scene);
                    appStage.show();

                } catch (IOException ex) {
                }
        
        
        
        
        
        
    }

    @FXML
    private void Singup(ActionEvent event) {
        
          try {
           Parent root = FXMLLoader.load(getClass().getResource("DashbordAdmin.fxml"));
                    Scene scene = new Scene(root, 1100, 650);
                    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    appStage.setScene(scene);
                    appStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginpageController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        
        
        
    }

    @FXML
    private void Forgot(ActionEvent event) {
    }
    
}
