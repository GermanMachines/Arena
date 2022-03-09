/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.gui;

import com.jfoenix.controls.JFXTextField;
import edu.arena.entities.Comentaire;
import edu.arena.entities.Post;
import edu.arena.services.ComentaireCrud;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.Notifications;



/**
 * FXML Controller class
 *
 * @author Lenovo
 */

public class ViewcomentController implements Initializable {

    @FXML
    private JFXTextField tfcomment;
    @FXML
    private JFXTextField tfid_user;
         Post p = new Post();
    @FXML
    private TableView<Comentaire> tvbooks;
    @FXML
    private TableColumn<Comentaire, String> colnom;
    @FXML
    private TableColumn<Comentaire, String> colcom;

    public final ObservableList<Comentaire> data =FXCollections.observableArrayList();
public int getTxtIdUser(){
    return Integer.parseInt(tfid_user.getText());
            }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     ComentaireCrud p1 = new ComentaireCrud();
      

        try {
            showPost();
        } catch (SQLException ex) {
            Logger.getLogger(ViewcomentController.class.getName()).log(Level.SEVERE, null, ex);
        }

         
      
    }    

    @FXML
    private void addcom(ActionEvent event) throws SQLException {
        ComentaireCrud cer = new ComentaireCrud();
        int id_user=getTxtIdUser();
    
        String titre = tfcomment.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
    // p.getTest();
      if( (titre.contains("fuck") ) || (titre.contains("aziz") )  || (titre.equals("")) ){
                       //Alert saisie jeux :
             alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Conditions de saisie");
            alert.setHeaderText(null);
            alert.setContentText("You need to fill all the fields first!");
            alert.showAndWait();
            //Alert saisie jeux !
        }else{
               Comentaire c = new Comentaire(id_user,titre,"2022-12-12",p.getTest());

      
       
        try{
       cer.ajouter(c);
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setTitle("Add comment");
        alert.setHeaderText("Results:");
        alert.setContentText("cpment added successfully!");
        } catch (SQLException ex){
                     //Alert Error jeux :
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Adding Error !! ");
            alert.setContentText(ex.getMessage());
            //Alert Error jeux !
        } finally{
              alert.showAndWait();
        }Notifications notificationBuilder = Notifications.create().title("notification").text("comentaire ajouter avec succés").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.TOP_CENTER).onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event){
                       System.out.println("clicked on");
                   } 
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();    
          
          
        }
        
          
   
    }

    public void showPost() throws SQLException{
        ComentaireCrud p1 = new ComentaireCrud();
       
        data.addAll(p1.postCommentaires(p.getTest()));
         
          colnom.setCellValueFactory(new PropertyValueFactory<>("id_user"));
           colcom.setCellValueFactory(new PropertyValueFactory<>("desc_com"));
            
        
         tvbooks.setItems(data);
        
         
        
    }
    
    
}
