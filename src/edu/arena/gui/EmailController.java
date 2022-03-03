/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Email;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author DeathKnight
 */
public class EmailController implements Initializable {

    @FXML
    private TextField tfAdresse;
    @FXML
    private TextArea taMessage;
    @FXML
    private TextField tfSubject;
    @FXML
    private Button btnSend;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void send(ActionEvent event) {
        Email email = new Email();
        try{
            Email.sendEmail(tfAdresse.getText(),"Arena Administration", tfSubject.getText());
              Alert a = new Alert(Alert.AlertType.NONE);
             a.setAlertType(Alert.AlertType.INFORMATION);
             a.setContentText("Send successfully");
        }catch(Exception e){
               Alert a = new Alert(Alert.AlertType.NONE);
             a.setAlertType(Alert.AlertType.ERROR);
             a.setContentText("Error could not send the email , try again later !");
        }
    }
    
}
