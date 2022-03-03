/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.Services.AvisService;
import edu.arena.Services.ProduitService;
import edu.arena.entities.Avis;
import edu.arena.entities.Produit;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import static java.util.stream.Collectors.toList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author DeathKnight
 */
public class AvisController implements Initializable {

    @FXML
    private ChoiceBox<Integer> cbIdProduit;
    @FXML
    private ChoiceBox<String> cbNomProduit;
    @FXML
    private ChoiceBox<Integer> cbScore;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField tfCommentaire;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ProduitService ps = new ProduitService();
            ObservableList<Integer> scores = FXCollections.observableArrayList(1,2,3,4,5);
            cbScore.setItems(scores);
        try{
       
            
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
         showAvis();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        AvisService as = new AvisService();
        int idUtulisateur = 0;
        if(event.getSource() == btnInsert){
            String etat = "";
            
           try{
            int idProd = cbIdProduit.getValue();
            String nomProd = cbNomProduit.getValue();
            String commentaire = tfCommentaire.getText();
            int score = cbScore.getValue();
             idUtulisateur = Integer.parseInt(idUser.getText());
            if(idUtulisateur == 0){
                idUtulisateur = 1;
            }
            as.ajouter(new Avis(score,commentaire,idUtulisateur,idProd));
            showAvis();
           }catch(SQLException e){
              
               etat += "Verifier les données que vous avez saisie !";
               
              
           }finally{
               if(etat == ""){
                   
                   etat += "Ajout Avec succés";
                   Alert alert = new Alert(AlertType.INFORMATION);
                   alert.setContentText(etat);
                   alert.setTitle("Success");
                    alert.showAndWait();
               }
               else{
                   Alert alert = new Alert(AlertType.ERROR);
                   alert.setContentText(etat);
                   alert.setTitle("Error");
                   alert.showAndWait();
             }
              

                
           }
        }
        
        if(event.getSource() == btnUpdate){
            
            int idAvis = Integer.parseInt(tfAvisId.getText());
            int idProd = cbIdProduit.getValue();
            String nomProd = cbNomProduit.getValue();
            String commentaire = tfCommentaire.getText();
            int score = cbScore.getValue();
             idUtulisateur = Integer.parseInt(idUser.getText());
             
            if(idUtulisateur == 0){
                idUtulisateur = 1;
            }
            
            as.update(new Avis(idAvis,score,commentaire,idUtulisateur,idProd));
            showAvis();
        }
        
        if(event.getSource() == btnDelete){
            as.delete(Integer.parseInt(tfAvisId.getText()));
            showAvis();
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
            cbScore.setValue(avis.getScore());
            idUser.setText(Integer.toString(avis.getIdUtulisateur()));
            int nb = cbIdProduit.getSelectionModel().getSelectedIndex();
           cbNomProduit.getSelectionModel().select(nb);
   }
       public void showAvis() throws SQLException{
         AvisService avis = new AvisService();
        ObservableList<Avis> list =  avis.getAll();
        
        
        //ProduitService p = new ProduitService();
        list.stream().forEach(r -> System.out.println(r.toString()));
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
       // tfId.setVisible(false);  
         //  int nb = cbIdProduit.getSelectionModel().getSelectedIndex();
         //  cbNomProduit.getSelectionModel().select(nb);
          // System.out.println(cbIdProduit.getValue());
           
       //    cbNomProduit.setValue(p.getNomProduit(cbIdProduit.getValue()));
         //  System.out.println(p.getNomProduit(cbIdProduit.getValue()));
        //String nom = getNomProduit(tcIdProduit);
        tvAvis.setItems(list);
    }    
    
}
