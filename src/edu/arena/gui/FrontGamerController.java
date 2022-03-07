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
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class FrontGamerController implements Initializable {

    @FXML
    private BorderPane DashbordPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void HomeAction(ActionEvent event) throws IOException {
           loadPage("Home");
    }
    @FXML
    private void PostsAction(ActionEvent event) throws IOException {
        loadPage("PostFront");
    }

      private void loadPage(String page) throws IOException{
       Parent root = null;
       root = FXMLLoader.load(getClass().getResource(page+".fxml"));
       DashbordPane.setCenter(root);
    }

    
    
}
