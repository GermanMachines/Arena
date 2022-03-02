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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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

    
}
