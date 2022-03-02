/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.jfoenix.controls.JFXTextField;
import edu.arena.entities.Jeux;
import edu.arena.entities.JeuxTournois;
import edu.arena.entities.Match;
import edu.arena.entities.Tournois;
import edu.arena.services.JeuxCrud;
import edu.arena.services.JeuxTournoisCrud;
import edu.arena.services.TournoisCrud;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author tarek
 */
public class TournoisFrontController implements Initializable {

    @FXML
    private TableView<Tournois> tvTournoix;
    @FXML
    private TableColumn<Tournois, Integer> colIdJeuxTournois;
    @FXML
    private TableColumn<Tournois, Integer> colidTournois;
    @FXML
    private TableColumn<Tournois, String> colTitreTournois;
    @FXML
    private TableColumn<Tournois, String> colDatedebutTournois;
    @FXML
    private TableColumn<Tournois, String> colDatefinTournois;
    @FXML
    private TableColumn<Tournois, String> colDescriptionTournois;
    @FXML
    private TableColumn<Tournois, String> colTypeTournois;
    @FXML
    private TableColumn<Tournois, Integer> colnbParticipantsTournois;
    @FXML
    private TableColumn<Tournois, String> ColWinner;
    @FXML
    private TableColumn<Tournois, String> ColStatus;
    public ObservableList<Tournois> data = FXCollections.observableArrayList();
    TournoisCrud jcr = new TournoisCrud();
    JeuxCrud jc = new JeuxCrud();
    Jeux j = new Jeux();
    @FXML
    private JFXTextField tfIdEquipe;
           
    
      public int getTxtIdEquipe() {
        return Integer.parseInt(tfIdEquipe.getText());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            // TODO
//
//            System.out.println(jc.getidJeuxbynom(j.getTest()));
//        } catch (SQLException ex) {
//            System.out.println("invalide");
//        }
         try {
        
            data.addAll(jcr.readAllbyJeux(jc.getidJeuxbynom(j.getTest())));
        } catch (SQLException ex) {
                        System.out.println(ex.getMessage());

        }
      
      
       
         colidTournois.setCellValueFactory(new PropertyValueFactory<>("IdTournois"));
         colTitreTournois.setCellValueFactory(new PropertyValueFactory<>("Titre"));
         colDatedebutTournois.setCellValueFactory(new PropertyValueFactory<>("Date_debut"));
         colDatefinTournois.setCellValueFactory(new PropertyValueFactory<>("Date_fin"));        
         colDescriptionTournois.setCellValueFactory(new PropertyValueFactory<>("DescriptionTournois"));
         colTypeTournois.setCellValueFactory(new PropertyValueFactory<>("Type"));  
         colnbParticipantsTournois.setCellValueFactory(new PropertyValueFactory<>("NbrParticipants"));
         colIdJeuxTournois.setCellValueFactory(new PropertyValueFactory<>("IdJeux"));
         ColWinner.setCellValueFactory(new PropertyValueFactory<>("Winner"));
         ColStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

        tvTournoix.setItems(data);
        
        
    
        
        
        
    }    

    @FXML
    private void handleMouseAction(MouseEvent event) {
        
    }

    @FXML
    private void ParticiperTournois(ActionEvent event){
        
           Tournois tournois = tvTournoix.getSelectionModel().getSelectedItem();
               int idtournois= tournois.getIdTournois();

        JeuxTournoisCrud cer = new JeuxTournoisCrud();
        int Equipeid=getTxtIdEquipe();
        
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          
         int res = cer.VerifParticipation(Equipeid, idtournois);
          
          if(res != 0){
              //Alert Equipe
              alert.setAlertType(Alert.AlertType.WARNING);
              alert.setTitle("Equipe");
              alert.setHeaderText(null);
              alert.setContentText("This Equipe Already Part of the Tournament!");
              alert.showAndWait();
              //Alert Equipe!
              
          }else{
              JeuxTournois ce = new JeuxTournois(Equipeid, idtournois);
              cer.ajouter(ce); //Alert Error jeux :
              //Alert Error jeux !
              alert.setAlertType(Alert.AlertType.INFORMATION);
              alert.setTitle("Participation");
              alert.setHeaderText("Results:");
              alert.setContentText("You Have Participated successfully!");
              alert.showAndWait();
              
          }
    }
    
    
    
    
}
