/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class HomeController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Home(MouseEvent event) throws IOException {
      bp.setCenter(ap);
         loadPage("PostFront");
    }

    @FXML
    private void PostBack(MouseEvent event) throws IOException {
        loadPage("PostBack");
    }

    @FXML
    private void COMBAack(MouseEvent event) throws IOException {
         loadPage("ComBack");
    }
    
    
    private void loadPage(String page) throws IOException{
       Parent root = null;
       root = FXMLLoader.load(getClass().getResource(page+".fxml"));
       bp.setCenter(root);
    }
    
}
