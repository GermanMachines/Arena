/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Equipe;
import edu.arena.entities.Outils;
import edu.arena.Services.EquipeService;
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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class EquipeFrontController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
 public ObservableList<Equipe> Matchdata = FXCollections.observableArrayList();
                EquipeService mtc = new EquipeService();
                  private List<Equipe> Match= new ArrayList<>();
                  
                   int iduser = Outils.getCurrentSession();
    @FXML
    private Button quit;
    @FXML
    private TextField recherche;
    @FXML
    private Button profil;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(iduser);
        try {
            // TODO
            Matchdata.addAll(mtc.afficher());
        
        
        } catch (SQLException ex) {
System.out.println(ex.getMessage());        }
        
          int column=0;
        int row=1;
           try {
        for(int i=0 ; i<Matchdata.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/edu/arena/gui/EquipeItem.fxml"));
         
                AnchorPane anchorPane = fxmlLoader.load();
          
         
            EquipeItemController itemController = fxmlLoader.getController();
            itemController.setData(Matchdata.get(i).getIdEquipe(),Matchdata.get(i).getNom(),Matchdata.get(i).getLogo(),Matchdata.get(i).getScore(),Matchdata.get(i).getRegion());
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
            
            
            
            
            GridPane.setMargin(anchorPane,new Insets(40));
              }
           }
           catch (IOException ex) {
                ex.printStackTrace();
            }
              
    }    

    @FXML
    private void quitter(ActionEvent event) {
          try{
            quit.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/Loginj.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    @FXML
    private void myprofile(ActionEvent event) {
          try{
            quit.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/UserProfile.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
