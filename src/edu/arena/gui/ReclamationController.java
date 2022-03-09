/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import edu.arena.Services.CategoryReclamationService;
import edu.arena.Services.ReclamationService;
import edu.arena.entities.CategoryReclamation;
import edu.arena.entities.Outils;
import edu.arena.entities.Produit;
import edu.arena.entities.Reclamation;
import edu.arena.entities.User;
import edu.arena.services.UserService;
import edu.arena.services.UserService;
import edu.arena.entities.Reclamation;
import edu.arena.utils.DataBase;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author DeathKnight
 */
public class ReclamationController implements Initializable {
    private Parent root;
    private Scene scene;
    private Stage stage;
    Connection con;
    Statement ste;

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfTitre;
    @FXML
    private JFXTextArea tfMessage;
    
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
    private JFXComboBox<String> cbEtat;
    @FXML
    private TableColumn<Reclamation, String> colNomCategory;
    @FXML
    private TableColumn<Reclamation, String> colNomUser;
    @FXML
    private TextField tfRecherche;
    public ObservableList<Reclamation> data = FXCollections.observableArrayList();
    @FXML
    private PieChart pcData;
    
    ObservableList< PieChart.Data> piechartdata;
    ArrayList< String> p = new ArrayList();
    ArrayList< Integer> c = new ArrayList();
    @FXML
    private JFXComboBox<String> cbCategory;
    @FXML
    private ChoiceBox<Integer> cbCategoryId;
    @FXML
    private Button btnExport;

    /**
     * Initializes the controller class.
     */
     int iduser = Outils.getCurrentSession();
    UserService us = new UserService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     //  try{
      
        User u = new User();
        u=us.findbyidcode(iduser);
        
        tfUser.setText(Integer.toString(iduser));
        
        tfId.setVisible(false);
        cbCategoryId.setVisible(false);
        tfUser.setVisible(false);
        
         ObservableList<String> etat = FXCollections.observableArrayList("True","False");
          cbEtat.setItems(etat);
         
           CategoryReclamationService crs = new CategoryReclamationService();
           
            try{
                ObservableList<CategoryReclamation> categList = crs.afficher();
               List<String> listCategory = categList.stream().map(p -> p.getNom()).collect(toList());
               List<Integer>listCategoryId = categList.stream().map(p -> p.getId()).collect(toList());
            
             ObservableList<String> obsListCategory = FXCollections.observableArrayList(listCategory);
             ObservableList<Integer> obsListCategoryId = FXCollections.observableArrayList(listCategoryId);
        //   prodList.stream().forEach(p -> {
              //  cbIdProduit.setItems(p.getId());
               cbCategory.setItems(obsListCategory);
               cbCategoryId.setItems(obsListCategoryId);
            }catch(Exception e){
                e.printStackTrace();
            }
            
         
           // showReclamation();
       //}catch(SQLException ex){
         //  ex.printStackTrace();
      // }
         
        
        try{
            ReclamationService rs = new ReclamationService();
             data =  rs.getAll();
        }catch(SQLException e){
            e.printStackTrace();
        }
       
      //  colId.setVisible(false);
      
      try{
       showReclamation();   
      }catch(Exception e){
          e.printStackTrace();
      }
   /*     colId.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("id"));
        colTitre.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("titre"));
        colMessage.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("message"));
        colCategory.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("categoryReclamationId"));
        colUser.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("userId"));
        colEtat.setCellValueFactory(new PropertyValueFactory<Reclamation,Boolean>("etat"));
        colDate.setCellValueFactory(new PropertyValueFactory<Reclamation,Date>("date"));
       // tfId.setVisible(false);
        colNomUser.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("nomUser"));
        colNomCategory.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("nomCategory"));
        tvReclamation.setItems(data);
        */
        // hide ids
        colCategory.setVisible(false);
        colUser.setVisible(false);
        colId.setVisible(false);
        
        //piechartdata.add()
        try{
           /* HashMap<String,Integer> hm = rs.stat();
            int nbTrue = hm.get("nbTrue");
            int nbFalse = hm.get("nbFalse");
               piechartdata.add(new PieChart.Data("nbTrue",nbTrue));
               piechartdata.add(new PieChart.Data("nbFalse",nbFalse));
               p.add(hm.get("nbTrue"));
               c.add(hm.get("nbFalse"));*/
           
           loadData();
               pcData.setData(piechartdata);
            
        }catch(SQLException e){
            e.printStackTrace();
        }
      //pcData.setData(piechartdata);
        
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
       /* colCategory.setVisible(false);
        colUser.setVisible(false);
        colId.setVisible(false); */
    }    
   
   
   public void showReclamation2(ObservableList<Reclamation>  list) throws SQLException{
       
       //   list.stream().forEach(r -> System.out.println(r.toString()));
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
       /* colCategory.setVisible(false);
        colUser.setVisible(false);
        colId.setVisible(false); */
    }  

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
          ReclamationService crs = new ReclamationService();
       int nb = cbCategory.getSelectionModel().getSelectedIndex();       
        cbCategoryId.getSelectionModel().select(nb);
         if(event.getSource() == btnInsert){
             //control saisie
       String error = controlSaisie();
           if(error.equals("")){
              crs.ajouter(new Reclamation(tfTitre.getText(),tfMessage.getText(),
                 Integer.parseInt(tfUser.getText()),cbCategoryId.getValue()
         ));
             showReclamation();
            Notifications n = Notifications.create()
                                .title("success")
                                .text("successfully added reclamation.")
                                .graphic(null)
                                .position(Pos.TOP_CENTER)
                                .hideAfter(Duration.seconds(3));
                                n.showInformation();
         }else{
              Alert a = new Alert(Alert.AlertType.ERROR);
             a.setContentText(error);
             a.show();
         }              
                               
        
        } 
         if(event.getSource() == btnUpdate){
            String error = controlSaisie();
           if(error.equals("")){
             
          Reclamation rec = new Reclamation();
          rec.setTitre(tfTitre.getText());
          rec.setMessage(tfMessage.getText());
          rec.setId(Integer.parseInt(tfId.getText()));
            
          rec.setUserId(Integer.parseInt(tfUser.getText()));
          
          //fix later
          rec.setCategoryReclamationId(cbCategoryId.getValue());
          
    
            
           java.sql.Date date = java.sql.Date.valueOf(tfDate.getValue());
           
       
               rec.setDatee(date);
          //fix thislater
          if(cbEtat.getValue() == "True"){
              rec.setEtat(true);
          }
          else{
              rec.setEtat(false);
          }
          crs.update(rec);
          showReclamation();
             
             
            Notifications n = Notifications.create()
                                .title("success")
                                .text("successfully updated reclamation")
                                .graphic(null)
                                .position(Pos.TOP_CENTER)
                                .hideAfter(Duration.seconds(3));
                                n.showInformation();
         }else{
             Alert a = new Alert(Alert.AlertType.ERROR);
             a.setContentText(error);
             a.show();
         }             
           
         
         }
              
        
           if(event.getSource() == btnDelete){         
        try{
         crs.delete(Integer.parseInt(tfId.getText()));
          Alert a = new Alert(Alert.AlertType.INFORMATION);
            Notifications n = Notifications.create()
                                .title("success")
                                .text("successfully deleted reclamation")
                                .graphic(null)
                                .position(Pos.TOP_CENTER)
                                .hideAfter(Duration.seconds(3));
                                n.showInformation();
             showReclamation();
         }catch(Exception e){
              Alert a = new Alert(Alert.AlertType.ERROR);
             a.setContentText(e.getMessage());
             a.show();
          }
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
        cbCategoryId.setValue(rec.getCategoryReclamationId());
        
        cbCategory.setValue(rec.getCategoryReclamation().getNom());
        tfUser.setText(Integer.toString(rec.getUserId()));
    }

    private void loadData() throws SQLException {
      
        piechartdata = FXCollections.observableArrayList();

        con = DataBase.getInstance().getConnection();
        ReclamationService rs = new ReclamationService();
         HashMap<String,Integer> hm = rs.stat();
        String[] names = {"nbTotal", "nbFalse", "nbTrue"};
        int[] values = {hm.get("nbTotal"), hm.get("nbFalse"),hm.get("nbTrue")};
        try {
                
        // ResultSet rs = con.createStatement().executeQuery(query);
            for(int i = 0; i<3 ; i++){
               piechartdata.add(new PieChart.Data(names[i],values[i]));
                p.add(names[i]);
                c.add(values[i]);
            
            }
            
              
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
   }
       public String controlSaisie(){
             String titre = tfTitre.getText();
             String message = tfMessage.getText();
            
             int cbCateg = cbCategory.getSelectionModel().getSelectedIndex();
           
             String error = "";
             if((titre.equals("") || message.equals("") || cbCateg < 0 )){
                 return "You have an empty field !";
             }
             
          
             
              return error;
         }


    @FXML
    private void filter(KeyEvent event) throws SQLException {
         ReclamationService rs = new ReclamationService();
         data.clear();
         data.addAll(rs.getAll().stream().filter((e)
                -> e.getTitre().toLowerCase().contains(tfRecherche.getText().toLowerCase())
                || e.getMessage().toLowerCase().contains(tfRecherche.getText().toLowerCase())
                || e.getNomCategory().toLowerCase().contains(tfRecherche.getText().toLowerCase())
  
        ).collect(Collectors.toList()));
        showReclamation2(data);
    }

    @FXML
    private void export(ActionEvent event) {
       ReclamationService rs = new ReclamationService();
       try{
           rs.export();
           Alert a = new Alert(Alert.AlertType.INFORMATION);
           a.setContentText("File exported in utils folder");
           a.show();
       }catch(Exception e){
           Alert a = new Alert(Alert.AlertType.ERROR);
           a.setContentText("Error ! could not export the file");
           a.show();
           e.printStackTrace();
       }
    }
}
