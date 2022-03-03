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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.regex.Pattern.CASE_INSENSITIVE;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
    @FXML
    private Label lID;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       try{
           tfId.setVisible(false);
           lID.setVisible(false);
            showCategoryReclamation();
       }catch(SQLException ex){
           ex.printStackTrace();
       }
        
    }   

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        CategoryReclamationService crs = new CategoryReclamationService();
         if(event.getSource() == btnInsert){
        
         String error = controlSaisie();
           if(error == ""){
             Alert a = new Alert(AlertType.INFORMATION);
             crs.ajouter(new CategoryReclamation(tfname.getText()));
             a.setContentText("Added Successfully");
             a.show();
         }else{
              Alert a = new Alert(AlertType.ERROR);
             a.setContentText(error);
             a.show();
         }
         showCategoryReclamation();
        } 
         if(event.getSource() == btnUpdate){
         crs.update(new CategoryReclamation(Integer.parseInt(tfId.getText()),tfname.getText()));
       
          String error = controlSaisie();
           if(error == ""){
             Alert a = new Alert(AlertType.INFORMATION);
             crs.update(new CategoryReclamation(Integer.parseInt(tfId.getText()),tfname.getText()));
             a.setContentText("Added Successfully");
             a.show();
         }else{
              Alert a = new Alert(AlertType.ERROR);
             a.setContentText(error);
             a.show();
         }
         showCategoryReclamation();
         
         
         
        } 
           if(event.getSource() == btnDelete){
             //control saisie
        try{
             crs.delete(Integer.parseInt(tfId.getText()));
              Alert a = new Alert(AlertType.INFORMATION);
             crs.update(new CategoryReclamation(Integer.parseInt(tfId.getText()),tfname.getText()));
             a.setContentText("Added Successfully");
             a.show();
             
        }catch(SQLException e){
             Alert a = new Alert(AlertType.ERROR);
             a.setContentText(e.getMessage());
             a.show();
        }
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
    
        public String controlSaisie(){
             String nom = tfname.getText();
             String error = "";
              Pattern pattern = Pattern.compile("[\\d]", Pattern.CASE_INSENSITIVE);
              Matcher matcher = pattern.matcher(nom);
              boolean matchFound = matcher.find();
              if(matchFound) {
                  error += "Name cant contain a number";
                    } 
              else {
                    error = "";
               }
              return error;
         }

 
    
}
