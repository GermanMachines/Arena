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
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class LoginAdminController implements Initializable {

    @FXML
    private Label lblmenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void equipe(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardEquipe.fxml"));
                    try {
                        Parent root = loader.load();
                        lblmenu.getScene().setRoot(root);

                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
    }

    @FXML
    private void users(ActionEvent event) {
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDashboard.fxml"));
                    try {
                        Parent root = loader.load();
                        lblmenu.getScene().setRoot(root);

                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
    }
    
}
