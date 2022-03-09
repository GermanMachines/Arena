/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Jeux;
import edu.arena.services.JeuxCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tarek
 */
public class DashbordJeuxFrontController implements Initializable {

    @FXML
    private VBox ChosenGameCard;
    @FXML
    private Label GameNameLabel;
    @FXML
    private ImageView GameImage;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    
    private MyListener myListener;
    
  
    private Image image; 
        public ObservableList<Jeux> data = FXCollections.observableArrayList();
        
        JeuxCrud jcr = new JeuxCrud();
        
       Jeux j = new Jeux();
             
             
  private List<Jeux> Jeux= new ArrayList<>();
    

    
    
    private void setChoseGame(Jeux jeux){
       
        GameNameLabel.setText(jeux.getNomJeux());
        
         j.setTest(jeux.getNomJeux());
        
        image = new Image(getClass().getResourceAsStream(jeux.getImageJeux()));
        
        GameImage.setImage(image);
        
        ChosenGameCard.setStyle(" -fx-background-color:#81171B;\n"+
                    "-fx-background-radius:30;");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    
        
        
              data.addAll(jcr.readAll());
        Jeux.addAll(getData());
        // Jeux.addAll(jcr.readAll());
   
        //System.out.println(data.size());
        
        
        if(Jeux.size()>0){
          //  setChoseGame(data.get(0).getNomJeux(),"");
              setChoseGame(Jeux.get(1));
                  myListener = new MyListener(){

                  @Override
                  public void onClickListener(int idjeux) {
                      
                     setChoseGame(Jeux.get(idjeux));
                  }
        
        };
        }
        
        
        int column=0;
        int row=1;
           try {
        for(int i=0 ; i<Jeux.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/edu/arena/gui/ItemGame.fxml"));
         
                AnchorPane anchorPane = fxmlLoader.load();
          
            
            ItemGameController itemController = fxmlLoader.getController();
            itemController.setData(data.get(i).getIdJeux(),data.get(i).getNomJeux(),data.get(i).getImageJeux(),myListener);
            if(column == 2){
                column=0;
                row++;
            }
            grid.add(anchorPane, column++, row); //Child , column , row
            
            //Set Item Grid Width
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);
            
            
               //Set Item Grid Height
            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);
            
            
            
            
            GridPane.setMargin(anchorPane,new Insets(16));
              }
           }
           catch (IOException ex) {
                ex.printStackTrace();
            }
              
              
        }
        
        public List<Jeux> getData(){
         List<Jeux> Jeux= new ArrayList<>();
         Jeux jeux;
         for (int i=0;i<data.size();i++){
             jeux = new Jeux();  
             jeux.setIdJeux(data.get(i).getIdJeux());
             jeux.setNomJeux(data.get(i).getNomJeux());

             jeux.setImageJeux("/resources/"+data.get(i).getImageJeux()+"");
             Jeux.add(jeux);
         }
         return Jeux;
    }

    @FXML
    private void ViewTournametBtn(ActionEvent event) throws SQLException {
        
           try {
              Parent root = FXMLLoader.load(getClass().getResource("TournoisFront.fxml"));
          Stage stage = new Stage();
     
          stage.setTitle("Tournois");
                    stage.setScene(new Scene(root)); 
                    stage.show();
     
        } catch (IOException ex) {
               System.out.println("can't load new window");
        }
    }
        
   }    
    

