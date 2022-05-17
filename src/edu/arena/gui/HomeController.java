/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.jfoenix.controls.JFXButton;
import edu.arena.entities.Outils;
import edu.arena.services.UserService;
import edu.arena.entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author DeathKnight
 */
public class HomeController implements Initializable {

    private Button createBtn;
    private Label etat;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private JFXButton btnlog;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Working Directory = " + System.getProperty("user.dir") );
    }    

    private void handleButtonAction(ActionEvent event) throws IOException {
        if(event.getSource() == createBtn){
         // try{
            UserService us = new UserService();
           // us.ajouter(new User(tfname.getText()));
            etat.setText("Added successfuly");
            etat.setTextFill(Paint.valueOf("#00FF00"));
            etat.setVisible(true);
            
            
        /*  }catch(SQLException ex){
              Alert alert = new Alert(AlertType.ERROR);
              alert.setContentText(ex.getMessage());
              etat.setText("couldn't add user");
              etat.setTextFill(Paint.valueOf("#FF0000"));
              etat.setVisible(true);
              
              alert.showAndWait();
          }
        }
    
*/
          }
    }

      public void switchToReclamationCategoryScene(ActionEvent event) throws IOException{
         
        root = FXMLLoader.load(getClass().getResource("ReclamationCategory.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void logout(ActionEvent event) throws Exception {
         Outils.close();
          try{
            btnlog.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("../gui/Login.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

   
}
