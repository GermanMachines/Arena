/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.Services.CategoryReclamationService;
import edu.arena.entities.CategoryReclamation;
import edu.arena.entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DeathKnight
 */
public class ReclamationCategoryController implements Initializable {

    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField tfname;
    @FXML
    private TableView<CategoryReclamation> tvCategoryReclamation;
    @FXML
    private TableColumn<CategoryReclamation, Integer> colId;
    @FXML
    private TableColumn<CategoryReclamation, String> colName;
    @FXML
    private TextField tfId;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       try{
            showCategoryReclamation();
       }catch(SQLException ex){
           ex.printStackTrace();
       }
        
    }   

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        CategoryReclamationService crs = new CategoryReclamationService();
         if(event.getSource() == btnInsert){
         crs.ajouter(new CategoryReclamation(tfname.getText()));
         showCategoryReclamation();
        } 
         if(event.getSource() == btnUpdate){
             //control saisie
         crs.update(new CategoryReclamation(Integer.parseInt(tfId.getText()),tfname.getText()));
         showCategoryReclamation();
        } 
           if(event.getSource() == btnDelete){
             //control saisie
         crs.delete(Integer.parseInt(tfId.getText()));
         showCategoryReclamation();
        } 
     
    }
    

    public void showCategoryReclamation() throws SQLException{
         CategoryReclamationService crs = new CategoryReclamationService();
        ObservableList<CategoryReclamation> list =  crs.afficher();
        colId.setVisible(false);
        colId.setCellValueFactory(new PropertyValueFactory<CategoryReclamation,Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<CategoryReclamation,String>("nom"));
        tfId.setVisible(false);
        
        
        
     
        tvCategoryReclamation.setItems(list);
    }
    
    @FXML
    private void handleMouseAction(MouseEvent event) {
        CategoryReclamation rec = tvCategoryReclamation.getSelectionModel().getSelectedItem();
        System.out.println(rec.toString());
        tfname.setText(rec.getNom());
        tfId.setText(Integer.toString(rec.getId()));

    }

 
    
}
