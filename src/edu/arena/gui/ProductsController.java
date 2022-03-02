/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.Services.ProduitService;
import edu.arena.entities.Produit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author DeathKnight
 */
public class ProductsController implements Initializable {

    @FXML
    private TextField tfName;
    @FXML
    private Button btnInsert;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        ProduitService ps = new ProduitService();
        if(event.getSource() == btnInsert){
            ps.ajouter(new Produit(tfName.getText()));
        }
    }
    
}
