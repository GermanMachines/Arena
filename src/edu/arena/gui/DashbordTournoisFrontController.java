/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Jeux;
import edu.arena.entities.Match;
import edu.arena.services.JeuxCrud;
import edu.arena.services.MatchCrud;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author tarek
 */
public class DashbordTournoisFrontController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
        public ObservableList<Match> Matchdata = FXCollections.observableArrayList();
                MatchCrud mtc = new MatchCrud();
                  private List<Match> Match= new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
          
        
        
              Matchdata.addAll(mtc.readAll());

        //Match.addAll(getData());
        // Jeux.addAll(jcr.readAll());
   
        //System.out.println(data.size());
        
        int column=0;
        int row=1;
           try {
        for(int i=0 ; i<Matchdata.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/edu/arena/gui/ItemMatch.fxml"));
         
                AnchorPane anchorPane = fxmlLoader.load();
          
         
            ItemMatchController itemController = fxmlLoader.getController();
            itemController.setData(Matchdata.get(i).getIdMatch(),Matchdata.get(i).getDateMatch(),Matchdata.get(i).getReference());
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
            
            
            
            
            GridPane.setMargin(anchorPane,new Insets(60));
              }
           }
           catch (IOException ex) {
                ex.printStackTrace();
            }
              
              
                
    }    
    
}
