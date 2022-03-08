/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Outils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class LoginJController implements Initializable {

    @FXML
    private Button btnprofile;
    @FXML
    private Button equipee;
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
    private void profil(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("UserProfile.fxml"));
                    try {
                        Parent root = loader.load();
                        lblmenu.getScene().setRoot(root);

                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
    }

    @FXML
    private void equipe(ActionEvent event) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EquipeFront.fxml"));
                    try {
                        Parent root = loader.load();
                        lblmenu.getScene().setRoot(root);

                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
    }

    @FXML
    private void logout(ActionEvent event) throws Exception {
        Outils.close();
          try{
            lblmenu.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/Login.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
