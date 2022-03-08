/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Outils;
import static edu.arena.entities.Outils.EmailUser;
import edu.arena.entities.User;
import edu.arena.services.UserService;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ForgotController implements Initializable {

    @FXML
    private Button tfmaill;
    @FXML
    private TextField tfemaill;
    @FXML
    private Button btnverif;
    @FXML
    private TextField tfcode;
    
  UserService e = new UserService();
    @FXML
    private TextField tfmdp;
    @FXML
    private TextField tfmdp1;

 
  



  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              


    }    

    @FXML
    private void SendMaill(ActionEvent event) throws IOException {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                e.envoyerCodeVerif(tfemaill.getText());
                String  c =  tfemaill.getText();
                
   //Outils.setEmailUser(c);
   //System.out.println(Outils.getEmailUser());
    }

    @FXML
    private void Onclick(ActionEvent event) throws IOException {
               if ( e.getRandomCode() ==  Integer.parseInt(tfcode.getText() ) ){
                   FXMLLoader loader = new FXMLLoader();
                 loader.setLocation(getClass().getResource("NewMdp.fxml"));
                    Parent root = loader.load();
                    
                    NewMdpController controller = loader.getController();
                    controller.setU(e.findUsertbymail(tfemaill.getText()));
                    
                     tfcode.getScene().setRoot(root);

               } 
                else {
                   JOptionPane.showMessageDialog(null, "Ce code est incorrecte");
               } 
                   
               }

  



                
                
                
        
        
    }
    
    
