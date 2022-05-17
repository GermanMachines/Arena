/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import edu.arena.entities.Outils;
import edu.arena.entities.User;
import edu.arena.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class LoginJController implements Initializable {

    @FXML
    private Label lblmenu;
    @FXML
    private JFXPasswordField tfancien;
    @FXML
    private JFXPasswordField tfnouveau;
    @FXML
    private JFXPasswordField tfconfirm;
    @FXML
    private JFXButton btnchange;
int iduser = Outils.getCurrentSession();
    UserService us = new UserService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }    

    private void profil(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("UserProfile.fxml"));
                    try {
                        Parent root = loader.load();
                        lblmenu.getScene().setRoot(root);

                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
    }

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
            Parent root =FXMLLoader.load(getClass().getResource("../gui/Login.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void changer(ActionEvent event) throws SQLException {
           User u = us.findbyidcode(iduser);
           String mdp = tfancien.getText();
            mdp = UserService.encrypt(mdp);
         
         if(  mdp.equals(u.getMdp()) )
         { 
                  us.modifierMotDePasse(u, tfnouveau.getText(), tfconfirm.getText());
         }
         else { Notifications notificationBuilder = Notifications.create()
      .title("Alert").text("ancien mot de passe incorrecte").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.TOP_CENTER)
              .onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        System.out.println("clicked on");
              }});
      notificationBuilder.darkStyle();
notificationBuilder.show();
         }
        
    }
    
}
