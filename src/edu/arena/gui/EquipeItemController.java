/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Equipe;
import edu.arena.entities.Outils;
import edu.arena.entities.User;
import edu.arena.services.EquipeService;
import edu.arena.services.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class EquipeItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label score;
    @FXML
    private ImageView image;
    @FXML
    private Label Region;
    @FXML
    private Label tfid;
       @FXML
    private Label imagenom;
   
   
    int iduser = Outils.getCurrentSession();
   EquipeService e = new EquipeService();
    UserService us = new UserService();
    @FXML
    private Button add;
 void setData(int idEquipe, String nom, int Score, String region) {
        
        tfid.setText(String.valueOf(idEquipe));
        nameLabel.setText(nom);
        score.setText(String.valueOf(score));
         Region.setText(region);
         String imagename = us.getImage(iduser);
       // Image image = new Image(getClass().getResourceAsStream(jeux.getImageJeux()));
        //Image myImage = new Image(getClass().getResourceAsStream("/resources/"+ImageJeux+""));
      Image imagee= new Image(getClass().getResourceAsStream("/resources/"+imagename+""));
      image.setImage(imagee);

        

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         tfid.setVisible(false);
         imagenom.setVisible(false);
         score.setVisible(false);
    }    

    @FXML
    private void AddUser(ActionEvent event) throws SQLException {
         Equipe e;
        e = new Equipe(Integer.parseInt(tfid.getText()), nameLabel.getText(), imagenom.getText(), 222,  Region.getText() );
          System.out.println(e);

         int id =   Integer.parseInt(tfid.getText());
        System.out.println(id);
     User u =  us.findbyidcode(iduser);
        System.out.println(u);
                System.out.println(us.findEquipe(e));
    if (us.findEquipe(e) < 10 ) {
        
    u.setId_equipe(id);
     us.modifier3(u);
    //  JOptionPane.showMessageDialog(null, "Equipe added!");
      Notifications notificationBuilder = Notifications.create()
      .title("Alert").text("Equipe added successfullly").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER)
              .onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        System.out.println("clicked on");
              }});
      notificationBuilder.darkStyle();
notificationBuilder.show();
    }
             
             
              
//                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
//
//                     alert.setAlertType(Alert.AlertType.INFORMATION);
//                     alert.setTitle("Add Equipe");
//                     alert.setHeaderText("Results:");
//                     alert.setContentText("Equipe added successfully!");
              
   else {
          Notifications notificationBuilder = Notifications.create()
      .title("Alert").text("Equipe is full ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER)
              .onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        System.out.println("clicked on");
              }});
      notificationBuilder.darkStyle();
notificationBuilder.show();
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
    }
//
//        alert.setAlertType(Alert.AlertType.INFORMATION);
//                     alert.setTitle("Error");
//                     alert.setHeaderText("Error");
//                     alert.setContentText("Equipe is full!");}

       
       
        
       
    }
    
}
