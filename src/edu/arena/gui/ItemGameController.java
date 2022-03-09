/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Jeux;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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

 
    
}
