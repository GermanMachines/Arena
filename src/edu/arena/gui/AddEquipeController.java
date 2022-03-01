/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Equipe;
import edu.arena.services.EquipeService;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AddEquipeController implements Initializable {

    @FXML
    private TextField tfLogoEq;
    @FXML
    private TextField tfScoreEq;
    @FXML
    private TextField tfRegionEq;
    @FXML
    private TextField tfNomeq;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterEquipe(ActionEvent event) {
        String nom = tfNomeq.getText();
        String logo = tfLogoEq.getText();
        int Score = parseInt(tfScoreEq.getText());
        String Region = tfRegionEq.getText();
        Equipe e = new Equipe(nom,logo,Score,Region);
        EquipeService sp = new EquipeService();
        Alert alert = new Alert(AlertType.INFORMATION);
         try {
             sp.ajouter(e);
             alert.setTitle("Succes");
             alert.setHeaderText("Ajoutée");
             alert.setContentText("equipe ajoutée");
          } catch (SQLException ex) {
              alert.setAlertType(AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Erreur d'ajour");
             alert.setContentText(ex.getMessage());
        } finally {alert.showAndWait();  }
       
        
    }
    
}
