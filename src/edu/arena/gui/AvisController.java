/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import edu.arena.Services.AvisService;
import edu.arena.Services.ProduitService;
import edu.arena.entities.Avis;
import edu.arena.entities.Produit;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author DeathKnight
 */
public class AvisController implements Initializable {

    @FXML
    private ChoiceBox<Integer> cbIdProduit;
    @FXML
    private JFXComboBox<String> cbNomProduit;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private JFXTextArea tfCommentaire;
    @FXML
    private TextField idUser;
    @FXML
    private TableView<Avis> tvAvis;
    @FXML
    private TableColumn<Avis, Integer> tcId;
    @FXML
    private TableColumn<Avis, Integer> tcIdProduit;
    @FXML
    private TableColumn<Avis, Integer> tcScore;
    @FXML
    private TableColumn<Avis, Integer> tcIdUtulisateur;
    @FXML
    private TableColumn<Avis, String> tcCommentaire;
    @FXML
    private TextField tfAvisId;
    @FXML
    private TableColumn<Avis, String> tcNomProduit;
    @FXML
    private TableColumn<Avis, String> tcNomUser;
    @FXML
    private Label lIdProduit;
    @FXML
    private Label lIdUser;
    @FXML
    private Label lIdAvis;
    @FXML
    private Label adminId;
    @FXML
    private Rating rating;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ProduitService ps = new ProduitService();
        try{
       
            
      File myObj = new File("C:/Users/SBS/Arena/src/edu/arena/utils/data.txt");
      
          Scanner myReader = new Scanner(myObj);
      
      String id = myReader.nextLine();
      String nom =myReader.nextLine();
      
        adminId.setText(id);
     
        
        //adminId.setVisible(false);
       
     
      myReader.close();

            ObservableList<Produit> prodList = ps.afficher();
            List<String> listNom = prodList.stream().map(p -> p.getNom()).collect(toList());
            List<Integer>  listId = prodList.stream().map(p -> p.getId()).collect(toList());
            
          ObservableList<String> obsListNom = FXCollections.observableArrayList(listNom);
          ObservableList<Integer> obsListId = FXCollections.observableArrayList(listId);
        //   prodList.stream().forEach(p -> {
              //  cbIdProduit.setItems(p.getId());
               cbNomProduit.setItems(obsListNom);
               cbIdProduit.setItems(obsListId);
      //   ObservableList<String> etat = FXCollections.observableArrayList("Chose","True","False");
       //   cbIdProduit.setItems((ObservableList<Produit>) p);
        //  cbIdProduit.setValue("Chose");
         //   }); 
         adminId.setVisible(false);
         tfAvisId.setVisible(false);
         idUser.setVisible(false);
         cbIdProduit.setVisible(false);
         lIdAvis.setVisible(false);
         lIdUser.setVisible(false);
         lIdProduit.setVisible(false); 
         showAvis();
        }catch(SQLException ex){
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        AvisService as = new AvisService();
        int idUtulisateur = 0;
        if(event.getSource() == btnInsert){
           int index = cbNomProduit.getSelectionModel().getSelectedIndex(); 
           cbIdProduit.getSelectionModel().select(index);
             String error = controlSaisie();
           if(error == ""){
             Alert a = new Alert(Alert.AlertType.INFORMATION);
            int idProd = cbIdProduit.getValue();
            String nomProd = cbNomProduit.getValue();
            String commentaire = tfCommentaire.getText();
            int score = (int)rating.getRating();
            if(idUser.getText() == ""){
                idUser.setText(adminId.getText());
            }
             idUtulisateur = Integer.parseInt(adminId.getText());
             
            as.ajouter(new Avis(score,commentaire,idUtulisateur,idProd));
            showAvis();  
           // a.setContentText("Added Successfully");
           // a.show();
            Notifications n = Notifications.create()
                                .title("success")
                                .text("successfully added")
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
           if(error == ""){
             Alert a = new Alert(Alert.AlertType.INFORMATION);
             
             
            int idAvis = Integer.parseInt(tfAvisId.getText());
            int idProd = cbIdProduit.getValue();
            String nomProd = cbNomProduit.getValue();
            String commentaire = tfCommentaire.getText();
            int score = (int)rating.getRating();
             idUtulisateur = Integer.parseInt(idUser.getText());
             
            if(idUtulisateur == 0){
                idUtulisateur = 1;
            }
            
            as.update(new Avis(idAvis,score,commentaire,idUtulisateur,idProd));
            showAvis();  
            
            
            Notifications n = Notifications.create()
                                .title("success")
                                .text("successfully updated")
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
            String error = controlSaisie();
              if(error == ""){
             Alert a = new Alert(Alert.AlertType.INFORMATION);
             
            as.delete(Integer.parseInt(tfAvisId.getText()));
            showAvis();
            
            
            Notifications n = Notifications.create()
                                .title("success")
                                .text("successfully deleted")
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
       
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Avis avis = tvAvis.getSelectionModel().getSelectedItem();
        
            tfAvisId.setText(Integer.toString(avis.getId()));
          //  cbIdProduit.getValue(avis.getIdProduit());
           // cbNomProduit.getValue();
             cbIdProduit.setValue(avis.getIdProduit());
            tfCommentaire.setText(avis.getCommentaire());
            rating.setRating(avis.getScore());
            idUser.setText(Integer.toString(avis.getIdUtulisateur()));
            int nb = cbIdProduit.getSelectionModel().getSelectedIndex();
            
            System.out.println(nb);
           cbNomProduit.getSelectionModel().select(nb);
   }
       public void showAvis() throws SQLException{
         AvisService avis = new AvisService();
        ObservableList<Avis> list =  avis.getAll();
        
        
        //ProduitService p = new ProduitService();
 
      //  colId.setVisible(false);
        tcIdUtulisateur.setCellValueFactory(new PropertyValueFactory<Avis,Integer>("idUtulisateur"));
        tcCommentaire.setCellValueFactory(new PropertyValueFactory<Avis,String>("commentaire"));
        tcScore.setCellValueFactory(new PropertyValueFactory<Avis,Integer>("score"));
        tcId.setCellValueFactory(new PropertyValueFactory<Avis,Integer>("id"));
        tcIdProduit.setCellValueFactory(new PropertyValueFactory<Avis,Integer>("idProduit"));
        tcNomUser.setCellValueFactory(new PropertyValueFactory<Avis,String>("nomUtulisateur"));
        tcNomProduit.setCellValueFactory(new PropertyValueFactory<Avis,String>("nomProduit"));
        
        //hide ids
        tcId.setVisible(false);
        tcIdUtulisateur.setVisible(false);
        tcIdProduit.setVisible(false);
        //tfId.setVisible(false);  
         //  int nb = cbIdProduit.getSelectionModel().getSelectedIndex();
         //  cbNomProduit.getSelectionModel().select(nb);
          // System.out.println(cbIdProduit.getValue());
           
       //    cbNomProduit.setValue(p.getNomProduit(cbIdProduit.getValue()));
         //  System.out.println(p.getNomProduit(cbIdProduit.getValue()));
        //String nom = getNomProduit(tcIdProduit);
        tvAvis.setItems(list);
    }    

    private void test(ScrollEvent event) {
                System.out.println(cbNomProduit.getSelectionModel().getSelectedIndex());
    }

    private void test1(MouseEvent event) {
        int nb = cbNomProduit.getSelectionModel().getSelectedIndex();
        cbIdProduit.getSelectionModel().select(nb);
    }
 public String controlSaisie(){
    
             String comment = tfCommentaire.getText();
            
             int score = (int)rating.getRating();
             int nom = cbNomProduit.getSelectionModel().getSelectedIndex();
            // System.out.println(cbCateg);
           
             String error = "";
             if((comment.equals("") || nom < 0  || score == 0 )){
                 return "You have an empty field !";
             }
             
          
             
              return error;
         }

    
}
