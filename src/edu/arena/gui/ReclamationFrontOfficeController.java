/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import edu.arena.Services.CategoryReclamationService;
import edu.arena.Services.ReclamationService;
import edu.arena.entities.CategoryReclamation;
import edu.arena.entities.Outils;
import edu.arena.entities.Reclamation;
import edu.arena.entities.User;
import edu.arena.services.UserService;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import static java.util.stream.Collectors.toList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author DeathKnight
 */
public class ReclamationFrontOfficeController implements Initializable {

    @FXML
    private TextArea taMessage;
    @FXML
    private JFXComboBox<String> cbCategory;
    @FXML
    private TextField tfTitle;
    @FXML
    private ChoiceBox<Integer> cbCategoryId;
    private User user;
    @FXML
    private TextField tfUserId;
    @FXML
    private TextField tfUsername;
    @FXML
    private TableColumn<Reclamation,String> clTitre;
    @FXML
    private TableColumn<Reclamation,String> clCategory;
    @FXML
    private TableColumn<Reclamation,String> clMessage;
    @FXML
    private TableView<Reclamation> tvReclamation;
    @FXML
    private TableColumn<Reclamation, Date> clDate;
    @FXML
    private TableColumn<Reclamation,Boolean> clEtat;
    @FXML
    private JFXButton sendBtn;
    


    /**
     * Initializes the controller class.
     */
    int iduser = Outils.getCurrentSession();
    UserService us = new UserService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO$
       
        tfUsername.setVisible(false);
        tfUserId.setVisible(false);
        cbCategoryId.setVisible(false);
          User u = us.findbyidcode(iduser);
          tfUserId.setText(Integer.toString(iduser));
          tfUsername.setText(u.getNom());
   /*    try {
      File myObj = new File("C:/Users/SBS/Arena/src/edu/arena/utils/data.txt");
      Scanner myReader = new Scanner(myObj);
      String id = myReader.nextLine();
      String nom =myReader.nextLine();
      
        tfUserId.setText(id);
        tfUsername.setText(nom);
        
        tfUserId.setDisable(true);
        tfUsername.setDisable(true);
     
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred when loading data.txt");
      e.printStackTrace();
    }*/
   
          ReclamationService r = new ReclamationService();
           CategoryReclamationService rs = new CategoryReclamationService();
        
        try{      
            ObservableList<CategoryReclamation> catRec = rs.afficher();
            List<String> listNom = catRec.stream().map(p -> p.getNom()).collect(toList());
            List<Integer>  listId = catRec.stream().map(p -> p.getId()).collect(toList());
            
          ObservableList<String> obsListNom = FXCollections.observableArrayList(listNom);
          ObservableList<Integer> obsListId = FXCollections.observableArrayList(listId);
        //   prodList.stream().forEach(p -> {
              //  cbIdProduit.setItems(p.getId());
               cbCategory.setItems(obsListNom);
            
               cbCategoryId.setItems(obsListId);
        
    }catch(Exception e){
        e.printStackTrace();
    } 
       
        try{
            showReclamation();
           }catch(Exception e){
        e.printStackTrace();
    } 
        
}
    @FXML
    private void sendReclamation(ActionEvent event) {
        int nb = cbCategory.getSelectionModel().getSelectedIndex();
              
        cbCategoryId.getSelectionModel().select(nb);
        
        
            String error = controlSaisie();
              if(error == ""){
             Alert a = new Alert(Alert.AlertType.INFORMATION);
             
                ReclamationService rs = new ReclamationService();
                Reclamation r = new Reclamation();
                r.setTitre(tfTitle.getText());
                r.setMessage(taMessage.getText());
                r.setUserId(Integer.parseInt(tfUserId.getText()));
                r.setCategoryReclamationId(cbCategoryId.getValue());
                try{
                    rs.ajouter(r);
                    showReclamation();
                }catch(SQLException e){
                     e.printStackTrace();
                }
            
            
             a.setContentText("Sent Successfully");
             a.show();
             Notifications n = Notifications.create()
                                .title("Succ??s")
                                .text("ajout avec succ??s")
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
     public void showReclamation() throws SQLException{
         ReclamationService rs = new ReclamationService();
       // ObservableList<Reclamation> list =  rs.getAll();
        ObservableList<Reclamation> list = rs.getOne(Integer.parseInt(tfUserId.getText()));
        list.stream().forEach(r -> System.out.println(r.toString()));
      //  colId.setVisible(false);
        clTitre.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("titre"));
        clCategory.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("nomCategory"));
        clMessage.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("message"));
        clDate.setCellValueFactory(new PropertyValueFactory<Reclamation,Date>("date"));
        clEtat.setCellValueFactory(new PropertyValueFactory<Reclamation, Boolean>("etat"));
       // tfId.setVisible(false);
    //   colNomUser.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("nomUser"));
    //   colNomCategory.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("nomCategory"));
        tvReclamation.setItems(list);
        
        // hide ids
       /* colCategory.setVisible(false);
        colUser.setVisible(false);
        colId.setVisible(false); */
    }
     
          public String controlSaisie(){
           int cat = cbCategory.getSelectionModel().getSelectedIndex();
             String titre = tfTitle.getText();
            String message = taMessage.getText();
             
             String error = "";
             if((titre.equals("") || message.equals("") || cat <0 )){
                 error += "You have an empty field ! ";
             }
         
             
          
             
              return error;
         }


    
}
