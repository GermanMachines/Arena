/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Equipe;
import edu.arena.entities.Jeux;
import edu.arena.entities.Match;
import edu.arena.entities.MatchEquipe;
import edu.arena.entities.Tournois;
import edu.arena.services.JeuxCrud;
import edu.arena.services.MatchEquipeCrud;
import edu.arena.services.TournoisCrud;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author tarek
 */
public class EquipeMatchFrontController implements Initializable {

    @FXML
    private TableColumn<MatchEquipe, String> colNomEquipe;
    @FXML
    private TableColumn<MatchEquipe, String> colRegionEq;
    @FXML
    private TableColumn<MatchEquipe, Integer> colScoreEq;
    @FXML
    private TableView<MatchEquipe> tvEquipes;

    public ObservableList<MatchEquipe> dataEquipes = FXCollections.observableArrayList();
//    
//     TournoisCrud jcr = new TournoisCrud();
//    JeuxCrud jc = new JeuxCrud();
//    Jeux j = new Jeux();
//    Match  m = new Match();
    MatchEquipeCrud me = new MatchEquipeCrud();
    MatchEquipe idm = new MatchEquipe();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dataEquipes.addAll(me.readAllbyMatch(idm.getTest()));
        System.out.println(dataEquipes);
         colNomEquipe.setCellValueFactory(new PropertyValueFactory<>("nomequipe"));
         colRegionEq.setCellValueFactory(new PropertyValueFactory<>("region"));
         colScoreEq.setCellValueFactory(new PropertyValueFactory<>("score"));


          tvEquipes.setItems(dataEquipes);
        
        
        
        
        
        
    }    

    @FXML
    private void handleMouseAction(MouseEvent event) {
    }
    
}
