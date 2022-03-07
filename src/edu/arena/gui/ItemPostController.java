/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Comentaire;
import edu.arena.entities.Post;
import edu.arena.entities.rate;
import edu.arena.services.ComentaireCrud;
import edu.arena.services.PostCrud;
import edu.arena.services.RateCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ItemPostController implements Initializable {

    @FXML
    private Label titreLabel;
    @FXML
    private Label auteurlabel;
    @FXML
    private Label datapostlabel;
    @FXML
    private ImageView img;
    @FXML
    private Label tfidpost;
  
    Post k = new  Post();
    @FXML
    private Label conteur;
    @FXML
    private Rating PostRarting;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    

    void setData(int id_post, String titre, String auteur, String date_post,String img_post) {
    titreLabel.setText(titre);
    auteurlabel.setText(auteur);
    datapostlabel.setText(date_post);
    Image imgp = new Image(getClass().getResourceAsStream("/edu/arena/image/"+img_post+""));
    img.setImage(imgp);
    tfidpost.setText(String.valueOf(id_post));
                                  
    }

    @FXML
    private void comentpostbtn(ActionEvent event) {
        k.setTest(Integer.parseInt(tfidpost.getText()));
                   try {
              Parent root = FXMLLoader.load(getClass().getResource("Viewcoment.fxml"));
          Stage stage = new Stage();
     
          stage.setTitle("comentaire");
                    stage.setScene(new Scene(root)); 
                    stage.show();
     
        } catch (IOException ex) {
               System.out.println("can't load comrnt window");
        }
    }

 

    
    private void submitRating(ActionEvent event) {
//        System.out.println("voici le rating"+PostRarting.getRating());





    }

    @FXML
    private void like(ActionEvent event) throws SQLException {
         k.setTest(Integer.parseInt(tfidpost.getText()));
                  RateCrud cer = new RateCrud();
      
    
      
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
   
             
           
      
               rate c = new rate( (int) PostRarting.getRating(),Post.getTest());


       
               cer.ajouter(c);   
    }
    }
    
