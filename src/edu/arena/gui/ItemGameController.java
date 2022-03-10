/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.arena.Services.AvisService;
import edu.arena.entities.Avis;
import edu.arena.entities.Jeux;
import edu.arena.entities.Outils;
import edu.arena.services.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author tarek
 */
public class ItemGameController{

    @FXML
    private Label nameLabel;
    @FXML
    private ImageView img;
    @FXML
    private Rating rating;
    
    
    int idUserr = Outils.getCurrentSession();
    UserService us = new UserService();
    @FXML
    private JFXTextField tfCommentaire;
    @FXML
    private JFXButton sendBtn;
    
       @FXML
    private void ClickedItem(MouseEvent mouseEvent) {

        myListener.onClickListener(idjeux);

    }
    
    
    
    public int idjeux;
    
    private MyListener myListener;

    
    private  Jeux jeux;
    public void setData(int id , String NomJeux , String ImageJeux , MyListener myListener){
//       this.jeux.setNomJeux(NomJeux);
     //   this.jeux.setImageJeux(ImageJeux);
       idjeux=id;
       this.myListener=myListener;
        nameLabel.setText(NomJeux);
       // Image image = new Image(getClass().getResourceAsStream(jeux.getImageJeux()));
        Image myImage = new Image(getClass().getResourceAsStream("/resources/"+ImageJeux+""));
       //Image image = new Image(getClass().getResourceAsStream("/resources/fifa2022.png"));
        img.setImage(myImage);

        
    } 
     public void sendAvis(Rating r, TextField commentaire) throws SQLException{
            AvisService as = new AvisService();
            Avis a = new Avis();
            a.setScore((int)r.getRating());
            a.setCommentaire(commentaire.getText());
            a.setIdUser(idUserr);
            a.setIdJeux(idjeux);
            
            System.out.println("send avis method "+a.toString());
                as.ajouter(a);
                             
            
    }

    @FXML
    private void ActionButtonClick(ActionEvent event) throws SQLException {
        if(event.getSource() == sendBtn){ 
        String error = controlSaisie((int)rating.getRating(),tfCommentaire.getText());
              if(error == ""){
             Alert a = new Alert(Alert.AlertType.INFORMATION);
             
             try{
                        sendAvis(rating,tfCommentaire);
                          a.setContentText("Sent Successfully");
                           a.show();
                    }catch(SQLException ex){
                          a.setContentText("error");
                           a.show();
                        ex.printStackTrace();
                    }
            
           
             
         }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
             a.setContentText(error);
             a.show();
         }
        }
                     
        }
         public String controlSaisie(int score,String comment){
    
             String error = "";
             if((comment.equals("") || score == 0 )){
                 return "You have an empty field !";
             }            
              return error;
         }
    
 
    
}
