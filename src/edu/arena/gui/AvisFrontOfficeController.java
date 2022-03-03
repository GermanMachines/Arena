/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.Services.ProduitService;
import edu.arena.entities.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DeathKnight
 */
public class AvisFrontOfficeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage stage;
    private Scene scene;
    Parent root;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    //    Event event = null;
    
        
    }

    @FXML
    private void load(ContextMenuEvent event) {
            ProduitService ps = new ProduitService();
        try{
           root = FXMLLoader.load(getClass().getResource("avisFrontOffice.fxml"));
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            ObservableList<Produit>  list = ps.afficher();
            Pane pane = new Pane();
            list.stream().forEach(p -> {
                Label l = new Label(p.getNom());
                pane.getChildren().add(l);
                
            });
            
          //  scene = new Scene(root)
           //        Parent p = pane.getScene();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
}
