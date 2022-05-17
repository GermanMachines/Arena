/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author tarek
 */
public class ItemFreeGameController implements Initializable {

    @FXML
    private Label actionfreegame;
    @FXML
    private Label titleFreeeGame;
    @FXML
    private ImageView img;
    @FXML
    private Label idFreeGame;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    void setData(int id, String title, String action) {
        
        idFreeGame.setText(String.valueOf(id));
        titleFreeeGame.setText(title);
        actionfreegame.setText(action);


       Image image = new Image(getClass().getResourceAsStream("/resources/Match Day-SavetheDate-v1.png"));
        img.setImage(image);
        

    }

    @FXML
    private void ViewFreeGame(ActionEvent event) {
    }
    
}
