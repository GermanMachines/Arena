/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.jfoenix.controls.JFXTextField;
import edu.arena.entities.MatchEquipe;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tarek
 */
public class ItemMatchController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private ImageView img;
    @FXML
    private Label DateLabel;
    @FXML
    private Label tfIdMatch;
    MatchEquipe m = new MatchEquipe();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setData(int idMatch, String dateMatch, String reference) {
        
        tfIdMatch.setText(String.valueOf(idMatch));
        nameLabel.setText(reference);
        DateLabel.setText(dateMatch);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        // System.out.println(dtf.format(now));   
       // Image image = new Image(getClass().getResourceAsStream(jeux.getImageJeux()));
        //Image myImage = new Image(getClass().getResourceAsStream("/resources/"+ImageJeux+""));
       Image image = new Image(getClass().getResourceAsStream("/resources/Match Day-SavetheDate-v1.png"));
        img.setImage(image);
        

    }

    @FXML
    private void ViewEquipesBtn(ActionEvent event) {
       
         m.setTest(Integer.parseInt(tfIdMatch.getText()));
                   try {
              Parent root = FXMLLoader.load(getClass().getResource("EquipeMatchFront.fxml"));
          Stage stage = new Stage();
     
          stage.setTitle("Equipes");
                    stage.setScene(new Scene(root)); 
                    stage.show();
     
        } catch (IOException ex) {
               System.out.println("can't load new Match Equipes window");
        }
    }
    
}
