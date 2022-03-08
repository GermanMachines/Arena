/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Equipe;
import edu.arena.services.EquipeService;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
        Equipe e = new Equipe(nom, logo, Score, Region);
        EquipeService sp = new EquipeService();
        Alert alert = new Alert(AlertType.INFORMATION);
        try {
            sp.ajouter(e);
            alert.setTitle("Succes");
            alert.setHeaderText("Ajoutée");
            alert.setContentText("equipe ajoutée");
            alert.setOnCloseRequest((evn) -> {
                try {
                    URL fxURL = getClass().getResource("showEquipe.fxml");
                    FXMLLoader loader = new FXMLLoader(fxURL);
                    Parent root = loader.load();
               //     ShowEquipeController sec = loader.getController();
               //     sec.setNom("Nom :" + tfNomeq.getText());
             //       sec.setLogo("Logo :" + tfLogoEq.getText());
                //    sec.setRegion("Region :" + tfRegionEq.getText());

                    tfNomeq.getScene().setRoot(root);
                    tfLogoEq.getScene().setRoot(root);
                    tfRegionEq.getScene().setRoot(root);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });
        } catch (SQLException ex) {
            alert.setAlertType(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Erreur d'ajour");
            alert.setContentText(ex.getMessage());
        } finally {
            alert.showAndWait();
        }

    }

}
