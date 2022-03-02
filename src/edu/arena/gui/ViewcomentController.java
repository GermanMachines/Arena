/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.jfoenix.controls.JFXTextField;
import edu.arena.entities.Comentaire;
import edu.arena.entities.Post;
import edu.arena.services.ComentaireCrud;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;



/**
 * FXML Controller class
 *
 * @author Lenovo
 */

public class ViewcomentController implements Initializable {

    @FXML
    private JFXTextField tfcomment;
    @FXML
    private JFXTextField tfid_user;
         Post p = new Post();

public int getTxtIdUser(){
    return Integer.parseInt(tfid_user.getText());
            }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addcom(ActionEvent event) throws SQLException {
        ComentaireCrud cer = new ComentaireCrud();
        int id_user=getTxtIdUser();
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
    // p.getTest();
               Comentaire c = new Comentaire(id_user,tfcomment.getText(),"2022-12-12",p.getTest());

       cer.ajouter(c);
        
          
          
   
    }
    
}
