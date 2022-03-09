/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import edu.arena.entities.Outils;
import edu.arena.entities.User;
import edu.arena.services.UserService;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfmdp;
    @FXML
    private Button btnlogin;
    @FXML
    private Button btnForgot;
    private int count = 0;
    @FXML
    private Button btnSign;
    @FXML
    private JFXCheckBox checkmdp;
    @FXML
    private JFXTextField tfmdp1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
     private void delay() throws InterruptedException{
        
        Thread.sleep(5000);

    };

    /*
     */
    @FXML
    private void LoginAction(ActionEvent event) throws InterruptedException {
        count++;
        String nom;
        nom = tfnom.getText();
        if (UserService.existe(nom) && "non".equals(UserService.getBlock(nom)) )  {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succes");

            String mdp = tfmdp.getText();
            mdp = UserService.encrypt(mdp);
            if (UserService.CompteExiste(nom, mdp) != 0) {
                int id = UserService.CompteExiste(nom, mdp);
                 Outils.start(id);
                    System.out.println(Outils.getCurrentSession());

                if ("admin".equals(UserService.getRole(nom))) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("BackOffice.fxml"));
                    try {
                        Parent root = loader.load();
                        tfnom.getScene().setRoot(root);

                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontGamer.fxml"));
                    try {
                        Parent root = loader.load();
                        tfnom.getScene().setRoot(root);

                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ce mot de passe est incorrecte");
                if (count % 3 == 0) {
delay();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ce nom d'utilisateur n'existe pas!");
        }
    }

    @FXML
    private void SignInAction(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
            Parent root = loader.load();
            tfnom.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.getMessage();
        }
        
    }

    @FXML
    private void forgot(ActionEvent event) throws IOException {
        URL fxURL = getClass().getResource("Forgot.fxml");
                    FXMLLoader loader = new FXMLLoader(fxURL);
                    Parent root = loader.load();
                    btnForgot.getScene().setRoot(root);

    }

    @FXML
    private void see(ActionEvent event) {
        if(checkmdp.isSelected()){
            tfmdp1.setText(tfmdp.getText());
            tfmdp1.setVisible(true);
            tfmdp.setVisible(false);
          return;  
        }  tfmdp.setText(tfmdp1.getText());
            tfmdp.setVisible(true);
            tfmdp1.setVisible(false); 
        
    }
    
    

}
