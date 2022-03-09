/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import edu.arena.entities.Comentaire;
import edu.arena.entities.Post;
import edu.arena.services.ComentaireCrud;
import edu.arena.services.PostCrud;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import static jdk.nashorn.internal.objects.NativeString.search;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ComBackController implements Initializable {

    @FXML
    private TableView<Post> tabPost;
    @FXML
    private TableColumn<Post,Integer> ColId;
    @FXML
    private TableColumn<Post,String> ColTitre;
    @FXML
    private TableColumn<Post,String> ColAuteur;
    @FXML
    private TableColumn<Post,String> ColImage;
    @FXML
    private TableColumn<Post,String> ColDate;
    @FXML
    private TableColumn<Comentaire,String> colIdcom;
    @FXML
    private TableColumn<Comentaire,String> colNomuser;
    @FXML
    private TableColumn<Comentaire,String> colcom;
    @FXML
    private TableColumn<Comentaire,String> coldatecom;
    PostCrud p =new PostCrud();
    ComentaireCrud c = new ComentaireCrud();
    public ObservableList<Post> data =FXCollections.observableArrayList();
    public ObservableList<Comentaire> comdata =FXCollections.observableArrayList();
    @FXML
    private TableView<Comentaire> tvtabCom;
    private TextField tfNomCO;
    @FXML
    private TextField tfCOM;
    @FXML
    private TextField tfDatecom;
    @FXML
    private TextField searchCom;
   
  
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {           afficherEvent();
       } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
        
    }    
    private void afficherEvent() throws SQLException {
       PostCrud c = new PostCrud();
       
        data.addAll(p.showpost());
  ColId.setCellValueFactory(new PropertyValueFactory<>("id_post"));
       ColTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
      ColAuteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
       ColImage.setCellValueFactory(new PropertyValueFactory<>("img_post"));
         ColDate.setCellValueFactory(new PropertyValueFactory<>("date_post"));
      tabPost.setItems(data);
   }  

  

    @FXML
    private void selectPostcom(ActionEvent event) {
       
       Post T = tabPost.getSelectionModel().getSelectedItem();
               int id_post= T.getId_post();
               
               
                 comdata.clear();

               List<Comentaire> coment = c.getCombyPost(id_post);
               
                     comdata.addAll(coment);
                     System.out.println(comdata);
                colIdcom.setCellValueFactory(new PropertyValueFactory<>("id_com"));
              colNomuser.setCellValueFactory(new PropertyValueFactory<>("nomuser"));
              colcom.setCellValueFactory(new PropertyValueFactory<>("desc_com"));
              coldatecom.setCellValueFactory(new PropertyValueFactory<>("date_com"));

        

        tvtabCom.setItems(comdata); 
        
        
        
        
    }

    @FXML
    private void deletecom(ActionEvent event) throws SQLException {
         {
        Comentaire c= tvtabCom.getSelectionModel().getSelectedItem();
        ComentaireCrud aS = new ComentaireCrud();
        if (aS.delete(c)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("La suppression de comentaire a été effectué avec succées");
        alert.showAndWait();
//        tvtabCom.refresh();
        
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La supression de comentaire  n'a pas été effectué!");
        alert.showAndWait();   
           tvtabCom.setItems(comdata);
        }
         comdata.clear();
        comdata.addAll(aS.getCombyPost(c.getId_post()));
    }
    }

   
    @FXML
    private void updatecom(ActionEvent event) {
       {
        Comentaire A= tvtabCom.getSelectionModel().getSelectedItem();
           

       
        String titre = tfCOM.getText();
        String image = tfDatecom.getText();
 
         A.setDesc_com(titre);
         A.setDate_com(image);
        
        
        ComentaireCrud aS = new ComentaireCrud();
        if (aS.update(A)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succées");
        alert.setHeaderText(null);
        alert.setContentText("La modification de comentaire a été effectué avec succées");
        alert.showAndWait();
      tvtabCom.refresh();
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La modufication  de comentaire  n'a pas été effectué!");
      alert.showAndWait();   
   tvtabCom.setItems(comdata);
       }
   }
     
    }
    

     @FXML
    private void selectCom(MouseEvent event) {
              
    Comentaire A= tvtabCom.getSelectionModel().getSelectedItem();
 
    tfCOM.setText(A.getDesc_com());
   
    tfDatecom.setText(A.getDate_com());
    }

    @FXML
    private void SearchC(KeyEvent event) throws SQLException {
             comdata.clear();
      
        comdata.addAll(c.readAll().stream().filter((e)-> e.getDesc_com().toLowerCase().contains(searchCom.getText().toLowerCase())
        ).collect(Collectors.toList()));
 
    }
    

}
