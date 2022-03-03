/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.Services.ReclamationService;
import edu.arena.entities.Reclamation;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DeathKnight
 */
public class ReclamationController implements Initializable {
    private Parent root;
    private Scene scene;
    private Stage stage;

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfMessage;
    @FXML
    private TextField tfCategory;
    @FXML
    private DatePicker tfDate;
    @FXML
    private TableColumn<Reclamation, Integer> colId;
    @FXML
    private TableColumn<Reclamation, String> colTitre;
    @FXML
    private TableColumn<Reclamation, String> colMessage;
    @FXML
    private TableColumn<Reclamation, String> colCategory;
    @FXML
    private TableColumn<Reclamation, Integer> colUser;
    @FXML
    private TableColumn<Reclamation, Date> colDate;
    @FXML
    private TableColumn<Reclamation, Boolean> colEtat;
    @FXML
    private TableView<Reclamation> tvReclamation;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField tfUser;
    @FXML
    private ChoiceBox<String> cbEtat;
    @FXML
    private TableColumn<Reclamation, String> colNomCategory;
    @FXML
    private TableColumn<Reclamation, String> colNomUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       try{
         ObservableList<String> etat = FXCollections.observableArrayList("Chose","True","False");
          cbEtat.setItems(etat);
          cbEtat.setValue("Chose");
            showReclamation();
       }catch(SQLException ex){
           ex.printStackTrace();
       }
    }
   public void showReclamation() throws SQLException{
         ReclamationService rs = new ReclamationService();
        ObservableList<Reclamation> list =  rs.getAll();
        list.stream().forEach(r -> System.out.println(r.toString()));
      //  colId.setVisible(false);
        colId.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("id"));
        colTitre.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("titre"));
        colMessage.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("message"));
        colCategory.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("categoryReclamationId"));
        colUser.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("userId"));
        colEtat.setCellValueFactory(new PropertyValueFactory<Reclamation,Boolean>("etat"));
        colDate.setCellValueFactory(new PropertyValueFactory<Reclamation,Date>("date"));
       // tfId.setVisible(false);
       colNomUser.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("nomUser"));
       colNomCategory.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("nomCategory"));
        tvReclamation.setItems(list);
        
        // hide ids
        colCategory.setVisible(false);
        colUser.setVisible(false);
        colId.setVisible(false);
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
          ReclamationService crs = new ReclamationService();
         if(event.getSource() == btnInsert){
             //control saisie
         crs.ajouter(new Reclamation(tfTitre.getText(),tfMessage.getText(),
                 Integer.parseInt(tfUser.getText()),Integer.parseInt(tfCategory.getText())
         ));
         showReclamation();
        } 
         if(event.getSource() == btnUpdate){
             //control saisie
          Reclamation rec = new Reclamation();
          rec.setTitre(tfTitre.getText());
          rec.setMessage(tfMessage.getText());
          rec.setId(Integer.parseInt(tfId.getText()));
          
          //fix later
          rec.setUserId(Integer.parseInt(tfUser.getText()));
          
          //fix later
          rec.setCategoryReclamationId(Integer.parseInt(tfCategory.getText()));
          //fix thislater
          if(cbEtat.getValue() == "True"){
              rec.setEtat(true);
          }
          else{
              rec.setEtat(false);
          }
          
          crs.update(rec);
          showReclamation();
         }
           if(event.getSource() == btnDelete){
             //control saisie
               System.out.println("deleted " + tfId.getText());
         crs.delete(Integer.parseInt(tfId.getText()));
         showReclamation();
        } 
    
    
}
        @FXML
    private void handleMouseAction(MouseEvent event) {
        Reclamation rec = tvReclamation.getSelectionModel().getSelectedItem();
        tfId.setText(Integer.toString(rec.getId()));
        tfTitre.setText(rec.getTitre());
        tfMessage.setText(rec.getMessage());
        if(rec.getEtat()){
            cbEtat.setValue("True");
        }
        else{
            cbEtat.setValue("False");
        }
        Date date = rec.getDate();
        tfDate.setValue(new java.sql.Date(date.getTime()).toLocalDate());
        tfCategory.setText(Integer.toString(rec.getCategoryReclamationId()));
        tfUser.setText(Integer.toString(rec.getUserId()));
    }
}
